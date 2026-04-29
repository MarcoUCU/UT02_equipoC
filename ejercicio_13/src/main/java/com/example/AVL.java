package com.example;

public class AVL<T extends Comparable<T>> implements TDAArbol<T> {
    protected NodoArbol<T> raiz;

    public AVL() {
        this.raiz = null;
    }

    @Override
    public boolean estaVacio() {
        return this.raiz == null;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    private int balance(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    private NodoArbol<T> rotarDerecha(NodoArbol<T> nodo) {
        NodoArbol<T> nuevo = nodo.getIzquierdo();
        nodo.setIzquierdo(nuevo.getDerecho());
        nuevo.setDerecho(nodo);
        return nuevo;
    }

    private NodoArbol<T> rotarIzquierda(NodoArbol<T> nodo) {
        NodoArbol<T> nuevo = nodo.getDerecho();
        nodo.setDerecho(nuevo.getIzquierdo());
        nuevo.setIzquierdo(nodo);
        return nuevo;
    }

    private NodoArbol<T> balancear(NodoArbol<T> nodo) {
        int b = balance(nodo);

        if (b > 1) {
            if (balance(nodo.getIzquierdo()) < 0)           // izquierda-derecha
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);                       // doble izquierda
        }

        if (b < -1) {
            if (balance(nodo.getDerecho()) > 0)              // derecha-izquierda
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);                     // doble derecha
        }

        return nodo;
    }

    @Override
    public void insertar(T dato) {
        this.raiz = insertar(dato, this.raiz);
    }

    private NodoArbol<T> insertar(T dato, NodoArbol<T> nodo) {
        if (nodo == null)
            return new NodoArbol<>(dato);

        if (dato.compareTo(nodo.getDato()) < 0)
            nodo.setIzquierdo(insertar(dato, nodo.getIzquierdo()));
        else if (dato.compareTo(nodo.getDato()) > 0)
            nodo.setDerecho(insertar(dato, nodo.getDerecho()));
        else
            return nodo; 

        return balancear(nodo);
    }

    @Override
    public NodoArbol<T> buscar(T dato) {
        return buscar(dato, this.raiz);
    }

    private NodoArbol<T> buscar(T dato, NodoArbol<T> nodo) {
        if (nodo == null) return null;
        if (dato.compareTo(nodo.getDato()) < 0)
            return buscar(dato, nodo.getIzquierdo());
        else if (dato.compareTo(nodo.getDato()) > 0)
            return buscar(dato, nodo.getDerecho());
        else
            return nodo;
    }

    @Override
    public boolean eliminar(T dato) {
        if (buscar(dato) == null) return false;
        this.raiz = eliminar(dato, this.raiz);
        return true;
    }

    private NodoArbol<T> eliminar(T dato, NodoArbol<T> nodo) {
        if (nodo == null) return null;

        if (dato.compareTo(nodo.getDato()) < 0)
            nodo.setIzquierdo(eliminar(dato, nodo.getIzquierdo()));
        else if (dato.compareTo(nodo.getDato()) > 0)
            nodo.setDerecho(eliminar(dato, nodo.getDerecho()));
        else {
            // Caso 1: sin hijos
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null)
                return null;
            // Caso 2: un hijo
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null)   return nodo.getIzquierdo();
            // Caso 3: dos hijos
            NodoArbol<T> sucesor = buscarMax(nodo.getIzquierdo());
            nodo.setDato(sucesor.getDato());
            nodo.setIzquierdo(eliminar(sucesor.getDato(), nodo.getIzquierdo()));
        }

        return balancear(nodo);
    }

    private NodoArbol<T> buscarMax(NodoArbol<T> nodo) {
        while (nodo.getDerecho() != null)
            nodo = nodo.getDerecho();
        return nodo;
    }

    @Override
    public void inorden() { inorden(this.raiz); }
    private void inorden(NodoArbol<T> nodo) {
        if (nodo == null) return;
        inorden(nodo.getIzquierdo());
        System.out.println(nodo.getDato());
        inorden(nodo.getDerecho());
    }

    @Override
    public void preorden() { preorden(this.raiz); }
    private void preorden(NodoArbol<T> nodo) {
        if (nodo == null) return;
        System.out.println(nodo.getDato());
        preorden(nodo.getIzquierdo());
        preorden(nodo.getDerecho());
    }

    @Override
    public void postorden() { postorden(this.raiz); }
    private void postorden(NodoArbol<T> nodo) {
        if (nodo == null) return;
        postorden(nodo.getIzquierdo());
        postorden(nodo.getDerecho());
        System.out.println(nodo.getDato());
    }

    @Override
    public int compareTo(T otro) { return 0; }
}
