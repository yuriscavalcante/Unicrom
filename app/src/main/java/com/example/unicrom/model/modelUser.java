package com.example.unicrom.model;

import java.util.Date;

public class modelUser {
    String nome, email, sexo, senha, dataNasc;
    int idade, mat;


    modelUser(){

    }

    public modelUser(String nome, String email, String sexo, String senha, String dataNasc, int idade, int mat) {
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.idade = idade;
        this.mat = mat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc(){return dataNasc;}

    public void setDataNasc(String dataNasc){this.dataNasc = dataNasc;}

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }
}
