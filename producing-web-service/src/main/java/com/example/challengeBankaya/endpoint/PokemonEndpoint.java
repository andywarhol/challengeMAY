package com.example.challengeBankaya.endpoint;

import com.example.challengeBankaya.repo.PokemonRepository;
import com.example.challengeBankaya.service.PokeApiService;
import com.example.demosoap.GetPokemonRequest;
import com.example.demosoap.GetPokemonResponse;
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
        logger.info(res.getPokemonEntity().getName());
        //res.setPokemonEntity(pokemonRepository.findPokemon(req.getName()));

        return res;
    }
}
