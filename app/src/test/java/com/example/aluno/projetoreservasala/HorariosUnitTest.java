package com.example.aluno.projetoreservasala;

import android.util.Log;

import com.example.aluno.projetoreservasala.Objetos.Horarios;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by xandizitxu on 04/12/16.
 */

public class HorariosUnitTest {
    Horarios horario = new Horarios("","");



    @Test
    public void verificaExistenciaDeHorarioValido_assert_true() {
        horario.setDataInicio(new Date());
        horario.setDataFim(new Date());
        assertEquals(true,this.horario.verificaExistenciaDeHorarioValido());
    }

    @Test
    public void getString_horainicio() {
        Date hoje = new Date();
        String hoje_string = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(hoje);
        horario.setDataInicio(hoje);
        assertEquals(hoje_string,horario.getDataInicioString());
    }

    @Test
    public void getString_horafim() {
        Date hoje = new Date();
        String hoje_string = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(hoje);


        horario.setDataInicio(hoje);
        horario.setDataFim(hoje);
        assertEquals(hoje_string,horario.getDataFimString());
    }

}
