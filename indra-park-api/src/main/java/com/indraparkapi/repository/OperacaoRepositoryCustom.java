package com.indraparkapi.repository;

import com.indraparkapi.model.Operacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OperacaoRepositoryCustom {

    List<Operacao> pesquisar(Optional<LocalDate> dataEntrada, Optional<LocalDate> dataSaida, Optional<String> placa);

}
