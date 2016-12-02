package com.example.aluno.projetoreservasala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

public class FirstActivity extends AppCompatActivity {
    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        currentUser = ParseUser.getCurrentUser();
        currentUser.getUsername();
        String bemvindo = "Hello " + currentUser.getUsername().toString();
        TextView welcome = (TextView) findViewById(R.id.lblWelcome);
        welcome.setText(bemvindo);
    }

    public void verSalas(View v) {
        Intent intent = new Intent(this, ListaSalas.class);
        startActivity(intent);
    }



    public void onClickLogout(View v) {
        currentUser.logOut();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
