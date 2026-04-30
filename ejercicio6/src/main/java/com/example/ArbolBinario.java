package com.example;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T> {

    private TDAElemento<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public T buscar(Comparable<T> criterioBusqueda) {
        if (esVacio()) return null;
        TDAElemento<T> encontrado = raiz.buscar(criterioBusqueda);
        if (encontrado != null) {
            return encontrado.getDato();
        } else {
            return null;
        }
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (esVacio()) {
            raiz = new Elemento<>((T) dato);
            return true;
        }
        return raiz.insertar(dato);
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (esVacio()) return false;
        if (buscar(criterioBusqueda) == null) return false;
        raiz = raiz.eliminar(criterioBusqueda);
        return true;
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (!esVacio()) {
            raiz.inOrder(e -> consumidor.accept(e.getDato()));
        }
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (!esVacio()) {
            raiz.preOrder(e -> consumidor.accept(e.getDato()));
        }
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (!esVacio()) {
            raiz.postOrder(e -> consumidor.accept(e.getDato()));
        }
    }

    public int altura() {
        if (esVacio()) return -1;
        return raiz.altura();
    }

    @Override
    public int cantidadNodos() {
        if (esVacio()) return 0;
        return raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        if (esVacio()) return 0;
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        if (esVacio()) return 0;
        return raiz.cantidadNodosInternos();
    }

    public TDALista<TDAElemento<T>> completos() {
        if (esVacio()) {
            return new ListaEnlazada<>();
        }
        return ((Elemento<T>) raiz).completos();
    }

    public TDALista<TDAElemento<T>> enNivel(int nivel) {
        if (esVacio()) {
            return new ListaEnlazada<>();
        }
        return ((Elemento<T>) raiz).enNivel(nivel);
    }
}