package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;

import java.util.ArrayList;

public class ViewSala extends AppCompatActivity {
    Sala sala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sala);
        Intent intent = getIntent();
        TextView lblSala = (TextView) findViewById(R.id.lblSalaNomeView);
        String id = intent.getExtras().getString("sala_id");
        String nome = intent.getExtras().getString("sala_nome");
        sala = new Sala(id,nome);
        Log.d("Sala nome:", sala.getSalaNome());
        lblSala.setText("Sala: " + sala.getSalaNome());
        ArrayList<Horarios> horarios = new ArrayList<>();
        horarios = Horarios.getSala(id);
    }

    public void cadastrarHorario(View v) {
        Intent intent = new Intent(this,CreateHorariosDate.class);
        intent.putExtra("salaid",sala.getSalaId());
        intent.putExtra("salanome",sala.getSalaNome());
        startActivityForResult(intent,1);
    }


}
