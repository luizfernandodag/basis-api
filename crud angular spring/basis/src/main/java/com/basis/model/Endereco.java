package com.basis.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ENDERECO")
@Getter
@Setter

public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enderecoPK;

	@Column(name = "TipoEndereço", nullable = false)
	TipoEndereco tipoEndereco;

	@Column(name = "Endereço", nullable = false)
	String Endereco;

	@Column(name = "Numero", nullable = true)
	String Numero;

	@Column(name = "Complemento", nullable = true)
	String Complemento;

	@Column(name = "Bairro", nullable = false)
	String Bairro;

	@Column(name = "CEP", nullable = false)
	String CEP;

	@Column(name = "Cidade", nullable = false)
	String Cidade;

	@Column(name = "UF", nullable = false)
	String UF;

	@ManyToOne
	@JoinColumn(name = "pessoaPK", nullable = false)
	private Pessoa pessoa;

	public Endereco(@Nonnull TipoEndereco tipoEndereco, @Nonnull String endereco, String numero, String complemento,
			@Nonnull String bairro, @Nonnull String CEP, @Nonnull String cidade, @Nonnull String uF, Pessoa pessoa) {
		this.tipoEndereco = tipoEndereco;
		Endereco = endereco;
		Numero = numero;
		Complemento = complemento;
		Bairro = bairro;
		this.CEP = CEP;
		Cidade = cidade;
		UF = uF;
		this.pessoa = pessoa;
	}

	public Endereco() {
	}

}
