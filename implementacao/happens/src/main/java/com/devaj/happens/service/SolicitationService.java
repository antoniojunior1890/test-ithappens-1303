package com.devaj.happens.service;

import com.devaj.happens.model.Solicitation;
import com.devaj.happens.repository.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitationService {

    @Autowired
    private SolicitationRepository solicitationRepository;

    public Solicitation save(Solicitation solicitation) {
        return solicitationRepository.save(solicitation);
    }

    public List<Solicitation> listAll() {
        return solicitationRepository.findAll();
    }
}
