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

public class Main3Activity extends AppCompatActivity {
    BarChart barChart;
    EditText fhg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        barChart=(BarChart) findViewById(R.id.bargraph);
        fhg=(EditText) findViewById(R.id.editText7);
        fhg.setInputType(InputType.TYPE_NULL);

        Intent intent=getIntent();
        Bundle datoss=intent.getExtras();
        String datosss=datoss.getString("DATOF");
        fhg.setText(datosss);

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
        String a=datosss;
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
                    e = e + hj;
                    d = Double.parseDouble(e);
                    b[i] = d;
                    ppp += 1;
                    e = "";
                    y = 0;
                    rt[i] = 0;
            }
            else{
                if(hj.equals(df)||hj.equals(",")){
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





        //////////////////////////////////////////////////////////////////////////////////
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        for (i=0;i<ppp;i++) {
            float hhh=(float) ghf[i];
            barEntries.add(new BarEntry(hhh, i));
        }

        BarDataSet barDataSet=new BarDataSet(barEntries,"Dates");

        ArrayList<String> theDates=new ArrayList<>();
        for (i=0;i<ppp;i++) {
            if(i==0) {
                theDates.add("Media");
            }
            else {
                if(i==1){
                    theDates.add("Mediana");
                }
                else {
                    if(i==2){
                        theDates.add("Varianza");
                    }
                    else {
                        if(i==3){
                            theDates.add("Dezviación");
                        }
                        else {
                            if(i==4){
                                theDates.add("Media de Frecuencia");
                            }
                            else{
                                theDates.add("Moda");
                            }
                        }
                    }
                }
            }
        }

        BarData theData=new BarData(theDates,barDataSet);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }
}
