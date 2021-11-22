package com.prova.restapi.database;

import com.prova.restapi.entidade.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAluno extends JpaRepository<Aluno, Long> {
    
}
