package com.example;
import java.util.function.Consumer;

public class ElementoBinario<T> implements TDAElemento<T> {

    private T dato;
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;

    public ElementoBinario(T dato) {
        this.dato = dato;
    }

    @SuppressWarnings("unchecked")
    static <T> T comoT(Comparable<T> dato) {
        return (T) dato;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return hijoDerecho;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {
        int cmp = criterioBusqueda.compareTo(dato);

        if (cmp == 0) return this;

        TDAElemento<T> encontrado = null;

        if (hijoIzquierdo != null)
            encontrado = hijoIzquierdo.buscar(criterioBusqueda);

        if (encontrado == null && hijoDerecho != null)
            encontrado = hijoDerecho.buscar(criterioBusqueda);

        return encontrado;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        // rellena primero izquierda, luego derecha
        if (hijoIzquierdo == null) {
            hijoIzquierdo = new ElementoBinario<>(comoT(dato));
            return true;
        }

        if (hijoDerecho == null) {
            hijoDerecho = new ElementoBinario<>(comoT(dato));
            return true;
        }

        if (hijoIzquierdo.insertar(dato)) return true;

        return hijoDerecho.insertar(dato);
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        return null;
    }

    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) hijoIzquierdo.inOrder(consumidor);
        consumidor.accept(this);
        if (hijoDerecho != null) hijoDerecho.inOrder(consumidor);
    }

    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (hijoIzquierdo != null) hijoIzquierdo.preOrder(consumidor);
        if (hijoDerecho != null) hijoDerecho.preOrder(consumidor);
    }

    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) hijoIzquierdo.postOrder(consumidor);
        if (hijoDerecho != null) hijoDerecho.postOrder(consumidor);
        consumidor.accept(this);
    }

    @Override
    public boolean esHoja() {
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    @Override
    public int cantidadHojas() {
        if (esHoja()) return 1;

        int total = 0;

        if (hijoIzquierdo != null) total += hijoIzquierdo.cantidadHojas();
        if (hijoDerecho != null) total += hijoDerecho.cantidadHojas();

        return total;
    }

    @Override
    public int cantidadNodosInternos() {
        return cantidadNodos() - cantidadHojas();
    }

    @Override
    public int cantidadNodos() {
        int total = 1;

        if (hijoIzquierdo != null) total += hijoIzquierdo.cantidadNodos();
        if (hijoDerecho != null) total += hijoDerecho.cantidadNodos();

        return total;
    }

    @Override
    public int altura() {
        int izq = hijoIzquierdo == null ? 0 : hijoIzquierdo.altura();
        int der = hijoDerecho == null ? 0 : hijoDerecho.altura();

        return 1 + Math.max(izq, der);
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda.compareTo(dato) == 0) return 0;

        if (hijoIzquierdo != null) {
            int n = hijoIzquierdo.obtenerNivel(criterioBusqueda);
            if (n != -1) return n + 1;
        }

        if (hijoDerecho != null) {
            int n = hijoDerecho.obtenerNivel(criterioBusqueda);
            if (n != -1) return n + 1;
        }

        return -1;
    }
}