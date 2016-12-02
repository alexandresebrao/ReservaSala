package com.example.aluno.projetoreservasala.Adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;

import java.util.List;

/**
 * Created by xandizitxu on 02/12/16.
 */

public class SalasAdapter extends ArrayAdapter<Sala> {

    public SalasAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public SalasAdapter(Context context, int resource, List<Sala> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemsala, null);
        }

        Sala p = getItem(position);

        if (p != null) {
            TextView lblSalaNome = (TextView) v.findViewById(R.id.lblSalaNome);
            Button btnSalaHorario = (Button) v.findViewById(R.id.btnSalaHorario);
            Button btnSalaReservar = (Button) v.findViewById(R.id.btnSalaReservar);

            if (lblSalaNome != null) {
                lblSalaNome.setText(p.getSalaNome());
            }
        }

        return v;
    }
}