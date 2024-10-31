package com.banco_do_nordeste.main.controller;

import com.banco_do_nordeste.main.interfaces.PessoaFisicaRepository;
//import com.banco_do_nordeste.main.model.*;
import com.banco_do_nordeste.main.model.Pessoa;
import com.banco_do_nordeste.main.model.PessoaJuridica;
import com.banco_do_nordeste.main.interfaces.*;
import com.banco_do_nordeste.main.interfaces.PessoaRepository;
import com.banco_do_nordeste.main.model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping("/")
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
