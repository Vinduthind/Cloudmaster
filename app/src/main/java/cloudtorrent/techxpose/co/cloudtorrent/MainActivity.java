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
import android.widget.Button;
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
    String current_roll,replace_roll;
    Button friend_result_button;
    String modUrl = "http://www.m.ptuexam.com/LoginMe.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webview);
        bar =(ProgressBar)findViewById(R.id.Bar);
        webview.setWebViewClient(new myclient());
        friend_result_button=(Button)findViewById(R.id.friend_result_button);
        CookieManager.getInstance().setAcceptCookie(true);
        friend_result_button.setVisibility(View.GONE);
        //dialog-------------------------------------------
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);


        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText current_roll_field = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final EditText replace_roll_field = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput2);




        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                            current_roll= current_roll_field.getText().toString();
                            replace_roll = replace_roll_field.getText().toString();
                                if(modUrl.indexOf(current_roll)!=-1) {

                                    if (current_roll.isEmpty() || replace_roll.isEmpty())
                                    {
                                        Toast.makeText(context, "Both fields are mandatory", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                    String newString = modUrl.replace(current_roll, replace_roll);
                                    webview.loadUrl(newString);
                                    Toast.makeText(context, "Roll no : " + replace_roll + " loading....", Toast.LENGTH_LONG).show();
                                    current_roll_field.setText(replace_roll);
                                    replace_roll_field.setText("");
                                }

                                }
                                else
                                {
                                    if (current_roll.isEmpty() || replace_roll.isEmpty())
                                    {
                                        Toast.makeText(context, "Both fields are mandatory", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                    Toast.makeText(context, "Please check current Roll no.", Toast.LENGTH_SHORT).show();
                                    current_roll_field.setText(current_roll);
                                    replace_roll_field.setText("");
                                    }
                                }


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();



        friend_result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                   alertDialog.show();

            }
        });





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


            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
          /*  if(modUrl.equals("http://www.m.ptuexam.com/frmAnnouncements.aspx" )|| modUrl.equals("http://www.m.ptuexam.com/LoginMe.aspx")|| modUrl.equals("http://www.m.ptuexam.com/frmAnnouncements.aspx#/frmResultTabulation.aspx") || modUrl.equals("http://www.m.ptuexam.com/frmResultTabulation.aspx"))
            {

                friend_result_button.setVisibility(View.GONE);
            }else {

                friend_result_button.setVisibility(View.VISIBLE);
            }
*/








        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String modUrlCheck = url.toString();

            if(modUrlCheck.equals("http://www.m.ptuexam.com/frmAnnouncements.aspx" )
                    || modUrlCheck.equals("http://www.m.ptuexam.com/LoginMe.aspx")
                    || modUrlCheck.equals("http://www.m.ptuexam.com/frmAnnouncements.aspx#/frmResultTabulation.aspx")
                    || modUrlCheck.equals("http://www.m.ptuexam.com/frmResultTabulation.aspx"))
            {

             friend_result_button.setVisibility(View.GONE);
            }else {

                friend_result_button.setVisibility(View.VISIBLE);
            }


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


    @Override
    public void onBackPressed() {
     if (friend_result_button.getVisibility() == View.VISIBLE)
     {


     }

        super.onBackPressed();
    }
}
