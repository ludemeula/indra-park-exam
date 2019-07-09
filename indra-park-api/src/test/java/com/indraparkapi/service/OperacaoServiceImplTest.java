package com.indraparkapi.service;

import com.indraparkapi.enums.TipoOperacao;
import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.repository.OperacaoRepository;
import com.indraparkapi.repository.OperacaoRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class OperacaoServiceImplTest {

    private OperacaoService operacaoService;

    @Mock
    private OperacaoRepository operacaoRepository;

    @Mock
    private OperacaoRepositoryCustom operacaoRepositoryCustom;

    private Operacao operacao;

    @Before
    public void setUp() {
        operacaoService = new OperacaoServiceImpl(operacaoRepository, operacaoRepositoryCustom);

        operacao = new Operacao();
        operacao.setPlaca("ABC-123");
        operacao.setModelo(TipoVeiculo.CARRO);
    }

    @Test
    public void entrar_comSucesso() throws Exception {
        operacaoService.entrar(operacao);

        assertThat(operacao.getTipo(), is(TipoOperacao.ENTRADA));
        assertThat(operacao.getDataHoraEntrada(), is(notNullValue()));
        verify(operacaoRepository, Mockito.times(1)).save(operacao);
    }

    @Test
    public void entrar_comExcecao() throws Exception {
//        operacaoService.entrar(operacao);
//
//        assertThat(operacao.getTipo(), is(TipoOperacao.ENTRADA));
//        assertThat(operacao.getDataHoraEntrada(), is(notNullValue()));
//        verify(operacaoRepository, Mockito.times(1)).save(operacao);
    }

    @Test
    public void sair() throws Exception {
        operacao.setDataHoraEntrada(LocalDateTime.now().minusHours(2));
        operacaoService.sair(operacao);

        assertThat(operacao.getTipo(), is(TipoOperacao.SAIDA));
        assertThat(operacao.getDataHoraSaida(), is(notNullValue()));
        assertThat(operacao.getValorTotal(), is(notNullValue()));
        verify(operacaoRepository, Mockito.times(1)).save(operacao);
    }

    @Test
    public void calcular_Carro_MaisDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CARRO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now().minusHours(2));

        assertThat(resultado, is(30D));
    }

    @Test
    public void calcular_Carro_MenosDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CARRO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now());

        assertThat(resultado, is(15D));
    }

    @Test
    public void calcular_Moto_MaisDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.MOTO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now().minusHours(2));

        assertThat(resultado, is(20D));
    }

    @Test
    public void calcular_Moto_MenosDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.MOTO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now());

        assertThat(resultado, is(10D));
    }

    @Test
    public void calcular_Caminhao_MaisDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CAMINHAO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now().minusHours(2));

        assertThat(resultado, is(70D));
    }

    @Test
    public void calcular_Caminhao_MenosDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CAMINHAO;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now());

        assertThat(resultado, is(35D));
    }

    @Test
    public void calcular_Caminhonete_MaisDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CAMINHONETE;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now().minusHours(2));

        assertThat(resultado, is(40D));
    }

    @Test
    public void calcular_Caminhonete_MenosDeUmaHora() throws Exception {
        TipoVeiculo tipoVeiculo = TipoVeiculo.CAMINHONETE;

        double resultado = operacaoService.calcular(tipoVeiculo, LocalDateTime.now());

        assertThat(resultado, is(20D));
    }

    @Test
    public void pesquisar() throws Exception {
        operacaoService.pesquisar(any(LocalDate.class), any(LocalDate.class), any(String.class));

        verify(operacaoRepositoryCustom, Mockito.times(1))
                .pesquisar(any(Optional.class), any(Optional.class), any(Optional.class));
    }

    @Test
    public void dashboard() throws Exception {
        operacaoService.dashboard();

        verify(operacaoRepository, Mockito.times(1)).dashboard(any(LocalDate.class));
    }

}