package com.example.clases;

public class Hechizo implements Comparable<Hechizo> {
    
    public int id;
    public String nombre;

    public Hechizo(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Hechizo hechizo){
        return Integer.compare(this.id, hechizo.id);
    }

    public boolean esProhibido(){
        return id % 2 != 0;
    }
}
