package io.github.kelvao.cloudflarescrapeexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import io.github.kelvao.cloudflarescrape.CloudflareScrape;
import io.github.kelvao.cloudflarescrape.CloudflareScrapeTask;

public class MainActivity extends AppCompatActivity implements CloudflareScrapeTask.Callback {

    private HashMap<String, String> coockies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setCallback(this)
                .build();
        cloudflareScrape.execute();
    }

    @Override
    public void CloudflareScrapedCoockies(HashMap<String, String> hashMap) {
        this.coockies = hashMap;
    }

}
