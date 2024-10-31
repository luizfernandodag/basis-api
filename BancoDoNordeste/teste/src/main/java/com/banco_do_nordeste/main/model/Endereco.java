package com.banco_do_nordeste.main.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "ENDERECO")
@Getter
@Setter

public  class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enderecoPK;

    @Column(name="TipoEndereço",nullable = false)
    TipoEndereco tipoEndereco;
     
    @Column(name="Endereço",nullable = false)
     String Endereco;
     
     @Column(name="Numero",nullable = true)
     String Numero;

    @Column(name="Complemento",nullable = true)
    String Complemento;
     
    @Column(name="Bairro",nullable = false)
     String Bairro;
     
     @Column(name = "CEP", nullable = false)
     String CEP;
    
     @Column(name="Cidade",nullable = false)
    String Cidade;
    
    @Column(name="UF",nullable = false)
    String UF;
   
    @ManyToOne
    @JoinColumn(name="pessoaPK", nullable=false)
    private Pessoa pessoa;

    
     public Endereco(@NotNull TipoEndereco tipoEndereco, @NotNull String endereco, String numero, String complemento,
            @NotNull String bairro, @NotNull String CEP, @NotNull String cidade, @NotNull String uF) {
        this.tipoEndereco = tipoEndereco;
        Endereco = endereco;
        Numero = numero;
        Complemento = complemento;
        Bairro = bairro;
        this.CEP = CEP;
        Cidade = cidade;
        UF = uF;
    }


    public Endereco() {
    }

}
