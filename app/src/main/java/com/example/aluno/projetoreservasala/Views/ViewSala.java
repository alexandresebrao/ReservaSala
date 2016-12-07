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
    String nome;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sala);
        Intent intent = getIntent();
        TextView lblSala = (TextView) findViewById(R.id.lblSalaNomeView);
        id = intent.getExtras().getString("salaid");
        nome = intent.getExtras().getString("salanome");
        lblSala.setText("Sala: " + nome);
    }

    public void cadastrarHorario(View v) {
        Intent intent = new Intent(this,CreateHorario.class);
        intent.putExtra("salaid",id);
        intent.putExtra("salanome",nome);
        startActivity(intent);
    }




}
