package com.example.aluno.projetoreservasala.Objetos;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Adaptadores.SalasAdapter;
import com.example.aluno.projetoreservasala.Views.ViewSala;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by xandizitxu on 02/12/16.
 */
public class Sala implements Parcelable {
    String nome;
    String id;
    ArrayList<Horarios> horarios;

    private SalaCallBack callback;

    public Sala(String nome){
        this.nome = nome;
    }

    public Sala(String id, String nome) throws com.parse.ParseException {
        this.nome = nome;
        this.id = id;
        this.horarios = getHorariosInn();
        removeOldHorarios();
    }

    public Sala(String id, String nome, boolean b) throws com.parse.ParseException {
        this.nome = nome;
        this.id = id;
    }


    protected Sala(Parcel in) {
        nome = in.readString();
        id = in.readString();
    }

    public static final Creator<Sala> CREATOR = new Creator<Sala>() {
        @Override
        public Sala createFromParcel(Parcel in) {
            return new Sala(in);
        }

        @Override
        public Sala[] newArray(int size) {
            return new Sala[size];
        }
    };

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

    public void getHorariosFirstTime() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Horarios");
        query.whereEqualTo("salaid", this.id);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null){
                    ArrayList<Horarios> horarios = new ArrayList<>();
                    for (ParseObject object : objects) {
                        String id_sala = object.getString("salaid");
                        Date datainicio = object.getDate("dataInicio");
                        Date datafim = object.getDate("dataFim");
                        String usuario = object.getString("usuarioid");
                        Horarios horario = null;
                        try {
                            horario = new Horarios(id_sala, datainicio, datafim, false, usuario);
                        } catch (com.parse.ParseException e1) {
                            e1.printStackTrace();
                        }
                        horarios.add(horario);
                    }
                        Sala.this.horarios = horarios;
                        removeOldHorarios();
                    try {
                        callback.returnHorarios();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });


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

    public boolean addHorario(Horarios horario) throws com.parse.ParseException {
        if (HorarioValido(horario)) {
            horario.save();
            this.horarios.add(horario);
            return true;
        }
        return false;
    }


    private boolean HorarioValido(Horarios horario) {
        boolean valor = true;
        for (Horarios h : this.horarios) {
            // (StartA <= EndB)  and  (EndA >= StartB)
            if ((h.dataInicio.before(horario.dataFim)) && (h.dataFim.after(horario.dataInicio))) {
                valor = false;
            }
        }
        return valor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(id);
    }


    public interface SalaCallBack {
        void returnHorarios() throws ParseException;
    }



    public void setCallback(Sala.SalaCallBack callback){

        this.callback = callback;
    }

    private void removeOldHorarios() {
        for (Iterator<Horarios> i = this.horarios.iterator(); i.hasNext();) {
            Horarios h = i.next();
            if (h.getDataFim().before(new Date())) {
                i.remove();
            }
        }
    }
}
