package com.basis.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basis.model.Pessoa;

@Repository
//@Entity
//@EnableJpaRepositories
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	void deleteByEmail(String email);
	void deleteByTelefone(String telefone);
	
	void updatePessoa(Pessoa pessoa);
}
