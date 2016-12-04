package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.aluno.projetoreservasala.Adaptadores.SalasAdapter;
import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListSalas extends AppCompatActivity implements SalasAdapter.SalasAdapterCallback {
    ParseUser currentUser = ParseUser.getCurrentUser();
    ListView listaSalas;
    ArrayList<Sala> salas = new ArrayList<>();
    SalasAdapter adapter;
    ListView listView;


    public void getSalas() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Salas");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                if (e == null) {

                    for (ParseObject object : parseObjects ) {

                        Sala item = new Sala(object.getObjectId(),object.getString("nome"));
                        salas.add(item);
                    }


                } else {
                    Log.d("ERROR:", "" + e.getMessage());
                }

            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salas);
        listaSalas = (ListView) findViewById(R.id.lstSalas);
        getSalas();
        adapter = new SalasAdapter(getApplicationContext(), R.layout.activity_lista_salas, salas);
        adapter.setCallback(ListSalas.this);
        listaSalas.setAdapter(adapter);

    }




    public void cadastrarSala(View v) {
        Intent intent = new Intent(this,CriarSala.class);
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String nome = data.getExtras().getString("nome");
                Sala sala = new Sala(nome);
                sala.save();
                salas.add(sala);
                salas = new ArrayList<>();
                getSalas();
                adapter.notifyDataSetChanged();


            }
        }
    }

    public void viewSala(String id) {
        for (Sala sala : salas) {
            if (sala.getSalaId().equals(id)) {
                Intent intent = new Intent(this,ViewSala.class);
                intent.putExtra("sala_id",sala.getSalaId());
                intent.putExtra("sala_nome",sala.getSalaNome());
                startActivityForResult(intent,1);
            }
        }
    }
}
