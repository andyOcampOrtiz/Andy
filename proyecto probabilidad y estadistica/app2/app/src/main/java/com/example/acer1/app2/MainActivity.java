package com.example.acer1.app2;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.acer1.app2.R.id.editText;
import static com.example.acer1.app2.R.id.editText4;
import static com.example.acer1.app2.R.id.editText3;
import static com.example.acer1.app2.R.id.editText5;

public class MainActivity extends AppCompatActivity {
public Button mediaa,mas,reinicio,medianaa,variacionn,desviacionn,modaa,mediadefrecuencia,graficar,pendiente,sesgo,sesgonuevo,apuntamiento;
    public EditText cadena,resultado,listacadena,editnumero,editfrecuencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaa=(Button) findViewById(R.id.button1);
        medianaa=(Button) findViewById(R.id.button);
        variacionn=(Button) findViewById(R.id.button2);
        desviacionn=(Button) findViewById(R.id.button3);
        sesgo=(Button) findViewById(R.id.button15);
        sesgonuevo=(Button) findViewById(R.id.button16);
        apuntamiento=(Button) findViewById(R.id.button17);
        modaa=(Button) findViewById(R.id.button4);
        graficar=(Button) findViewById(R.id.button6);
        mas=(Button) findViewById(R.id.button12);
        reinicio=(Button) findViewById(R.id.button13);
        mediadefrecuencia=(Button) findViewById(R.id.button5);
        pendiente=(Button) findViewById(R.id.button8);
        cadena=(EditText) findViewById(editText4);
        resultado=(EditText) findViewById(editText);
        resultado.setInputType(InputType.TYPE_NULL);
        listacadena=(EditText) findViewById(editText3);
        listacadena.setInputType(InputType.TYPE_NULL);
        editnumero=(EditText) findViewById(editText4);
        editfrecuencia=(EditText) findViewById(editText5);

        reinicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editfrecuencia.setText("");
                editnumero.setText("");
                resultado.setText("");
                listacadena.setText("");

            }
        });

        pendiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent pendientee = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(pendientee);

            }
        });


        graficar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    String cadenax = listacadena.getText().toString();
                    Intent graficar = new Intent(MainActivity.this, Main2Activity.class);
                    graficar.putExtra("DATO", cadenax);
                    startActivity(graficar);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos para graficar", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(editnumero.getText().toString().equals(".")||editfrecuencia.getText().toString().equals(".")){
                    editnumero.setText("");
                    editfrecuencia.setText("");
                }
                else {
                    if (!editnumero.getText().toString().equals("")&&!editfrecuencia.getText().toString().equals("")) {
                        ////////
                        int numfrecuencia=Integer.parseInt(editfrecuencia.getText().toString());
                        for(int q=0;q<numfrecuencia;q++) {
                            if (!listacadena.getText().toString().equals("")) {
                                listacadena.setText(listacadena.getText().toString() + "+" + editnumero.getText().toString());

                            } else {
                                listacadena.setText(editnumero.getText().toString());

                            }
                        }
                        ///////
                        editnumero.setText("");
                        editfrecuencia.setText("");
                    }
                    else {

                    }
                }
            }
        });


        mediaa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepite = 0;
                    double moda = 0;

                    for (i = 0; i < ghf.length; i++) {
                        int vecesQueSeRepite = 0;
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepite++;
                        }
                        if (vecesQueSeRepite > maximaVecesQueSeRepite) {
                            moda = ghf[i];
                            maximaVecesQueSeRepite = vecesQueSeRepite;

                        }

                    }


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(moda);//moda

                    resultado.setText(qh);


                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        medianaa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }

//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }

                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepite = 0;
                    double moda = 0;

                    for (i = 0; i < ghf.length; i++) {
                        int vecesQueSeRepite = 0;
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepite++;
                        }
                        if (vecesQueSeRepite > maximaVecesQueSeRepite) {
                            moda = ghf[i];
                            maximaVecesQueSeRepite = vecesQueSeRepite;

                        }

                    }

                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(moda);//moda

                    resultado.setText(qw);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        variacionn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }

