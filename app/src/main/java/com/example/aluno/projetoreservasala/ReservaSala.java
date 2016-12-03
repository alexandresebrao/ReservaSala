package com.example.aluno.projetoreservasala;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by aluno on 01/12/16.
 */
public class ReservaSala extends Application{
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("APPLICATION_ID")
        .clientKey(null).server("http://192.168.25.6:1337/parse/").build()
        );
    }
}
