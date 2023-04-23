package com.github.piotrselak.gdninternship2023.nbp.domain;

import java.util.ArrayList;

// TODO: Maybe Rate should be abstract class or interface
public record ExchangeBidAskRate(String table, String currency, String code, ArrayList<BidAskRate> rates) {
}
