package io.github.kelvao.cloudflarescrape;

@SuppressWarnings("ALL")
public class CloudflareScrape {

    private String UA;
    private String URL;
    private CloudflareScrapTask.Callback callback;

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

        public Builder setUA(String UA) {
            this.UA = UA;
            return this;
        }

        public Builder setURL(String URL) {
            this.URL = URL;
            return this;
        }

        public Builder setCallback(CloudflareScrapTask.Callback callback) {
            this.callback = callback;
            return this;
        }

        public CloudflareScrape build() {
            return new CloudflareScrape(this);
        }
    }
}
