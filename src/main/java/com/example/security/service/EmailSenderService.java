package com.example.security.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}