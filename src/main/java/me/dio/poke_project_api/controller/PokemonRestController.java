package me.dio.poke_project_api.controller;

import me.dio.poke_project_api.model.Pokemon;
import me.dio.poke_project_api.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pokemon") // Define o caminho base para todos os endpoints neste controller
public class PokemonRestController {

    private final PokemonService pokemonService;

    // Injeção de dependência via construtor (prática recomendada)
    public PokemonRestController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Pokemon>> getAll() {
        return ResponseEntity.ok(pokemonService.getAll());
    }

    // O ID é um Long, o caminho é claro e sem ambiguidades.
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable Long id) {
        Pokemon pokemon = pokemonService.getById(id);
        return ResponseEntity.ok(pokemon);
    }

    // Caminho único para busca por nome, evitando conflito com getById.
    @GetMapping("/by-name/{name}")
    public ResponseEntity<Pokemon> getByName(@PathVariable String name) {
        Pokemon pokemon = pokemonService.getByName(name);
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping
    public ResponseEntity<Pokemon> insert(@RequestBody Pokemon pokemon) {
        // Salva o Pokémon através do serviço
        pokemonService.insert(pokemon);
        // Cria a URI para o novo recurso criado, uma boa prática REST (ex: /pokemon/25)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pokemon.getId())
                .toUri();
        // Retorna 201 Created com a localização do novo recurso
        return ResponseEntity.created(location).body(pokemon);
    }

    // O ID vem da URL (@PathVariable), e os dados para atualizar vêm do corpo (@RequestBody)
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
        // Retorna 200 OK com o corpo do objeto atualizado
        return ResponseEntity.ok(pokemon);
    }

    // O ID vem da URL. O método não precisa de corpo na requisição.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pokemonService.delete(id);
        // Retorna 204 No Content, indicando sucesso sem corpo de resposta.
        return ResponseEntity.noContent().build();
    }
}