//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepite = 0;
                    double moda = 0;

                    for (i = 0; i < ghf.length; i++) {
                        int vecesQueSeRepite = 0;
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepite++;
                        }
                        if (vecesQueSeRepite > maximaVecesQueSeRepite) {
                            moda = ghf[i];
                            maximaVecesQueSeRepite = vecesQueSeRepite;

                        }

                    }

                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(moda);//moda

                    resultado.setText(qe);

                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        desviacionn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepite = 0;
                    double moda = 0;

                    for (i = 0; i < ghf.length; i++) {
                        int vecesQueSeRepite = 0;
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepite++;
                        }
                        if (vecesQueSeRepite > maximaVecesQueSeRepite) {
                            moda = ghf[i];
                            maximaVecesQueSeRepite = vecesQueSeRepite;

                        }

                    }

                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(moda);//moda

                    resultado.setText(qr);

                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modaa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepitee = 0;
                    double moda = 0;
                    int veces[] = new int[ppp];
                    double reales[] = new double[ppp];
                    double vecesnew[] = new double[ppp];
                    double perro[] = new double[ppp];
                    int vecesQueSeRepitee = 0;
                    int vces = 0;

                    for (i = 0; i < ghf.length; i++) {
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepitee++;
                        }
                        veces[i] = vecesQueSeRepitee;
                        vecesQueSeRepitee = 0;
                    }

                    for (i = 0; i < ghf.length; i++) {
                        //System.out.println(M[i]+" se repite "+veces[i]);
                    }

                    double inicio = 0 - 1;
                    int divisor = 0;


                    for (i = 0; i < ghf.length; i++) {
                        if (inicio != ghf[i]) {
                            vces++;
                            reales[i] = ghf[i];
                            vecesnew[i] = veces[i];
                            inicio = ghf[i];
                            perro[i] = 1;
                            divisor++;
                        } else {
                            perro[i] = 0;
                        }
                    }
                    double klp[] = new double[divisor];
                    double rty[] = new double[divisor];
                    int asd = 0;
                    for (i = 0; i < ghf.length; i++) {
                        if (perro[i] == 1) {
                            //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                            klp[asd] = reales[i];
                            rty[asd] = vecesnew[i];
                            asd++;
                        }
                    }

//
                    double mayor = 0;


                    for (i = 0; i < divisor; i++) {
                        if (rty[i] > mayor) {
                            mayor = rty[i];
                        }
                    }

                    String modisisima = "";
                    for (i = 0; i < divisor; i++) {
                        //System.out.println(klp[i]+" "+rty[i]);
                        if (rty[i] == mayor) {
                            //System.out.println(klp[i]+" "+rty[i]);
                            modisisima = modisisima + String.valueOf(klp[i]) + ",";
                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);


//media de datos frecuenciales
                    double mddff = 0;
                    double divdiv = 0;
                    for (i = 0; i < divisor; i++) {
                        mddff = mddff + (klp[i] * rty[i]);
                        divdiv = divdiv + rty[i];
                        //System.out.println(mddff+" "+divdiv);
                        //System.out.println(klp[i]+" "+rty[i]);
                    }
                    double mddf = mddff / divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion


                    resultado.setText(modisisima);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        mediadefrecuencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepitee = 0;
                    double moda = 0;
                    int veces[] = new int[ppp];
                    double reales[] = new double[ppp];
                    double vecesnew[] = new double[ppp];
                    double perro[] = new double[ppp];
                    int vecesQueSeRepitee = 0;
                    int vces = 0;

                    for (i = 0; i < ghf.length; i++) {
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepitee++;
                        }
                        veces[i] = vecesQueSeRepitee;
                        vecesQueSeRepitee = 0;
                    }

                    for (i = 0; i < ghf.length; i++) {
                        //System.out.println(M[i]+" se repite "+veces[i]);
                    }

                    double inicio = 0 - 1;
                    int divisor = 0;


                    for (i = 0; i < ghf.length; i++) {
                        if (inicio != ghf[i]) {
                            vces++;
                            reales[i] = ghf[i];
                            vecesnew[i] = veces[i];
                            inicio = ghf[i];
                            perro[i] = 1;
                            divisor++;
                        } else {
                            perro[i] = 0;
                        }
                    }
                    double klp[] = new double[divisor];
                    double rty[] = new double[divisor];
                    int asd = 0;
                    for (i = 0; i < ghf.length; i++) {
                        if (perro[i] == 1) {
                            //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                            klp[asd] = reales[i];
                            rty[asd] = vecesnew[i];
                            asd++;
                        }
                    }

