package io.github.kelvao.cloudflarescrape;

import android.os.AsyncTask;

import java.util.HashMap;

class CloudflareScrapeTask extends AsyncTask<Void, Void, HashMap<String, String>> {

    private final String UA;
    private final String URL;
    private final Callback callback;

    CloudflareScrapeTask(String UA, String URL, Callback callback) {
        this.UA = UA;
        this.URL = URL;
        this.callback = callback;
    }

    @Override
    protected HashMap<String, String> doInBackground(Void... voids) {
        CloudflareScrapeCore cloudflareScrapeCore = new CloudflareScrapeCore(URL);
        cloudflareScrapeCore.setUA(UA);
        return cloudflareScrapeCore.Coockies2HashMap(cloudflareScrapeCore.cookies());
    }

    @Override
    protected void onPostExecute(HashMap<String, String> coockies) {
        callback.CloudflareScrapedCoockies(coockies);
    }

    public interface Callback {
        void CloudflareScrapedCoockies(HashMap<String, String> coockies);
    }
}
