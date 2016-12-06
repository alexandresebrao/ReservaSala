package com.example.aluno.projetoreservasala.Views;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.example.aluno.projetoreservasala.R;

import java.util.Calendar;


public class CreateHorariosBeta extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener, RadialTimePickerDialogFragment.OnTimeSetListener, RadialTimePickerDialogFragment.OnDialogDismissListener, CalendarDatePickerDialogFragment.OnDialogDismissListener {

    private static final String FRAG_TAG_TIME_PICKER = "2";
    private static final String FRAG_TAG_DATE_PICKER = "1";

    String year;
    String month;
    String day;
    String tipoHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_horarios_beta);
        Button btnCalendar = (Button) findViewById(R.id.btnEscolherInicioBeta);

    }

    public void setHorarioInicio(View v) {
        tipoHorario = "inicio";
        setDate();

    }

    public void setHorarioFim(View v) {
        tipoHorario = "fim";
        setDate();
    }

    private void setDate(){
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(CreateHorariosBeta.this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setOnDismissListener(CreateHorariosBeta.this)
                .setDoneText("Próximo");


        cdp.setCancelable(false);
        cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
    }

    private void setHour(){
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(CreateHorariosBeta.this)
                .setDoneText("Próximo")
                .setForced24hFormat()
                .setOnDismissListener(CreateHorariosBeta.this);



        rtpd.setCancelable(false);
        rtpd.show(getSupportFragmentManager(), FRAG_TAG_TIME_PICKER);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        setHour();
    }


    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {

    }

    @Override
    public void onDialogDismiss(DialogInterface dialoginterface) {
        Toast.makeText(this,"Escolha de horário cancelada",Toast.LENGTH_LONG).show();
    }
}
