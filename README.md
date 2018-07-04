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

Instanciate a new CloudflareScrap object;

```java
CloudflareScrape cloudflareScrape = new CloudflareScrape.Builder()
                .setURL("your url")
                .setCallback(this) //implements this callback any way
                .build();
```

And just execute the AsyncTask:

```java
cloudflareScrape.execute();
```
The callback returns the coockie to use in your Jsoup

```java
@Override
    public void CloudflareScrapedCoockies(HashMap<String, String> hashMap) {
        //Add your AsyncTaks to Jsoup
    }
    
//Your AsyncTaks to Jsoup will look like this
 @Override
    protected final Void doInBackground(Void... voids) {
        try {
            Connection.Response response = Jsoup.connect(URL).cookie(your hashmap with coockies).execute(); //maybe you get this with your constructor
            Document html = response.parse();
            ...
            }
    }
