package com.example.aluno.projetoreservasala.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.projetoreservasala.R;

public class CreateHorarioTime extends AppCompatActivity {
    Button btnSave;
    TextView lblHorarioInicio;
    TextView lblHorarioFim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_horario_time);
    }
}
