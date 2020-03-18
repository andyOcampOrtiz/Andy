package com.example.pruebaqro;

public class Alumnos {
private int idalumnos;
private String nombreA;
private int idpadres;
private int idmadres;



    public Alumnos() {
        this.idalumnos = idalumnos;
        this.nombreA = nombreA;
        this.idpadres = idpadres;
        this.idmadres=idmadres;
    }

    public int getIdalumnos() {
        return idalumnos;
    }

    public void setIdalumnos(int idalumnos) {
        this.idalumnos = idalumnos;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public int getIdpadres() {
        return idpadres;
    }

    public void setIdpadres(int idpadres) {
        this.idpadres = idpadres;
    }

    public int getIdmadres() { return idmadres; }

    public void setIdmadres(int idmadres) { this.idmadres = idmadres; }
}
