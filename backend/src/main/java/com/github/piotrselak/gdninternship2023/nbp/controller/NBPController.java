package com.github.piotrselak.gdninternship2023.nbp.controller;

import com.github.piotrselak.gdninternship2023.nbp.domain.BiggestDifference;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.service.NBPService;
import com.github.piotrselak.gdninternship2023.nbp.validator.DateValidator;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/exchanges/{code}/")
public class NBPController {
    private final NBPService service;

    public NBPController(NBPService service) {
        this.service = service;
    }

    @GetMapping("{date}")
    public Rate getRateByCodeAndDate(@PathVariable("code") String code, @PathVariable("date") String date) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationException("Currency code lacks proper formatting.");
        if (!DateValidator.isValid(date)) throw new ValidationException("Given date should have yyyy-mm-dd format.");
        return service.getAverageExchangeRate(code, date);
    }

    @GetMapping("minmax/{n}")
    public MinMaxRates getRateMinMaxByCodeAndN(@PathVariable("code") String code, @PathVariable("n") int n) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationException("Currency code lacks proper formatting.");
        if (n < 1 || n > 255) throw new ValidationException("Quotation count should be in <1, 255> range.");
        return service.getMinMaxRates(code, n);
    }

    @GetMapping("difference/{n}")
    public BiggestDifference getRateDifferenceByCodeAndN(@PathVariable("code") String code, @PathVariable("n") int n) {
        if (!code.matches("^[A-z]{3}$")) throw new ValidationException("Currency code lacks proper formatting.");
        if (n < 1 || n > 255) throw new ValidationException("Quotation count should be in <1, 255> range.");
        return service.getBiggestBidAndAskDifference(code, n);
    }
}
