package com.github.piotrselak.gdninternship2023.nbp.service;

import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
import com.github.piotrselak.gdninternship2023.nbp.validator.DateValidator;
import org.springframework.stereotype.Service;

@Service
public class NBPService {
    private NBPRepository repository;

    public NBPService(NBPRepository repository) {
        this.repository = repository;
    }

    public Rate getAverageExchangeRate(String code, String date) {
        if (!code.matches("\b[A-z]{3}\b")) throw new ValidationError("Currency code lacks proper formatting.");
        if (!DateValidator.isValid(date)) throw new ValidationError("Given date should have yyyy-mm-dd format.");

        // TODO: it may cause exception - do smth here
        return repository
                .getRate(code, date);
    }
}
