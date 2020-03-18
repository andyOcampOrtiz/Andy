package com.example.acer1.app2;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.acer1.app2.R.id.editText4;

public class Main2Activity extends AppCompatActivity {
    EditText txtlistacadena,editintervalo;
    RadioGroup rdgroup;
    public TextView txtintervalo;
    public Button graficar,graficarint,agregarint;
    public String qh="";
    public String qw="";
    public String qe="";
    public String qr="";
    public String qt="";
   // public String todo="";
    public String modisisima="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtlistacadena=(EditText) findViewById(R.id.editText2);
        txtlistacadena.setInputType(InputType.TYPE_NULL);
        editintervalo=(EditText) findViewById(R.id.editText16);
        graficar=(Button) findViewById(R.id.button7);
       // rdgroup=(RadioGroup) findViewById(R.id.radioGroup);
        graficarint=(Button) findViewById(R.id.button19);
        agregarint=(Button) findViewById(R.id.button18);
        txtintervalo=(TextView) findViewById(R.id.textView20);


        Intent intent=getIntent();
        Bundle dato=intent.getExtras();
            String datos=dato.getString("DATO");
            txtlistacadena.setText(datos);


        graficar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int wy=0;
                double yw=0.0;
                int contador = 0;
                int i=0;
                int j=0;
                int y=0;
                int mediana=0;
                int medianaa=0;
                double medianita=0;
                String e="";
                String a=txtlistacadena.getText().toString();
                double d=0;
                String df="+";
                System.out.println("Ingrese registros:");


                int u=a.length();
                double[] b = new double[u];
                double[] rt= new double[u];
                int ppp=0;
                for(i=0;i<a.length();i++){


                    char o=a.charAt(i);
                    String hj= (new StringBuffer().append(o)).toString();
                    if(i==u-1){
                        e=e+hj;
                        d=Double.parseDouble(e);
                        b[i]=d;
                        ppp+=1;
                        e="";
                        y=0;
                        rt[i]=0;
                    }
                    else{
                        if(hj.equals(df)){
                            y=y+1;
                            d=Double.parseDouble(e);
                            b[i]=d;
                            e="";
                            ppp+=1;
                            rt[i]=0;
                        }

                        else{
                            e=e+hj;
                            y=0;
                            b[i]=0;
                            rt[i]=1;
                        }
                    }
                }



                double[] ghf=new double[ppp];
                int pos=0;
                for(i=0;i<a.length();i++){
                    if(rt[i]==0){

                        ghf[pos]=b[i];
                        pos+=1;
                    }

                }


//metodo Burbuja

                for(i = 0; i < ppp - 1; i++)
                {

                    for(j = 0; j < ppp - 1; j++)
                    {

                        if (ghf[j] < ghf[j + 1])
                        {
                            double tmp = ghf[j+1];
                            ghf[j+1] = ghf[j];
                            ghf[j] = tmp;
                        }



                    }

                }
                for(i = 0;i < ppp; i++)
                {
                    wy=wy+1;
                    yw=yw+ghf[i];
                }



                //mediana



                if((ppp%2)==0){
                    mediana=(ppp/2)-1;
                    medianaa=mediana+1;
                    for(i=0;i<ppp;i++){
                        if(i==mediana || i==medianaa){
                            medianita=medianita+ghf[i];
                        }
                    }
                    medianita=medianita/2;

                }
                else{

                    mediana=(ppp/2);
                    for(i=0;i<ppp;i++){
                        if(i==mediana){
                            medianita=medianita+ghf[i];
                        }

                    }
                }



                //Moda


                int maximaVecesQueSeRepitee = 0;
                double moda=0;
                int veces[]= new int[ppp];
                double reales[]=new double[ppp];
                double vecesnew[]=new double[ppp];
                double perro[]=new double[ppp];
                int vecesQueSeRepitee=0;
                int vces=0;

                for(i=0; i<ghf.length; i++){
                    for(j=0; j<ghf.length; j++){
                        if(ghf[i] == ghf[j])
                            vecesQueSeRepitee++;
                    }
                    veces[i]=vecesQueSeRepitee;
                    vecesQueSeRepitee=0;
                }


