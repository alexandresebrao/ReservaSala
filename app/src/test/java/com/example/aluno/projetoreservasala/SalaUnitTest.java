package com.example.aluno.projetoreservasala;

import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.parse.ParseException;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by xandizitxu on 04/12/16.
 */

public class SalaUnitTest {
    Sala sala;

    public SalaUnitTest() throws ParseException {
        sala = new Sala("abc");
    }

    @Test
    public void getsalaid_isCorrect() throws Exception {

        assertEquals(null, sala.getSalaId());
    }

    @Test
    public void getsalanome_isCorrect() throws Exception {

        assertEquals("abc", sala.getSalaNome());
    }




}
