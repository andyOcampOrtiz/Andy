package com.example.acer1.app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    BarChart barChart;
    EditText fhg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        barChart=(BarChart) findViewById(R.id.bargraph);
        fhg=(EditText) findViewById(R.id.editText77);
        fhg.setInputType(InputType.TYPE_NULL);

        Intent intent=getIntent();
        Bundle datoss=intent.getExtras();
        String cadenanumeross=datoss.getString("DATOT");
        fhg.setText(cadenanumeross);
        String numerodeintervalos=datoss.getString("DATOQ");


///////////////////////////////////////////////////////////////////////////////////////////////


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
        String a=cadenanumeross;
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

        for(i=0; i<ghf.length; i++){
            //System.out.println(M[i]+" se repite "+veces[i]);
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

        String modisisima="";
        for(i=0;i<divisor;i++){
            //System.out.println(klp[i]+" "+rty[i]);
            if(rty[i]==mayor){
                //System.out.println(klp[i]+" "+rty[i]);
                modisisima=modisisima+String.valueOf(klp[i])+",";
            }
        }
        modisisima=modisisima.substring(0,modisisima.length()-1);


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

        String qh=String.valueOf(nm);//media
        String qw=String.valueOf(medianita);//mediana
        String qe=String.valueOf(varianza);//varianza
        String qr=String.valueOf(desviacion);//desviacion
        String qt=String.valueOf(mddf);//media de frecuencia
        //modisisima es la moda


//////////////////////////////////////////////////////////////////////////////////////////////







        double mayorque=0;
        double menorque=999999;
//double kbvc=0;
        for(i=0;i<divisor;i++){
            if(klp[i]>mayorque){
                mayorque=klp[i];
            }
        }


        for(i=0;i<divisor;i++){
            if(klp[i]<menorque){
                menorque=klp[i];
            }
        }


        double perr=mayorque-menorque;
        int intervalo= Integer.parseInt(numerodeintervalos);
        int numveces=0;
        if(perr==0){
            perr=1;
        }
        if(perr%intervalo==0){
            numveces=(int)perr;
            numveces=numveces/intervalo;
        }else{
            numveces=(int)perr;
            numveces=(numveces/intervalo)+1;
        }
        System.out.println("veces a repetir"+numveces);

        int contadorr[]=new int[numveces];
        String texto[]=new String[numveces];
        String lkjh="";
        String fds="";
        int yuyu=0;
        for(i=0;i<numveces;i++){
            double asdf=menorque+intervalo;
            lkjh=String.valueOf(menorque);
            fds=String.valueOf(asdf);
            //System.out.println(menorque+"-"+asdf);
            for(j=0;j<divisor;j++){
                if(i==0){
                    if(klp[j]>=menorque && klp[j]<=asdf){
                        //System.out.println(klp[j]);
                        int g=(int) rty[j];
                        yuyu=yuyu+g;
                    }
                }
                else{ if(klp[j]>menorque && klp[j]<=asdf){
                    int g=(int) rty[j];
                    yuyu=yuyu+g;
                }

                }


            }

            contadorr[i]=yuyu;
            texto[i]=lkjh+"-"+asdf;
            yuyu=0;
            menorque=menorque+intervalo;
        }

        for(i=0;i<numveces;i++){
            System.out.println("numeros entre :"+texto[i]+" es: "+contadorr[i]);
        }










        //////////////////////////////////////////////////////////////////////////////////
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        for (i=0;i<numveces;i++) {
            float hhh=(float) contadorr[i];
            barEntries.add(new BarEntry(hhh, i));
        }

        BarDataSet barDataSet=new BarDataSet(barEntries,"Dates");

        ArrayList<String> theDates=new ArrayList<>();
        for (i=0;i<numveces;i++) {
                theDates.add(texto[i]);
        }

        BarData theData=new BarData(theDates,barDataSet);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }
}