//
                    double mayor = 0;


                    for (i = 0; i < divisor; i++) {
                        if (rty[i] > mayor) {
                            mayor = rty[i];
                        }
                    }

                    String modisisima = "";
                    for (i = 0; i < divisor; i++) {
                        //System.out.println(klp[i]+" "+rty[i]);
                        if (rty[i] == mayor) {
                            //System.out.println(klp[i]+" "+rty[i]);
                            modisisima = modisisima + String.valueOf(klp[i]) + ",";
                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);


//media de datos frecuenciales
                    double mddff = 0;
                    double divdiv = 0;
                    for (i = 0; i < divisor; i++) {
                        mddff = mddff + (klp[i] * rty[i]);
                        divdiv = divdiv + rty[i];
                        //System.out.println(mddff+" "+divdiv);
                        //System.out.println(klp[i]+" "+rty[i]);
                        //klp[] es numeros en x
                        //rty[] es frecuencia del numero
                    }
                    double mddf = mddff / divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(mddf);//media de frecuencia
                    //modisisima es la moda

                    resultado.setText(qt);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });




        sesgo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepitee = 0;
                    double moda = 0;
                    int veces[] = new int[ppp];
                    double reales[] = new double[ppp];
                    double vecesnew[] = new double[ppp];
                    double perro[] = new double[ppp];
                    int vecesQueSeRepitee = 0;
                    int vces = 0;

                    for (i = 0; i < ghf.length; i++) {
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepitee++;
                        }
                        veces[i] = vecesQueSeRepitee;
                        vecesQueSeRepitee = 0;
                    }

                    for (i = 0; i < ghf.length; i++) {
                        //System.out.println(M[i]+" se repite "+veces[i]);
                    }

                    double inicio = 0 - 1;
                    int divisor = 0;


                    for (i = 0; i < ghf.length; i++) {
                        if (inicio != ghf[i]) {
                            vces++;
                            reales[i] = ghf[i];
                            vecesnew[i] = veces[i];
                            inicio = ghf[i];
                            perro[i] = 1;
                            divisor++;
                        } else {
                            perro[i] = 0;
                        }
                    }
                    double klp[] = new double[divisor];
                    double rty[] = new double[divisor];
                    int asd = 0;
                    for (i = 0; i < ghf.length; i++) {
                        if (perro[i] == 1) {
                            //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                            klp[asd] = reales[i];
                            rty[asd] = vecesnew[i];
                            asd++;
                        }
                    }

//
                    double mayor = 0;


                    for (i = 0; i < divisor; i++) {
                        if (rty[i] > mayor) {
                            mayor = rty[i];
                        }
                    }

                    String modisisima = "";
                    float modatemporal = 0;
                    for (i = 0; i < divisor; i++) {
                        //System.out.println(klp[i]+" "+rty[i]);
                        if (rty[i] == mayor) {
                            //System.out.println(klp[i]+" "+rty[i]);
                            modisisima = modisisima + String.valueOf(klp[i]) + ",";
                            modatemporal = (float) klp[i];
                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);


//media de datos frecuenciales
                    double mddff = 0;
                    double divdiv = 0;
                    for (i = 0; i < divisor; i++) {
                        mddff = mddff + (klp[i] * rty[i]);
                        divdiv = divdiv + rty[i];
                        //System.out.println(mddff+" "+divdiv);
                        //System.out.println(klp[i]+" "+rty[i]);
                        //klp[] es numeros en x
                        //rty[] es frecuencia del numero
                    }
                    double mddf = mddff / divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    double ratat = (mddf - modatemporal) / desviacion;
                    float servotemporal = (float) ratat;

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(mddf);//media de frecuencia
                    String qy = String.valueOf(servotemporal);//servo temporal
                    //modisisima es la moda

                    resultado.setText(qy);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });



        sesgonuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepitee = 0;
                    double moda = 0;
                    int veces[] = new int[ppp];
                    double reales[] = new double[ppp];
                    double vecesnew[] = new double[ppp];
                    double perro[] = new double[ppp];
                    int vecesQueSeRepitee = 0;
                    int vces = 0;

                    for (i = 0; i < ghf.length; i++) {
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepitee++;
                        }
                        veces[i] = vecesQueSeRepitee;
                        vecesQueSeRepitee = 0;
                    }

                    for (i = 0; i < ghf.length; i++) {
                        //System.out.println(M[i]+" se repite "+veces[i]);
                    }

                    double inicio = 0 - 1;
                    int divisor = 0;


                    for (i = 0; i < ghf.length; i++) {
                        if (inicio != ghf[i]) {
                            vces++;
                            reales[i] = ghf[i];
                            vecesnew[i] = veces[i];
                            inicio = ghf[i];
                            perro[i] = 1;
                            divisor++;
                        } else {
                            perro[i] = 0;
                        }
                    }
                    double klp[] = new double[divisor];
                    double rty[] = new double[divisor];
                    int asd = 0;
                    for (i = 0; i < ghf.length; i++) {
                        if (perro[i] == 1) {
                            //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                            klp[asd] = reales[i];
                            rty[asd] = vecesnew[i];
                            asd++;
                        }
                    }

