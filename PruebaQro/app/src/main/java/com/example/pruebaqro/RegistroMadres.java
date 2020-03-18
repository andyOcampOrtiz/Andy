package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroMadres extends AppCompatActivity {

    Button registrarmom;
    EditText nombremadre,codigomadre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_madres);
        registrarmom=(Button) findViewById(R.id.button12);
        nombremadre=(EditText)findViewById(R.id.editText14);
        codigomadre=(EditText)findViewById(R.id.editText13);
    }

    public void registt(View view){
        realizar();
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
        finish();
    }
    private void realizar(){
        Basededatos bd=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=bd.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_madres",codigomadre.getText().toString());
        values.put("nombreMadre",nombremadre.getText().toString());


        Long resultante=basededatos.insert("madres","id_madres",values);
        basededatos.close();
    }
}
