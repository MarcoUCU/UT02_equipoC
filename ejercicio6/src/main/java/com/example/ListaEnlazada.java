package com.example;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListaEnlazada<T> implements TDALista<T> {

    private Nodo<T> primero;
    private int tamaño;

    public ListaEnlazada() {
        this.primero = null;
        this.tamaño = 0;
    }

    @Override
    public void agregar(T elem) {
        Nodo<T> nuevo = new Nodo<>(elem);

        if (esVacio()) {
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        tamaño++;
    }

    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        Nodo<T> nuevo = new Nodo<>(elem);

        if (index == 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
            Nodo<T> anterior = primero;
            for (int i = 0; i < index - 1; i++) {
                anterior = anterior.getSiguiente();
            }
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }

        tamaño++;
    }

    @Override
    public T obtener(int index) {
        validarIndice(index);

        Nodo<T> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

    @Override
    public T remover(int index) {
        validarIndice(index);

        T datoRemovido;

        if (index == 0) {
            datoRemovido = primero.getDato();
            primero = primero.getSiguiente();
        } else {
            Nodo<T> anterior = primero;
            for (int i = 0; i < index - 1; i++) {
                anterior = anterior.getSiguiente();
            }
            Nodo<T> aEliminar = anterior.getSiguiente();
            datoRemovido = aEliminar.getDato();
            anterior.setSiguiente(aEliminar.getSiguiente());
        }

        tamaño--;
        return datoRemovido;
    }

    @Override
    public boolean remover(T elem) {
        if (esVacio()) return false;

        if (Objects.equals(primero.getDato(), elem)) {
            primero = primero.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<T> anterior = primero;
        Nodo<T> actual = primero.getSiguiente();

        while (actual != null) {
            if (Objects.equals(actual.getDato(), elem)) {
                anterior.setSiguiente(actual.getSiguiente());
                tamaño--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    @Override
    public boolean contiene(T elem) {
        return indiceDe(elem) != -1;
    }

    @Override
    public int indiceDe(T elem) {
        Nodo<T> actual = primero;
        int indice = 0;

        while (actual != null) {
            if (Objects.equals(actual.getDato(), elem)) return indice;
            actual = actual.getSiguiente();
            indice++;
        }

        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = primero;

        while (actual != null) {
            if (criterio.test(actual.getDato())) return actual.getDato();
            actual = actual.getSiguiente();
        }

        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        ListaEnlazada<T> ordenada = new ListaEnlazada<>();
        Nodo<T> actual = primero;

        while (actual != null) {
            insertarOrdenado(ordenada, actual.getDato(), comparator);
            actual = actual.getSiguiente();
        }

        return ordenada;
    }

    private void insertarOrdenado(ListaEnlazada<T> lista, T elem, Comparator<T> comparator) {
        if (lista.esVacio()) {
            lista.agregar(elem);
            return;
        }

        if (comparator.compare(elem, lista.obtener(0)) <= 0) {
            lista.agregar(0, elem);
            return;
        }

        int i = 1;
        while (i < lista.tamaño() && comparator.compare(elem, lista.obtener(i)) > 0) {
            i++;
        }

        lista.agregar(i, elem);
    }

    @Override
    public int tamaño() {
        return tamaño;
    }

    @Override
    public boolean esVacio() {
        return tamaño == 0;
    }

    @Override
    public void vaciar() {
        primero = null;
        tamaño = 0;
    }

    private void validarIndice(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
    }
}