package com.example.challengeBankaya.repo;

import com.example.demosoap.PokemonEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class PokemonRepository {
    private static final Map<String, PokemonEntity> pokemones = new HashMap<>();

    @PostConstruct
    public void initData() {
        PokemonEntity p1 = new PokemonEntity();
        p1.setId(1);
        p1.setName("Ponyta");
        p1.setBaseExperience(3);

        PokemonEntity p2 = new PokemonEntity();
        p2.setId(2);
        p2.setName("Pikachu");
        p2.setBaseExperience(5);

        pokemones.put(p1.getName(), p1);
        pokemones.put(p2.getName(), p2);

    }

    public PokemonEntity findPokemon(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return pokemones.get(name);
    }

}
