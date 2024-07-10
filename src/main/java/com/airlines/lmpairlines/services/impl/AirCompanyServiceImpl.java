package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.AirCompanyDAO;
import com.airlines.lmpairlines.dto.AirCompanyDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.AirCompany;
import com.airlines.lmpairlines.model.entities.Airplane;
import com.airlines.lmpairlines.services.AirCompanyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirCompanyServiceImpl implements AirCompanyService {
    @Autowired
    private AirCompanyDAO airCompanyDAO;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public AirCompany insert(AirCompanyDTO airCompanyDTO) throws NotFoundException {
        AirCompany airCompany=new AirCompany();
        airCompany.setName(airCompanyDTO.getName());
        airCompany=airCompanyDAO.saveAndFlush(airCompany);
        entityManager.refresh(airCompany);
        return findAirCompanyById(airCompany.getAirCompanyId());
    }

    @Override
    public AirCompany findAirCompanyById(Integer id) throws NotFoundException {
        return airCompanyDAO.findById(id).orElseThrow(NotFoundException::new);
    }
}
