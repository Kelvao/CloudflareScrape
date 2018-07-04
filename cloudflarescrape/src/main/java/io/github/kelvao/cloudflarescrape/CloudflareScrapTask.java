package io.github.kelvao.cloudflarescrape;

import android.os.AsyncTask;

import java.util.HashMap;

public class CloudflareScrapTask extends AsyncTask<Void, Void, HashMap<String, String>> {

    private final Callback callback;
    private final String UA;
    private final String URL;

    public CloudflareScrapTask(Callback callback, String UA, String URL) {
        this.callback = callback;
        this.UA = UA;
        this.URL = URL;
    }

    @Override
    protected HashMap<String, String> doInBackground(Void... voids) {
        CloudflareScrapeCore cloudflareScrapeCore = new CloudflareScrapeCore(URL);
        cloudflareScrapeCore.setUA(UA);
        return cloudflareScrapeCore.List2Map(cloudflareScrapeCore.cookies());
    }

    @Override
    protected void onPostExecute(HashMap<String, String> stringStringHashMap) {
        callback.onAsyncResult(stringStringHashMap);
    }

    public interface Callback {
        void onAsyncResult(HashMap<String, String> result);
    }
}
