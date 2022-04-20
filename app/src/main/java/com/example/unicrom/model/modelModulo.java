package com.example.unicrom.model;

public class modelModulo {
    String modulo, moduloId, prof, surl/*,url*/;


    modelModulo(){

    }

    public modelModulo(String modulo, String moduloId, String prof, String surl) {
        this.modulo = modulo;
        this.moduloId = moduloId;
        this.prof = prof;
        this.surl = surl;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getModuloId() {
        return moduloId;
    }

    public void setModuloId(String moduloId) {
        this.moduloId = moduloId;
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

    /*
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }*/
}