                double inicio=0-1;
                int divisor=0;


                for(i=0;i<ghf.length;i++){
                    if(inicio!=ghf[i]){
                        vces++;
                        reales[i]=ghf[i];
                        vecesnew[i]=veces[i];
                        inicio=ghf[i];
                        perro[i]=1;
                        divisor++;
                    }
                    else{
                        perro[i]=0;
                    }
                }
                double klp[]=new double[divisor];
                double rty[]=new double[divisor];
                int asd=0;
                for(i=0;i<ghf.length;i++){
                    if(perro[i]==1){
                        //System.out.println(reales[i]+" se repite "+vecesnew[i]+" numeros"+divisor);
                        klp[asd]=reales[i];
                        rty[asd]=vecesnew[i];
                        asd++;
                    }
                }

//
                double mayor=0;


                for(i=0;i<divisor;i++){
                    if(rty[i]>mayor){
                        mayor=rty[i];
                    }
                }


                for(i=0;i<divisor;i++){

                    if(rty[i]==mayor) {

                            modisisima = modisisima + String.valueOf(klp[i]+",");

                    }
                }
                modisisima=modisisima.substring(0,modisisima.length()-1);
//b=a.substring(0,a.length()-1);

//media de datos frecuenciales
                double mddff=0;
                double divdiv=0;
                for(i=0;i<divisor;i++){
                    mddff=mddff+(klp[i]*rty[i]);
                    divdiv=divdiv+rty[i];
                    //System.out.println(mddff+" "+divdiv);
                    //System.out.println(klp[i]+" "+rty[i]);
                }
                double mddf=mddff/divdiv;
//System.out.println("media de datos frecuenciales es: "+mddf);


                //varianza y desviacion estandar media


                double nm=yw/wy;
                double[] dif=new double[ppp];
                double varianza=0;

                for(i=0;i<ppp;i++){
                    dif[i]=ghf[i]-nm;

                }

                for(i=0;i<ppp;i++){
                    varianza=varianza+(dif[i]*dif[i]);
                }
                double desviacion=0;
                varianza=varianza/wy;
                desviacion=Math.sqrt(varianza);

                qh=String.valueOf(nm);//media
                qw=String.valueOf(medianita);//mediana
                qe=String.valueOf(varianza);//varianza
                qr=String.valueOf(desviacion);//desviacion
                qt=String.valueOf(mddf);//media de frecuencia
                //modisisima es la moda
               String todo=qh+"+"+qw+"+"+qe+"+"+qr+"+"+qt+"+"+modisisima;
                Intent graficar = new Intent(Main2Activity.this, Main3Activity.class);
                graficar.putExtra("DATOF", todo);

                startActivity(graficar);


            }
        });


        graficarint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!txtintervalo.getText().toString().equals("")) {
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
                    String a = txtlistacadena.getText().toString();
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


                    for (i = 0; i < divisor; i++) {

                        if (rty[i] == mayor) {

                            modisisima = modisisima + String.valueOf(klp[i] + ",");

                        }
                    }
                    modisisima = modisisima.substring(0, modisisima.length() - 1);
//b=a.substring(0,a.length()-1);

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

                    qh = String.valueOf(nm);//media
                    qw = String.valueOf(medianita);//mediana
                    qe = String.valueOf(varianza);//varianza
                    qr = String.valueOf(desviacion);//desviacion
                    qt = String.valueOf(mddf);//media de frecuencia
                    //modisisima es la moda

                    String todoo=txtintervalo.getText().toString();//num intervalo
                    String cadenanum=txtlistacadena.getText().toString();
                    Intent graficarr = new Intent(Main2Activity.this, Main5Activity.class);
                    graficarr.putExtra("DATOT", cadenanum);
                    graficarr.putExtra("DATOQ", todoo);

                    startActivity(graficarr);

                }
                else {
                    Toast.makeText(Main2Activity.this, "Ingresa numero de intervalos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        agregarint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!editintervalo.getText().toString().equals("")||!editintervalo.getText().toString().equals(".")){
                    txtintervalo.setText(editintervalo.getText().toString());
                    editintervalo.setText("");
                }
            }
        });




    }


}
