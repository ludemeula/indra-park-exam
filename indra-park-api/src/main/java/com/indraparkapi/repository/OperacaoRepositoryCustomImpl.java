package com.indraparkapi.repository;

import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.dto.DashboardDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class OperacaoRepositoryCustomImpl implements OperacaoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Operacao> pesquisar(Optional<LocalDate> dataEntrada, Optional<LocalDate> dataSaida, Optional<String> placa) {
        StringBuilder sql = new StringBuilder(" SELECT operacao from Operacao operacao WHERE 1 = 1 ");

        if (dataEntrada.isPresent())
            sql.append("AND DATE(dataHoraEntrada) = :dataEntrada ");
        if (dataSaida.isPresent())
            sql.append("AND DATE(dataHoraSaida) = :dataSaida ");
        if (placa.isPresent())
            sql.append(" AND placa LIKE :placa ");

        Query query = entityManager.createQuery(sql.toString());
        if (dataEntrada.isPresent())
            query.setParameter("dataEntrada", dataEntrada.get());
        if (dataSaida.isPresent())
            query.setParameter("dataSaida", dataSaida.get());
        if (placa.isPresent())
            query.setParameter("placa", "%" + placa.get().toUpperCase() + "%");

        return query.getResultList();
    }
}
