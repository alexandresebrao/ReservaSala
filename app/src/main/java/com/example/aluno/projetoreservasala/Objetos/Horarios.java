package com.example.aluno.projetoreservasala.Objetos;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xandizitxu on 03/12/16.
 */

public class Horarios {
    String salaid;
    String id;
    Date dataInicio;
    Date dataFim;
    boolean repeteSemana;
    String usuario;

    public Horarios(String salaid, Date dataInicio, Date dataFim, boolean repeteSemana, String usuario) {
        this.salaid = salaid;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.repeteSemana = repeteSemana;
        this.usuario = usuario;

    }


    public Horarios(String salaid, String usuario) {
        this.salaid = salaid;
        this.usuario = usuario;
        this.dataInicio = null;
        this.dataFim = null;
    }


    public static ArrayList<Horarios> getSala(String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Horarios");
        final ArrayList<Horarios> horarios = new ArrayList<>();
        query.whereEqualTo("sala_id",id);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {

                    for (ParseObject object : parseObjects ) {

                        String id_sala = object.getString("salaid");
                        String datainicio = object.getString("dataInicio");
                        String datafim = object.getString("dataFim");
                        Boolean repetesemana = object.getBoolean("repeteSemana");
                        String usuario = object.getString("usuario");

                        Horarios horario = new Horarios(id_sala,new Date(datainicio),new Date(datafim),repetesemana,usuario);
                        horarios.add(horario);
                    }


                } else {
                    Log.d("ERROR:", "" + e.getMessage());
                }

            }

        });
        return horarios;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicioString() {
        return this.dataInicio.toString();
    }

    public boolean isDataInicio() {
        if (this.dataInicio.equals(null)) {
            return false;
        } else {
            return true;
        }
    }
}
