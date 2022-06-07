package com.example.unicrom.model;

public class modelCurso {
    String curso, prof, surl, sobre, valor, psurl;
    int tempo;

    modelCurso(){

    }

    public modelCurso(String curso, String prof, String surl, String sobre, String valor, String psurl, int tempo) {
        this.curso = curso;
        this.prof = prof;
        this.surl = surl;
        this.sobre = sobre;
        this.valor = valor;
        this.psurl = psurl;
        this.tempo = tempo;
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

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPsurl() {
        return psurl;
    }

    public void setPsurl(String psurl) {
        this.psurl = psurl;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
