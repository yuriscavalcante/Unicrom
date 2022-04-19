package com.example.unicrom.model;

public class modelModulo {
    String modulo, id, prof, surl, url;


    modelModulo(){

    }

    public modelModulo(String modulo, String id, String prof, String surl, String url) {
        this.modulo = modulo;
        this.id = id;
        this.prof = prof;
        this.surl = surl;
        this.url = url;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
