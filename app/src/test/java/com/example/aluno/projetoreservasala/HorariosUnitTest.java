package com.example.aluno.projetoreservasala;

import com.example.aluno.projetoreservasala.Objetos.Horarios;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by xandizitxu on 04/12/16.
 */

public class HorariosUnitTest {
    Horarios horario = new Horarios("1",new Date(),new Date(),false,"joao");

    // Pegar Horario de uma sala especifica
    @Test
    public void pegarsala_x() throws Exception {
        Horarios horario2 = new Horarios("1",new Date(),new Date(),false,"joao");
        assertEquals(horario.getSala("3"),horario2);

    }


}
