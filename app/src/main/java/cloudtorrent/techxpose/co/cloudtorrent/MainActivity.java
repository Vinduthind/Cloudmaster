package cloudtorrent.techxpose.co.cloudtorrent;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    WebView webview;
    ProgressBar bar;
     AdView mAdView;
    String modUrl = "http://www.m.ptuexam.com/LoginMe.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webview);
        bar =(ProgressBar)findViewById(R.id.Bar);
        webview.setWebViewClient(new myclient());

        CookieManager.getInstance().setAcceptCookie(true);


        //dialog-------------------------------------------
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);


        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        //---------------------------------------------dialog




        //advertisement------------------------------------------


        MobileAds.initialize(getApplicationContext(),"ca-app-pub-7426325861660851~6514118927");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //-----------------------------------------------





        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        //webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(modUrl);

    }
    public  class myclient extends WebViewClient
    {



        @Override
        public void onPageFinished(WebView view, String url) {
           modUrl=url.toString();
            Toast.makeText(MainActivity.this, modUrl, Toast.LENGTH_SHORT).show();

            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);



        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&webview.canGoBack()){

            webview.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }
}
