package com.example.unicrom.model;

public class modelCurso {
    String curso, id, prof, surl, url, modulo, moduloId, profM, surlm;

    modelCurso(){

    }

    public modelCurso(String curso, String id, String prof, String surl, String url, String modulo, String moduloId, String profM, String surlm) {
        this.curso = curso;
        this.id = id;
        this.prof = prof;
        this.surl = surl;
        this.url = url;
        this.modulo = modulo;
        this.moduloId = moduloId;
        this.profM = profM;
        this.surlm = surlm;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public String getProfM() {
        return profM;
    }

    public void setProfM(String profM) {
        this.profM = profM;
    }

    public String getSurlm() {
        return surlm;
    }

    public void setSurlm(String surlm) {
        this.surlm = surlm;
    }
}
