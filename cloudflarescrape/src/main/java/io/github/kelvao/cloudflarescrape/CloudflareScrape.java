package io.github.kelvao.cloudflarescrape;

@SuppressWarnings("ALL")
public class CloudflareScrape {

    private String UA;
    private String URL;
    private CloudflareScrapeTask.Callback callback;

    CloudflareScrape(Builder builder) {
        this.UA = builder.UA;
        this.URL = builder.URL;
        this.callback = builder.callback;
    }

    public void execute() throws RuntimeException {
        if (URL != null && callback != null) {
            CloudflareScrapeTask cloudflareScrapTask = new CloudflareScrapeTask(UA, URL, callback);
            cloudflareScrapTask.execute();
        } else {
            String message = "";
            if (URL == null) {
                message = "Url";
                if (callback == null)
                    message = message + " and Callback";
            } else if (callback == null) {
                message = "Callback";
            }
            throw new RuntimeException(message + " is null", new Throwable());
        }
    }

    public static class Builder {

        private String UA;
        private String URL;
        private CloudflareScrapeTask.Callback callback;

        public Builder setUA(String UA) {
            this.UA = UA;
            return this;
        }

        public Builder setURL(String URL) {
            this.URL = URL;
            return this;
        }

        public Builder setCallback(CloudflareScrapeTask.Callback callback) {
            this.callback = callback;
            return this;
        }

        public CloudflareScrape build() {
            return new CloudflareScrape(this);
        }
    }
}
