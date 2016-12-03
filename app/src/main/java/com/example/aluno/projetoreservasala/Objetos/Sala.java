package com.example.aluno.projetoreservasala.Objetos;

import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;
import java.util.List;

/**
 * Created by xandizitxu on 02/12/16.
 */
public class Sala {
    String nome;
    String id;


    public Sala(String nome) {
        this.nome = nome;
    }

    public Sala(String id, String nome){
        this.nome = nome;
        this.id = id;
    }

    public Sala() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sala get(String id) {
        ParseQuery query = new ParseQuery("Salas");
        final Sala sala = new Sala();
        query.whereEqualTo("_id", id);
        query.getInBackground(id, new GetCallback<ParseObject>() {
            public void done(ParseObject salaParse, com.parse.ParseException e) {
                if (e == null) {
                    sala.setId(salaParse.getObjectId());
                    sala.setNome(salaParse.getString("nome"));
                } else {
                    // something went wrong
                }
            }
        });
        return sala;
    }

    public String getSalaNome() {
        return this.nome;
    }

    public String getSalaId() {
        return this.id;
    }

    public void save() {
        ParseObject sala = new ParseObject("Salas");
        sala.put("nome", this.nome);
        sala.saveInBackground();
    }

}
