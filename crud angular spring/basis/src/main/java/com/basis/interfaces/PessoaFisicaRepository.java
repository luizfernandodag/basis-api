package com.basis.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basis.implementacaoes.*;

@Repository
//@EnableJpaRepositories
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
	
	void deleteByNome(String nome);
	void deleteByCPF(String cdpf);
	
	void updatePessoaFisica(PessoaFisica pessoa);
}
