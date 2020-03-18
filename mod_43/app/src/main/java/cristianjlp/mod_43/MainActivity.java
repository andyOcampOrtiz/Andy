package cristianjlp.mod_43;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.juang.jplot.PlotPlanitoXY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PlotPlanitoXY plot;
    private LinearLayout pantalla;
    Context context;

    DecimalFormat form = new DecimalFormat("0.000");
    DecimalFormat form2 = new DecimalFormat("0");
    private Button bfi, bff, bc,bg,ok_;
    private EditText txtfi, txtff, txtsc, txtps1,txtps2,txtps3,txtps4,txtps5,
            txtedad, txtgenero,cantidad_invertir,cantidad_invertir_por_mes;
    private int diai, mesi, anoi, diaf, mesf, anof;
    private TextView textresult,txttotal_,meses;
    private CheckBox b_verification_bfi,b_verification_bff,b_verification_segurity;

    private ArrayList<Float> resultados_=new ArrayList<>();
    float [] x_h,y_h,x_m,y_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        pantalla = findViewById(R.id.pantalla);

        bfi = findViewById(R.id.bfi);
        bff = findViewById(R.id.bff);
        bc = findViewById(R.id.bc);
        bg = findViewById(R.id.bg);
        cantidad_invertir=findViewById(R.id.cantidad_invertir);
        cantidad_invertir_por_mes=findViewById(R.id.cantidad_invertir_mes);
        ok_=findViewById(R.id.ok_);
        meses=findViewById(R.id.meses);
        txtfi = findViewById(R.id.txtfi);
        txtfi.setEnabled(false);
        txtff = findViewById(R.id.txtff);
        txtff.setEnabled(false);
        txtsc = findViewById(R.id.txtsc);
        txtps1 = findViewById(R.id.txtps1);
        txtps2 = findViewById(R.id.txtps2);
        txtps3 = findViewById(R.id.txtps3);
        txtps4 = findViewById(R.id.txtps4);
        txtps5 = findViewById(R.id.txtps5);
        txtedad= findViewById(R.id.txtedad);
        txtgenero = findViewById(R.id.txtgenero);
        textresult = findViewById(R.id.txtresult);
        txttotal_ = findViewById(R.id.txttotal_);
        b_verification_bff = findViewById(R.id.verification_bff);
        b_verification_bfi = findViewById(R.id.verification_bfi);
        b_verification_segurity = findViewById(R.id.verification_segurity);

        bff.setVisibility(View.GONE);
        txtff.setVisibility(View.GONE);
        bc.setEnabled(false);
        bg.setEnabled(false);
        txtsc.setEnabled(false);
        txtps1.setEnabled(false);
        txtps2.setEnabled(false);
        txtps3.setEnabled(false);
        txtps4.setEnabled(false);
        txtps5.setEnabled(false);
        txtedad.setEnabled(false);
        txtgenero.setEnabled(false);
        b_verification_bfi.setEnabled(false);
        b_verification_bff.setEnabled(false);
        b_verification_bff.setVisibility(View.GONE);
        ok_.setVisibility(View.GONE);
        cantidad_invertir.setVisibility(View.GONE);
        cantidad_invertir_por_mes.setVisibility(View.GONE);

        bfi.setOnClickListener(this);
        bff.setOnClickListener(this);
        obtener();
        calcular();

        b_verification_segurity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                cantidad_invertir.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cantidad_invertir_por_mes.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                ok_.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (b_verification_segurity.isChecked()){
                    ok_.setEnabled(true);
                    bc.setEnabled(false);
                }else{
                    ok_.setEnabled(false);
                    bc.setEnabled(false);
                }
            }
        });
        b_verification_bfi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bff.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                txtff.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                b_verification_bff.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if(b_verification_bfi.isChecked()){
                    bfi.setEnabled(false);
                }else{
                    bfi.setEnabled(true);
                }
            }
        });
        b_verification_bff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b_verification_bff.isChecked()){
                    txtsc.setEnabled(true);
                    txtps1.setEnabled(true);
                    txtps2.setEnabled(true);
                    txtps3.setEnabled(true);
                    txtps4.setEnabled(true);
                    txtps5.setEnabled(true);
                    txtedad.setEnabled(true);
                    txtgenero.setEnabled(true);
                    bc.setEnabled(false);
                    bg.setEnabled(false);
                    bff.setEnabled(false);
                }else{
                    txtsc.setEnabled(false);
                    txtps1.setEnabled(false);
                    txtps2.setEnabled(false);
                    txtps3.setEnabled(false);
                    txtps4.setEnabled(false);
                    txtps5.setEnabled(false);
                    txtedad.setEnabled(false);
                    txtgenero.setEnabled(false);
                    bg.setEnabled(false);
                    bff.setEnabled(true);
                }
            }
        });
    }
    public void obtener(){
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtff.getText().toString().isEmpty() || txtfi.getText().toString().isEmpty() ||
                        txtsc.getText().toString().isEmpty() ||txtedad.getText().toString().isEmpty()||txtgenero.getText().toString().isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Alguna Caja de Texto esta vacia", Toast.LENGTH_SHORT).show();
                }else {
                    int mm=Integer.parseInt(txtsc.getText().toString());
                    int mm2=Integer.parseInt(txtedad.getText().toString());
                    int promedio_salarial_1=Integer.parseInt(txtps1.getText().toString());
                    int promedio_salarial_2=Integer.parseInt(txtps2.getText().toString());
                    int promedio_salarial_3=Integer.parseInt(txtps3.getText().toString());
                    int promedio_salarial_4=Integer.parseInt(txtps4.getText().toString());
                    int promedio_salarial_5=Integer.parseInt(txtps5.getText().toString());

                    textresult.setText("");
                    float porcentage;
                    float factoractualizacion=1.11f;
                    float resultado;
                    int edad_restante_h = 73-mm2;
                    int edad_restante_m = 78-mm2;
                    float total_;
                    float y=0.0f;
                    if(txtgenero.getText().charAt(0)=='H') {
                        if(mm2>=73){
                            edad_restante_h=0;
                        }else{

                        }
                        for (int cont = 0; cont <= edad_restante_h; cont++) {
                            float semanas_cotizadas = (mm - 500);
                            float inclementoanual = semanas_cotizadas / 52;
                            float cuantiabasica;        //porcentaje de cuantía básica
                            float factorincremento;   //Los salarios incremento anual
                            ////////////////////////////////////////////////////////////////////////
                            int c_invertir_total;
                            if (cantidad_invertir.getText().toString().isEmpty()){
                                c_invertir_total=0;
                            }else{
                                c_invertir_total = Integer.parseInt(cantidad_invertir.getText().toString());
                            }
                            float c_sueldos = Float.parseFloat(cantidad_invertir_por_mes.getText().toString());
                            float total_promedio_salarial;

                            float total;
                            float totalano;

                            if (y<=c_invertir_total){
                               y++;
                            }else{

                            }
                            total=c_sueldos*88.36f*30f*0.10075f;
                            totalano=total*12;
                            ////////////////////////////////////////////////////////////////////////
                            if(y==1){
                                total_promedio_salarial=(totalano+promedio_salarial_4+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                            }
                            else{ if(y==2){
                                total_promedio_salarial=(totalano+totalano+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                            } else{ if (y==3){
                                total_promedio_salarial=(totalano+totalano+totalano+promedio_salarial_2+promedio_salarial_1)/5;
                            }else{ if (y==4){
                                total_promedio_salarial=(totalano+totalano+totalano+totalano+promedio_salarial_1)/5;
                            } else { if (y>=5){
                                total_promedio_salarial=totalano;
                            } else{
                                total_promedio_salarial=(promedio_salarial_5+promedio_salarial_4+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                            }
                            }
                            }
                            }
                            }
                            System.out.println("promedio año: "+total_promedio_salarial);
                            float gruposalarial=total_promedio_salarial/88.36f;
                            System.out.println("grupo salarial: "+gruposalarial);
                            ////////////////////////////////////////////////////////////////////////
                            if (gruposalarial >= 1.01 && gruposalarial <= 1.25) {
                                cuantiabasica = 80;
                                factorincremento = .563f;
                            } else {
                                if (gruposalarial >= 1.26 && gruposalarial <= 1.50) {
                                    cuantiabasica = 77.11f;
                                    factorincremento = .814f;
                                } else {
                                    if (gruposalarial >= 1.51 && gruposalarial <= 1.75) {
                                        cuantiabasica = 58.18f;
                                        factorincremento = 1.178f;
                                    } else {
                                        if (gruposalarial >= 1.76 && gruposalarial <= 2) {
                                            cuantiabasica = 49.23f;
                                            factorincremento = 1.430f;
                                        } else {
                                            if (gruposalarial >= 2.01 && gruposalarial <= 2.25) {
                                                cuantiabasica = 42.67f;
                                                factorincremento = 1.615f;
                                            } else {
                                                if (gruposalarial >= 2.26 && gruposalarial <= 2.50) {
                                                    cuantiabasica = 37.65f;
                                                    factorincremento = 1.756f;
                                                } else {
                                                    if (gruposalarial >= 2.51 && gruposalarial <= 2.75) {
                                                        cuantiabasica = 33.68f;
                                                        factorincremento = 1.868f;
                                                    } else {
                                                        if (gruposalarial >= 2.75 && gruposalarial <= 3) {
                                                            cuantiabasica = 30.48f;
                                                            factorincremento = 1.958f;
                                                        } else {
                                                            if (gruposalarial >= 3.01 && gruposalarial <= 3.25) {
                                                                cuantiabasica = 27.83f;
                                                                factorincremento = 2.063f;
                                                            } else {
                                                                if (gruposalarial >= 3.26 && gruposalarial <= 3.5) {
                                                                    cuantiabasica = 25.60f;
                                                                    factorincremento = 2.096f;
                                                                } else {
                                                                    if (gruposalarial >= 3.51 && gruposalarial <= 3.75) {
                                                                        cuantiabasica = 23.70f;
                                                                        factorincremento = 2.149f;
                                                                    } else {
                                                                        if (gruposalarial >= 3.76 && gruposalarial <= 4) {
                                                                            cuantiabasica = 22.07f;
                                                                            factorincremento = 2.195f;
                                                                        } else {
                                                                            if (gruposalarial >= 4.01 && gruposalarial <= 4.25) {
                                                                                cuantiabasica = 20.65f;
                                                                                factorincremento = 2.235f;
                                                                            } else {
                                                                                if (gruposalarial >= 4.26 && gruposalarial <= 4.5) {
                                                                                    cuantiabasica = 19.39f;
                                                                                    factorincremento = 2.271f;
                                                                                } else {
                                                                                    if (gruposalarial >= 4.51 && gruposalarial <= 4.75) {
                                                                                        cuantiabasica = 18.29f;
                                                                                        factorincremento = 2.302f;
                                                                                    } else {
                                                                                        if (gruposalarial >= 4.76 && gruposalarial <= 5) {
                                                                                            cuantiabasica = 17.30f;
                                                                                            factorincremento = 2.33f;
                                                                                        } else {
                                                                                            if (gruposalarial >= 5.01 && gruposalarial <= 5.25) {
                                                                                                cuantiabasica = 16.41f;
                                                                                                factorincremento = 2.355f;
                                                                                            } else {
                                                                                                if (gruposalarial >= 5.26 && gruposalarial <= 5.5) {
                                                                                                    cuantiabasica = 15.61f;
                                                                                                    factorincremento = 2.377f;
                                                                                                } else {
                                                                                                    if (gruposalarial >= 5.51 && gruposalarial <= 5.75) {
                                                                                                        cuantiabasica = 14.88f;
                                                                                                        factorincremento = 2.398f;
                                                                                                    } else {
                                                                                                        if (gruposalarial >= 5.76 && gruposalarial <= 6) {
                                                                                                            cuantiabasica = 14.22f;
                                                                                                            factorincremento = 2.416f;
                                                                                                        } else {
                                                                                                            if (gruposalarial >= 6.01 && gruposalarial <= 6.25) {
                                                                                                                cuantiabasica = 13.62f;
                                                                                                                factorincremento = 2.433f;
                                                                                                            } else {
                                                                                                                cuantiabasica = 13;
                                                                                                                factorincremento = 2.45f;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            float a = ((((factorincremento * inclementoanual) + cuantiabasica)) / 100) * (total_promedio_salarial);
                            System.out.println("a: "+a);
                            float b = a * 30 * factoractualizacion;
                            System.out.println("b: "+b);

                            if(mm<500){
                                //Toast.makeText(MainActivity.this, "Necesitas almenos 500 semanas para poder cotizar", Toast.LENGTH_SHORT).show();
                                bg.setEnabled(false);
                            }else{ if(mm2<60){
                                //Toast.makeText(MainActivity.this, "Necesitas tener almenos 60 años de edad", Toast.LENGTH_SHORT).show();
                                bg.setEnabled(false);
                            }else { if(mm2==60){
                                textresult.setText(null);
                                porcentage=.75f;
                                resultado=b*porcentage;
                                bg.setEnabled(true);
                                float c =total_promedio_salarial*0.10075f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                resultados_.add(total_);
                                textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                            }else { if(mm2==61){
                                textresult.setText(null);
                                porcentage=0.80f;
                                resultado=b*porcentage;
                                bg.setEnabled(true);
                                float c =total_promedio_salarial*0.10075f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                resultados_.add(total_);
                                textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                            }else { if(mm2==62){
                                textresult.setText(null);
                                porcentage=0.85f;
                                resultado=b*porcentage;
                                bg.setEnabled(true);
                                float c =total_promedio_salarial*0.10075f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                resultados_.add(total_);
                                textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                            }else { if(mm2==63){
                                textresult.setText(null);
                                porcentage=0.90f;
                                resultado=b*porcentage;
                                bg.setEnabled(true);
                                float c =total_promedio_salarial*0.10075f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                resultados_.add(total_);
                                textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                            } else { if(mm2==64){
                                textresult.setText(null);
                                porcentage=0.95f;
                                resultado=b*porcentage;
                                bg.setEnabled(true);
                                float c =total_promedio_salarial*0.10075f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                resultados_.add(total_);
                                textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                            }else {
                                if (mm2>=73) {
                                    textresult.setText(null);
                                    porcentage = 1.0f;
                                    resultado = b * porcentage;
                                    bg.setEnabled(true);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =1f;
                                    total_ = (resultado * 12 * d) - c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $" + resultados_ + " de acuerdo a la esperanza de vida");
                                } else {
                                    textresult.setText(null);
                                    porcentage = 1.0f;
                                    resultado = b * porcentage;
                                    bg.setEnabled(true);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =73-mm2;
                                    total_ = (resultado * 12 * d) - c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $" + resultados_ + " de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }
                            }
                            }
                            }
                            }
                            }
                            }
                            }
                            mm+= 52;
                            mm2++;
                        }
                    }else{
                        if(txtgenero.getText().charAt(0)=='M'){
                            if(mm2>=78){
                                edad_restante_m=0;
                            }else{

                            }
                            for (int cont = 0; cont <= edad_restante_m; cont++) {
                                float semanas_cotizadas = (mm - 500);
                                float inclementoanual = semanas_cotizadas / 52;
                                float cuantiabasica;        //porcentaje de cuantía básica
                                float factorincremento;   //Los salarios incremento anual
                                ////////////////////////////////////////////////////////////////////
                                int c_invertir_total = Integer.parseInt(cantidad_invertir.getText().toString());
                                float c_sueldos = Float.parseFloat(cantidad_invertir_por_mes.getText().toString());
                                float total_promedio_salarial;
                                //float y=0.0f;
                                float total;
                                float totalano;
                                total=c_sueldos*88.33f*30f*0.10075f;
                               // y=c_invertir_total;
                                if (y<=c_invertir_total){
                                    y++;
                                }else{

                                }
                                totalano=total*12;
                                //meses.setText("Meses a invertir: "+totalano);
                                if(y==1){
                                    total_promedio_salarial=(totalano+promedio_salarial_4+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                                }
                                else{ if(y==2){
                                    total_promedio_salarial=(totalano+totalano+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                                } else{ if (y==3){
                                    total_promedio_salarial=(totalano+totalano+totalano+promedio_salarial_2+promedio_salarial_1)/5;
                                }else{ if (y==4){
                                    total_promedio_salarial=(totalano+totalano+totalano+totalano+promedio_salarial_1)/5;
                                } else { if (y>=5){
                                    total_promedio_salarial=totalano;
                                } else{
                                    total_promedio_salarial=(promedio_salarial_5+promedio_salarial_4+promedio_salarial_3+promedio_salarial_2+promedio_salarial_1)/5;
                                }
                                }
                                }
                                }
                                }
                                float gruposalarial=total_promedio_salarial/88.36f;
                                ////////////////////////////////////////////////////////////////////
                                if (gruposalarial >= 1.01 && gruposalarial <= 1.25) {
                                    cuantiabasica = 80;
                                    factorincremento = .563f;
                                } else {
                                    if (gruposalarial >= 1.26 && gruposalarial <= 1.50) {
                                        cuantiabasica = 77.11f;
                                        factorincremento = .814f;
                                    } else {
                                        if (gruposalarial >= 1.51 && gruposalarial <= 1.75) {
                                            cuantiabasica = 58.18f;
                                            factorincremento = 1.178f;
                                        } else {
                                            if (gruposalarial >= 1.76 && gruposalarial <= 2) {
                                                cuantiabasica = 49.23f;
                                                factorincremento = 1.430f;
                                            } else {
                                                if (gruposalarial >= 2.01 && gruposalarial <= 2.25) {
                                                    cuantiabasica = 42.67f;
                                                    factorincremento = 1.615f;
                                                } else {
                                                    if (gruposalarial >= 2.26 && gruposalarial <= 2.50) {
                                                        cuantiabasica = 37.65f;
                                                        factorincremento = 1.756f;
                                                    } else {
                                                        if (gruposalarial >= 2.51 && gruposalarial <= 2.75) {
                                                            cuantiabasica = 33.68f;
                                                            factorincremento = 1.868f;
                                                        } else {
                                                            if (gruposalarial >= 2.75 && gruposalarial <= 3) {
                                                                cuantiabasica = 30.48f;
                                                                factorincremento = 1.958f;
                                                            } else {
                                                                if (gruposalarial >= 3.01 && gruposalarial <= 3.25) {
                                                                    cuantiabasica = 27.83f;
                                                                    factorincremento = 2.063f;
                                                                } else {
                                                                    if (gruposalarial >= 3.26 && gruposalarial <= 3.5) {
                                                                        cuantiabasica = 25.60f;
                                                                        factorincremento = 2.096f;
                                                                    } else {
                                                                        if (gruposalarial >= 3.51 && gruposalarial <= 3.75) {
                                                                            cuantiabasica = 23.70f;
                                                                            factorincremento = 2.149f;
                                                                        } else {
                                                                            if (gruposalarial >= 3.76 && gruposalarial <= 4) {
                                                                                cuantiabasica = 22.07f;
                                                                                factorincremento = 2.195f;
                                                                            } else {
                                                                                if (gruposalarial >= 4.01 && gruposalarial <= 4.25) {
                                                                                    cuantiabasica = 20.65f;
                                                                                    factorincremento = 2.235f;
                                                                                } else {
                                                                                    if (gruposalarial >= 4.26 && gruposalarial <= 4.5) {
                                                                                        cuantiabasica = 19.39f;
                                                                                        factorincremento = 2.271f;
                                                                                    } else {
                                                                                        if (gruposalarial >= 4.51 && gruposalarial <= 4.75) {
                                                                                            cuantiabasica = 18.29f;
                                                                                            factorincremento = 2.302f;
                                                                                        } else {
                                                                                            if (gruposalarial >= 4.76 && gruposalarial <= 5) {
                                                                                                cuantiabasica = 17.30f;
                                                                                                factorincremento = 2.33f;
                                                                                            } else {
                                                                                                if (gruposalarial >= 5.01 && gruposalarial <= 5.25) {
                                                                                                    cuantiabasica = 16.41f;
                                                                                                    factorincremento = 2.355f;
                                                                                                } else {
                                                                                                    if (gruposalarial >= 5.26 && gruposalarial <= 5.5) {
                                                                                                        cuantiabasica = 15.61f;
                                                                                                        factorincremento = 2.377f;
                                                                                                    } else {
                                                                                                        if (gruposalarial >= 5.51 && gruposalarial <= 5.75) {
                                                                                                            cuantiabasica = 14.88f;
                                                                                                            factorincremento = 2.398f;
                                                                                                        } else {
                                                                                                            if (gruposalarial >= 5.76 && gruposalarial <= 6) {
                                                                                                                cuantiabasica = 14.22f;
                                                                                                                factorincremento = 2.416f;
                                                                                                            } else {
                                                                                                                if (gruposalarial >= 6.01 && gruposalarial <= 6.25) {
                                                                                                                    cuantiabasica = 13.62f;
                                                                                                                    factorincremento = 2.433f;
                                                                                                                } else {
                                                                                                                    cuantiabasica = 13;
                                                                                                                    factorincremento = 2.45f;
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                float a = ((((factorincremento * inclementoanual) + cuantiabasica)) / 100) * (total_promedio_salarial);
                                float b = a * 30 * factoractualizacion;
                                if(mm<500){
                                    //Toast.makeText(MainActivity.this, "Necesitas almenos 500 semanas para poder cotizar", Toast.LENGTH_SHORT).show();
                                    bg.setEnabled(false);
                                }else{ if(mm2<60){
                                    //Toast.makeText(MainActivity.this, "Necesitas tener almenos 60 años de edad", Toast.LENGTH_SHORT).show();
                                    bg.setEnabled(false);
                                }else { if(mm2==60){
                                  //  textresult.setText(null);
                                    porcentage=.75f;
                                    resultado=b*porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }else { if(mm2==61){
                                //    textresult.setText(null);
                                    porcentage=0.80f;
                                    resultado=b*porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }else { if(mm2==62){
                                //    textresult.setText(null);
                                    porcentage=0.85f;
                                    resultado=b*porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }else { if(mm2==63){
                                 //   textresult.setText(null);
                                    porcentage=0.90f;
                                    resultado=b*porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                } else { if(mm2==64){
                                 //   textresult.setText(null);
                                    porcentage=0.95f;
                                    resultado=b*porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $"+resultados_+" de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }else {if (mm2>=78) {
                                 //   textresult.setText(null);
                                    porcentage = 1.0f;
                                    resultado = b * porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d = 1;
                                    total_ = (resultado * 12 * d) - c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $" + resultados_ + " de acuerdo a la esperanza de vida");
                                } else {
                                  //  textresult.setText(null);
                                    porcentage = 1.0f;
                                    resultado = b * porcentage;
                                    bg.setEnabled(true);
                                    bc.setEnabled(false);
                                    float c =total_promedio_salarial*0.10075f*365f;
                                    float d =78-mm2;
                                    total_ = (resultado * 12 * d) - c;
                                    resultados_.add(total_);
                                    textresult.setText("Recibiras $" + resultados_ + " de acuerdo a la esperanza de vida");
                                    //Toast.makeText(MainActivity.this, "Recibiras $"+form2.format(total_)+" de acuerdo a la esperanza de vida", Toast.LENGTH_SHORT).show();
                                }
                                }
                                }
                                }
                                }
                                }
                                }
                                }
                                mm+= 52;
                                mm2++;
                            }
                        }
                    }
                }
            }
        });
    }
    public void calcular(){
        ok_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cantidad_invertir.getText().toString().isEmpty() || cantidad_invertir_por_mes.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                }else{
                    float c_sueldos = Float.parseFloat(cantidad_invertir_por_mes.getText().toString());
                    float c_total = Float.parseFloat(cantidad_invertir.getText().toString());
                    float total;
                    float totalano;

                    if (c_total >= 0 && 13 >= c_total) {
                    if (c_sueldos >= 0 && 25 >= c_sueldos) {
                        Toast.makeText(MainActivity.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                        total = c_sueldos * 88.36f * 30f * 0.1075f;
                        totalano = total * 12;
                        meses.setText("Tienes que invertir: " + totalano + " por año");
                        bc.setEnabled(true);
                    } else {
                        Toast.makeText(MainActivity.this, "Datos erroneos en sueldo a invertir", Toast.LENGTH_SHORT).show();
                        bc.setEnabled(false);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Datos erroneos en años a invertir", Toast.LENGTH_SHORT).show();
                    bc.setEnabled(false);
                }
            }
            }
        });
    }
    public void Graph_(View v){
        //----------------Variables
        int mm=Integer.parseInt(txtsc.getText().toString());
        int mm2=Integer.parseInt(txtedad.getText().toString());
        float mm3=Float.parseFloat(txtps1.getText().toString());
        float porcentage;
        float salariominimo = 88.36f;
        float gruposalarial = mm3/salariominimo;
        float r_ira = mm-500;
        float inclementoanual=r_ira/52;
        float factoractualizacion = 1.11f;
        float cuantiabasica;
        float factorincremento;
        float resultado;
        float total_;
        //----------------Variables
        //----------------Arreglos
        x_h=new float[14];
        y_h=new float[14];
        x_m=new float[19];
        y_m=new float[19];
        //----------------Arreglos
        //----------------método para pintar
        if(gruposalarial>=1.01 && gruposalarial<=1.25){
            cuantiabasica = 80f;
            factorincremento = .563f;
        }else{
            if(gruposalarial>=1.26 && gruposalarial<=1.50){
                cuantiabasica = 77.11f;
                factorincremento = .814f;
            }else{
                if(gruposalarial>=1.51 && gruposalarial<=1.75){
                    cuantiabasica = 58.18f;
                    factorincremento = 1.178f;
                }else{
                    if(gruposalarial>=1.76 && gruposalarial<=2){
                        cuantiabasica = 49.23f;
                        factorincremento = 1.430f;
                    }else{
                        if(gruposalarial>=2.01 && gruposalarial<=2.25){
                            cuantiabasica = 42.67f;
                            factorincremento = 1.615f;
                        }else{
                            if(gruposalarial>=2.26 && gruposalarial<=2.50){
                                cuantiabasica = 37.65f;
                                factorincremento = 1.756f;
                            }else{
                                if (gruposalarial>=2.51 && gruposalarial<=2.75){
                                    cuantiabasica = 33.68f;
                                    factorincremento = 1.868f;
                                }else{
                                    if(gruposalarial>=2.75 && gruposalarial<=3){
                                        cuantiabasica = 30.48f;
                                        factorincremento = 1.958f;
                                    }else{
                                        if (gruposalarial>=3.01 && gruposalarial<=3.25){
                                            cuantiabasica = 27.83f;
                                            factorincremento = 2.063f;
                                        }else{
                                            if (gruposalarial>=3.26 && gruposalarial<=3.5){
                                                cuantiabasica = 25.60f;
                                                factorincremento = 2.096f;
                                            }else{
                                                if(gruposalarial>=3.51 && gruposalarial<=3.75){
                                                    cuantiabasica = 23.70f;
                                                    factorincremento = 2.149f;
                                                }else{
                                                    if(gruposalarial>=3.76 && gruposalarial<=4){
                                                        cuantiabasica = 22.07f;
                                                        factorincremento = 2.195f;
                                                    }else{
                                                        if(gruposalarial>=4.01 && gruposalarial<=4.25){
                                                            cuantiabasica = 20.65f;
                                                            factorincremento = 2.235f;
                                                        }else{
                                                            if(gruposalarial>=4.26 && gruposalarial<=4.5){
                                                                cuantiabasica = 19.39f;
                                                                factorincremento = 2.271f;
                                                            }else {
                                                                if(gruposalarial>=4.51 && gruposalarial<=4.75){
                                                                    cuantiabasica = 18.29f;
                                                                    factorincremento = 2.302f;
                                                                }else{
                                                                    if(gruposalarial>=4.76 && gruposalarial<=5){
                                                                        cuantiabasica = 17.30f;
                                                                        factorincremento = 2.33f;
                                                                    }else{
                                                                        if(gruposalarial>=5.01 && gruposalarial<=5.25){
                                                                            cuantiabasica = 16.41f;
                                                                            factorincremento = 2.355f;
                                                                        }else{
                                                                            if(gruposalarial>=5.26 && gruposalarial<=5.5){
                                                                                cuantiabasica = 15.61f;
                                                                                factorincremento = 2.377f;
                                                                            }else{
                                                                                if(gruposalarial>=5.51 && gruposalarial<=5.75){
                                                                                    cuantiabasica = 14.88f;
                                                                                    factorincremento = 2.398f;
                                                                                }else{
                                                                                    if(gruposalarial>=5.76 && gruposalarial<=6){
                                                                                        cuantiabasica = 14.22f;
                                                                                        factorincremento = 2.416f;
                                                                                    }else{
                                                                                        if(gruposalarial>=6.01 && gruposalarial<=6.25){
                                                                                            cuantiabasica = 13.62f;
                                                                                            factorincremento = 2.433f;
                                                                                        }else{
                                                                                            cuantiabasica = 13f;
                                                                                            factorincremento = 2.45f;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        float a=(((factorincremento*inclementoanual)+cuantiabasica)/100)*mm3;
        float b=a*30*factoractualizacion;
        int m = Integer.parseInt(txtedad.getText().toString());
        int j=0;
        if(txtgenero.getText().toString().charAt(0)=='H'){
            for(int i = m;i<=73;i++) {
                if (i == 60) {
                    porcentage = .75f;
                    resultado = b * porcentage;
                    float c =mm3*0.1f*365f;
                    float d =73-mm2;
                    total_ = (resultado * 12 * d)-c;
                    x_h[j] = i;
                    y_h[j] = total_;
                } else {
                    if (i == 61) {
                        porcentage = .80f;
                        resultado = b * porcentage;
                        float c =mm3*0.1f*365f;
                        float d =73-mm2;
                        total_ = (resultado * 12 * d)-c;
                        x_h[j] = i;
                        y_h[j] = total_;
                    } else {
                        if (i == 62) {
                            porcentage = .85f;
                            resultado = b * porcentage;
                            float c =mm3*0.1f*365f;
                            float d =73-mm2;
                            total_ = (resultado * 12 * d)-c;
                            x_h[j] = i;
                            y_h[j] = total_;
                        } else {
                            if (i == 63) {
                                porcentage = .90f;
                                resultado = b * porcentage;
                                float c =mm3*0.1f*365f;
                                float d =73-mm2;
                                total_ = (resultado * 12 * d)-c;
                                x_h[j] = i;
                                y_h[j] = total_;
                            } else {
                                if (i == 64) {
                                    porcentage = .95f;
                                    resultado = b * porcentage;
                                    float c =mm3*0.1f*365f;
                                    float d =73-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    x_h[j] = i;
                                    y_h[j] = total_;
                                } else {
                                    porcentage = 1f;
                                    resultado = b * porcentage;
                                    float c =mm3*0.1f*365f;
                                    float d =73-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    x_h[j] = i;
                                    y_h[j] = total_;
                                }
                            }
                        }
                    }
                }
                j++;
            }
            txttotal_.setText("");
            /*for(int i=0;i>=y_h.length;i++){
                if(y_h[i+1]>=y_h[i]){
                    y_h[i]=y_h[i+1];
                    txttotal_.setText("El resultado: "+y_h[i]+"Año: ");
                }else {

                }
            }*/
            plot = new PlotPlanitoXY(context,"Gráfica","Edad","Dinero");
            plot.SetSerie1(x_h,y_h,"graph 1",5,true);
            Drawable myDrawable = getResources().getDrawable(R.drawable.blanco);
            Bitmap myFondo = ((BitmapDrawable) myDrawable).getBitmap();
            plot.SetImagFondo1(myFondo);
            plot.SetHD(true);
            plot.SetTouch(true);
            pantalla.addView(plot);
        }else{
            for(int i = m;i<=78;i++) {
                if (i == 60) {
                    porcentage = .75f;
                    resultado = b * porcentage;
                    float c =mm3*0.1f*365f;
                    float d =78f-mm2;
                    total_ = (resultado * 12 * d)-c;
                    x_m[j] = i;
                    y_m[j] = total_;
                } else {
                    if (i == 61) {
                        porcentage = .80f;
                        resultado = b * porcentage;
                        float c =mm3*0.1f*365f;
                        float d =78f-mm2;
                        total_ = (resultado * 12 * d)-c;
                        x_m[j] = i;
                        y_m[j] = total_;
                    } else {
                        if (i == 62) {
                            porcentage = .85f;
                            resultado = b * porcentage;
                            float c =mm3*0.1f*365f;
                            float d =78f-mm2;
                            total_ = (resultado * 12 * d)-c;
                            x_m[j] = i;
                            y_m[j] = total_;
                        } else {
                            if (i == 63) {
                                porcentage = .90f;
                                resultado = b * porcentage;
                                float c =mm3*0.1f*365f;
                                float d =78f-mm2;
                                total_ = (resultado * 12 * d)-c;
                                x_m[j] = i;
                                y_m[j] = total_;
                            } else {
                                if (i == 64) {
                                    porcentage = .95f;
                                    resultado = b * porcentage;
                                    float c =mm3*0.1f*365f;
                                    float d =78f-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    x_m[j] = i;
                                    y_m[j] = total_;
                                } else {
                                    porcentage = 1f;
                                    resultado = b * porcentage;
                                    float c =mm3*0.1f*365f;
                                    float d =78f-mm2;
                                    total_ = (resultado * 12 * d)-c;
                                    x_m[j] = i;
                                    y_m[j] = total_;
                                }
                            }
                        }
                    }
                }
                j++;
            }
            for(int i=0;i>=y_m.length;i++){
                if(y_m[i+1]>=y_m[i]){
                    y_m[i]=y_m[i+1];
                }else {
                    txttotal_.setText("El resultado: "+y_h[i]+"Año: ");
                }
            }
            plot = new PlotPlanitoXY(context,"Gráfica","Edad","Dinero");
            plot.SetSerie1(x_m,y_m,"Mujer",5,true);
            Drawable myDrawable = getResources().getDrawable(R.drawable.blanco);
            Bitmap myFondo = ((BitmapDrawable) myDrawable).getBitmap();
            plot.SetImagFondo1(myFondo);
            plot.SetHD(true);
            plot.SetTouch(true);
            pantalla.addView(plot);
        }
    }
    public void limpiar(View v){


        boolean state = false;
        resultados_.clear();
        textresult.setText(null);
        txtedad.setText(null);
        txtps1.setText(null);
        txtps2.setText(null);
        txtps3.setText(null);
        txtps4.setText(null);
        txtps5.setText(null);
        txtsc.setText(null);
        txtff.setText(null);
        txtfi.setText(null);
        txtgenero.setText(null);
        meses.setText(null);
        cantidad_invertir.setText(null);
        cantidad_invertir_por_mes.setText(null);
        b_verification_bfi.setChecked(state);
        b_verification_bff.setChecked(state);
        b_verification_segurity.setChecked(state);
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
                    if(year>=1943 && year<=1997){
                        if(year==1997){
                            if(month<=7){
                                if(month==7){
                                    if(dayOfMonth==1){
                                        txtfi.setText(dayOfMonth+"/"+month+"/"+year);
                                        b_verification_bfi.setEnabled(true);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Fecha de inicio Fuera de rango", Toast.LENGTH_SHORT).show();
                                        txtfi.setText("");
                                        b_verification_bfi.setEnabled(false);
                                    }
                                }else {
                                    txtfi.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                                    b_verification_bfi.setEnabled(true);
                                }
                            }else{
                                txtfi.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                                b_verification_bfi.setEnabled(true);
                            }
                        }else {
                            if (year == 1943) {
                                if (month <= 1) {
                                    if (month == 1) {
                                        if (dayOfMonth >= 19) {
                                            txtfi.setText(dayOfMonth + "/" + month + "/" + year);
                                            b_verification_bfi.setEnabled(true);
                                        } else {
                                            Toast.makeText(MainActivity.this, "Fecha de inicio Fuera de rango", Toast.LENGTH_SHORT).show();
                                            txtfi.setText("");
                                            b_verification_bfi.setEnabled(false);
                                        }
                                    } else {
                                        txtfi.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                        b_verification_bfi.setEnabled(true);
                                    }
                                } else {
                                    txtfi.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                    b_verification_bfi.setEnabled(true);
                                }
                            } else {
                                txtfi.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                b_verification_bfi.setEnabled(true);
                            }
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Fecha de inicio Fuera de rango", Toast.LENGTH_SHORT).show();
                        txtfi.setText(null);
                        b_verification_bfi.setSelected(false);
                        b_verification_bfi.setEnabled(false);
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
                    final Calendar p=Calendar.getInstance();
                    int diaf2=p.get(Calendar.DAY_OF_MONTH);
                    int mesf2=p.get(Calendar.MONTH);
                    int anof2=p.get(Calendar.YEAR);
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

                    ////////////////////////////////////////////////////////////////////////
                    if (year<=anof2) {
                        if(year==anof2){
                            if (month<=(mesf2+1)){
                                if(month==(mesf2+1)){
                                    if(dayOfMonth<=diaf2){
                                        if (dias <= 90) {
                                            txtff.setText(fec);
                                            b_verification_bff.setEnabled(true);
                                        } else {
                                            Toast.makeText(MainActivity.this, "dejaste de cotizar hace más de 90 dias sorry", Toast.LENGTH_SHORT).show();
                                            b_verification_bff.setEnabled(false);
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "fecha inválida aún no llega ese día sorry", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    if (dias <= 90) {
                                        txtff.setText(fec);
                                        b_verification_bff.setEnabled(true);
                                    } else {
                                        Toast.makeText(MainActivity.this, "dejaste de cotizar hace más de 90 dias sorry", Toast.LENGTH_SHORT).show();
                                        b_verification_bff.setEnabled(false);
                                    }
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "fecha inválida aún no llega ese día sorry", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            if (dias <= 90) {
                                txtff.setText(fec);
                                b_verification_bff.setEnabled(true);
                            } else {
                                Toast.makeText(MainActivity.this, "dejaste de cotizar hace más de 90 dias sorry", Toast.LENGTH_SHORT).show();
                                b_verification_bff.setEnabled(false);
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "fecha inválida aún no llega ese día sorry", Toast.LENGTH_SHORT).show();
                    }

                ////////////////////////////////////////////////////////////////
                }
            }, diaf, mesf, anof);
            datePickerDialig.show();
        }
    }
}
