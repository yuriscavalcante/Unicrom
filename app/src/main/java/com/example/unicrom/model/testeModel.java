package com.example.unicrom.model;

public class testeModel {
    String nome, mat;

    testeModel(){

    }

    public testeModel(String nome, String mat) {
        this.nome = nome;
        this.mat = mat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }
}
