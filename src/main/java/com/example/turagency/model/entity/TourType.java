package com.example.turagency.model.entity;

public enum TourType {
    REST("Rest"),
    EXCURSION("Excursion"),
    SHOPPING("Shopping");

    private final String value;

    TourType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}