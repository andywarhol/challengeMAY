package com.example.challengeBankaya.endpoint;

import com.example.challengeBankaya.repo.PokemonRepository;
import com.example.challengeBankaya.service.CharacteristicsEnum;
import com.example.challengeBankaya.service.PokeApiService;
import com.example.demosoap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(PokemonEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/demosoap";

    private PokemonRepository pokemonRepository;

    @Autowired
    PokeApiService service;

    @Autowired
    public PokemonEndpoint (PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemonResponse(@RequestPayload GetPokemonRequest req){
        GetPokemonResponse res = service.getPokemonByName(req.getName());
        logger.info("POKEMON REQUESTED: {}", res.getPokemonEntity().getName());

        return res;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonIdRequest")
    @ResponsePayload
    public GetPokemonIdResponse getPokemonId(@RequestPayload GetPokemonIdRequest req){
        return (GetPokemonIdResponse) service.getPokemonByName(req.getName(), CharacteristicsEnum.ID);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonAbilitiesRequest")
    @ResponsePayload
    public GetPokemonAbilitiesResponse getPokemonAbilities(@RequestPayload GetPokemonAbilitiesRequest req){
        return (GetPokemonAbilitiesResponse) service.getPokemonByName(req.getName(), CharacteristicsEnum.ABILITIES);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonExpRequest")
    @ResponsePayload
    public GetPokemonExpResponse getPokemonAbilities(@RequestPayload GetPokemonExpRequest req){
        return (GetPokemonExpResponse) service.getPokemonByName(req.getName(), CharacteristicsEnum.BASE_EXPERIENCE);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonItemsRequest")
    @ResponsePayload
    public GetPokemonItemsResponse getPokemonAbilities(@RequestPayload GetPokemonItemsRequest req){
        return (GetPokemonItemsResponse) service.getPokemonByName(req.getName(), CharacteristicsEnum.HELD_ITEMS);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonLocationRequest")
    @ResponsePayload
    public GetPokemonLocationResponse getPokemonAbilities(@RequestPayload GetPokemonLocationRequest req){
        return (GetPokemonLocationResponse) service.getPokemonByName(req.getName(), CharacteristicsEnum.LOCATION_AREA_ENCOUNTERS);
    }

}
