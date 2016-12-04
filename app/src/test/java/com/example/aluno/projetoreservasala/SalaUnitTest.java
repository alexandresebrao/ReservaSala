package com.example.aluno.projetoreservasala;

import com.example.aluno.projetoreservasala.Objetos.Sala;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by xandizitxu on 04/12/16.
 */

public class SalaUnitTest {
    Sala sala = new Sala("1234","jota");

    @Test
    public void getsalaid_isCorrect() throws Exception {

        assertEquals("1234", sala.getSalaId());
    }

    @Test
    public void getsalanome_isCorrect() throws Exception {

        assertEquals("jota", sala.getSalaNome());
    }

    @Test
    public void salaNome_naopodeserbranco() throws Exception {
        Sala sala2 = new Sala("123131","");
        assertFalse(sala2.valida());
    }

}
