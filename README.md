# CloudflareScrape

[![](https://jitpack.io/v/Kelvao/CloudflareScrape.svg)](https://jitpack.io/#Kelvao/CloudflareScrape)

Library to use Jsoup on web pages with Cloudflare


## How to install

Just add you have to add these lines in your build.gradle file:

```xml
allprojects {
    repositories {
      maven { url 'https://jitpack.io' }
      ...
}

implementation 'com.github.Kelvao:CloudflareScrape:0.1.0'

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


The callback returns the coockie to use in your Jsoup Task:

```java
@Override
    public void CloudflareScrapedCoockies(HashMap<String, String> hashMap) {
        //Add your AsyncTask to Jsoup
    }
```


Your AsyncTask to Jsoup will look like this:

```java
//Send the coockies to your constructor or as you prefer
private HashMap<String, String> coockies;

public JsoupTask(HashMap<String, String> coockies) {
        this.coockies = coocies;
}

 @Override
    protected final Void doInBackground(Void... voids) {
        try {
            //And use in your Jsoup           
            Connection.Response response = Jsoup.connect(YOUR_URL).cookie(coockies).execute();
            Document html = response.parse();
            ...
            }
    }
```

Every time you make user Jsoup, use the coockies already generated and passed from callback.

## Credits

This project use [this class](https://github.com/zhkrb/cloudflare-scrape-Android) from [zhkrb](https://github.com/zhkrb)

## License

CloudflareScrape is released under the MIT license. See [LICENSE](https://github.com/Kelvao/CloudflareScrape/blob/master/LICENSE) for details.
