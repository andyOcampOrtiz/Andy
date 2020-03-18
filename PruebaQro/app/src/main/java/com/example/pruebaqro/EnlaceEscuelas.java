package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnlaceEscuelas extends AppCompatActivity {

    Button enlazar;
    EditText codigoalum,codigoescuela,codigoenlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlace_escuelas);
        enlazar=(Button) findViewById(R.id.button8);
        codigoalum=(EditText) findViewById(R.id.editText9);
        codigoescuela=(EditText) findViewById(R.id.editText10);
        codigoenlace=(EditText) findViewById(R.id.editText12);



    }
    public void enlazar(View view) {
        registroo();
        Toast.makeText(this,"enlace exitoso", Toast.LENGTH_LONG).show();
        finish();
    }

    private void registroo() {
        Basededatos bd=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=bd.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_alum",codigoalum.getText().toString());
        values.put("id_escuela",codigoescuela.getText().toString());
        values.put("id_no",codigoenlace.getText().toString());

        Long resultante=basededatos.insert("noescuela","id_no",values);
        basededatos.close();

    }
}
