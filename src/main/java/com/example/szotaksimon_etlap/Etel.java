package com.example.szotaksimon_etlap;

public class Etel {
    private int id;
    private String nev;
    private String leiras;
    private int ar;
    private String kategoria;
    private int kategoriaId;

    public Etel(int id, String nev, String leiras, int ar, String kategoria, int kategoriaId) {
        this.id = id;
        this.nev = nev;
        this.leiras = leiras;
        this.ar = ar;
        this.kategoria = kategoria;
        this.kategoriaId = kategoriaId;
    }

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public int getAr() {
        return ar;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }

    public String getKategoria() {
        return kategoria;
    }
}