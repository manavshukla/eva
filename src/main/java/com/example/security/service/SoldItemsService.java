package com.example.security.service;

import java.util.List;

import com.example.security.model.SoldItem;

public interface SoldItemsService {

    public void save(List<SoldItem> soldItems);

    public void delete(SoldItem soldItems);
}