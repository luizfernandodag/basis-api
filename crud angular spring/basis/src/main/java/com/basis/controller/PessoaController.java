package com.basis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basis.implementacaoes.PessoaFisica;
import com.basis.implementacaoes.PessoaJuridica;
import com.basis.interfaces.PessoaFisicaRepository;
import com.basis.interfaces.PessoaJuridicaRepository;
import com.basis.interfaces.PessoaRepository;
import com.basis.model.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
		if (pessoa != null) {
			return new ResponseEntity<>(pessoa, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/fisicas")
	public ResponseEntity<PessoaFisica> addPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
		PessoaFisica novaPessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		return new ResponseEntity<>(novaPessoaFisica, HttpStatus.CREATED);
	}

	@PostMapping("/juridicas")
	public ResponseEntity<PessoaJuridica> addPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
		PessoaJuridica novaPessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
		return new ResponseEntity<>(novaPessoaJuridica, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
