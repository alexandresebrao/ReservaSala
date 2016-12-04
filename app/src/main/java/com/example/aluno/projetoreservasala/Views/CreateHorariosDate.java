package com.example.aluno.projetoreservasala.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.aluno.projetoreservasala.R;

public class CreateHorariosDate extends AppCompatActivity {

    Button btnSave;
    Button btnHorarioInicio;
    Button btnHorarioFim;
    Button btnProximoHoraInicio;
    Button btnProximoFimInicio;

    TextView lblHorarioInicio;
    TextView lblHorarioFim;
    TextView lblInicioReservaSala;
    TextView lblFimReservaSala;

    DatePicker datePickerInicio;
    DatePicker datePickerFim;

    TimePicker timePickerInicio;
    TimePicker timePickerFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_horarios);
        btnSave = (Button) findViewById(R.id.btnSave);

        datePickerInicio = (DatePicker) findViewById(R.id.datePickerStart);
        datePickerFim = (DatePicker) findViewById(R.id.datePickerEnd);

        lblHorarioInicio = (TextView) findViewById(R.id.lblHorarioInicio);
        lblHorarioFim = (TextView) findViewById(R.id.lblHorarioFim);
        lblInicioReservaSala = (TextView) findViewById(R.id.lblInicioReservaSala);
        lblFimReservaSala = (TextView) findViewById(R.id.lblFimReservaSala);

        btnProximoHoraInicio = (Button) findViewById(R.id.btnProximoHoraInicio);
        btnProximoFimInicio = (Button) findViewById(R.id.btnProximoFimInicio);
        btnHorarioInicio = (Button) findViewById(R.id.btnHorarioInicio);
        btnHorarioFim = (Button) findViewById(R.id.btnHorarioFim);

        timePickerInicio = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerFim = (TimePicker) findViewById(R.id.timePickerEnd);

        btnSave.setVisibility(View.INVISIBLE);
    }

    private void camadaText(int valor) {
        lblInicioReservaSala.setVisibility(valor);
        lblHorarioInicio.setVisibility(valor);
        lblFimReservaSala.setVisibility(valor);
        lblHorarioFim.setVisibility(valor);
        btnHorarioInicio.setVisibility(valor);
        btnHorarioFim.setVisibility(valor);
    }

    public void escolherDataInicio(View v) {
        camadaText(View.INVISIBLE);
        datePickerInicio.setVisibility(View.VISIBLE);
        btnProximoHoraInicio.setVisibility(View.VISIBLE);

    }

    public void escolherHoraInicio(View v) {
        datePickerInicio.setVisibility(View.INVISIBLE);
        btnProximoHoraInicio.setVisibility(View.INVISIBLE);
        btnProximoFimInicio.setVisibility(View.VISIBLE);
        timePickerInicio.setVisibility(View.VISIBLE);

    }

    public void escolherInicio(View v) {
        btnProximoFimInicio.setVisibility(View.INVISIBLE);
        timePickerInicio.setVisibility(View.INVISIBLE);
        btnProximoFimInicio.setVisibility(View.INVISIBLE);
        camadaText(View.VISIBLE);
    }



}