//
                    double mayor = 0;


                    for (i = 0; i < divisor; i++) {
                        if (rty[i] > mayor) {
                            mayor = rty[i];
                        }
                    }

                    String modisisima = "";
                    float modatemporal = 0;
                    for (i = 0; i < divisor; i++) {
                        //System.out.println(klp[i]+" "+rty[i]);
                        if (rty[i] == mayor) {
                            //System.out.println(klp[i]+" "+rty[i]);
                            modisisima = modisisima + String.valueOf(klp[i]) + ",";
                            modatemporal = (float) klp[i];
                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);


//media de datos frecuenciales
                    double mddff = 0;
                    double divdiv = 0;
                    for (i = 0; i < divisor; i++) {
                        mddff = mddff + (klp[i] * rty[i]);
                        divdiv = divdiv + rty[i];
                        //System.out.println(mddff+" "+divdiv);
                        //System.out.println(klp[i]+" "+rty[i]);
                        //klp[] es numeros en x
                        //rty[] es frecuencia del numero
                    }
                    double mddf = mddff / divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    double ratat = (mddf - modatemporal) / desviacion;
                    float servotemporal = (float) ratat;

                    //klp[] es numeros en x
                    //rty[] es frecuencia del numero
                    float momentodos=0;
                    float momentotres=0;
                    float momentocuatro=0;
                    float sumafrecuencias=0;

                    for(i=0;i<divisor;i++){
                        float unidadx=0;
                        float unidadfrec=(float) rty[i];
                        unidadx=(float) (klp[i]-mddf);
                        momentodos+=unidadfrec*(unidadx*unidadx);
                        momentotres+=unidadfrec*(unidadx*unidadx*unidadx);
                        momentocuatro+=unidadfrec*(unidadx*unidadx*unidadx*unidadx);
                        sumafrecuencias+=unidadfrec;

                    }
                    momentodos=momentodos/sumafrecuencias;
                    momentotres=momentotres/sumafrecuencias;
                    momentocuatro=momentocuatro/sumafrecuencias;
                    double kkkkkk=momentodos*momentodos*momentodos;
                    kkkkkk=Math.sqrt(kkkkkk);
                    float kkkk=(float) kkkkkk;
                    float sesgodos=momentotres/kkkk;
                    float apuntamientodedatos=(momentocuatro/(momentodos*momentodos))-3;

                    String momentodoss=""+momentodos;
                    String momentotress=""+momentotres;
                    String momentocuatroo=""+momentocuatro;

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(mddf);//media de frecuencia
                    String qy = String.valueOf(servotemporal);//servo temporal
                    String qu = String.valueOf(sesgodos);
                    //modisisima es la moda
                    String posicionsesgo="";
                    if(sesgodos==0){
                        posicionsesgo="sesgo es simetrico";
                    }
                    else{
                        if(sesgodos<0){
                            posicionsesgo="sesgo es asimetrico negativo";
                        }
                        else {
                            posicionsesgo="sesgo es asimetrico positivo";
                        }
                    }



                    String textoresultado="momentodos="+momentodoss+"    momentotres="+momentotress+"    momentocuatro="+momentocuatroo+"          "+posicionsesgo;

                    resultado.setText(qu);
                    Toast.makeText(MainActivity.this, textoresultado, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });






        apuntamiento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!listacadena.getText().toString().equals("")) {
                    EditText cadena = (EditText) findViewById(editText4);
                    int wy = 0;
                    double yw = 0.0;
                    int contador = 0;
                    int i = 0;
                    int j = 0;
                    int y = 0;
                    int mediana = 0;
                    int medianaa = 0;
                    double medianita = 0;
                    String e = "";
                    String a = listacadena.getText().toString();
                    double d = 0;
                    String df = "+";
                    System.out.println("Ingrese registros:");


                    int u = a.length();
                    double[] b = new double[u];
                    double[] rt = new double[u];
                    int ppp = 0;
                    for (i = 0; i < a.length(); i++) {


                        char o = a.charAt(i);
                        String hj = (new StringBuffer().append(o)).toString();
                        if (i == u - 1) {
                            e = e + hj;
                            d = Double.parseDouble(e);
                            b[i] = d;
                            ppp += 1;
                            e = "";
                            y = 0;
                            rt[i] = 0;
                        } else {
                            if (hj.equals(df)) {
                                y = y + 1;
                                d = Double.parseDouble(e);
                                b[i] = d;
                                e = "";
                                ppp += 1;
                                rt[i] = 0;
                            } else {
                                e = e + hj;
                                y = 0;
                                b[i] = 0;
                                rt[i] = 1;
                            }
                        }
                    }


                    double[] ghf = new double[ppp];
                    int pos = 0;
                    for (i = 0; i < a.length(); i++) {
                        if (rt[i] == 0) {

                            ghf[pos] = b[i];
                            pos += 1;
                        }

                    }


//metodo Burbuja

                    for (i = 0; i < ppp - 1; i++) {

                        for (j = 0; j < ppp - 1; j++) {

                            if (ghf[j] < ghf[j + 1]) {
                                double tmp = ghf[j + 1];
                                ghf[j + 1] = ghf[j];
                                ghf[j] = tmp;
                            }


                        }

                    }
                    for (i = 0; i < ppp; i++) {
                        wy = wy + 1;
                        yw = yw + ghf[i];
                    }


                    //mediana


                    if ((ppp % 2) == 0) {
                        mediana = (ppp / 2) - 1;
                        medianaa = mediana + 1;
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana || i == medianaa) {
                                medianita = medianita + ghf[i];
                            }
                        }
                        medianita = medianita / 2;

                    } else {

                        mediana = (ppp / 2);
                        for (i = 0; i < ppp; i++) {
                            if (i == mediana) {
                                medianita = medianita + ghf[i];
                            }

                        }
                    }


                    //Moda


                    int maximaVecesQueSeRepitee = 0;
                    double moda = 0;
                    int veces[] = new int[ppp];
                    double reales[] = new double[ppp];
                    double vecesnew[] = new double[ppp];
                    double perro[] = new double[ppp];
                    int vecesQueSeRepitee = 0;
                    int vces = 0;

                    for (i = 0; i < ghf.length; i++) {
                        for (j = 0; j < ghf.length; j++) {
                            if (ghf[i] == ghf[j])
                                vecesQueSeRepitee++;
                        }
                        veces[i] = vecesQueSeRepitee;
                        vecesQueSeRepitee = 0;
                    }

                    for (i = 0; i < ghf.length; i++) {
                        //System.out.println(M[i]+" se repite "+veces[i]);
                    }

                    double inicio = 0 - 1;
                    int divisor = 0;


                    for (i = 0; i < ghf.length; i++) {
                        if (inicio != ghf[i]) {
                            vces++;
                            reales[i] = ghf[i];
                            vecesnew[i] = veces[i];
                            inicio = ghf[i];
                            perro[i] = 1;
                            divisor++;
                        } else {
                            perro[i] = 0;
                        }
                    }
                    double klp[] = new double[divisor];
                    double rty[] = new double[divisor];
                    int asd = 0;
                    for (i = 0; i < ghf.length; i++) {
                        if (perro[i] == 1) {
                            //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                            klp[asd] = reales[i];
                            rty[asd] = vecesnew[i];
                            asd++;
                        }
                    }

