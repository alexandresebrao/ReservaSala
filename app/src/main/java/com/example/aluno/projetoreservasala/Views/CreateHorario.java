package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreateHorario extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener, RadialTimePickerDialogFragment.OnTimeSetListener {

    private static final String FRAG_TAG_TIME_PICKER = "2";
    private static final String FRAG_TAG_DATE_PICKER = "1";


    Horarios horario;

    int year;
    int month;
    int day;
    int tipoHorario;

    Button btnHorarioFim;

    TextView lblhorarioInicio;
    TextView lblhorarioFim;

    String salaid;
    String salanome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_horarios);
        Intent intent = getIntent();

        horario = new Horarios("sala","usuario");

        lblhorarioInicio = (TextView) findViewById(R.id.lblHorarioInicio);
        lblhorarioFim = (TextView) findViewById(R.id.lblHorarioFim);
        TextView lblSalaHorario = (TextView) findViewById(R.id.lblSalaHorarioBeta);

        btnHorarioFim = (Button) findViewById(R.id.btnEscolherHoraFimBeta);
        btnHorarioFim.setVisibility(View.INVISIBLE);

        salaid = intent.getExtras().getString("salaid");
        salanome = intent.getExtras().getString("salanome");

        lblSalaHorario.setText(String.format("Reservar sala: %s",salanome));



    }

    public void setHorarioInicio(View v) {
        tipoHorario = 1;
        setDate();

    }

    public void setHorarioFim(View v) {
        tipoHorario = 2;
        setDate();
    }

    private void setDate(){
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(CreateHorario.this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setCancelText("Cancelar")
                .setDoneText("Próximo");
        cdp.setCancelable(false);
        cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
    }

    private void setHour(){
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(CreateHorario.this)
                .setDoneText("Próximo")
                .setCancelText("Cancelar")
                .setForced24hFormat();
        rtpd.setCancelable(false);
        rtpd.show(getSupportFragmentManager(), FRAG_TAG_TIME_PICKER);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        setHour();
    }


    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String toParse = String.format("%s-%s-%s %s:%s", year, month + 1, day, hourOfDay, minute);
        Date d = null;
        try {
            d = sdf.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (tipoHorario == 1) {
            horario.setDataInicio(d);
            btnHorarioFim.setVisibility(View.VISIBLE);
            lblhorarioInicio.setText(horario.getDataInicioString());

        }
        else
        {
            if (horario.setDataFim(d)) {
                lblhorarioFim.setText(horario.getDataFimString());
            }
            else {
                Toast.makeText(this,"Horário para o Fim inválido",Toast.LENGTH_LONG).show();
            }
        }
    }

}
