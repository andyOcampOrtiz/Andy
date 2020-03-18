package com.example.andre.andy_bus;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText txtLatInicio,txtLongInicio,txtLatFinal,txtLongFinal, txtcorreo, txtpassword;

    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    RequestQueue mQueue;
    Button btnlogin;
   // RadioButton RBSesion;
    TextView tvregistrar;
    String duration = "";
    double LatI=18.875333;
    double LongI=-99.219921;
    double LatM=18.873183;
    double LongM=-99.225037;
    double LatM2=18.879012;
    double LongM2=-99.22752;
    double LatM3=18.886832;
    double LongM3=-99.228526;
    double LatM4=18.894988;
    double LongM4=-99.228953;
    double LatM5=18.905655;
    double LongM5=-99.231796;
    double LatM6=18.916553;
    double LongM6=-99.237071;
    double LatF=18.924592;
    double LongF=-99.238168;
    Toolbar toolbar;
    //private static final String STRING_PREFERENCES="michattimereal.Mensaje.Mensajeria";
    //private static final String PREFERENCE_ESTADO_BUTTON_SESION="estado.button.sesion";
    //private boolean isActivateRadioButton;
    //private final int REQUEST_LOCATION=0;
    //private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////indica en que estado guardo e radio button para iniciar el siguiente main
       /* if(obtenerEstadoButton()){
            Intent log = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(log);
            finish();
        } */

        txtcorreo=(EditText)findViewById(R.id.editcorreo);
        txtpassword=(EditText)findViewById(R.id.editpassword);
        tvregistrar=(TextView) findViewById(R.id.txtregistro);
      //  RBSesion=(RadioButton) findViewById(R.id.radioButton);

        //isActivateRadioButton=RBSesion.isChecked();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
           final int REQUEST_LOCATION = 2;

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Display UI and wait for user interaction
            } else {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION);
            }
        } else {
            // permission has been granted, continue as usual
         //   Location myLocation =
           //         LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

////////////////////////////////////////////////////////////es para cambiar el estado del botton a verdadero o falso
     /*   RBSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivateRadioButton){
                    RBSesion.setChecked(false);
                }
                isActivateRadioButton=RBSesion.isChecked();
            }
        }); */

        btnlogin=(Button) findViewById(R.id.button1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(txtcorreo.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(), "algun campo esta vacio", Toast.LENGTH_SHORT).show();
               }
               else {
                   String crreolog= URLEncoder.encode(txtcorreo.getText().toString().trim());
                   String psswordlog=URLEncoder.encode(txtpassword.getText().toString().trim());
                   login("http://192.168.137.1:80/aplicacion/login.php?correolog=" + crreolog + "&passwordlog=" + psswordlog + "");
               }
            }
        });
       request= Volley.newRequestQueue(this);
        mQueue= Volley.newRequestQueue(this);

        tvregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(miIntent);
            }
        });


    }
