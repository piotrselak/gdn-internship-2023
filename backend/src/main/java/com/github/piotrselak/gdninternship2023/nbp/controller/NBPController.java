package com.github.piotrselak.gdninternship2023.nbp.controller;

import com.github.piotrselak.gdninternship2023.nbp.domain.BiggestDifference;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.service.NBPService;
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
        return service.getAverageExchangeRate(code, date);
    }

    @GetMapping("minmax/{n}")
    public MinMaxRates getRateMinMaxByCodeAndN(@PathVariable("code") String code, @PathVariable("n") int n) {
        return service.getMinMaxRates(code, n);
    }

    @GetMapping("difference/{n}")
    public BiggestDifference getRateDifferenceByCodeAndN(@PathVariable("code") String code, @PathVariable("n") int n) {
        return service.getBiggestBidAndAskDifference(code, n);
    }
}
