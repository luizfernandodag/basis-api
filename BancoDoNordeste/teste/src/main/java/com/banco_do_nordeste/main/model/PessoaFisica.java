package com.banco_do_nordeste.main.model;

//import com.banco_do_nordeste.main.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PessoaFisica")
@Inheritance(strategy = InheritanceType.JOINED )
public class PessoaFisica extends Pessoa {

    @Id
    @OneToOne
    @JoinColumn(name="pessoaPK")
    private Pessoa pessoa;

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="pessoafisicaPK")
    private String pessoafisicaPK;

    @Column(name="CPF", nullable=false)
    private String CPF;

    @Column(name="Nome", nullable=false)
    private String Nome;

}
