package com.basis.implementacaoes;

import com.basis.model.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PessoaJuridica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pessoajuridicaPK;

	@OneToOne
	@JoinColumn(name = "pessoaPK")
	private Pessoa pessoa;
	@Column(name = "CNPJ", nullable = false)
	private String CNPJ;
	@Column(name = "RazaoSocial", nullable = false)
	private String RazaoSocial;

}