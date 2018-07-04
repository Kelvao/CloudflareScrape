package io.github.kelvao.cloudflarescrape;

@SuppressWarnings("ALL")
public class CloudflareScrape {

    private String UA;
    private String URL;
    private CloudflareScrapTask.Callback callback;

    public String getUA() {
        return UA;
    }

    public String getURL() {
        return URL;
    }

    public CloudflareScrapTask.Callback getCallback() {
        return callback;
    }

    CloudflareScrape(Builder builder) {
        this.UA = builder.UA;
        this.URL = builder.URL;
        this.callback = builder.callback;
    }

    public void execute() {
        CloudflareScrapTask cloudflareScrapTask = new CloudflareScrapTask(UA, URL, callback);
        cloudflareScrapTask.execute();
    }

    public static class Builder {

        private String UA;
        private String URL;
        private CloudflareScrapTask.Callback callback;

        public void setUA(String UA) {
            this.UA = UA;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public void setCallback(CloudflareScrapTask.Callback callback) {
            this.callback = callback;
        }

        public CloudflareScrape build() {
            return new CloudflareScrape(this);
        }
    }
}
