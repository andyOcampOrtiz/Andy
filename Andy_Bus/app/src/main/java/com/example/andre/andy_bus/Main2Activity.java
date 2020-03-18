package com.example.andre.andy_bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    Button btnregistrar;
    EditText txtnombre, txtappaterno, txtapmaterno, txtcorreoreg, txtpasswordreg, txtnumerotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnregistrar=(Button)findViewById(R.id.btnregistro);
        txtnombre=(EditText)findViewById(R.id.editnombre);
        txtappaterno=(EditText)findViewById(R.id.editappaterno);
        txtapmaterno=(EditText)findViewById(R.id.editapmaterno);
        txtcorreoreg=(EditText)findViewById(R.id.editcorreoreg);
        txtpasswordreg=(EditText)findViewById(R.id.editpasswordreg);
        txtnumerotel=(EditText)findViewById(R.id.editnumerotel);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtnombre.getText().toString().equals("") || txtappaterno.getText().toString().equals("") || txtapmaterno.getText().toString().equals("") || txtcorreoreg.getText().toString().equals("") || txtpasswordreg.getText().toString().equals("") || txtnumerotel.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "algun campo esta vacio", Toast.LENGTH_SHORT).show();
                }else {
                    ejecutarServicio("http://192.168.137.1:80/aplicacion/registro.php");
                    Intent reg = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(reg);
                    finish();

                }
            }
        });
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Toast.makeText(getApplicationContext(), "excelent", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
              Map<String,String> parametros=new HashMap<String, String>();
              parametros.put("name",txtnombre.getText().toString().trim());
                parametros.put("appaterno",txtappaterno.getText().toString().trim());
                parametros.put("apmaterno",txtapmaterno.getText().toString().trim());
                parametros.put("correo",txtcorreoreg.getText().toString().trim());
                parametros.put("password",txtpasswordreg.getText().toString().trim());
                parametros.put("numtel",txtnumerotel.getText().toString().trim());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
