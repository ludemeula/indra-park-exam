package com.indraparkapi.service;

import com.indraparkapi.enums.TipoOperacao;
import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.dto.DashboardDto;
import com.indraparkapi.repository.OperacaoRepository;
import com.indraparkapi.repository.OperacaoRepositoryCustom;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Classe reponsável por implementar os serviços de {@link Operacao}.
 *
 * @author ludemeula
 */
@Service
public class OperacaoServiceImpl implements  OperacaoService {

    private OperacaoRepository operacaoRepository;
    private OperacaoRepositoryCustom operacaoRepositoryCustom;

    @Autowired
    public OperacaoServiceImpl(OperacaoRepository operacaoRepository, OperacaoRepositoryCustom operacaoRepositoryCustom) {
        this.operacaoRepository = operacaoRepository;
        this.operacaoRepositoryCustom = operacaoRepositoryCustom;
    }

    @Override
    public Operacao entrar(Operacao operacao) {
//        validarDuplicata(operacao.getPlaca());

        operacao.setDataHoraEntrada(LocalDateTime.now());
        operacao.setTipo(TipoOperacao.ENTRADA);

        return operacaoRepository.save(operacao);
    }

    @Override
    public Operacao sair(Operacao operacao) {
        operacao.setDataHoraSaida(LocalDateTime.now());
        operacao.setTipo(TipoOperacao.SAIDA);
        operacao.setValorTotal(calcular(operacao.getModelo(), operacao.getDataHoraEntrada()));

        return operacaoRepository.save(operacao);
    }

    @Override
    public double calcular(TipoVeiculo tipoVeiculo, LocalDateTime dataHoraEntrada) {
        long between = ChronoUnit.HOURS.between(dataHoraEntrada, LocalDateTime.now());

        return (between > 1 ? between * tipoVeiculo.getValor() : tipoVeiculo.getValor());
    }

    @Override
    public List<Operacao> pesquisar(LocalDate dataEntrada, LocalDate dataSaida, String placa) {
        return operacaoRepositoryCustom.pesquisar(Optional.ofNullable(dataEntrada), Optional.ofNullable(dataSaida), Optional.ofNullable(placa));
    }

    @Override
    public List<DashboardDto> dashboard() {
        return operacaoRepository.dashboard(LocalDate.now().minusDays(7));
    }
}