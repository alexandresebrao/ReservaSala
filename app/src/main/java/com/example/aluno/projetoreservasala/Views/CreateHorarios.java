package com.example.aluno.projetoreservasala.Views;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.R;

public class CreateHorarios extends AppCompatActivity {

    Button btnSave;
    Button btnHorarioInicio;
    Button btnHorarioFim;
    Button btnProximoHoraInicio;
    Button btnProximoFimInicio;
    Button btnProximoHoraFim;
    Button btnProximoFimFim;

    TextView lblHorarioInicio;
    TextView lblHorarioFim;
    TextView lblInicioReservaSala;
    TextView lblFimReservaSala;
    TextView lblReservaSala;

    DatePicker datePickerInicio;
    DatePicker datePickerFim;

    TimePicker timePickerInicio;
    TimePicker timePickerFim;

    String horarioInicio;
    String horaFim;

    Horarios horario;

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
        lblReservaSala = (TextView) findViewById(R.id.lblReservaSala);

        btnProximoHoraInicio = (Button) findViewById(R.id.btnProximoHoraInicio);
        btnProximoFimInicio = (Button) findViewById(R.id.btnProximoFimInicio);
        btnHorarioInicio = (Button) findViewById(R.id.btnHorarioInicio);
        btnHorarioFim = (Button) findViewById(R.id.btnHorarioFim);
        btnProximoHoraFim = (Button) findViewById(R.id.btnProximoHoraFim);
        btnProximoFimFim = (Button) findViewById(R.id.btnProximoFimFim);
        btnSave.setVisibility(View.INVISIBLE);

        timePickerInicio = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerFim = (TimePicker) findViewById(R.id.timePickerEnd);

        lblHorarioInicio.setText("");
        lblHorarioFim.setText("");


    }

    private void camadaText(int valor) {
        lblReservaSala.setVisibility(valor);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void escolherInicio(View v) {
        btnProximoFimInicio.setVisibility(View.INVISIBLE);
        timePickerInicio.setVisibility(View.INVISIBLE);
        btnProximoFimInicio.setVisibility(View.INVISIBLE);
        setHorarioInicio();
        camadaText(View.VISIBLE);

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setHorarioInicio() {
        int year =  datePickerInicio.getYear();
        int month = datePickerInicio.getMonth()+1;
        int day = datePickerInicio.getDayOfMonth();

        int hour = timePickerInicio.getHour();
        int minute = timePickerInicio.getMinute();

        String texto = String.format("%s /%s /%s %s:%s", String.valueOf(day) ,String.valueOf(month),String.valueOf(year),String.valueOf(hour),String.valueOf(minute));
        lblHorarioInicio.setText(texto);
    }

    public void escolherDataFim(View v) {
        camadaText(View.INVISIBLE);
        datePickerFim.setVisibility(View.VISIBLE);
        btnProximoHoraFim.setVisibility(View.VISIBLE);

    }

    public void escolherHoraFim(View v) {
        datePickerFim.setVisibility(View.INVISIBLE);
        btnProximoHoraFim.setVisibility(View.INVISIBLE);
        btnProximoFimFim.setVisibility(View.VISIBLE);
        timePickerFim.setVisibility(View.VISIBLE);

    }

    public void escolherFim(View v) {
        btnProximoFimFim.setVisibility(View.INVISIBLE);
        timePickerFim.setVisibility(View.INVISIBLE);
        btnProximoFimFim.setVisibility(View.INVISIBLE);
        camadaText(View.VISIBLE);
    }

}