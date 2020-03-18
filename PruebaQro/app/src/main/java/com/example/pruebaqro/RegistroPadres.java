package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroPadres extends AppCompatActivity {

    Button registrar;
    EditText nombrepadre,nombremadre,idp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_padres);
        registrar=(Button) findViewById(R.id.button6);

        nombrepadre=(EditText) findViewById(R.id.editText2);
        idp=(EditText) findViewById(R.id.editText6);
    }


    public void registrarpadre(View view){
        registro();
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
        finish();
    }



    private void registro() {
        Basededatos bd=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=bd.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_padres",idp.getText().toString());
        values.put("nombrePadre",nombrepadre.getText().toString());


        Long resultante=basededatos.insert("padres","id_padres",values);
        basededatos.close();

    }

}
