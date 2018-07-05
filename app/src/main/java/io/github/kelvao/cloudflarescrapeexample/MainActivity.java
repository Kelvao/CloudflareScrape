package io.github.kelvao.cloudflarescrapeexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.kelvao.cloudflarescrape.CloudflareScrape;
import io.github.kelvao.cloudflarescrape.CloudflareScrapeTask;
import io.github.kelvao.cloudflarescrapeexample.adapters.JobAdapter;
import io.github.kelvao.cloudflarescrapeexample.models.JobModel;

public class MainActivity extends AppCompatActivity implements CloudflareScrapeTask.Callback, JobAdapter.Callback, JsoupTask.Callback {

    public static final String URL = "https://codepen.io/jobs/";
    @BindView(R.id.rv_jobs)
    RecyclerView rv_jobs;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srl_refresh;
    private ArrayList<JobModel> jobs;
    private JobAdapter adapter;
    private HashMap<String, String> cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setURL(URL)
                .setUA("Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0")
                .setCallback(this)
                .build();
        cloudflareScrape.execute();
        jobs = new ArrayList<>();
        setupSwipeRefreshLayout();
        setupRecyclerView();
    }

    private void setupSwipeRefreshLayout() {
        srl_refresh.setRefreshing(true);
        srl_refresh.setOnRefreshListener(() -> {
            jobs = new ArrayList<>();
            adapter.notifyDataSetChanged();
            new JsoupTask(URL, cookies, this).execute();
            updateUi(true);
        });
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
    public void CloudflareScrapedCookies(HashMap<String, String> hashMap) {
        cookies = hashMap;
        new JsoupTask(URL, hashMap, this).execute();
    }

    @Override
    public void onJobClick(String link) {
        String url = "https://codepen.io" + link;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void updateUi(boolean isLoading) {
        srl_refresh.setRefreshing(isLoading);
        rv_jobs.setVisibility(isLoading ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onSuccess(ArrayList<JobModel> jobs) {
        this.jobs.addAll(jobs);
        adapter.notifyDataSetChanged();
        updateUi(false);
    }

    @Override
    public void onFailed(Exception error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        updateUi(false);
    }
}
