package com.example.civiladvocacy;

import java.io.Serializable;

public class Official implements Serializable {
private String official_name;
private String official_office;
private String official_party;
private String phone_number;
private String official_email_id;
private String official_address;
private String official_website;
private int official_photo;

private String official_photo_url;
private String twitter;
private String facebook;
private String youtube;

Official(){
    this.official_name = "";
    this.official_email_id = "";
    this.official_office = "";
    this.official_party = "";
    this.phone_number = "";
    this.official_address = "";
    this.official_website = "";
    this.official_photo_url = "";
    this.facebook = "";
    this.twitter = "";
    this.youtube = "";
}

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getOfficial_photo_url() {
        return official_photo_url;
    }

    public void setOfficial_photo(int official_photo) {
        this.official_photo = official_photo;
    }

    public int getOfficial_photo() {
        return official_photo;
    }

    public String getOfficial_address() {
        return official_address;
    }

    public String getOfficial_email_id() {
        return official_email_id;
    }

    public String getOfficial_name() {
        return official_name;
    }

    public String getOfficial_website() {
        return official_website;
    }

    public String getOfficial_office() {
        return official_office;
    }

    public String getOfficial_party() {
        return official_party;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setOfficial_email_id(String official_email_id) {
        this.official_email_id = official_email_id;
    }

    public void setOfficial_name(String official_name) {
        this.official_name = official_name;
    }

    public void setOfficial_office(String official_office) {
        this.official_office = official_office;
    }

    public void setOfficial_party(String official_party) {
        this.official_party = official_party;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setOfficial_address(String official_address) {
        this.official_address = official_address;
    }

    public void setOfficial_website(String official_website) {
        this.official_website = official_website;
    }

    public void setOfficial_photo_url(String official_photo_url) {
        this.official_photo_url = official_photo_url;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
