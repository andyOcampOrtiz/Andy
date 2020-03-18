package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroEscuela extends AppCompatActivity {

    Button registrar;
    EditText nombreesc,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_escuela);
        registrar=(Button) findViewById(R.id.button4);
        nombreesc=(EditText) findViewById(R.id.editText5);
        id=(EditText) findViewById(R.id.editText4);

    }

    public void registrarescuela(View view){

        registrar();
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
        finish();
    }

    private void registrar() {
        Basededatos bd=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=bd.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_esc",id.getText().toString());
        values.put("nombre_esc",nombreesc.getText().toString());

        Long resultante=basededatos.insert("escuela","id_esc",values);
        basededatos.close();

    }
}
