package io.github.kelvao.cloudflarescrapeexample.models;

public class JobModel {

    private String company;
    private String title;
    private String featured_text;
    private String type;
    private String location;
    private String link;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeatured_text() {
        return featured_text;
    }

    public void setFeatured_text(String featured_text) {
        this.featured_text = featured_text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
