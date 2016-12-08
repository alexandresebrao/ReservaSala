package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Adaptadores.HorariosAdapter;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class ViewSala extends AppCompatActivity {

    Sala sala;

    ArrayList<Horarios> horariosfinal;

    HorariosAdapter adapter;

    ListView listaHorarios;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sala);
        Intent intent = getIntent();
        TextView lblSala = (TextView) findViewById(R.id.lblSalaNomeView);
        String id = intent.getExtras().getString("salaid");
        String nome = intent.getExtras().getString("salanome");
        sala = new Sala(id,nome);
        lblSala.setText("Sala: " + sala.getSalaNome());
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Aguarde");
        alertDialogBuilder.setMessage("Carregando Horarios...").setCancelable(false);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        getHorarios(sala.getSalaId());

    }


    public void cadastrarHorario(View v) {
        Intent intent = new Intent(this,CreateHorario.class);
        intent.putExtra("salaid",sala.getSalaId());
        intent.putExtra("salanome",sala.getSalaNome());
        startActivityForResult(intent,1);
    }

    public void getHorarios(String salaid) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Horarios");
        final ArrayList<Horarios> horarios = new ArrayList<>();
        query.whereEqualTo("salaid", salaid);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    ArrayList<Horarios> horarios = new ArrayList<Horarios>();
                    for (ParseObject object : parseObjects) {

                        String id_sala = object.getString("salaid");
                        Date datainicio = object.getDate("dataInicio");
                        Date datafim = object.getDate("dataFim");
                        String usuario = object.getString("usuarioid");

                        Horarios horario = new Horarios(id_sala, datainicio, datafim, false, usuario);
                        horarios.add(horario);
                    }
                    updateHorario(horarios);
                    alertDialog.dismiss();
                } else {
                    Log.d("ERROR:", "" + e.getMessage());
                }


            }
        });

    }

    private void updateHorario(ArrayList<Horarios> horarios) {
        horariosfinal = horarios;
        adapter = new HorariosAdapter(getApplicationContext(), R.layout.activity_ver_sala, horariosfinal);
        listaHorarios = (ListView) findViewById(R.id.lstHorario);
        listaHorarios.setAdapter(adapter);
        try {
            isOcupied();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Horarios horario = (Horarios) data.getExtras().getSerializable("horario");
                horario.setHorarios(horariosfinal);
                if  (horario.save()) {

                    horariosfinal.add(horario);
                    adapter.notifyDataSetChanged();
                    try {
                        isOcupied();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this,"Horario bate",Toast.LENGTH_LONG).show();
                }
            }

        }
    }


    private void isOcupied() throws ParseException {
        Date d = new Date();
        TextView lblOcupied = (TextView) findViewById(R.id.lblOcupied);
        lblOcupied.setText("Situação atual: Disponível");

        for (Horarios h : horariosfinal) {
            if ((d.after(h.getDataInicio()) && (d.before(h.getDataFim())))) {
                lblOcupied.setText("Situação atual: Ocupada");
                Log.d("Horarios: ",String.format("%s -> Data inicio: %s, Data fim: %s",d.toString(),h.getDataInicioString(),h.getDataFimString()));
            }

        }

    }
}
