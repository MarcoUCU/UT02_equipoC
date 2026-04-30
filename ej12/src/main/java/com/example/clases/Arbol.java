package com.example.clases;

import java.util.function.Consumer;

import com.example.tda.TDAArbolBinario;
import com.example.tda.TDAElemento;

public class Arbol<T> implements TDAArbolBinario<T> {
    
    TDAElemento<T> raiz;

    public T buscar(Comparable<T> criterio) {
        if (raiz == null) return null;
        return raiz.buscar(criterio).getDato();
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new Elemento<>((T) dato);
            return true;
        }
        return raiz.insertar(dato);
    }
    
    @Override
    public boolean eliminar(Comparable<T> criterio) {
        if (raiz == null) return false;

        int antes = cantidadNodos();
        raiz = raiz.eliminar(criterio);
        int despues = cantidadNodos();

        return despues < antes;
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrder(consumidor);
        }
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.preOrder(consumidor);
        }
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.postOrder(consumidor);
        }
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int cantidadNodos() {
        if (raiz == null) return 0;
        return raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        if (raiz == null) return 0;
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        if (raiz == null) return 0;
        return raiz.cantidadNodosInternos();
    }
}