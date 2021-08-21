package br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity;

import br.com.mars.exploringmars.domain.planet.model.Plateau;

import javax.persistence.*;

@Entity(name = "plateau")
public class PlateauEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plateau_id_seq")
    @SequenceGenerator(name = "plateau_id_seq", sequenceName = "plateau_id_seq", allocationSize = 1)
    private Long id;

    private Long planetId;

    private String name;

    private Integer xSize;

    private Integer ySize;

    public PlateauEntity(Long id, Long planetId, String name, Integer xSize, Integer ySize) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public PlateauEntity() {
    }

    public static PlateauEntity fromDomain(Long planetId, Plateau plateau) {
        return new PlateauEntity(
                plateau.getId(),
                planetId,
                plateau.getName(),
                plateau.getXSize(),
                plateau.getYSize()
        );
    }

    public Plateau toDomain() {
        return new Plateau(
                this.id,
                this.planetId,
                this.name,
                this.xSize,
                this.ySize
        );
    }

    public Long getId() {
        return id;
    }

    public Long getPlanetId() {
        return planetId;
    }

    public String getName() {
        return name;
    }

    public Integer getxSize() {
        return xSize;
    }

    public Integer getySize() {
        return ySize;
    }
}
