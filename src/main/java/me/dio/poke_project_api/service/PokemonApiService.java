package me.dio.poke_project_api.service;

import me.dio.poke_project_api.model.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemonapi", url = "https://pokeapi.co/api/v2/")
public interface PokemonApiService {

    @GetMapping("/pokemon/{id}/")
    Pokemon getPokemonById(@PathVariable("id") Long id);

    @GetMapping("/pokemon/{name}/")
    Pokemon getPokemonByName(@PathVariable("name") String name);
}
