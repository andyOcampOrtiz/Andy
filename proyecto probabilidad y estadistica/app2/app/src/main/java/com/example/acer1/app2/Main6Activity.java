package com.example.acer1.app2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {

    LineChart lineChart;
    EditText fhg,fghh;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        lineChart=(LineChart) findViewById(R.id.linegraph3);
        fhg=(EditText) findViewById(R.id.editText78);
        fhg.setInputType(InputType.TYPE_NULL);
        fghh=(EditText) findViewById(R.id.editText79);
        fghh.setInputType(InputType.TYPE_NULL);

        Intent intent=getIntent();
        Bundle dat=intent.getExtras();
        String cadenanumx=dat.getString("DATOH");
        String cadenanumy=dat.getString("DATOJ");





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
        String a=cadenanumx;
        String zaq=cadenanumy;
        double d=0;
        String df="+";


        int u=a.length();
        int su=zaq.length();
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

        /////////////////////////////////////////////////////////////////////////////////////

        int ccc=0;
        double[] rtt= new double[su];
        double[] bbb = new double[su];


        for(i=0;i<zaq.length();i++){


            char o=zaq.charAt(i);
            String hj= (new StringBuffer().append(o)).toString();
            if(i==su-1){
                e=e+hj;
                d=Double.parseDouble(e);
                bbb[i]=d;
                ccc+=1;
                e="";
                y=0;
                rtt[i]=0;
            }
            else{
                if(hj.equals(df)){
                    y=y+1;
                    d=Double.parseDouble(e);
                    bbb[i]=d;
                    e="";
                    ccc+=1;
                    rtt[i]=0;
                }

                else{
                    e=e+hj;
                    y=0;
                    bbb[i]=0;
                    rtt[i]=1;
                }
            }
        }


        double[] thf=new double[ccc];
        int ypos=0;
        for(i=0;i<zaq.length();i++){
            if(rtt[i]==0){

                thf[ypos]=bbb[i];
                ypos+=1;
            }

        }

        /////////////////////////////////////////////////////////////////////////////////////////

        // ghf X  thf Y

        float mediaxx=0;
        float mediayy=0;

        for(i=0;i<ppp;i++){
            mediaxx+=(float) ghf[i];
        }
        for(i=0;i<ccc;i++){
            mediayy+=(float) thf[i];
        }

        mediayy=mediayy/ccc;
        mediaxx=mediaxx/ppp;

        String mediaxxx=""+mediaxx;
        String mediayyy=""+mediayy;
        //mediax.setText(mediaxxx);
        //mediay.setText(mediayyy);



        float xcuadrada=0;
        float ycuadrada=0;
        float xconvertido=0;
        float yconvertido=0;
        float xpory=0;
        for(i=0;i<ppp;i++){
            xconvertido=(float) ghf[i];
            yconvertido=(float) thf[i];
            xcuadrada=xcuadrada+(xconvertido*xconvertido);
            ycuadrada=ycuadrada+(yconvertido*yconvertido);
            xpory+=(ghf[i]*thf[i]);
        }
        xcuadrada=(xcuadrada/ppp)-(mediaxx*mediaxx);
        ycuadrada=(ycuadrada/ccc)-(mediayy*mediayy);

       // String ycuadraday=""+ycuadrada;
       // String xcuadradax=""+xcuadrada;
        //varianzax.setText(xcuadradax);
        //varianzay.setText(ycuadraday);

        float covarianzatotal=((xpory/ppp)-(mediaxx*mediayy));
        String covarianzatota=""+covarianzatotal;
        //covarianza.setText(covarianzatota);
///////////////////////////////////////////////////////////////////////////////////////////////





double bhu[]=new double[ppp];

        for(i = 0; i < ghf.length - 1; i++)
        {
            for(j = 0; j < ghf.length - 1; j++)
            {
                if (ghf[j] > ghf[j + 1])
                {
                    //ghf valores en x
                    //thf valores en y
                    double tmp = ghf[j+1];
                    ghf[j+1] = ghf[j];
                    ghf[j] = tmp;
                    double cvb=thf[j+1];
                    thf[j+1]=thf[j];
                    thf[j]=cvb;
                }
            }
        }









        //////////////////////////////////////////////////////////////////////////////////////////


        ArrayList<Entry> points=new ArrayList<>();



        /////////////////////////////////////////////////////////////////////////////////////




        for(i=0;i<ppp;i++){
            int lkjh= (int) ghf[i];
            float poiu=(float) thf[i];
                points.add(new Entry(poiu, lkjh));
        }



/////////////////////////////////////////////////////////////////////////////



        int menor=99999;
        int mayor=0-1;
        double asd=0;
        for(i=0;i<ppp;i++){
            if(ghf[i]>mayor){
                mayor=(int) ghf[i];
            }
        }

        for(i=0;i<ppp;i++){
            if(ghf[i]<menor){
                menor=(int) ghf[i];
            }
        }

        int mayorres=0;
        if(mayor>=ppp){
            mayorres=mayor+2;
        }
        else{
            mayorres=ppp+2;
        }

        String burro[]=new String[mayorres];
        for(i=0;i<mayorres;i++){
            burro[i]=""+i;
        }

        ////////////////////////////////////////////////////////////////////////

        ArrayList<Entry> pojn=new ArrayList<>();
        float unix=0;
        float uniy=0;
        float unitotal=0;
        for(i=0;i<ppp;i++) {
            int lkjh= (int) ghf[i];
            float poiu=(float) thf[i];
           unix=(covarianzatotal/xcuadrada)*(lkjh);
            uniy=((covarianzatotal/xcuadrada)*(-1*xcuadrada))+mediayy;
            unitotal=unix+uniy;
            pojn.add(new Entry(unitotal, lkjh));
        }
        /////////////////////////////////////////////////////////////////////////////////

        //ghf es numeros en x
        //thf es numeros en y
        //mediayy es media d y
        //mediaxx es media de x
        //raiz cuadrada Math.sqrt(variable)

        float ximenosmediax=0;
        float yimenosmediay=0;
        float xicuadrada=0;
        float xi=0;
        float yi=0;
        float xiyi=0;
        float Sxy=0;
        for(i=0;i<ppp;i++){
            unix=(float) ghf[i];
            uniy=(float) thf[i];
            ximenosmediax+=((unix-mediaxx)*(unix-mediaxx));
            yimenosmediay+=((uniy-mediayy)*(uniy-mediayy));
            xicuadrada=xicuadrada+(unix*unix);
            xi=xi+unix;
            yi=yi+uniy;
            xiyi+=unix*uniy;
            Sxy+=((unix-mediaxx)*(uniy-mediayy));
        }

        Sxy=Sxy/ppp;

        float Sx=(float)Math.sqrt(ximenosmediax/ppp);
        float Sy=(float)Math.sqrt(yimenosmediay/ppp);
        float coeficienteperson=Sxy/(Sx*Sy);
        float mrectaregresion=((ppp*xiyi)-(xi*yi))/((ppp*xicuadrada)-(xi*xi));
        float brectaregresion=((yi*xicuadrada)-(xi*xiyi))/((ppp*xicuadrada)-(xi*xi));
        String ecuacion="Recta="+mrectaregresion+"x"+"+("+brectaregresion+"), Coeficiente de Person="+coeficienteperson;

        float Yest=0;
        float formula=0;
        ArrayList<Entry> recta=new ArrayList<>();
        for(i=0;i<ppp;i++) {
            int lkjh= (int) ghf[i];
            float poiu=(float) thf[i];
            formula=(mrectaregresion*lkjh)+brectaregresion;
            recta.add(new Entry(formula, lkjh));
        Yest+=formula;
        }

        float errorestandardeestimacion=(float)Math.sqrt(((yi-Yest)*(yi-Yest))/ppp);


        //////////////////////////////////////////////////////////////////////////////////
            ArrayList<ILineDataSet> lineDataSets=new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(points,"puntos");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setColor(Color.TRANSPARENT);

       /* LineDataSet lineDataSet2 = new LineDataSet(pojn,"formula1");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.GREEN);*/

        LineDataSet lineDataSet3 = new LineDataSet(recta,"formula2");
        lineDataSet3.setDrawCircles(false);
        lineDataSet3.setColor(Color.GREEN);

        lineDataSets.add(lineDataSet1);
        //lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);



       lineChart.setData(new LineData(burro,lineDataSets));

        fhg.setText(ecuacion);
        //fghh.setText();




    }
}
