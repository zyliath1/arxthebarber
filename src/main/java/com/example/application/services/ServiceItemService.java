package com.example.application.services;

import com.example.application.data.ServiceItem;
import com.example.application.data.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService {

    @Autowired
    private ServiceItemRepository repository;

    public List<ServiceItem> findAll() {
        return repository.findAll();
    }

    public ServiceItem save(ServiceItem item) {
        return repository.save(item);
    }

    public void delete(ServiceItem item) {
        repository.delete(item);
    }
}
