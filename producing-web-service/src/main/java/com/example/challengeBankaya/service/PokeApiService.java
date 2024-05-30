package com.example.challengeBankaya.service;

import com.example.demosoap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZonedDateTime;


@Service
public class PokeApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final DbService dbService;

    public PokeApiService(DbService dbService) {
        this.dbService = dbService;
    }

    public GetPokemonResponse getPokemonByName() {
       return getPokemonByName();
    }

    public GetPokemonResponse getPokemonByName(String name){

        //Convert to lower case because pokeapi ignores uppercase names
        String pokeName = name.toLowerCase();

        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/pokemon/" + pokeName).toUriString();
        ResponseEntity<PokemonEntity> pokemonResult = restTemplate.getForEntity(apiUrl, PokemonEntity.class);

        if (pokemonResult.getStatusCode().toString().equals("200 OK")) {
            dbService.insertRequest("Test", "METODO GENERAL");
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

            String reqMethod = method.toString();
            dbService.insertRequest("Test", reqMethod);

            switch (method) {
                case ID:
                    GetPokemonIdResponse idRes = new GetPokemonIdResponse();
                    idRes.setId(pokemonFound.getId());
                    return idRes;
                case ABILITIES:
                    GetPokemonAbilitiesResponse abRes = new GetPokemonAbilitiesResponse();
                    for (AbilityType abilityType: pokemonFound.getAbilities()){
                        abRes.getAbilities().add(abilityType);
                    }
                    return abRes;
                case BASE_EXPERIENCE:
                    GetPokemonExpResponse expRes = new GetPokemonExpResponse();
                    expRes.setBaseExperience(pokemonFound.getBaseExperience());
                    return expRes;
                case HELD_ITEMS:
                    GetPokemonItemsResponse itemsRes = new GetPokemonItemsResponse();
                     for (ItemType itemType: pokemonFound.getHeldItems()){
                         itemsRes.getHeldItems().add(itemType);
                     }
                     return itemsRes;
                case LOCATION_AREA_ENCOUNTERS:
                    GetPokemonLocationResponse locationRes = new GetPokemonLocationResponse();
                    locationRes.setLocationAreaEncounters(pokemonFound.getLocationAreaEncounters());
                    return locationRes;
                default:
                    break;
            }
        }

        return new GetPokemonResponse();
    }
}