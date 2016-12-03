package com.example.aluno.projetoreservasala.Objetos;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xandizitxu on 03/12/16.
 */

public class Horarios {
    private String salaid;
    private String id;
    private Date dataInicio;
    private Date dataFim;
    private boolean repeteSemana;

    public Horarios(String salaid, Date dataInicio, Date dataFim, boolean repeteSemana) {
        this.salaid = salaid;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.repeteSemana = repeteSemana;

    }

    public void cadastrar() {

    }

    public ArrayList<Horarios> getHorarios() {
        ArrayList<Horarios> horarios= new ArrayList<>();
        // TODO: Adicionar horarios
        return horarios;
    }
}
