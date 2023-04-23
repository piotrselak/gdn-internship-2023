package com.github.piotrselak.gdninternship2023.nbp.controller;

import com.github.piotrselak.gdninternship2023.nbp.service.NBPService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/exchanges/")
public class NBPController {
    private NBPService service;

    public NBPController(NBPService service) {
        this.service = service;
    }
}
