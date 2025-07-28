package me.dio.poke_project_api.service.impl;

import org.springframework.transaction.annotation.Transactional;
import me.dio.poke_project_api.model.Pokemon;
import me.dio.poke_project_api.repository.PokemonRepository;
import me.dio.poke_project_api.service.PokemonApiService;
import me.dio.poke_project_api.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PokemonServiceImpl  implements PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonApiService pokemonApiService;

    public PokemonServiceImpl(PokemonApiService pokemonApiService, PokemonRepository pokemonRepository) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pokemon> getAll() {
        return pokemonRepository.findAllFetchingTypes();
    }

    @Override
    @Transactional
    public Pokemon getById(Long id) {
        return pokemonRepository.findById(id)
                .orElseGet(() -> {
                    Pokemon novoPokemon = pokemonApiService.getPokemonById(id);
                    pokemonRepository.save(novoPokemon);
                    return novoPokemon;
                });
    }

    @Override
    @Transactional
    public Pokemon getByName(String name) {
        Optional<Pokemon> existingPokemon = Optional.ofNullable(pokemonRepository.findByName(name));
        if (existingPokemon.isPresent()) {
            return existingPokemon.get();
        } else {
            Pokemon novoPokemon = pokemonApiService.getPokemonByName(name);
            pokemonRepository.save(novoPokemon);
            return novoPokemon;
        }
    }

    @Override
    @Transactional
    public void insert(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    @Override
    @Transactional
    public void update(Long id, Pokemon pokemon) {
        Optional<Pokemon> pokemonBd = pokemonRepository.findById(id);
        if (pokemonBd.isPresent()) {
            Pokemon pokemonToUpdate = pokemonBd.get();
            pokemonToUpdate.setName(pokemon.getName());
            pokemonToUpdate.setHeight(pokemon.getHeight());
            pokemonToUpdate.setOrder(pokemon.getOrder());
            pokemonToUpdate.setSprites(pokemon.getSprites());
            pokemonToUpdate.setTypes(pokemon.getTypes());
            pokemonToUpdate.setBaseExperience(pokemon.getBaseExperience());
            pokemonToUpdate.setIsDefault(pokemon.getIsDefault());
            pokemonRepository.save(pokemonToUpdate);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!pokemonRepository.existsById(id)){
            throw new NoSuchElementException("Pokémon not found with id: " + id);
        }
        pokemonRepository.deleteById(id);
    }
}
