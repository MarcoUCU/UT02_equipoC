package com.example;

public class Nave implements Comparable<Nave>{
    private int codigo;
    private String clase;
    private int combustible;


    public int getCodigo() {
        return codigo;
    }


    public String getClase() {
        return clase;
    }


    public int getCombustible() {
        return combustible;
    }


    public Nave(int codigo, String clase, int combustible) {
        this.codigo = codigo;
        this.clase = clase;
        this.combustible = combustible;
    }


    @Override
    public int compareTo(Nave o) {
        return this.codigo-o.codigo;
    }
}
