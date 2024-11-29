package com.example.security.service;

import com.example.security.model.Cashier;
import com.example.security.model.Sale;
import com.example.security.model.User;
import com.example.security.payload.request.CashierRequest;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashierService {

  void create(CashierRequest newUser);

  Page<Cashier> list(Pageable pageable);
}
