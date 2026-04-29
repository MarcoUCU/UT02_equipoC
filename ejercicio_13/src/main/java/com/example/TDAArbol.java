package com.example;

public interface TDAArbol<T> extends Comparable<T> {
    void insertar(T dato);
    boolean eliminar(T dato);
    NodoArbol<T> buscar(T dato);
    boolean estaVacio();
    int altura();
    void inorden();
    void preorden();
    void postorden();
}