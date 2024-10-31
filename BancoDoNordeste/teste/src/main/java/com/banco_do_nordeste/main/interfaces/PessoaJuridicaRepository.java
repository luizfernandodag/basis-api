package com.banco_do_nordeste.main.interfaces;

import com.banco_do_nordeste.main.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@EnableJpaRepositories
//@Entity
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica,Long> {
}
