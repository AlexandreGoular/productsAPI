package com.example.springboot.controllers;


import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @PostMapping("/products") // Acessando Localhost:8080/products com o metodo Post ele executa oque esta dentro do Escopo
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) { // Ele pega como Parametro o nosso record entao tera que ser passo um nome e value
        var productModel = new ProductModel(); // Ele cria uma nova Instancia do ProductModel que seria a nossa tabela
        BeanUtils.copyProperties(productRecordDto, productModel); // Ele pega os parametros do record e copia para o productModel
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); // Ele salva no Repositorio o productModel
    }
}
