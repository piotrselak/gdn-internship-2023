package com.github.piotrselak.gdninternship2023.nbp;

import org.springframework.stereotype.Service;

@Service
public class NBPService {
    private NBPRepository repository;

    public NBPService(NBPRepository repository) {
        this.repository = repository;
    }


}
