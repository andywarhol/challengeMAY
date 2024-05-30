package com.example.challengeBankaya.repo;

import com.example.challengeBankaya.entity.RequestsEntity;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<RequestsEntity, Integer> {

}
