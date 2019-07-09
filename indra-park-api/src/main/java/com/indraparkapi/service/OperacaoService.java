package com.indraparkapi.service;

import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.dto.DashboardDto;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interface que provém os serviços de {@link Operacao}.
 *
 * @author ludemeula
 */
public interface OperacaoService {

    Operacao entrar(Operacao operacao);

    Operacao sair(Operacao operacao);

    double calcular(TipoVeiculo tipoVeiculo, LocalDateTime dataHoraEntrada);

    List<Operacao> pesquisar(LocalDate dataEntrada, LocalDate dataSaida, String placa);

    List<DashboardDto> dashboard();
}
