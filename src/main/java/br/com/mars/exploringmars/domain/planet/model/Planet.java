package br.com.mars.exploringmars.domain.planet.model;

public class Planet {

    private final Long id;

    private final String name;

    public Planet(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Long getId() { return id; }

    public String getName() { return name; }
}
