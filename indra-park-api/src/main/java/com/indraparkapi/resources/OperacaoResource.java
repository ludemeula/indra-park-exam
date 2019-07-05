package com.indraparkapi.resources;

import com.indraparkapi.model.Veiculo;
import com.indraparkapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/operacao")
public class OperacaoResource {

    @Autowired
    private VeiculoRepository veiculoRepository;


    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        return ResponseEntity.ok(veiculoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoRepository.save(veiculo));
    }
}
