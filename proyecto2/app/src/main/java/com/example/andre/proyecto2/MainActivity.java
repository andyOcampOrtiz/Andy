package com.example.andre.proyecto2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bfi, bff, bc;
    private EditText txtfi, txtff, txtsc, txtps, txtedad;
    private int diai, mesi, anoi, diaf, mesf, anof;
    private TextView textresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bfi=(Button)findViewById(R.id.bfi);
        bff=(Button)findViewById(R.id.bff);
        bc=(Button)findViewById(R.id.bc);
        txtfi=(EditText)findViewById(R.id.txtfi);
        txtfi.setEnabled(false);
        txtff=(EditText)findViewById(R.id.txtff);
        txtff.setEnabled(false);
        txtsc=(EditText)findViewById(R.id.txtsc);
        txtps=(EditText)findViewById(R.id.txtps);
        txtedad=(EditText)findViewById(R.id.txtedad);
        textresult=(TextView)findViewById(R.id.textresult);

        bfi.setOnClickListener(this);
        bff.setOnClickListener(this);
        obtener();

    }
    public void obtener(){

    bc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (txtff.getText().toString().isEmpty() || txtfi.getText().toString().isEmpty() || txtps.getText().toString().isEmpty() || txtsc.getText().toString().isEmpty() || txtedad.getText().toString().isEmpty() ) {
                Toast.makeText(MainActivity.this, "Alguna Caja de Texto esta vacia", Toast.LENGTH_SHORT).show();
            }else {
                int mm=Integer.parseInt(txtsc.getText().toString());
                int mm2=Integer.parseInt(txtedad.getText().toString());
                double mm3=Double.parseDouble(txtps.getText().toString());
                double porcentage=0;
                double salariominimo=88.36;
                double gruposalarial=mm3/salariominimo;
                double inclementoanual=(mm-500)/52;
                double factoractualizacion=1.11;
                double cuantiabasica=13;
                double factorincremento=2.45;
                double a=((factorincremento*inclementoanual)+cuantiabasica)/100;
                double b=mm3*a*30*factoractualizacion;
                double resultado=0;
                String result;
                if(mm<500){
                Toast.makeText(MainActivity.this, "Necesitas almenos 500 semanas para poder cotizar", Toast.LENGTH_SHORT).show();
            }else{ if(mm2<60){
                Toast.makeText(MainActivity.this, "Necesitas tener almenos 60 años de edad", Toast.LENGTH_SHORT).show();
            }else {
                if(mm2==60){
                    porcentage=.75;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();
                }else { if(mm2==61){
                    porcentage=0.80;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();

                }else { if(mm2==62){
                    porcentage=0.85;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();

                }else { if(mm2==63){
                    porcentage=0.90;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();

                } else { if(mm2==64){
                    porcentage=0.95;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();

                }else {
                    porcentage=1.0;
                    resultado=b*porcentage;
                    result=String.valueOf(resultado);
                    textresult.setText("Recibiras $"+result+" mensualmente");
                    Toast.makeText(MainActivity.this, "Recibiras $"+result+" mensualmente", Toast.LENGTH_SHORT).show();

                }

                }

                }

                }

                }
            }

            }

            }

        }
    });

    }





    @Override
    public void onClick(View v) {
        if(v==bfi){
            final Calendar c=Calendar.getInstance();
            diai=c.get(Calendar.DAY_OF_MONTH);
            mesi=c.get(Calendar.MONTH);
            anoi=c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialig = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month+=1;
                    if(year<=1997){
                        if(year==1997){
                            if(month<=7){
                                if(month==7){
                                    if(dayOfMonth==1){
                                        txtfi.setText(dayOfMonth+"/"+month+"/"+year);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Fecha de inicio Fuera de rango", Toast.LENGTH_SHORT).show();
                                        txtfi.setText("");
                                    }
                                }else {
                                    txtfi.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                                }
                            }else{
                                txtfi.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                            }
                        }else {
                            txtfi.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Fecha de inicio Fuera de rango", Toast.LENGTH_SHORT).show();
                        txtfi.setText("");

                    }
/////////////////////////////////////////////////////////////////////////////////////////////////

                }
            }, diai, mesi, anoi);
            datePickerDialig.show();
        }
        if(v==bff){
            final Calendar c=Calendar.getInstance();
            diaf=c.get(Calendar.DAY_OF_MONTH);
            mesf=c.get(Calendar.MONTH);
            anof=c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialig = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    int dias = 0;
                    Date FechaA;
                    Date FechaU;
                    month += 1;
                    String fec = dayOfMonth + "/" + month + "/" + year;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
                    try {
                        FechaU = simpleDateFormat.parse(fec);
                        FechaA = new Date(System.currentTimeMillis());
                        System.out.println(FechaA + "-----" + FechaU);
                        dias = (int) ((FechaA.getTime() - FechaU.getTime()) / 86400000);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String gh = Integer.toString(dias);
                    if (dias <= 90) {
                        txtff.setText(fec);
                    } else {
                        Toast.makeText(MainActivity.this, "dejaste de cotizar hace más de 90 dias sorry", Toast.LENGTH_SHORT).show();
                    }
                }
            }, diaf, mesf, anof);
            datePickerDialig.show();
        }
    }
}
