package com.example.aluno.projetoreservasala;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by aluno on 01/12/16.
 */
public class ReservaSala extends Application{
    public void onCreate() {
//        Para Servidor Local (Desenvolvimento)

//        super.onCreate();
//        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("APPLICATION_ID")
//        .clientKey(null).server("http://192.168.25.6:1337/parse/").build()
//        );

//      Para Versão Release ( Produção )
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("Pz4UEHg2wC0JJEXCJmpsj2zb17b6ONsw0K8KXGtX")
                .clientKey("uPZk6eOa27yd1M1ErfScK9vku72f21z7x8gjJ4sO").server("https://parseapi.back4app.com/").build()
        );
    }
}
