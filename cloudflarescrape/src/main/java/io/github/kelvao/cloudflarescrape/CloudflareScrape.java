package io.github.kelvao.cloudflarescrape;

import android.content.Context;

public class CloudflareScrape {

    private String UA;
    private String URL;
    private Context context;
    private CloudflareScrapTask.Callback callback;

    public String getUA() {
        return UA;
    }

    public String getURL() {
        return URL;
    }

    public Context getContext() {
        return context;
    }

    public CloudflareScrapTask.Callback getCallback() {
        return callback;
    }

    CloudflareScrape(Builder builder) {
        this.UA = builder.UA;
        this.URL = builder.URL;
        this.context = builder.context;
        this.callback = builder.callback;
    }

    public void execute(){

    }

    public static class Builder {

        private String UA;
        private String URL;
        private Context context;
        private CloudflareScrapTask.Callback callback;

        public void setUA(String UA) {
            this.UA = UA;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public void setCallback(CloudflareScrapTask.Callback callback) {
            this.callback = callback;
        }

        public CloudflareScrape build() {
            return new CloudflareScrape(this);
        }
    }
}
