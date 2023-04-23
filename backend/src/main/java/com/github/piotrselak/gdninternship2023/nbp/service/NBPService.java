package com.github.piotrselak.gdninternship2023.nbp.service;

import com.github.piotrselak.gdninternship2023.nbp.domain.BidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.BiggestDifference;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
import com.github.piotrselak.gdninternship2023.nbp.validator.DateValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NBPService {
    private final NBPRepository repository;

    public NBPService(NBPRepository repository) {
        this.repository = repository;
    }

    public Rate getAverageExchangeRate(String code, String date) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationError("Currency code lacks proper formatting.");
        if (!DateValidator.isValid(date)) throw new ValidationError("Given date should have yyyy-mm-dd format.");
        try {
            return repository.getAverageRate(code, date);
        } catch (WebClientResponseException.NotFound e) {
            throw new RateNotFound();
        }
    }

    public MinMaxRates getMinMaxRates(String code, int nQuotas) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationError("Currency code lacks proper formatting.");
        if (nQuotas < 1 || nQuotas > 255) throw new ValidationError("Quotation count should be in <1, 255> range.");

        ArrayList<Rate> rates;
        try {
            rates = repository.getAverageExchangeRateWithQuotations(code, nQuotas);
        } catch (WebClientResponseException.NotFound e) {
            throw new RateNotFound();
        }
        Optional<Rate> min = rates.stream().min(Rate::compareTo);
        Optional<Rate> max = rates.stream().max(Rate::compareTo);
        if (min.isEmpty()) throw new EmptyRateArrayException("Couldn't get min and max value from empty collection.");

        return new MinMaxRates(min.get(), max.get());
    }

    // highest ask and lowest bid (bid is buy in api)
    public BiggestDifference getBiggestBidAndAskDifference(String code, int nQuotas) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationError("Currency code lacks proper formatting.");
        if (nQuotas < 1 || nQuotas > 255) throw new ValidationError("Quotation count should be in <1, 255> range.");

        ArrayList<BidAskRate> rates;
        try {
            rates = repository.getBidAskRateWithQuotations(code, nQuotas);
        } catch (WebClientResponseException.NotFound e) {
            throw new RateNotFound();
        }
        Optional<Double> lowestBid = rates.stream().map(BidAskRate::bid).min(Double::compareTo);
        Optional<Double> highestAsk = rates.stream().map(BidAskRate::ask).max(Double::compareTo);
        if (lowestBid.isEmpty())
            throw new EmptyRateArrayException("Couldn't get min and max value from empty collection.");
        return new BiggestDifference(lowestBid.get(), highestAsk.get(), Math.abs(highestAsk.get() - lowestBid.get()));
    }
}
