package com.basis.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basis.model.Endereco;

@Repository
//@EnableJpaRepositories
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
