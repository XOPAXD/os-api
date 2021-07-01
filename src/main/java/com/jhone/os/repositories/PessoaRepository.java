package com.jhone.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhone.os.domain.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	@Query("SELECT obj FROM Pessoa obj Where obj.cpf =:cpf")
	Pessoa findbyCpf(@Param("cpf") String cpf);

}
