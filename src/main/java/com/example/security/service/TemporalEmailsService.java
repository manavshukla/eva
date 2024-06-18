package com.example.security.service;


import java.util.List;

import com.example.security.model.TemporalEmails;

// data access object
public interface TemporalEmailsService {


    public int save(TemporalEmails temporalEmails);

    public List<TemporalEmails> getAllTemporaryCodes(String email);

    public void delete(TemporalEmails temporalEmails);
}