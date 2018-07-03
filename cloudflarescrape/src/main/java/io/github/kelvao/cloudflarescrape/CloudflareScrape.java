package io.github.kelvao.cloudflarescrape;

import android.content.Context;

public class CloudflareScrape {

    private String UA;
    private Context context;

    public String getUA() {
        return UA;
    }

    public Context getContext() {
        return context;
    }

    CloudflareScrape(Builder builder) {
        this.UA = builder.UA;
        this.context = builder.context;
    }

    public static class Builder {

        private String UA;
        private Context context;

        public Builder(String UA, Context context) {
            this.UA = UA;
            this.context = context;
        }

        public void setUA(String UA) {
            this.UA = UA;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public CloudflareScrape build(){
            return new CloudflareScrape(this);
        }
    }
}
