package com.example.acer1.app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    public Button add,graphicar,calcu;
    public EditText valorx,valory,valoresx,valoresy;
    public TextView covarianza,varianzax,varianzay,mediax,mediay;

    public String modisisima="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        add=(Button) findViewById(R.id.button9);
        graphicar=(Button) findViewById(R.id.button11);
        calcu=(Button) findViewById(R.id.button10);
        valorx=(EditText) findViewById(R.id.editText8);
        valory=(EditText) findViewById(R.id.editText10);
        valoresx=(EditText) findViewById(R.id.editText11);
        valoresx.setInputType(InputType.TYPE_NULL);
        valoresy=(EditText) findViewById(R.id.editText12);
        valoresy.setInputType(InputType.TYPE_NULL);
        covarianza=(TextView) findViewById(R.id.textView8);
        varianzax=(TextView) findViewById(R.id.textView5);
        varianzay=(TextView) findViewById(R.id.textView6);
        mediax=(TextView) findViewById(R.id.textView2);
        mediay=(TextView) findViewById(R.id.textView3);



        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(valorx.getText().toString().equals(".")||valory.getText().toString().equals(".")){
                    valorx.setText("");
                    valory.setText("");
                }
                else {
                    if (!valorx.getText().toString().equals("")&&!valory.getText().toString().equals("")) {
                        if (!valoresx.getText().toString().equals("")&&!valoresy.getText().toString().equals("")) {
                            valoresx.setText(valoresx.getText().toString() + "+" + valorx.getText().toString());
                            valoresy.setText(valoresy.getText().toString() + "+" + valory.getText().toString());

                        } else {
                            valoresx.setText(valorx.getText().toString());
                            valoresy.setText(valory.getText().toString());

                        }

                        valorx.setText("");
                        valory.setText("");
                    }
                    else {

                    }
                }
            }
        });



        calcu.setOnClickListener(new View.OnClickListener() {
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
                String a=valoresx.getText().toString();
                String zaq=valoresy.getText().toString();
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
                for(i=0;i<ppp;i++){
                    mediayy+=(float) thf[i];
                }

                mediayy=mediayy/ppp;
                mediaxx=mediaxx/ppp;

                String mediaxxx=""+mediaxx;
                String mediayyy=""+mediayy;
                mediax.setText(mediaxxx);
                mediay.setText(mediayyy);



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

                String ycuadraday=""+ycuadrada;
                String xcuadradax=""+xcuadrada;
                varianzax.setText(xcuadradax);
                varianzay.setText(ycuadraday);

                float covarianzatotal=((xpory/ppp)-(mediaxx*mediayy));
                String covarianzatota=""+covarianzatotal;
                covarianza.setText(covarianzatota);

            }
        });


        graphicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent pendienteee = new Intent(Main4Activity.this, Main6Activity.class);
                String cadeenax=valoresx.getText().toString();//num intervalo
                String cadeenay=valoresy.getText().toString();
                pendienteee.putExtra("DATOH", cadeenax);
                pendienteee.putExtra("DATOJ", cadeenay);
                startActivity(pendienteee);

            }
        });


    }
    }

