package com.curso.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.mc.domain.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer>{

}
