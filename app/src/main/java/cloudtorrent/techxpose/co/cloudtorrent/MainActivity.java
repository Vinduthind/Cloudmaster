package cloudtorrent.techxpose.co.cloudtorrent;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    ProgressBar bar;
     AdView mAdView;
    String modUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webview);
        bar =(ProgressBar)findViewById(R.id.Bar);
        webview.setWebViewClient(new myclient());

        CookieManager.getInstance().setAcceptCookie(true);
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
        webview.loadUrl("http://www.m.ptuexam.com/LoginMe.aspx");

    }
    public  class myclient extends WebViewClient
    {

//jhgjhjhjsjhdvhivdhkhjkfjdj

        @Override
        public void onPageFinished(WebView view, String url) {
           modUrl=url.toString();

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
