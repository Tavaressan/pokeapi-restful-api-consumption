package me.dio.poke_project_api.service;

import me.dio.poke_project_api.model.Pokemon;

public interface PokemonService {

    Iterable<Pokemon> getAll();

    Pokemon getById(Long id);

    Pokemon getByName(String name);

    void insert(Pokemon pokemon);

    void update(Long id, Pokemon pokemon);

    void delete(Long id);
}
