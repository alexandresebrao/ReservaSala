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
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.R;
import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateHorarios extends AppCompatActivity {

    Button btnSave;
    Button btnHorarioInicio;
    Button btnHorarioFim;
    Button btnProximoHora;
    Button btnProximoFim;

    TextView lblHorarioInicio;
    TextView lblHorarioFim;
    TextView lblInicioReservaSala;
    TextView lblFimReservaSala;
    TextView lblReservaSala;

    DatePicker datePicker;

    TimePicker timePicker;

    int dataSelecionada; // 1 - data Inicio 2 - data Fim

    Horarios horario;

    ParseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_horarios);
        btnSave = (Button) findViewById(R.id.btnSave);

        datePicker = (DatePicker) findViewById(R.id.datePicker);


        lblHorarioInicio = (TextView) findViewById(R.id.lblHorarioInicio);
        lblHorarioFim = (TextView) findViewById(R.id.lblHorarioFim);
        lblInicioReservaSala = (TextView) findViewById(R.id.lblInicioReservaSala);
        lblFimReservaSala = (TextView) findViewById(R.id.lblFimReservaSala);
        lblReservaSala = (TextView) findViewById(R.id.lblReservaSala);

        btnProximoHora = (Button) findViewById(R.id.btnProximoHoraInicio);
        btnProximoFim = (Button) findViewById(R.id.btnProximoFim);
        btnHorarioInicio = (Button) findViewById(R.id.btnHorarioInicio);
        btnHorarioFim = (Button) findViewById(R.id.btnHorarioFim);

        btnSave.setVisibility(View.INVISIBLE);
        btnHorarioFim.setVisibility(View.INVISIBLE);

        timePicker = (TimePicker) findViewById(R.id.timePicker);


        lblHorarioInicio.setText("");
        lblHorarioFim.setText("");

        currentUser = ParseUser.getCurrentUser();
        horario = new Horarios(currentUser.getObjectId(),"sala");
    }

    private void camadaText(int valor) {
        lblReservaSala.setVisibility(valor);
        lblInicioReservaSala.setVisibility(valor);
        lblHorarioInicio.setVisibility(valor);
        lblFimReservaSala.setVisibility(valor);
        lblHorarioFim.setVisibility(valor);
        btnHorarioInicio.setVisibility(valor);
        btnHorarioFim.setVisibility(valor);

        if (horario.verificaExistenciaDeHorarioValido()) {
            btnSave.setVisibility(valor);
        } else {
            btnSave.setVisibility(View.INVISIBLE);
        }
    }

    public void escolherDataInicio(View v) {
        dataSelecionada = 1;
        camadaText(View.INVISIBLE);
        datePickerVisible(View.VISIBLE);
    }

    public void escolherDataFim(View v) {
        dataSelecionada = 2;
        camadaText(View.INVISIBLE);
        datePickerVisible(View.VISIBLE);
    }

    public void escolherHora(View v) {
        datePickerVisible(View.INVISIBLE);
        timePickerVisible(View.VISIBLE);
    }

    private void datePickerVisible(int valor) {
        datePicker.setVisibility(valor);
        btnProximoHora.setVisibility(valor);
    }

    private void timePickerVisible(int valor) {
        btnProximoFim.setVisibility(valor);
        timePicker.setVisibility(valor);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void finalizarEscolha(View v) throws ParseException {
        timePickerVisible(View.INVISIBLE);
        String year = String.valueOf(datePicker.getYear());
        String month = String.valueOf(datePicker.getMonth() + 1);
        String day = String.valueOf(datePicker.getDayOfMonth());

        String hour = String.valueOf(timePicker.getCurrentHour());
        String minute = String.valueOf(timePicker.getCurrentMinute());



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String toParse = String.format("%s-%s-%s %s:%s", year, month, day, hour, minute);
        Date data = sdf.parse(toParse);
        if (dataSelecionada == 1) {
            horario.setDataInicio(data);
            lblHorarioInicio.setText(horario.getDataInicioString());
        } else {
            if (horario.setDataFim(data)) {
                lblHorarioFim.setText(horario.getDataFimString());
            }
            else {
                Toast.makeText(this,"Horario de Fim Inválido, verifique", Toast.LENGTH_LONG).show();
            }

        }
        camadaText(View.VISIBLE);
    }

}
