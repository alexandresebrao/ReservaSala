package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Adaptadores.HorariosAdapter;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;


public class ViewSala extends AppCompatActivity implements Sala.SalaCallBack {

    Sala sala;

    TextView lblSala;


    HorariosAdapter adapter;

    ListView listaHorarios;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Aguarde");
        alertDialogBuilder.setMessage("Carregando Horarios...").setCancelable(false);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        setContentView(R.layout.activity_ver_sala);
        Intent intent = getIntent();
        lblSala = (TextView) findViewById(R.id.lblSalaNomeView);
        String id = intent.getExtras().getString("salaid");
        String nome = intent.getExtras().getString("salanome");

        try {
            sala = new Sala(id,nome,false);
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        sala.setCallback(ViewSala.this);
        lblSala.setText("Sala: " + nome);
        sala.getHorariosFirstTime();


    }


    public void cadastrarHorario(View v) {
        Intent intent = new Intent(this,CreateHorario.class);
        intent.putExtra("sala",sala);
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Horarios horario = (Horarios) data.getExtras().getSerializable("horario");
                try {
                    if (sala.addHorario(horario)) {
                        adapter.notifyDataSetChanged();
                        try {
                            setLabelOcupied();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {

                        Toast.makeText(this, "Horario bate", Toast.LENGTH_LONG).show();
                    }
                } catch (com.parse.ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setLabelOcupied() throws ParseException {
        TextView lblOcupado = (TextView) findViewById(R.id.lblOcupied);
        lblOcupado.setText("Situação atual: Disponível");
        if (sala.isOcupied()) {
            lblOcupado.setText("Situação atual: Ocupado");
        }
    }

    @Override
    public void returnHorarios() throws ParseException {
        setLabelOcupied();
        adapter = new HorariosAdapter(getApplicationContext(), R.layout.activity_ver_sala, sala.getHorarios());
        listaHorarios = (ListView) findViewById(R.id.lstHorario);
        listaHorarios.setAdapter(adapter);
        alertDialog.dismiss();
    }
}
