package com.example.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.SoldItem;
import com.example.security.repository.SoldItemsRepository;
import com.example.security.service.SoldItemsService;

import java.util.List;

@Service
public class SoldItemsServiceImpl implements SoldItemsService {

    @Autowired
    private SoldItemsRepository repository;

    public void save(List<SoldItem> soldItems) {
        for (SoldItem item : soldItems){
            repository.save(item);
        }
    }

    public void delete(SoldItem soldItems){
        repository.delete(soldItems);
    }
}
