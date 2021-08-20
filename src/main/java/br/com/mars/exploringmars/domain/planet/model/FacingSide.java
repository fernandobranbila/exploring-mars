package br.com.mars.exploringmars.domain.planet.model;

import java.util.Arrays;

public enum FacingSide {
    N("N"),S("S"),W("W"),E("E");

    private String value;

    FacingSide(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
