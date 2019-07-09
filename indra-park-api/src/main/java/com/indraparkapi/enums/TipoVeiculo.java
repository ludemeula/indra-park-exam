package com.indraparkapi.enums;

public enum TipoVeiculo {

    CARRO("Carro", 15),
    MOTO("Moto", 10),
    CAMINHAO("Caminhão", 35),
    CAMINHONETE("Caminhonete", 20);

    private final String modelo;
    private final double valor;

    TipoVeiculo(String modelo, double valor) {
        this.modelo = modelo;
        this.valor = valor;
    }

    public String getModelo() {
        return modelo;
    }

    public double getValor() {
        return valor;
    }
}