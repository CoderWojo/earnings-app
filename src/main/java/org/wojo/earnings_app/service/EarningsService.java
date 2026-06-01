package org.wojo.earnings_app.service;

import org.springframework.stereotype.Service;
import org.wojo.earnings_app.entity.Earning;
import org.wojo.earnings_app.exception.NotFoundException;
import org.wojo.earnings_app.repository.EarningsRepository;

import java.util.List;


@Service
public class EarningsService {

    private final EarningsRepository repository;

    public EarningsService(EarningsRepository repository) {
        this.repository = repository;
    }

    public Earning save(Earning earning) {
        return repository.save(earning);
    }

    public Earning updateEarning(Earning earning) {
        if(!repository.existsById(earning.getId())){
            throw new NotFoundException("You provided incorrect id.");
        }

        return repository.save(earning);
    }

    public void deleteById(int id) {
        if(!repository.existsById(id)) {
            throw new NotFoundException("You provided incorrect id.");
        }

        repository.deleteById(id);
    }

    public List<Earning> getAll() {
        return repository.findAll();
    }
}
