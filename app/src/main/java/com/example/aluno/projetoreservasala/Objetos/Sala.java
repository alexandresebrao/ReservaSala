package com.example.aluno.projetoreservasala.Objetos;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;

/**
 * Created by xandizitxu on 02/12/16.
 */
public class Sala {
    String usuario;
    String nome;
    String id;

    public Sala(String usuario, String nome) {
        this.id = null;
        this.usuario = usuario;
        this.nome = nome;
    }

    public Sala() {

    }

    public String getSalaNome() {
        return this.nome;
    }

    public String getSalaId() {
        return this.id;
    }

    public void save() {
        ParseObject sala = new ParseObject("Salas");
        sala.put("usuario", this.usuario);
        sala.put("nome", this.nome);
        sala.saveInBackground();
        this.id = sala.getObjectId();
    }




}
