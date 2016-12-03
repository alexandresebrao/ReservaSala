package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
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

public class ListaSalas extends AppCompatActivity {
    ParseUser currentUser = ParseUser.getCurrentUser();
    ListView listaSalas;
    ArrayList<Sala> salas = new ArrayList<>();
    SalasAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salas);
        listaSalas = (ListView) findViewById(R.id.lstSalas);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Sala");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                if (e == null) {

                    for (Iterator<ParseObject> i = parseObjects.iterator(); i.hasNext(); ) {
                        ParseObject object = i.next();
                        Sala item = new Sala();
                        item.get(object.getObjectId());
                        salas.add(item);
                    }
                    adapter = new SalasAdapter(getApplicationContext(), R.layout.activity_lista_salas, salas);
                    listaSalas.setAdapter(adapter);

                } else {
                    Log.d("ERROR:", "" + e.getMessage());
                }
            }

        });

    }



    public List<Sala> getSalas() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Salas");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    for (Iterator<ParseObject> i = objects.iterator(); i.hasNext(); ) {
                        ParseObject object = i.next();
                        Sala sala = new Sala(object.getString("nome"));
                        salas.add(sala);
                    }
                }
            }
        });
        return salas;
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


            }
        }
    }
}
