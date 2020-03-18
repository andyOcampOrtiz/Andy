package com.example.pruebahubox;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button boton2;
    TextView texto;
    String result2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        boton2=(Button) findViewById(R.id.button2);
        texto=(TextView) findViewById(R.id.textView);
        setSupportActionBar(toolbar);

        Intent intent3=getIntent();
        Bundle extra= intent3.getExtras();
        result2=extra.getString("resultado");
        texto.setText(result2);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }

}
