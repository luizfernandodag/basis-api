package com.digisystem.luiz.controladores;

import com.digisystem.luiz.model.Compra;
import com.digisystem.luiz.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/compras")
public class CompraController {

   

        @Autowired
        private CompraService CompraService;

        @GetMapping
        public List<Compra> getAllCompras() {
            return CompraService.getAllCompras();
        }

        @GetMapping("/{id}")
        public Compra getCompraById(@PathVariable Long id) {
            return CompraService.getCompraById(id);
        }



        @PutMapping("/update/{id}")
        public void updateCompra(@PathVariable Long id, @RequestBody Compra Compra) {
            CompraService.update(id, Compra);

        }

        @PostMapping
        public void saveCompra(@RequestBody Compra Compra) {
            CompraService.saveCompra(Compra);
        }

        @DeleteMapping("/delete/{id}")
        public void deleteCompra(@PathVariable Long id) {
            CompraService.deleteCompra(id);
        }
    }


