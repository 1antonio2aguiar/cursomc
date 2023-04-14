package com.curso.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.mc.domain.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep, Integer>{

}
