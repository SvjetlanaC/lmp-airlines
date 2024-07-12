package com.airlines.lmpairlines.security;

import com.airlines.lmpairlines.model.CustomerDetails;
import com.airlines.lmpairlines.model.entities.Customer;
import com.airlines.lmpairlines.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(a->a.disable()).cors(Customizer.withDefaults()).authorizeHttpRequests(a -> a
                .requestMatchers(HttpMethod.POST,"/customers/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/customers/register").permitAll()
//                .requestMatchers("/customers/register").permitAll()
//                .requestMatchers(HttpMethod.POST,"/customers/register").permitAll()
//                .requestMatchers(HttpMethod.GET,"/productOffers/**").permitAll()
//                .requestMatchers("/customers/activate").permitAll()
//                .requestMatchers(HttpMethod.GET,"/productOffers").permitAll()
//                .requestMatchers("/categories").permitAll()
//                .requestMatchers("/attributes/**").permitAll()
//                .requestMatchers("/products/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/comments/**").permitAll()
//                .requestMatchers("/logs/**").permitAll()
//                .requestMatchers(HttpMethod.GET,"/productAttributes/**").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Customer customer=customerService.findCustomerByUsername(username);
                CustomerDetails customerDetails=new CustomerDetails(customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getAddress(),customer.getCountry(),customer.getCity(),customer.getRole().getName());
                if(customer!=null)
                    return customerDetails;
                else throw new UsernameNotFoundException("User not found!");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
