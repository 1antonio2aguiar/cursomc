package com.curso.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.mc.domain.Logradouro;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Integer>{

}
