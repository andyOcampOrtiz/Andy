package com.example.pruebaqro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Consultas extends AppCompatActivity {

    EditText nombrealum;
    Button consultar;
    ListView lista;
    ArrayList<String> listainfo;
    ArrayList<Alumnos> listaAlumnos;
    ArrayList<Padres> listaPadres;
    ArrayList<Escuelas> listaEscuelas;
    ArrayList<Madres> listaMadres;
    Basededatos bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        nombrealum = (EditText) findViewById(R.id.editText11);
        consultar=(Button) findViewById(R.id.button10);
        lista=(ListView) findViewById(R.id.lista1);

        bd=new Basededatos(this,"bdprueba",null,1);
    }
    public void enlazar(View view) {
        buscar();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainfo);
        lista.setAdapter(adapter);
    }

    private void buscar(){
        SQLiteDatabase db=bd.getReadableDatabase();
        Alumnos alumnos=null;
        Padres padres=null;
        Escuelas escuelas=null;
        Madres madres=null;
        listaAlumnos=new ArrayList<Alumnos>();
        listaPadres=new ArrayList<Padres>();
        listaEscuelas=new ArrayList<Escuelas>();
        listaMadres=new ArrayList<Madres>();



        Cursor cursor=db.rawQuery("Select * from alumnos where nombreAlumno='"+nombrealum.getText().toString()+"'",null);


int id2=0;
int id3=0;
int idmadre=0;
int idpat=0;
String pat ="",mad="";
int idesc=0, idesc2=0;
int contable=0;
        try {
            while (cursor.moveToNext()) {

              id2=cursor.getInt(0);
              id3=cursor.getInt(2);
              idmadre=cursor.getInt(3);

            }

          //  System.out.println("Id del alumno es: -----------"+id2);
          //  System.out.println("Id del padre es: -----------"+id3);
            Cursor cursor1=db.rawQuery("Select * from alumnos where id_alumno='"+id2+"'",null);
            Cursor cursor2=db.rawQuery("Select * from padres where id_padres='"+id3+"'",null);
            Cursor cursor3=db.rawQuery("Select * from noescuela where id_alum='"+id2+"' ",null);
            Cursor cursor5=db.rawQuery("Select * from madres where id_madres='"+idmadre+"' ",null);
            try{
                while (cursor3.moveToNext()){
                    idesc=cursor3.getInt(2);
                  //  idesc2=cursor3.getInt(5);
                    contable++;
                }
                System.out.println("Numero de escuelas---------"+contable+"-------------"+idesc+"---------");


                Cursor cursor4=db.rawQuery("Select * from escuela where id_esc='"+idesc+"'",null);
               // System.out.println("Id de la escuela es: -----------"+idesc);
                try{
                    while (cursor4.moveToNext()){
                        escuelas=new Escuelas();
                        escuelas.setId_esc(cursor4.getInt(0));
                        escuelas.setNombre(cursor4.getString(1));
                        listaEscuelas.add(escuelas);
                    }
                }catch (Exception e){
                    Toast.makeText(this,"Consulta invalida",Toast.LENGTH_LONG).show();
                }
                while (cursor1.moveToNext()) {
                    alumnos = new Alumnos();
                    alumnos.setIdalumnos(cursor1.getInt(0));
                    alumnos.setNombreA(cursor1.getString(1));
                    alumnos.setIdpadres(cursor1.getInt(2));
                    alumnos.setIdmadres(cursor1.getInt(3));
             /*   padres.setId_pad(cursor.getInt(0));
                padres.setNombrepadre(cursor.getString(1));
                padres.setNombreMadre(cursor.getString(2));
*/
                    listaAlumnos.add(alumnos);
                }
                while (cursor2.moveToNext()){
                    padres=new Padres();
                    padres.setId_pad(cursor2.getInt(0));
                    padres.setNombrepadre(cursor2.getString(1));
                    idpat=cursor2.getInt(0);
                    pat=cursor2.getString(1);

                    listaPadres.add(padres);
                }
                while (cursor5.moveToNext()){
                    madres=new Madres();
                    madres.setId_mad(cursor5.getInt(0));
                    madres.setNombremadre(cursor5.getString(1));
                    listaMadres.add(madres);

                }

              //  System.out.println("Idpadre="+idpat+" ----------nombre "+pat+" ---------nombrema "+mad+"");

            }catch (Exception e){
                Toast.makeText(this,"Consulta invalida",Toast.LENGTH_LONG).show();
            }

            listainfo = new ArrayList<String>();
            for (int i = 0; i < listaAlumnos.size(); i++) {
                listainfo.add(listaAlumnos.get(i).getIdalumnos() + "-" + listaAlumnos.get(i).getNombreA() + "-" + listaAlumnos.get(i).getIdpadres()+"-"+listaAlumnos.get(i).getIdmadres());
            }
            for (int i = 0; i < listaPadres.size(); i++) {
                listainfo.add(listaPadres.get(i).getId_pad() + "-" + listaPadres.get(i).getNombrepadre());
            }
            for (int i = 0; i < listaEscuelas.size(); i++) {
                listainfo.add(listaEscuelas.get(i).getId_esc() + "-" + listaEscuelas.get(i).getNombre());
            }
            for (int i = 0; i < listaMadres.size(); i++) {
                listainfo.add(listaMadres.get(i).getId_mad() + "-" + listaMadres.get(i).getNombremadre());
            }
        }catch (Exception e){
            Toast.makeText(this,"Consulta invalida",Toast.LENGTH_LONG).show();
        }

    }
}
