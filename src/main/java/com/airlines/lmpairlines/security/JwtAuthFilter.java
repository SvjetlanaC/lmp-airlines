package com.airlines.lmpairlines.security;

import com.airlines.lmpairlines.model.CustomerDetails;
import com.airlines.lmpairlines.model.entities.Customer;
import com.airlines.lmpairlines.services.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    //private final UserDetailsService userDetailsService;
    @Autowired
    private  CustomerService customerService;
    @Autowired
    private  JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader(AUTHORIZATION);
        final String username;
        final String jwtToken;




        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        jwtToken=authHeader.substring(7);
        username= jwtUtils.extractUsername(jwtToken); //TODO to be implemented  userEmail = jwtService.extractUsername(jwt);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Customer customer=customerService.findCustomerByUsername(username);
            UserDetails userDetails = new CustomerDetails(customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getAddress(),customer.getCountry(),customer.getCity(),customer.getRole().getName());

            if (jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);


    }
}
