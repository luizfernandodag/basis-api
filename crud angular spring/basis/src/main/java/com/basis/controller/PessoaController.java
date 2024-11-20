package com.basis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basis.implementacaoes.PessoaFisica;
import com.basis.implementacaoes.PessoaJuridica;
import com.basis.model.Pessoa;
import com.basis.repositories.PessoaFisicaRepository;
import com.basis.repositories.PessoaJuridicaRepository;
import com.basis.repositories.PessoaRepository;

@RestController
//@RequestMapping("/pessoas")
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	/*@GetMapping("/pessoas")
	public ResponseEntity<List<pessoa>> getAllpessoas(@RequestParam(required = false) String title) {
		try {*/
//---//PESSOAS-----------------------------------------------------------------------------------------------
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
		if (pessoa != null) {
			return new ResponseEntity<>(pessoa, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	///%% CREATE%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public ResponseEntity<Pessoa> addPessoaFisica(@RequestBody Pessoa pessoa) {
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
	}
	///%READ%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	@GetMapping("/listpessoas")
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		
	
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	///%UPDATE%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> updatepessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

		if (pessoaData.isPresent()) {
			Pessoa _pessoa = pessoaData.get();
			_pessoa.setEnderecos(_pessoa.getEnderecos());
			_pessoa.setEmail(_pessoa.getEmail());
			_pessoa.setTelefone(_pessoa.getTelefone());
			return new ResponseEntity<>(pessoaRepository.save(_pessoa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	
	
	//%% DELETE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletePessoaByEmail(@PathVariable String email) {
		pessoaRepository.deleteByEmail(email);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}
//.//PESSOAS FISICAS----------------------------------------------
	// CREATED
	@PostMapping("/addpessoafisica")
	public ResponseEntity<PessoaFisica> addPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
		PessoaFisica novaPessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		return new ResponseEntity<>(novaPessoaFisica, HttpStatus.CREATED);
	}
	
	// READ
	
	@GetMapping("/pessoasfisicas")
	public ResponseEntity<List<PessoaFisica>> getAllPessoasFisicas() {
		
	
		List<PessoaFisica> pessoas = pessoaFisicaRepository.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	//UPDATE
	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> updatepessoafisica(@PathVariable("id") long id, 
			@RequestBody PessoaFisica pessoa) {
		Optional<PessoaFisica> pessoaData = pessoaFisicaRepository.findById(id);

		if (pessoaData.isPresent()) {
			PessoaFisica _pessoa = pessoaData.get();
			_pessoa.setCPF(pessoa.getCPF());
			_pessoa.setNome(pessoa.getNome());
			_pessoa.setEnderecos(pessoa.getEnderecos());
			_pessoa.setEmail(pessoa.getEmail());
			_pessoa.setTelefone(pessoa.getTelefone());
			return new ResponseEntity<>(pessoaRepository.save(_pessoa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//DELETE
	
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletePessoaFisicaByCPF(@PathVariable String cpf) {
		pessoaFisicaRepository.deleteByCPF(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{nome}")
	public ResponseEntity<Void> deletePessoa(@PathVariable String nome) {
		pessoaRepository.deleteByNome(nome);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}
//... // PESSOAS JURIDICAS _---------------------------------------------------------------------------------
	//CREATE
	@PostMapping("/addpessoajuridicas")
	public ResponseEntity<PessoaJuridica> addPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
		PessoaJuridica novaPessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
		return new ResponseEntity<>(novaPessoaJuridica, HttpStatus.CREATED);
	}
	
	// READ
	@GetMapping("/pessoasjuridicas")
	public ResponseEntity<List<PessoaJuridica>> getAllPessoasJuridicas() {
		
	
		List<PessoaJuridica> pessoas = this.pessoaJuridicaRepository.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
		//UPDATE
	@PutMapping("/pessoasjuridicas/{id}")
	public ResponseEntity<PessoaJuridica> updatepessoajuridica(@PathVariable("id") long id, 
			@RequestBody PessoaJuridica pessoajuridica) {
		Optional<PessoaJuridica> pessoaData = pessoaJuridicaRepository.findById(id);

		if (pessoaData.isPresent()) {
			PessoaJuridica _pessoa = pessoaData.get();
			_pessoa.setCNPJ(pessoajuridica.getCNPJ());
			_pessoa.setEnderecos(pessoajuridica.getEnderecos());
			_pessoa.setRazaoSocial(pessoajuridica.getRazaoSocial());
			_pessoa.setEmail(pessoajuridica.getEmail());
			_pessoa.setTelefone(pessoajuridica.getTelefone());



			return new ResponseEntity<>(pessoaJuridicaRepository.save(_pessoa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
		//DELETE
	
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletePessoaJuridicaByCNPJ(@PathVariable String cnpj) {
		pessoaJuridicaRepository.deleteByCNPJ(cnpj);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{nome}")
	public ResponseEntity<Void> deletePessoaByRazaoSocial(@PathVariable String razaosocial) {
		pessoaJuridicaRepository.deleteByRazaoSocial(razaosocial);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}


	
	
}


