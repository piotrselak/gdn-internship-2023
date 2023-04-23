package com.github.piotrselak.gdninternship2023.nbp.domain;

public record Rate(String no, String effectiveDate, double mid) implements Comparable<Rate> {
    @Override
    public int compareTo(Rate rate) {
        return Double.compare(this.mid(), rate.mid());
    }
}
