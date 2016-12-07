package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aluno.projetoreservasala.Adaptadores.HorariosAdapter;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewSala extends AppCompatActivity {

    Sala sala;

    ArrayList<Horarios> horariosfinal;

    HorariosAdapter adapter;

    ListView listaHorarios;

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
        getHorarios(sala.getSalaId());
    }


    public void cadastrarHorario(View v) {
        Intent intent = new Intent(this,CreateHorario.class);
        intent.putExtra("salaid",sala.getSalaId());
        intent.putExtra("salanome",sala.getSalaNome());
        startActivity(intent);
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
                        String usuario = object.getString("usuario");

                        Horarios horario = new Horarios(id_sala, datainicio, datafim, false, usuario);
                        horarios.add(horario);
                    }
                    updateHorario(horarios);

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
    }
}
