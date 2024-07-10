package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.DestinationDAO;
import com.airlines.lmpairlines.dto.DestinationDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Airplane;
import com.airlines.lmpairlines.model.entities.Destination;
import com.airlines.lmpairlines.services.DestinationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationDAO destinationDAO;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Destination insert(DestinationDTO destinationDTO) throws NotFoundException {
        Destination destination=new Destination();
        destination.setName(destinationDTO.getName());

        destination=destinationDAO.saveAndFlush(destination);
        entityManager.refresh(destination);
        return findDestinationById(destination.getDestinationId());
    }

    @Override
    public Destination findDestinationById(Integer id) throws NotFoundException {
        return destinationDAO.findById(id).orElseThrow(NotFoundException::new);
    }
}
