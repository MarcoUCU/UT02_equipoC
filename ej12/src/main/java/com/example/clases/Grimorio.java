package com.example.clases;

import java.util.ArrayList;
import java.util.List;

import com.example.tda.TDAArbolBinario;

public class Grimorio {
    TDAArbolBinario<Hechizo> listaHechizos;

    public Grimorio(){
        this.listaHechizos = new Arbol<>();
    }

    public TDAArbolBinario<Hechizo> getHechizos(){
        return this.listaHechizos;
    }
    public List<Hechizo> obtenerProhibidos() {
        List<Hechizo> lista = new ArrayList<>();
        
        listaHechizos.inOrder(h -> {
            if ((h).esProhibido()) {
                lista.add(h);
            }
        });
        return lista;
    }

    public String generarCantico() {
        StringBuilder sb = new StringBuilder();

        listaHechizos.inOrder(h -> { // Recorre el arbol inorden y por cada elemento hace lo siguiente
            if (h.esProhibido()) {  // Si el hechizo es prohibido lo agrego
                if (sb.length() > 0) { // Si hay algo en el string agrega un -
                    sb.append(" - ");
                }
                sb.append(h.nombre);
            }
        });

        return sb.toString();
}
}
