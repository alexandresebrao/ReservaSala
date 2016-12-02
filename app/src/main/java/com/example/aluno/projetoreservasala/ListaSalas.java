package com.example.aluno.projetoreservasala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.aluno.projetoreservasala.Adaptadores.SalasAdapter;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ListaSalas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salas);

        ListView yourListView = (ListView) findViewById(R.id.lstSalas);

        // get data from the table by the ListAdapter

        List<Sala> salas = getSalas();
        ListAdapter salasAdapter = new SalasAdapter(this, R.layout.itemsala, salas);

        yourListView .setAdapter(salasAdapter);

    }

    public List<Sala> getSalas() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Salas");
        final List<Sala> salas = null;
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        Sala sala = new Sala(object.getString("usuario").toString(), object.getString("nome").toString());
                        salas.add(sala);
                    }
                }
            }
        });
        return salas;
    }
}
