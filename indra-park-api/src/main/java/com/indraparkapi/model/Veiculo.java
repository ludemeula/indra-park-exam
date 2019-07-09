package com.indraparkapi.model;

import com.indraparkapi.enums.TipoVeiculo;

import javax.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "modelo", nullable = false)
    private TipoVeiculo modelo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
