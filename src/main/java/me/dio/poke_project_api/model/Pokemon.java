package me.dio.poke_project_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Pokemon {
    @Id
    private Long id;

    private String name;
    @JsonProperty("base_experience")
    private Integer baseExperience;
    private Integer height;
    @JsonProperty("is_default")
    private Boolean isDefault;
    @Column(name = "pokemon_order")
    private Integer order;
    private Integer weight;

    @ElementCollection
    @CollectionTable(name = "pokemon_types", joinColumns = @JoinColumn(name = "pokemon_id"))
    private List<PokemonType> types;

    @Embedded
    private Sprites sprites;

}



