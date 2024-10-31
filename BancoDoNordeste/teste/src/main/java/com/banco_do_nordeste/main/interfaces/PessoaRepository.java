package com.banco_do_nordeste.main.interfaces;

//import com.banco_do_nordeste.main.Pessoa;
import com.banco_do_nordeste.main.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// @Entity
// @EnableJpaRepositories
//@EntityScan(basePackages = {"com.banco_do_nordeste.model"})
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
