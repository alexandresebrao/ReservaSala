package com.example.aluno.projetoreservasala.Objetos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xandizitxu on 02/12/16.
 */
public class Sala {
    String nome;
    String id;
    ArrayList<Horarios> horarios;


    public Sala(String nome){
        this.nome = nome;
    }

    public Sala(String id, String nome) throws com.parse.ParseException {
        this.nome = nome;
        this.id = id;
        this.horarios = getHorariosInn();
    }


    public void setId(String id) {
        this.id = id;
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

    private ArrayList<Horarios> getHorariosInn() throws com.parse.ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Horarios");
        query.whereEqualTo("salaid", this.id);
        List<ParseObject> objects = query.find();
        ArrayList<Horarios> horarios = new ArrayList<>();
        for (ParseObject object : objects) {
            String id_sala = object.getString("salaid");
            Date datainicio = object.getDate("dataInicio");
            Date datafim = object.getDate("dataFim");
            String usuario = object.getString("usuarioid");

            Horarios horario = new Horarios(id_sala, datainicio, datafim, false, usuario);
            horarios.add(horario);
        }
        return horarios;
    }

    public boolean isOcupied() throws java.text.ParseException {
        Date d = new Date();
        boolean ocupado = false;

        for (Horarios h : this.horarios) {
            if (d.before(h.getDataFim())) {
                if (d.after(h.getDataInicio())) {
                    ocupado = true;
                    Log.d("Horarios: ",String.format("%s -> Data inicio: %s, Data fim: %s",d.toString(),h.getDataInicio(),h.getDataFim()));
                }
            }
        }
        return ocupado;

    }

    public ArrayList<Horarios> getHorarios() {
        return horarios;
    }

    public boolean addHorario(Horarios horario) {
        horario.save();
        this.horarios.add(horario);

        return false;
    }
}
