package com.example.aluno.projetoreservasala.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.projetoreservasala.Objetos.Horarios;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xandizitxu on 07/12/16.
 */

public class HorariosAdapter extends ArrayAdapter<Horarios> {

    public HorariosAdapter(Context context, int resource, List<Horarios> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemhorario, null);
        }

        final Horarios p = getItem(position);

        if (p != null) {
            TextView lblHorario = (TextView) v.findViewById(R.id.lblHorarioItem);
            TextView lblReservadoPara = (TextView) v.findViewById(R.id.lblReservaUsuario);
            lblHorario.setText(p.getDataInicioString() + " - " + p.getDataFimString());
            lblReservadoPara.setText("Reservado para: " + p.getUsuario());
        }

        return v;
    }

}
