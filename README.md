# CloudflareScrape

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/Kelvao/CloudflareScrape.svg)](https://jitpack.io/#Kelvao/CloudflareScrape)

Library to use Jsoup on web pages with Cloudflare


## How to install

Just add you have to add these lines in your build.gradle file:

```groovy
allprojects {
    repositories {
      maven { url 'https://jitpack.io' }
      ...
}

implementation 'com.github.Kelvao:CloudflareScrape:0.2.0'

```


## How to use

Instantiate a new CloudflareScrap object and set the Builder:

```java
CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setURL("your url")
                .setCallback(this) //implement wherever you want
                .build();
```


And just execute the AsyncTask only once (probably in your "onCreateView"):

```java
cloudflareScrape.execute();
```


The callback returns the cookie to use in your Jsoup Task:

```java
@Override
    public void CloudflareScrapedCookies(HashMap<String, String> hashMap) {
        //Add your AsyncTask to Jsoup
    }
```


Your AsyncTask to Jsoup will look like this:

```java
//Send the cookies to your constructor or as you prefer
private HashMap<String, String> cookies;

public JsoupTask(HashMap<String, String> cookies) {
        this.cookies = cookies;
}

 @Override
    protected final Void doInBackground(Void... voids) {
        try {
            //And use in your Jsoup           
            Connection.Response response = Jsoup.connect(YOUR_URL).cookies(cookies).execute();
            Document html = response.parse();
            ...
            }
    }
```

__Every time you use Jsoup, use the cookies already generated from callback.__

## Credits

This project use [this class](https://github.com/zhkrb/cloudflare-scrape-Android) from [zhkrb](https://github.com/zhkrb).

## License

CloudflareScrape is released under the Apache 2.0 license. See [LICENSE](https://github.com/Kelvao/CloudflareScrape/blob/master/LICENSE) for details.
