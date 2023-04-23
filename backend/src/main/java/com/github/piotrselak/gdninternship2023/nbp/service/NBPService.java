package com.github.piotrselak.gdninternship2023.nbp.service;

import com.github.piotrselak.gdninternship2023.nbp.controller.ValidationException;
import com.github.piotrselak.gdninternship2023.nbp.domain.BidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.BiggestDifference;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
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
        return repository.getAverageRate(code, date);
    }

    public MinMaxRates getMinMaxRates(String code, int nQuotas) {
        ArrayList<Rate> rates = repository.getAverageExchangeRateWithQuotations(code, nQuotas);

        Optional<Rate> min = rates.stream().min(Rate::compareTo);
        Optional<Rate> max = rates.stream().max(Rate::compareTo);
        if (min.isEmpty()) throw new EmptyRateArrayException("Couldn't get min and max value from empty collection.");

        return new MinMaxRates(min.get(), max.get());
    }

    // highest ask and lowest bid (bid is buy in api)
    public BiggestDifference getBiggestBidAndAskDifference(String code, int nQuotas) {
        ArrayList<BidAskRate> rates = repository.getBidAskRateWithQuotations(code, nQuotas);

        Optional<Double> lowestBid = rates.stream().map(BidAskRate::bid).min(Double::compareTo);
        Optional<Double> highestAsk = rates.stream().map(BidAskRate::ask).max(Double::compareTo);
        if (lowestBid.isEmpty())
            throw new EmptyRateArrayException("Couldn't get min and max value from empty collection.");
        return new BiggestDifference(lowestBid.get(), highestAsk.get(), Math.abs(highestAsk.get() - lowestBid.get()));
    }
}
