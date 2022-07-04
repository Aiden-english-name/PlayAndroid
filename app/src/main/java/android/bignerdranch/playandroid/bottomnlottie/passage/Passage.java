package android.bignerdranch.playandroid.bottomnlottie.passage;

import androidx.appcompat.app.AppCompatActivity;

import android.bignerdranch.playandroid.R;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Passage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        try {
            WebView webView = (WebView) findViewById(R.id.web_view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}