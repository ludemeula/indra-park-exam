package com.indraparkapi.model.dto;

import com.indraparkapi.enums.TipoVeiculo;

/**
 * DTO responsável por obter os dados que serão apresentados na Dashboard.
 *
 * @author ludemeula
 */
public class DashboardDto {

    private long quantidade;
    private TipoVeiculo modelo;
    private String data;

    public DashboardDto(long quantidade, TipoVeiculo modelo, String data) {
        this.quantidade = quantidade;
        this.modelo = modelo;
        this.data = data;
    }

    public TipoVeiculo getModelo() {
        return modelo;
    }

    public void setModelo(TipoVeiculo modelo) {
        this.modelo = modelo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
}