//
                    double mayor = 0;


                    for (i = 0; i < divisor; i++) {
                        if (rty[i] > mayor) {
                            mayor = rty[i];
                        }
                    }

                    String modisisima = "";
                    float modatemporal = 0;
                    for (i = 0; i < divisor; i++) {
                        //System.out.println(klp[i]+" "+rty[i]);
                        if (rty[i] == mayor) {
                            //System.out.println(klp[i]+" "+rty[i]);
                            modisisima = modisisima + String.valueOf(klp[i]) + ",";
                            modatemporal = (float) klp[i];
                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);


//media de datos frecuenciales
                    double mddff = 0;
                    double divdiv = 0;
                    for (i = 0; i < divisor; i++) {
                        mddff = mddff + (klp[i] * rty[i]);
                        divdiv = divdiv + rty[i];
                        //System.out.println(mddff+" "+divdiv);
                        //System.out.println(klp[i]+" "+rty[i]);
                        //klp[] es numeros en x
                        //rty[] es frecuencia del numero
                    }
                    double mddf = mddff / divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                    //varianza y desviacion estandar media


                    double nm = yw / wy;
                    double[] dif = new double[ppp];
                    double varianza = 0;

                    for (i = 0; i < ppp; i++) {
                        dif[i] = ghf[i] - nm;

                    }

                    for (i = 0; i < ppp; i++) {
                        varianza = varianza + (dif[i] * dif[i]);
                    }
                    double desviacion = 0;
                    varianza = varianza / wy;
                    desviacion = Math.sqrt(varianza);

                    double ratat = (mddf - modatemporal) / desviacion;
                    float servotemporal = (float) ratat;

                    //klp[] es numeros en x
                    //rty[] es frecuencia del numero
                    float momentodos=0;
                    float momentotres=0;
                    float momentocuatro=0;
                    float sumafrecuencias=0;

                    for(i=0;i<divisor;i++){
                        float unidadx=0;
                        float unidadfrec=(float) rty[i];
                        unidadx=(float) (klp[i]-mddf);
                        momentodos+=unidadfrec*(unidadx*unidadx);
                        momentotres+=unidadfrec*(unidadx*unidadx*unidadx);
                        momentocuatro+=unidadfrec*(unidadx*unidadx*unidadx*unidadx);
                        sumafrecuencias+=unidadfrec;

                    }
                    momentodos=momentodos/sumafrecuencias;
                    momentotres=momentotres/sumafrecuencias;
                    momentocuatro=momentocuatro/sumafrecuencias;
                    double kkkkkk=momentodos*momentodos*momentodos;
                    kkkkkk=Math.sqrt(kkkkkk);
                    float kkkk=(float) kkkkkk;
                    float sesgodos=momentotres/kkkk;
                    float apuntamientodedatos=(momentocuatro/(momentodos*momentodos))-3;

                    String momentodoss=""+momentodos;
                    String momentotress=""+momentotres;
                    String momentocuatroo=""+momentocuatro;

                    String qh = String.valueOf(nm);//media
                    String qw = String.valueOf(medianita);//mediana
                    String qe = String.valueOf(varianza);//varianza
                    String qr = String.valueOf(desviacion);//desviacion
                    String qt = String.valueOf(mddf);//media de frecuencia
                    String qy = String.valueOf(servotemporal);//servo temporal
                    String qu = String.valueOf(sesgodos);
                    String qi = String.valueOf(apuntamientodedatos);

                    //modisisima es la moda
                    String posicionapuntamiento="";
                    if(apuntamientodedatos==0){
                        posicionapuntamiento="apuntamiento es mesocurtico";
                    }
                    else{
                        if(apuntamientodedatos<0){
                            posicionapuntamiento="apuntamiento es platicurtico";
                        }
                        else {
                            posicionapuntamiento="apuntamiento es leptocurtico";
                        }
                    }



                    String textoresultado="momentodos="+momentodoss+"    momentotres="+momentotress+"     momentocuatro="+momentocuatroo+"     "+posicionapuntamiento;

                    resultado.setText(qi);
                    Toast.makeText(MainActivity.this, textoresultado, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
