package com.banco_do_nordeste.main.interfaces;

import com.banco_do_nordeste.main.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Entity
@Repository
//@EnableJpaRepositories
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
}
