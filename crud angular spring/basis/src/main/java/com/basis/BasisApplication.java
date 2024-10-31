package com.basis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.basis.implementacaoes.PessoaFisica;
import com.basis.implementacaoes.PessoaJuridica;
import com.basis.model.Endereco;
import com.basis.model.Pessoa;
import com.basis.model.TipoEndereco;
import com.basis.repositories.EnderecoRepository;
import com.basis.repositories.PessoaRepository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
//@ComponentScan(basePackages = "")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class BasisApplication {
	
	/*@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(BasisApplication.class, args);
	}
	/*public void run(String ...args) throws Exception
	{
		
		Pessoa p1 = new PessoaFisica("000000","Nome Pessoa fisica");
		Pessoa p2 = new PessoaJuridica("000000","Nome Pessoa juridica");
		TipoEndereco tipoendereco = TipoEndereco.comercial;
		Endereco end = new Endereco(tipoendereco,"endereco","01","complemento", "bairro","000000","cidade","UF",  p1 );
		
		PessoaFisica p3 = new PessoaFisica("000000","Nome Pessoa fisica");
		
		p1.getEnderecos().add(end);
		
		this.enderecoRepository.saveAll(Arrays.asList(end));
		
		this.pessoaRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
	
	}*/

}
