package com.example.webscraping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

   // String URL = "https://www.bbc.co.uk/podcasts";
   // String prueba = "https://open.live.bbc.co.uk/mediaselector/6/redir/version/2.0/mediaset/audio-nondrm-download/proto/https/vpid/p085fvmc.mp3";
    Button boton;
   // ProgressDialog mProgressDialog;
   // ImageView logo;
   // Bitmap bitmap;
   // String imgSrc = "";
   // String url = "https://ichef.bbci.co.uk/images/ic/480x480/p079zwcd.jpg";
    private static final short REQUEST_CODE = 6545;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.button1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Intent a= new Intent(MainActivity.this, Main2Activity.class);
                //  startActivity(a);
                //  new Title().execute();
                // new Logo().execute();

                // Ejecutamos metodo para dar permisos
                checkSelfPermission();


            }
        });
    }

    private static boolean isDownloadManagerAvailable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }

    private void checkSelfPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

        } else {

            // executeDownload();
            // new Logo().execute();
            Intent a = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(a);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted! Do the work
                    //  executeDownload();
                    // new Logo().execute();
                    Intent a = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(a);
                } else {
                    // permission denied!
                    Toast.makeText(this, "Debes de dar permisos para navegar ", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}


    // Ignoren todo el comentario siguiente era para realizar pruebas

/*
    private void executeDownload() {

        // registrer receiver in order to verify when download is complete
        registerReceiver(new DonwloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Downloading file " + NAME_FILE);
        request.setTitle("Downloading");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, NAME_FILE);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }
    public class DonwloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                Toast.makeText(context,"Download completed", Toast.LENGTH_LONG).show();
                // DO SOMETHING WITH THIS FILE
            }
        }
    }


    private class Title extends AsyncTask<Void, Void, Void> {
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Cargando Sitio");
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(URL).get();
                // Get the html document title
                title = document.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView

            mProgressDialog.dismiss();
        }
    }

    public class Logo extends AsyncTask<Void, Void, Void> {


        @Override
        public void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Cargando imagen");
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        public Void doInBackground(Void... params) {

            try {
                // Connect to the web site
                Document document = Jsoup.connect(URL).get();
                // Using Elements to get the class data
                Elements img = document.select("a[class=rmp-card__body] img[src]");
                // Locate the src attribute
                imgSrc = "https:"+img.attr("src");
                System.out.println("url encontrada------------- "+imgSrc);
                // Download image from URL
                InputStream input = new java.net.URL(imgSrc).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            // Set downloaded image into ImageView
            logo = (ImageView) findViewById(R.id.logo2);
            logo.setImageBitmap(bitmap);
            mProgressDialog.dismiss();


        }
    }
    */






