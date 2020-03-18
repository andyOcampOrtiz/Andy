package com.example.pruebahubox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    String result="";
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1=(Button) findViewById(R.id.button);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a=(int)(Math.random()*10+1);
                if(a>=1 && a<=5){
                    result="Aguila";
                }else{
                    result="Sol";
                }

                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("resultado",result);
                startActivity(intent);
                finish();

            }
        });
    }
}
