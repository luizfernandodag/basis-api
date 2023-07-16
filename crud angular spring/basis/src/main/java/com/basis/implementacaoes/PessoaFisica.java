package com.basis.implementacaoes;

import com.basis.model.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PessoaFisica")
@Inheritance(strategy = InheritanceType.JOINED)
public class PessoaFisica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "pessoaPK")
	private Pessoa pessoa;

	// @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoafisicaPK")
	private long pessoafisicaPK;

	@Column(name = "CPF", nullable = false)
	private String CPF;

	@Column(name = "Nome", nullable = false)
	private String Nome;

}
