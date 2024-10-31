package com.banco_do_nordeste.main.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter @Setter
public class PessoaJuridica extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pessoajuridicaPK;

    @OneToOne
    @JoinColumn(name = "pessoaPK")
    private Pessoa pessoa;
    @Column(name="CNPJ", nullable = false)
    private String CNPJ;
    @Column(name="RazaoSocial", nullable = false)
    private String RazaoSocial;

}