package com.indraparkapi.resources;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.dto.DashboardDto;
import com.indraparkapi.repository.OperacaoRepository;
import com.indraparkapi.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/operacao")
public class OperacaoResource {

    @Autowired
    private OperacaoRepository operacaoRepository;

    @Autowired
    private OperacaoService operacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Operacao> findById(@PathVariable long id) {
        Optional<Operacao> operacao = operacaoRepository.findById(id);
        if (!operacao.isPresent())
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok(operacao.get());
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Operacao>> pesquisar(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDate dataEntrada,
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  @RequestParam(required = false) LocalDate dataSaida,
                                                    @RequestParam(required = false) String placa) {
        return ResponseEntity.ok(operacaoService.pesquisar(dataEntrada, dataSaida, placa));
    }

    @GetMapping
    public ResponseEntity<List<Operacao>> findAll() {
        return ResponseEntity.ok(operacaoRepository.currentDay());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<DashboardDto>> dashboard() {
        return ResponseEntity.ok(operacaoService.dashboard());
    }

    @PostMapping
    public ResponseEntity entrar(@Valid @RequestBody Operacao operacao) {
        return ResponseEntity.ok(operacaoService.entrar(operacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacao> sair(@PathVariable long id) {
        return ResponseEntity.ok(operacaoService.sair(operacaoRepository.findById(id).get()));
    }

    @GetMapping("/calcular")
    public ResponseEntity<Double> calcular(@RequestParam String modelo,
                                         @RequestParam
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraEntrada) {
        return ResponseEntity.ok(operacaoService.calcular(TipoVeiculo.valueOf(modelo), dataHoraEntrada));
    }
}
