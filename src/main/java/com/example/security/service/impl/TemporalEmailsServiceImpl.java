package com.example.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.example.security.model.TemporalEmails;
import com.example.security.repository.TemporalEmailsRepository;
import com.example.security.service.TemporalEmailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// data access object
@Service
public class TemporalEmailsServiceImpl implements TemporalEmailsService {

    @Autowired
    private TemporalEmailsRepository repository;

    public int save(TemporalEmails temporalEmails){
        Random rand = new Random();
        int tempCode = rand.nextInt(1000, 10000);
        temporalEmails.setCode(tempCode);

        repository.save(temporalEmails);
        return tempCode;
    }

    public List<TemporalEmails> getAllTemporaryCodes(String email) {
        List<TemporalEmails> temporalEmailsList = new ArrayList<>();
        Streamable.of(repository.getTemporaryCodes(email)).forEach(temporalEmailsList::add);
        return temporalEmailsList;
        //return repository.getAllTemporaryEmail(email);
    }

    public void delete(TemporalEmails temporalEmails){
        repository.delete(temporalEmails);
    }
}
