package com.github.piotrselak.gdninternship2023.nbp.domain;


import java.util.ArrayList;

public record ExchangeRate(String table, String currency, String code, ArrayList<Rate> rates) {
}
