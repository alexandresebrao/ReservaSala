package com.example.aluno.projetoreservasala;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by aluno on 01/12/16.
 */
public class ReservaSala extends Application{
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0IkKl7Bdd5GVnDvFG3CWwG8QAxffgdKGaMo85LzE")
        .clientKey("8YYcp07DjXvjB1thh2DufIT1RIsyKJ4CHN8hzxqu")
        .server("https://parseapi.back4app.com/").build()
        );
    }
}
