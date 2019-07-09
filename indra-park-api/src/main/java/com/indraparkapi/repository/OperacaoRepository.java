package com.indraparkapi.repository;

import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.dto.DashboardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

    String CURRENT_DAY = "SELECT o FROM Operacao o " +
            "WHERE DATE(data_hora_entrada) = DATE(now()) OR DATE(data_hora_saida) = DATE(now())";

    String DASHBOARD = " SELECT new com.indraparkapi.model.dto.DashboardDto(COUNT(modelo), modelo, TO_CHAR(dataHoraEntrada, 'DD/MM/YYYY')) " +
            " FROM Operacao WHERE DATE(dataHoraEntrada) > :dataInicio " +
            " GROUP BY modelo, col_2_0_ ORDER BY col_2_0_ ";

    @Query(value = CURRENT_DAY)
    List<Operacao> currentDay();

    @Query(value = DASHBOARD)
    List<DashboardDto> dashboard(@Param("dataInicio") LocalDate dataInicio);
}
