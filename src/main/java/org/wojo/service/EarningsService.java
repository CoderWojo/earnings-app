package org.wojo.service;

import org.wojo.entity.Earning;
import org.wojo.repository.EarningsRepository;

import java.util.List;

public class EarningsService {

    private EarningsRepository repository;

    public EarningsService(EarningsRepository repository) {
        this.repository = repository;
    }

    public List<Earning> getAllEarnings(){
        // Wypisz Listę Earnings którą otrzymasz od repository

        return repository.findAll();
    }

    public int addEarning(Earning earning) {
        return repository.addEarning(earning);
    }

}
