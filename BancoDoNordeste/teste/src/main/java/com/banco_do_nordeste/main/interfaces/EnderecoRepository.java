package com.banco_do_nordeste.main.interfaces;

//import com.banco_do_nordeste.main.Endereco;
import com.banco_do_nordeste.main.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Entity
@Repository
//@EnableJpaRepositories
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
