package com.indraparkapi.resources;

import com.indraparkapi.enums.TipoOperacao;
import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.service.OperacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OperacaoResourceTest extends AbstractResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OperacaoService operacaoService;

    @Before
    public void setUp() {

    }

    @Test
    public void findAll() throws Exception {
        operacaoService.entrar(getOperacao("OCN-7766"));

        mvc.perform(get("/api/operacao")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void entrar() throws Exception {

        mvc.perform(
                post("/api/operacao")
                        .content(toJson(getOperacao("ABC-1234")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private Operacao getOperacao(final String placa) {
        Operacao operacao = new Operacao();
        operacao.setPlaca(placa);
        operacao.setModelo(TipoVeiculo.CARRO);
//        operacao.setTipo(TipoOperacao.ENTRADA);

        return operacao;
    }
}