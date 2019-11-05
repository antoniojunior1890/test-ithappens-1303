package com.devaj.happens.service;

import com.devaj.happens.exception.NotFoundException;
import com.devaj.happens.model.Solicitation;
import com.devaj.happens.repository.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Solicitation getById(long id) {
        Optional<Solicitation> result = solicitationRepository.findById(id);

        return result.orElseThrow(()-> new NotFoundException("Solicitação não encontrado com id "+id));
    }
}
