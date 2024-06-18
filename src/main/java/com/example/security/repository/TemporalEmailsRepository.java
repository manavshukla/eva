package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.security.model.TemporalEmails;

import java.util.List;

@Repository
public interface TemporalEmailsRepository extends JpaRepository<TemporalEmails, Integer> {
    @Query("SELECT t FROM TemporalEmails t WHERE t.email = :e" )
    public List<TemporalEmails> getTemporaryCodes(@Param("e") String email);
}