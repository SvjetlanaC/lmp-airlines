package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.AirplaneDAO;
import com.airlines.lmpairlines.dto.AirplaneDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Airplane;
import com.airlines.lmpairlines.services.AirplaneService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private AirplaneDAO airplaneDAO;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Airplane insert(AirplaneDTO airplaneDTO) throws NotFoundException {
        Airplane airplane=new Airplane();
        airplane.setBrand(airplaneDTO.getBrand());
        airplane.setSeats(airplaneDTO.getSeats());
        airplane=airplaneDAO.saveAndFlush(airplane);
        entityManager.refresh(airplane);
        return findAirplaneById(airplane.getAirplaneId());
    }

    @Override
    public Airplane findAirplaneById(Integer id) throws NotFoundException {
        return airplaneDAO.findById(id).orElseThrow(NotFoundException::new);
    }
}
