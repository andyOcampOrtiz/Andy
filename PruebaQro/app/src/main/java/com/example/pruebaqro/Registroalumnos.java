package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registroalumnos extends AppCompatActivity {

    Button registrar,actualizar,eliminar;
    EditText nombrealum,idalum,idpad,idmad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroalumnos);
        registrar=(Button)findViewById(R.id.button3);
        nombrealum=(EditText) findViewById(R.id.editText);
        idalum=(EditText) findViewById(R.id.editText7);
        idpad=(EditText) findViewById(R.id.editText8);
        idmad=(EditText) findViewById(R.id.editText30);
        actualizar=(Button) findViewById(R.id.button13);
        eliminar=(Button) findViewById(R.id.button14);
    }

    public void actualizaralimno(View view){
     Basededatos db=new Basededatos(this,"bdprueba",null,1);
     SQLiteDatabase basededatos=db.getWritableDatabase();
     String parametro=(idalum.getText().toString());
     ContentValues values=new ContentValues();
     values.put("nombreAlumno",nombrealum.getText().toString());
     values.put("idPadres",idpad.getText().toString());
     values.put("idMadre",idmad.getText().toString());
     String campo="id_alumno";
    basededatos.update("alumnos",values, campo+"=?", new String[]{parametro});
     Toast.makeText(this, "Actualizado",Toast.LENGTH_LONG).show();
     db.close();
    }

    public void eliminaralumno(View view){
        Basededatos db=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=db.getWritableDatabase();
        String parametros=(idalum.getText().toString());
        String campo="id_alumno";
        basededatos.delete("alumnos",campo+"=?", new String[]{parametros});
        //db.delete("alumnos",values, campo+"=?",parametro);
        Toast.makeText(this, "eliminado",Toast.LENGTH_LONG).show();
        db.close();
    }

    public void registraralumno(View view){
        registrarr();
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
        finish();
    }

    private void registrarr() {
        Basededatos bd=new Basededatos(this,"bdprueba",null,1);
        SQLiteDatabase basededatos=bd.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_alumno",idalum.getText().toString());
        values.put("nombreAlumno",nombrealum.getText().toString());
        values.put("idPadres",idpad.getText().toString());
        values.put("idMadre",idmad.getText().toString());

        Long resultante=basededatos.insert("alumnos","id_alumno",values);
        basededatos.close();

    }

}
