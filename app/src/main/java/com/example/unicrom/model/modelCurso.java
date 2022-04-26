package com.example.unicrom.model;

public class modelCurso {
    String curso, prof, surl;

    modelCurso(){

    }

    public modelCurso(String curso, String prof, String surl) {
        this.curso = curso;
        this.prof = prof;
        this.surl = surl;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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
}
