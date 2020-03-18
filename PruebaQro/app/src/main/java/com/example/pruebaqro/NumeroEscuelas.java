package com.example.pruebaqro;

public class NumeroEscuelas {
    private int idno;
    private int idalumno;
    private int idescuela;

    public NumeroEscuelas(int idno, int idalumno, int idescuela) {
        this.idno = idno;
        this.idalumno = idalumno;
        this.idescuela = idescuela;
    }

    public int getIdno() {
        return idno;
    }

    public void setIdno(int idno) {
        this.idno = idno;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public int getIdescuela() {
        return idescuela;
    }

    public void setIdescuela(int idescuela) {
        this.idescuela = idescuela;
    }
}
