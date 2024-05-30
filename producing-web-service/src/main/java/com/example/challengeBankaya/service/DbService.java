package com.example.challengeBankaya.service;

import com.example.challengeBankaya.entity.RequestsEntity;
import com.example.challengeBankaya.repo.PokemonRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DbService {

    private PokemonRepository pokemonRepository;
    public DbService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public void insertRequest(String ip, String method) {
        RequestsEntity request = new RequestsEntity();
        request.setIp(ip);
        request.setFecha(new Date());
        request.setMetodo(method);

        try {
            pokemonRepository.save(request);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al guardar la solicitud en la base de datos", e);
        }

    }
}
