package com.example.challengeBankaya.service;

import com.example.demosoap.GetPokemonIdResponse;
import com.example.demosoap.GetPokemonResponse;
import com.example.demosoap.PokemonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class PokeApiService {

    private final RestTemplate restTemplate = new RestTemplate();




    public GetPokemonResponse getPokemonByName() {
       return getPokemonByName();
    }

    public GetPokemonResponse getPokemonByName(String name){

        //Convert to lower case because pokeapi ignores uppercase names
        String pokeName = name.toLowerCase();

        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/pokemon/" + pokeName).toUriString();
        ResponseEntity<PokemonEntity> pokemonResult = restTemplate.getForEntity(apiUrl, PokemonEntity.class);

        if (pokemonResult.getStatusCode().toString().equals("200 OK")) {
            PokemonEntity pokemonFound = pokemonResult.getBody();
            GetPokemonResponse res = new GetPokemonResponse();
            res.setPokemonEntity(pokemonFound);

            return res;
        }

        return new GetPokemonResponse();
    }

    public <T> Object getPokemonByName(String name, CharacteristicsEnum method){

        //Convert to lower case because pokeapi ignores uppercase names
        String pokeName = name.toLowerCase();

        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/pokemon/" + pokeName).toUriString();
        ResponseEntity<PokemonEntity> pokemonResult = restTemplate.getForEntity(apiUrl, PokemonEntity.class);

        if (pokemonResult.getStatusCode().toString().equals("200 OK")) {

            PokemonEntity pokemonFound = pokemonResult.getBody();
            switch (method) {

                case ID:
                    GetPokemonIdResponse idRes = new GetPokemonIdResponse();
                    idRes.setId(pokemonFound.getId());
                    return idRes;

                case ABILITIES:
                    break;

                default:
                    break;
            }






        }

        return new GetPokemonResponse();
    }
}