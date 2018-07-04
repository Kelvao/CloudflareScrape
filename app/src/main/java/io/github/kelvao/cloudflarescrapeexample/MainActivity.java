package io.github.kelvao.cloudflarescrapeexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import io.github.kelvao.cloudflarescrape.CloudflareScrapTask;
import io.github.kelvao.cloudflarescrape.CloudflareScrape;

public class MainActivity extends AppCompatActivity implements CloudflareScrapTask.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setURL("https://www.tocomlink.com/download/")
                .setCallback(this)
                .build();
        cloudflareScrape.execute();
    }


    @Override
    public void CloudflareScrapedCoockies(HashMap<String, String> hashMap) {

    }
}
