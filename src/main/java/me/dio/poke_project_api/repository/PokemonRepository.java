package me.dio.poke_project_api.repository;

import me.dio.poke_project_api.model.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
    Pokemon findByName(String name);

    @Query("SELECT p FROM Pokemon p JOIN FETCH p.types")
    List<Pokemon> findAllFetchingTypes();
}
