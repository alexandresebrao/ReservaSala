package com.example.aluno.projetoreservasala.Objetos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xandizitxu on 03/12/16.
 */

public class Horarios implements Serializable{
    String salaid;
    String id;
    Date dataInicio;
    Date dataFim;
    boolean repeteSemana;
    String usuario;
    ArrayList<Horarios> horarios;



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



    public void setHorarios(ArrayList<Horarios> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<Horarios> getHorarios() {
        return this.horarios;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicioString() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(this.dataInicio);
    }


    public boolean setDataFim(Date dataFim) {
        this.dataFim = dataFim;
        return isDataFimValid();
    }

    public boolean isDataFimValid() {
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
        if (verificaExistenciaDeHorarioValido() && valid()) {
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


    public boolean valid() {
        boolean valor = true;
        for (Horarios h : this.horarios) {
            // (StartA <= EndB)  and  (EndA >= StartB)
            if ((h.dataInicio.before(this.dataFim)) && (h.dataFim.after(this.dataInicio))) {
                valor = false;
            }
        }
        return valor;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
}
