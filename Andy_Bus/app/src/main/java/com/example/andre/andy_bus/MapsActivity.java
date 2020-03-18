package com.example.andre.andy_bus;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private Marker marcador2;
    double lat2 = 0.0;
    double lng2 = 0.0;
    double lati2 = 0.0;
    double longi2 = 0.0;
    double longi = 0.0;
    double lati = 0.0;
    RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            dialog.show();
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng center = null;
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        // setUpMapIfNeeded();

        // recorriendo todas las rutas
        for (int i = 0; i < Utilidades.routes.size(); i++) {
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Obteniendo el detalle de la ruta
            List<HashMap<String, String>> path = Utilidades.routes.get(i);

            // Obteniendo todos los puntos y/o coordenadas de la ruta
            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                if (center == null) {
                    //Obtengo la 1ra coordenada para centrar el mapa en la misma.
                    center = new LatLng(lat, lng);
                }
                points.add(position);
            }

            // Agregamos todos los puntos en la ruta al objeto LineOptions
            lineOptions.addAll(points);
            //Definimos el grosor de las Polilíneas
            lineOptions.width(5);
            //Definimos el color de la Polilíneas
            lineOptions.color(Color.RED);
        }

        //////////////////////////////////////////////////////////////////////////////////////////

        String center2 = null;
        ArrayList<String> points2 = null;
        String position2 = "";
        String position3 = "";
        String bbb = "";

        // PolylineOptions lineOptions = null;
        // setUpMapIfNeeded();

        // recorriendo todas las rutas
        for (int i = 0; i < Utilidades.duracion.size(); i++) {
            points2 = new ArrayList<String>();
            lineOptions = new PolylineOptions();

            // Obteniendo el detalle de la ruta
            List<HashMap<String, String>> path = Utilidades.duracion.get(i);

            // Obteniendo todos los puntos y/o coordenadas de la ruta
            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point2 = path.get(j);

                String lat2 = point2.get("duration");
                String lng2 = point2.get("distance");
                position2 = new String(lat2);
                bbb = bbb + "-" + new String(lat2);
                position3 = new String(lng2);
                points2.add(position2 + " , " + position3);

            }

            // Agregamos todos los puntos en la ruta al objeto LineOptions
            lineOptions.addAll(points);
            //Definimos el grosor de las Polilíneas
            lineOptions.width(5);
            //Definimos el color de la Polilíneas
            lineOptions.color(Color.RED);
        }

        /////////////////////////////////////////////////////////////////////////////////////////


        // Dibujamos las Polilineas en el Google Map para cada ruta
        mMap.addPolyline(lineOptions);

        LatLng origen = new LatLng(Utilidades.coordenadas.getLatitudInicial(), Utilidades.coordenadas.getLongitudInicial());
        mMap.addMarker(new MarkerOptions().position(origen).title("Parada Base"));

        LatLng media = new LatLng(Utilidades.coordenadas.getLatitudMedia(), Utilidades.coordenadas.getLongitudMedia());
        mMap.addMarker(new MarkerOptions().position(media).title("Parada2: " + bbb));

        LatLng media2 = new LatLng(Utilidades.coordenadas.getLatitudMedia2(), Utilidades.coordenadas.getLongitudMedia2());
        mMap.addMarker(new MarkerOptions().position(media2).title("Parada3"));

        LatLng media3 = new LatLng(Utilidades.coordenadas.getLatitudMedia3(), Utilidades.coordenadas.getLongitudMedia3());
        mMap.addMarker(new MarkerOptions().position(media3).title("Parada4"));

        LatLng media4 = new LatLng(Utilidades.coordenadas.getLatitudMedia4(), Utilidades.coordenadas.getLongitudMedia4());
        mMap.addMarker(new MarkerOptions().position(media4).title("Parada5"));

        LatLng media5 = new LatLng(Utilidades.coordenadas.getLatitudMedia5(), Utilidades.coordenadas.getLongitudMedia5());
        mMap.addMarker(new MarkerOptions().position(media5).title("Parada6"));

        LatLng media6 = new LatLng(Utilidades.coordenadas.getLatitudMedia6(), Utilidades.coordenadas.getLongitudMedia6());
        mMap.addMarker(new MarkerOptions().position(media6).title("Parada7"));
        Location location = new Location("a");
        location.setLatitude(Utilidades.coordenadas.getLatitudInicial());
        location.setLongitude(Utilidades.coordenadas.getLongitudInicial());

        Location location2 = new Location("b");

        location2.setLatitude(Utilidades.coordenadas.getLatitudFinal());
        location2.setLongitude(Utilidades.coordenadas.getLongitudFinal());

        double distancia = location.distanceTo(location2);

        LatLng destino = new LatLng(Utilidades.coordenadas.getLatitudFinal(), Utilidades.coordenadas.getLongitudFinal());
        mMap.addMarker(new MarkerOptions().position(destino).title("distancia final es: " + distancia));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 15));

        miUbicacion();

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 10);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("posición: " + coordenadas)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus3)));
        //fromResource(R.drawable.andybus)
        //defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        // mMap.animateCamera(miUbicacion);
    }
    /////////////////////////////////////////////////////////////////////////////////////////
/*
    private void agregarMarcador2(double lati2, double longi2) {
        LatLng coordenadas1 = new LatLng(lati2, longi2);
        CameraUpdate miUbicacion2 = CameraUpdateFactory.newLatLngZoom(coordenadas1, 10);
        if (marcador2 != null) marcador2.remove();
        marcador2 = mMap.addMarker(new MarkerOptions()
                .position(coordenadas1)
                .title("json " + coordenadas1)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus3)));
        //fromResource(R.drawable.andybus)
        //defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        // mMap.animateCamera(miUbicacion);
    }
    */

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat2 = location.getLatitude();
            lng2 = location.getLongitude();
            agregarMarcador(lat2, lng2);
        }
    }


    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
      //  jsonobtener();

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
public void jsonobtener(){
    String url = "http://192.168.137.1/archivosweb/coordenadas2.json";

    JsonObjectRequest requestt = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("coordenadas");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject cordenadas = jsonArray.getJSONObject(i);

                            String pñl = cordenadas.getString("latitud");
                            String lñp = cordenadas.getString("longitud");
                            lati = Double.parseDouble(pñl);
                            longi = Double.parseDouble(lñp);
                            lati2 = lati;
                            longi2 = longi;
                            agregarMarcador2(lati2, longi2);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });

        mQueue.add(requestt);

}
*/
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }



