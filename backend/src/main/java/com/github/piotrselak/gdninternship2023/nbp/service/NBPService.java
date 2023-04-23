package com.github.piotrselak.gdninternship2023.nbp.service;

import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
import com.github.piotrselak.gdninternship2023.nbp.repository.NullExchangeRateException;
import com.github.piotrselak.gdninternship2023.nbp.validator.DateValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NBPService {
    private final NBPRepository repository;

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

    public MinMaxRates getMinMaxRates(String code, int nQuotas) {
        if (!code.matches("\b[A-z]{3}\b")) throw new ValidationError("Currency code lacks proper formatting.");
        if (nQuotas < 1 || nQuotas > 255) throw new ValidationError("Quotation count should be in <1, 255> range.");

        ArrayList<Rate> rates = repository.getExchangeRateWithQuotations(code, nQuotas).rates();
        Optional<Rate> min = rates.stream().min(Rate::compareTo);
        Optional<Rate> max = rates.stream().max(Rate::compareTo);
        if (min.isEmpty()) throw new EmptyRateArrayException("Couldn't get min and max value from empty collection.");

        return new MinMaxRates(min.get(), max.get());
    }
}
