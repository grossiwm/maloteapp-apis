package com.gabrielrossilopes.appmalote.service;

import com.gabrielrossilopes.appmalote.model.dominio.Malote;
import com.gabrielrossilopes.appmalote.repository.MaloteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaloteService {

    @Autowired
    private MaloteRepository maloteRepository;


    public Malote salvaMalote(Malote malote) {
        return maloteRepository.save(malote);
    }

    public Malote getById(Long id) {
        return maloteRepository.findById(id).get();
    }

}
