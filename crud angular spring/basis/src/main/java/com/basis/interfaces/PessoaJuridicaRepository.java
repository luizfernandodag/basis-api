package com.basis.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basis.implementacaoes.PessoaJuridica;

@Repository
//@EnableJpaRepositories
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	void deleteByCNPJ();
}
