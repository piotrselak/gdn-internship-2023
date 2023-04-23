package com.github.piotrselak.gdninternship2023.nbp.controller;

import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.service.NBPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges/")
public class NBPController {
    private NBPService service;

    public NBPController(NBPService service) {
        this.service = service;
    }

    @GetMapping("{code}/{date}")
    public Rate getRateByCodeAndDate(@PathVariable("code") String code, @PathVariable("date") String date) {
        return service.getAverageExchangeRate(code, date);
    }
}
