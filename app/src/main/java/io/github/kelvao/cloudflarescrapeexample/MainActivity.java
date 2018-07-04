package io.github.kelvao.cloudflarescrapeexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import io.github.kelvao.cloudflarescrape.CloudflareScrape;
import io.github.kelvao.cloudflarescrape.CloudflareScrapeTask;
import io.github.kelvao.cloudflarescrapeexample.adapters.JobAdapter;
import io.github.kelvao.cloudflarescrapeexample.models.JobModel;

public class MainActivity extends AppCompatActivity implements CloudflareScrapeTask.Callback, JobAdapter.Callback, JsoupTask.Callback {

    public static final String URL = "https://codepen.io/jobs/";
    @BindView(R.id.rv_jobs)
    RecyclerView rv_jobs;
    private ArrayList<JobModel> jobs;
    private JobAdapter adapter;
    private HashMap<String, String> cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setURL(URL)
                .setUA("Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0")
                .setCallback(this)
                .build();
        cloudflareScrape.execute();
        jobs = new ArrayList<>();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rv_jobs.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_jobs.setLayoutManager(linearLayoutManager);
        adapter = new JobAdapter(jobs, this);
        rv_jobs.setAdapter(adapter);
    }

    @Override
    public void CloudflareScrapedCoockies(HashMap<String, String> hashMap) {
        this.cookies = hashMap;
        new JsoupTask(URL, cookies, this);
    }

    @Override
    public void onJobClick(JobModel job) {

    }

    @Override
    public void onSuccess(ArrayList<JobModel> jobs) {
        this.jobs = jobs;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Exception error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