////////////////////////////////////////////////////////////////////////////////////////7 codigo para verificar estado de radio button
/*public void guardarEstadoButton(){
    SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
    preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,RBSesion.isChecked()).apply();
}
public boolean obtenerEstadoButton(){
    SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
   return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);
    }*/
    //////////////////////////////////////////////////////////////////////////////////////
   /* Latitud : 17.581
Longitud : -99.881*/

    private void webServiceObtenerRuta(double LatitudInicial, double LongitudInicial, double LatitudFinal, double LongitudFinal) {

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + LatitudInicial + "," + LongitudInicial + "&destination=" + LatitudFinal + "," + LongitudFinal + "&waypoints="+LatM+","+LongM+"|"+LatM2+","+LongM2+"|"+LatM3+","+LongM3+"|"+LatM4+","+LongM4+"|"+LatM5+","+LongM5+"|"+LatM6+","+LongM6+"&key=AIzaSyBHFrDTu6GKCqZoFSlPufft3BgF7QDNgYQ";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
                //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
                //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.
                JSONArray jRoutes = null;
                JSONArray jLegs = null;
                JSONArray jSteps = null;
                JSONArray jlatt = null;
                JSONArray jlonn = null;

                String perro="";
                String perro2="";
                String jlat="";
                String jlon="";
                int ll=0;
                int m=0;
                int n=0;
                int perro3=0;
                int perro4=0;
                String hh="";
                int oso=0;
                int oso2=0;


                try {

                    jRoutes = response.getJSONArray("routes");

                    /** Traversing all routes */
                    for(int i=0;i<jRoutes.length();i++){
                        jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                        List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();
                        List<HashMap<String, String>> path2 = new ArrayList<HashMap<String, String>>();

                        /** Traversing all legs */
                        for(int j=0;j<jLegs.length();j++){
                            jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");


                            /** Traversing all steps */
                            for(int k=0;k<jSteps.length();k++){
                                ll++;
                                String polyline = "";
                                polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                                HashMap<String, String> hm2 = new HashMap<String, String>();

                                perro=(String) ((JSONObject)((JSONObject)jSteps.get(k)).get("duration")).get("text");
                                perro2=(String) ((JSONObject)((JSONObject)jSteps.get(k)).get("distance")).get("text");

                              //System.out.println("Distancia: "+ll+" es: "+ perro2);
                            //    System.out.println("Duracion es: "+perro);
                                hh=perro.substring(0,1);
                               m=Integer.parseInt(hh);
                                perro3+=m;
                          //      System.out.println("conversion es: "+m);

                               hm2.put("duration", (String) ((JSONObject)((JSONObject)jSteps.get(k)).get("duration")).get("text"));
                                hm2.put("distance", (String) ((JSONObject)((JSONObject)jSteps.get(k)).get("distance")).get("text"));
                                path2.add(hm2);

                                        List<LatLng> list = decodePoly(polyline);

                                /** Traversing all points */
                                for(int l=0;l<list.size();l++){
                                    HashMap<String, String> hm = new HashMap<String, String>();
                                    hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                                    hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                                    path.add(hm);
                                }
                            }
                            oso+=perro3;
                           System.out.println("duracion total: "+perro3);
                            perro3=0;

                           // System.out.println("duracion de viaje total es: "+oso);
                           // System.out.println("Duracion es: "+ll+" "+m);
                            Utilidades.routes.add(path);
                            Utilidades.duracion.add(path2);
                        }
                        System.out.println("duracion de viaje total es: "+oso);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
            }
        }
        );

        request.add(jsonObjectRequest);
    }

   //public List<List<HashMap<String,String>>> parse(JSONObject jObject){
        //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
        //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
        //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.
     /*   JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        try {

            jRoutes = jObject.getJSONArray("routes");*/

            /** Traversing all routes */
            /*for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();
*/
                /** Traversing all legs */
  /*              for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");
*/
                    /** Traversing all steps */
  /*                  for(int k=0;k<jSteps.length();k++){
                        String polyline = "";
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePoly(polyline);
*/
                        /** Traversing all points */
  /*                      for(int l=0;l<list.size();l++){
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                            hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                            path.add(hm);
                        }

                    }
                    Utilidades.routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
        }
        return Utilidades.routes;
    }

*/


    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////

private void login(String URL){

    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray jsonArray) {
            JSONObject jsonObject = null;
           // guardarEstadoButton();/////button radio
            for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        if (jsonArray.getJSONObject(i).toString().equals("null")){

                        }else {
                            Utilidades.coordenadas.setLatitudInicial(LatI);
                            Utilidades.coordenadas.setLongitudInicial(LongI);
                            Utilidades.coordenadas.setLatitudMedia(LatM);
                            Utilidades.coordenadas.setLongitudMedia(LongM);
                            Utilidades.coordenadas.setLatitudMedia2(LatM2);
                            Utilidades.coordenadas.setLongitudMedia2(LongM2);
                            Utilidades.coordenadas.setLatitudMedia3(LatM3);
                            Utilidades.coordenadas.setLongitudMedia3(LongM3);
                            Utilidades.coordenadas.setLatitudMedia4(LatM4);
                            Utilidades.coordenadas.setLongitudMedia4(LongM4);
                            Utilidades.coordenadas.setLatitudMedia5(LatM5);
                            Utilidades.coordenadas.setLongitudMedia5(LongM5);
                            Utilidades.coordenadas.setLatitudMedia6(LatM6);
                            Utilidades.coordenadas.setLongitudMedia6(LongM6);
                            Utilidades.coordenadas.setLatitudFinal(LatF);
                            Utilidades.coordenadas.setLongitudFinal(LongF);

                            webServiceObtenerRuta(LatI, LongI, LatF, LongF);

                            Intent log = new Intent(MainActivity.this, MapsActivity.class);
                            startActivity(log);
                            finish();

                         //   request= Volley.newRequestQueue(getApplicationContext());
                        }

                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

            Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();

        }
    });
    RequestQueue requestQueue= Volley.newRequestQueue(this);
    requestQueue.add(jsonArrayRequest);
}



}
