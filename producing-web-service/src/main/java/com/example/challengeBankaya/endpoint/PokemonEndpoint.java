package com.example.challengeBankaya.endpoint;

import com.example.challengeBankaya.repo.PokemonRepository;
import com.example.demosoap.GetPokemonRequest;
import com.example.demosoap.GetPokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/demosoap";

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonEndpoint (PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemonResponse(@RequestPayload GetPokemonRequest req){
        GetPokemonResponse res = new GetPokemonResponse();
        res.setPokemonEntity(pokemonRepository.findPokemon(req.getName()));

        return res;
    }
}
