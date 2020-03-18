package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button registro_alum, registro_esc,registro_padres,enlazarescalum, consultar,registrar_madres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registro_alum=(Button) findViewById(R.id.button);
        registro_esc=(Button) findViewById(R.id.button2);
        registro_padres=(Button) findViewById(R.id.button5) ;
        enlazarescalum=(Button) findViewById(R.id.button7);
        consultar=(Button) findViewById(R.id.button9);
        registrar_madres=(Button) findViewById(R.id.button11);

        registro_alum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MainActivity.this, Registroalumnos.class);
                startActivity(a);

            }
        });

        registro_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(MainActivity.this, RegistroEscuela.class);
                startActivity(b);

            }
        });

        registro_padres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c=new Intent(MainActivity.this,RegistroPadres.class);
                startActivity(c);
            }
        });
        enlazarescalum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d=new Intent(MainActivity.this,EnlaceEscuelas.class);
                startActivity(d);
            }
        });
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e=new Intent(MainActivity.this,Consultas.class);
                startActivity(e);
            }
        });

        registrar_madres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f=new Intent(MainActivity.this,RegistroMadres.class);
                startActivity(f);
            }
        });
    }
}
