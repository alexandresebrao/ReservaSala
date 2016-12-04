package com.example.aluno.projetoreservasala.Objetos;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Views.ViewSala;
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


    public Sala(String nome){
        this.nome = nome;
    }

    public Sala(String id, String nome){
        this.nome = nome;
        this.id = id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSalaNome() {
        return this.nome;
    }

    public String getSalaId() {
        return this.id;
    }

    public Boolean save() {
        if (this.valida()) {
            final ParseObject sala = new ParseObject("Salas");
            sala.put("nome", this.nome);
            sala.saveInBackground(new SaveCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {

                        Sala.this.id = sala.getObjectId();

                    } else {
                        // The save failed.
                    }
                }
            });
            return true;
        }
        else {
            return false;
        }

    }


    public boolean valida() {
        if (this.nome.equals("")) {
            return false;
        }
        else
        {
            return true;
        }
    }
}
