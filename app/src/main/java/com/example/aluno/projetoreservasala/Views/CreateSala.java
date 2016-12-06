package com.example.aluno.projetoreservasala.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.projetoreservasala.Objetos.Sala;
import com.example.aluno.projetoreservasala.R;
import com.parse.ParseUser;

public class CreateSala extends AppCompatActivity {
    ParseUser currentUser = ParseUser.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_sala);
    }

    public void saveSala(View v) {
        EditText salaNome = (EditText) findViewById(R.id.txtSalaNome);
        String salanome = salaNome.getText().toString();
        if (salanome.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),"Nome não pode ser branco",Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            returnIntent.putExtra("nome",salaNome.getText().toString());
            finish();
        }
    }
}
