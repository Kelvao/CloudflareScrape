package io.github.kelvao.cloudflarescrapeexample;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.github.kelvao.cloudflarescrapeexample.models.JobModel;

public class JsoupTask extends AsyncTask<Void, Void, ArrayList<JobModel>> {

    private String URL;
    private HashMap<String, String> cookies;
    private Callback callback;
    private ArrayList<JobModel> jobs;

    public JsoupTask(String URL, HashMap<String, String> cookies, Callback callback) {
        this.URL = URL;
        this.cookies = cookies;
        this.callback = callback;
        jobs = new ArrayList<>();
    }

    @Override
    protected ArrayList<JobModel> doInBackground(Void... voids) {
        try {
            Connection.Response response = Jsoup.connect(URL).cookies(cookies).execute();
            Document html = response.parse();
            Elements job_list = html.select("job-list").first().getElementsByTag("job");
            for (int i = 0; i < job_list.size(); i++) {
                JobModel job = new JobModel();
                Elements div = job_list.get(i).getElementsByClass("job");
                job.setLink(div.select("a").attr("href"));
                job.setCompany(div.select("div.job-company").text());
                job.setTitle(div.select("div.job-title").text());
                if (div.select("div.job-featured-text span").hasText()) {
                    job.setFeatured_text(div.select("div.job-featured-text span").text());
                }
                job.setType(div.select("div.job-type").text());
                job.setLocation(div.select("div.job-location").text());
                jobs.add(job);
            }

        } catch (IOException e) {
            callback.onFailed(e);
        }
        return jobs;
    }

    @Override
    protected void onPostExecute(ArrayList<JobModel> jobs) {
        callback.onSuccess(jobs);
    }

    public interface Callback {
        void onSuccess(ArrayList<JobModel> jobs);

        void onFailed(Exception error);
    }
}
