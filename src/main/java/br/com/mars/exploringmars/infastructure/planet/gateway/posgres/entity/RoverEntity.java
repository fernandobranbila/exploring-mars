package br.com.mars.exploringmars.infastructure.planet.gateway.posgres.entity;

import br.com.mars.exploringmars.domain.planet.model.FacingSide;
import br.com.mars.exploringmars.domain.planet.model.Rover;

import javax.persistence.*;

@Entity(name = "rover")
public class RoverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rover_id_seq")
    @SequenceGenerator(name = "rover_id_seq", sequenceName = "rover_id_seq", allocationSize = 1)
    private  Long id;

    private  Long plateauId;

    private String name;

    private  Integer xPosition;

    private  Integer yPosition;

    @Enumerated(EnumType.STRING)
    private  FacingSide facingSide;

    public RoverEntity(Long id, Long plateauId, String name, Integer xPosition, Integer yPosition, FacingSide facingSide) {
        this.id = id;
        this.plateauId = plateauId;
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.facingSide = facingSide;
    }

    public RoverEntity() {
    }

    public Rover toDomain(){
        return new Rover(
                this.id,
                this.plateauId,
                this.name,
                this.xPosition,
                this.yPosition,
                this.facingSide
        );
    }

    public static RoverEntity fromDomain(Long plateauId, Rover rover){
        return new RoverEntity(
                rover.getId(),
                plateauId,
                rover.getName(),
                rover.getXPosition(),
                rover.getYPosition(),
                rover.getFacingSide()
        );
    }

    public static RoverEntity fromDomainForUpdate(Rover rover){
        return new RoverEntity(
                rover.getId(),
                rover.getPlateauId(),
                rover.getName(),
                rover.getXPosition(),
                rover.getYPosition(),
                rover.getFacingSide()
        );
    }
}
