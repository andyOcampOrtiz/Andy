package com.example.webscraping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {

    WebView sitioweb;
    String URL = "https://www.bbc.co.uk/podcasts", url2 = "";
    String urlreferencia = "", mUrl = "https://www.bbc.co.uk/podcasts";
    ProgressDialog mProgressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sitioweb = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = sitioweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        sitioweb.setWebViewClient(new WebViewClient());
        sitioweb.loadUrl(URL);
        //habilita el zoom
        sitioweb.getSettings().setBuiltInZoomControls(true);
        //oculta botones de zoom
        sitioweb.getSettings().setDisplayZoomControls(false);

//Obtiene la URL del album seleccionado para posteriormente poder mandar la URL al metodo de descarga
        sitioweb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final WebView webview = (WebView) view;
                final WebView.HitTestResult result = webview.getHitTestResult();

                if (result.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            // 2. and here we get the url (remember to remove the WebView client and return true so that the hyperlink will not be really triggered)
                            mUrl = url; // mUrl is a member variant of the activity
                            System.out.println("-pppp-----------------------" + mUrl);
                            view.setWebViewClient(new WebViewClient());
                            view.loadUrl(mUrl);
                            new Logo().execute();
                            // getWebsite();
                            return true;

                        }
                    });

                }
                return false;
            }
        });
////////////////////////////////////////////////////////////////////////
        sitioweb.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {


                //Variable objeto para enlazar la descarga
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));

                //Permitir tipos de red para descargar archivos
                myRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                //Muestra en la barra de notificaciones el progreso de la descarga
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                //Muestra en la barra de notificaciones cuando la descarga finaliza
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //Obtiene el nombre del archivo y el tipo de archivo que es en este caso .mp3
                myRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));

                DownloadManager myManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                myManager.enqueue(myRequest);

                Toast.makeText(Main2Activity.this, "Descargando archivo", Toast.LENGTH_LONG).show();

            }
        });

    }


    // El siguiente metodo es para que al momento de presionar la tecla atras si el webview puede regresar regresara y no cerrará la app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (sitioweb.canGoBack()) {
                        sitioweb.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //En este metodo es en donde se inicia el web scraping utilizando la librería jsoup
    public class Logo extends AsyncTask<Void, Void, Void> {
        @Override
        public Void doInBackground(Void... params) {

            try {
                // conecta al sitio web
                Document document = Jsoup.connect(mUrl).get();
                // usa un objeto Element para obtener la clase de los archivos a descargar
                Elements img = document.select("a[class=link-complex popup__list__item island--squashed br-subtle-bg-ontext br-subtle-bg-onbg--hover br-subtle-link-ontext--hover]");
                // loacaliza el atributo con el cual se identificara el archivo a descargar
                System.out.println("url pppppp------------- " + img.attr("href"));
                for (Element link : img) {
                    urlreferencia = "https:" + link.attr("href");

                    System.out.println("url encontrada------------- " + urlreferencia);

                    DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(urlreferencia));
                    myRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                    myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    DownloadManager myManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    myManager.enqueue(myRequest);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
    //El siguiente es otro metodo para poder realizar el web scraping
/*

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements links = doc.select("a[class=link-complex popup__list__item island--squashed br-subtle-bg-ontext br-subtle-bg-onbg--hover br-subtle-link-ontext--hover]");


                    for (Element link : links) {
                        System.out.println("wwwwwwwww-------------------"+link.attr("href"));
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

*/

