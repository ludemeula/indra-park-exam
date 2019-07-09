package com.indraparkapi.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indraparkapi.enums.TipoOperacao;
import com.indraparkapi.enums.TipoVeiculo;
import com.indraparkapi.model.Operacao;
import com.indraparkapi.model.Veiculo;
import com.indraparkapi.repository.OperacaoRepository;
import com.indraparkapi.service.OperacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(OperacaoResource.class)
public class OperacaoResourceTest {

//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private OperacaoRepository operacaoRepository;
//
//    @Autowired
//    private OperacaoService operacaoService;

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void entrar() throws Exception {
        Operacao operacao = new Operacao();
        operacao.setPlaca("OCN-7766");
        operacao.setModelo(TipoVeiculo.CARRO);
        operacao.setTipo(TipoOperacao.ENTRADA);
        operacao.setDataHoraEntrada(LocalDateTime.now());

        asJsonString(operacao);


//        mvc.perform(post("/api/operacao/").content(asJsonString(operacao))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                //.andExpect(Status.isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}