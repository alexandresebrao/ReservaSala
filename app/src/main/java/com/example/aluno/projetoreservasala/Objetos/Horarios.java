package com.example.aluno.projetoreservasala.Objetos;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.lang.reflect.Array;
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





    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicioString() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(this.dataInicio);
    }

    public boolean isDataInicio() {
        if (this.dataInicio.equals(null)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean setDataFim(Date dataFim) {
        this.dataFim = dataFim;
        return isDataFimValid();
    }

    private boolean isDataFimValid() {
        if (this.dataFim.before(this.dataInicio)) {
            this.dataFim = null;
            return false;
        }
        else {
            return true;
        }
    }

    public String getDataFimString() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(this.dataFim);
    }

    public boolean verificaExistenciaDeHorarioValido() {
        if ((this.dataInicio != null) && (this.dataFim != null)) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean save() {
        if (verificaExistenciaDeHorarioValido()) {
            final ParseObject horarioParse = new ParseObject("Horarios");
            horarioParse.put("salaid", this.salaid);
            horarioParse.put("dataInicio", this.dataInicio);
            horarioParse.put("dataFim", this.dataFim);
            horarioParse.put("usuarioid",this.usuario);
            horarioParse.saveInBackground(new SaveCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {

                        Horarios.this.id = horarioParse.getObjectId();

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






    public String getUsuario() {
        return this.usuario;
    }

}
