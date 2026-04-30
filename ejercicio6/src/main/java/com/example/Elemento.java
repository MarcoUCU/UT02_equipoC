package com.example;

import java.util.function.Consumer;

public class Elemento<T> implements TDAElemento<T> {

    private T dato;
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;

    public Elemento(T dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
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
        int comparacion = criterioBusqueda.compareTo(this.dato);
        if (comparacion == 0) return this;
        if (comparacion < 0) {
            if (hijoIzquierdo != null) return hijoIzquierdo.buscar(criterioBusqueda);
        } else {
            if (hijoDerecho != null) return hijoDerecho.buscar(criterioBusqueda);
        }
        return null;
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        int comparacion = nuevoDato.compareTo(this.dato);
        if (comparacion == 0) return false;
        if (comparacion < 0) {
            if (hijoIzquierdo == null) {
                hijoIzquierdo = new Elemento<>((T) nuevoDato);
                return true;
            }
            return hijoIzquierdo.insertar(nuevoDato);
        } else {
            if (hijoDerecho == null) {
                hijoDerecho = new Elemento<>((T) nuevoDato);
                return true;
            }
            return hijoDerecho.insertar(nuevoDato);
        }
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        int comparacion = criterioBusqueda.compareTo(this.dato);
        if (comparacion < 0) {
            if (hijoIzquierdo != null) hijoIzquierdo = hijoIzquierdo.eliminar(criterioBusqueda);
            return this;
        }
        if (comparacion > 0) {
            if (hijoDerecho != null) hijoDerecho = hijoDerecho.eliminar(criterioBusqueda);
            return this;
        }

        if (hijoIzquierdo == null) return hijoDerecho;
        if (hijoDerecho == null) return hijoIzquierdo;

        TDAElemento<T> mayor = hijoIzquierdo;
        while (mayor.getHijoDerecho() != null) mayor = mayor.getHijoDerecho();
        this.dato = mayor.getDato();
        hijoIzquierdo = hijoIzquierdo.eliminar((Comparable<T>) mayor.getDato());
        return this;
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
    public int altura() {
        int a = -1;
        int b = -1;

        if (hijoIzquierdo != null) {
            a = hijoIzquierdo.altura();
        }
        if (hijoDerecho != null) {
            b = hijoDerecho.altura();
        }

        return Math.max(a, b) + 1;
    }

    @Override
    public int cantidadNodos() {
        int contadorIzq = 0;
        int contadorDer = 0;

        if (hijoIzquierdo != null) {
            contadorIzq = hijoIzquierdo.cantidadNodos();
        }
        if (hijoDerecho != null) {
            contadorDer = hijoDerecho.cantidadNodos();
        }

        return 1 + contadorIzq + contadorDer;
    }

    @Override
    public int cantidadHojas() {
        if (esHoja()) return 1;

        int contadorIzq = 0;
        int contadorDer = 0;

        if (hijoIzquierdo != null) {
            contadorIzq = hijoIzquierdo.cantidadHojas();
        }
        if (hijoDerecho != null) {
            contadorDer = hijoDerecho.cantidadHojas();
        }

        return contadorIzq + contadorDer;
    }

    @Override
    public int cantidadNodosInternos() {
        if (esHoja()) return 0;

        int contadorIzq = 0;
        int contadorDer = 0;

        if (hijoIzquierdo != null) {
            contadorIzq = hijoIzquierdo.cantidadNodosInternos();
        }
        if (hijoDerecho != null) {
            contadorDer = hijoDerecho.cantidadNodosInternos();
        }

        return 1 + contadorIzq + contadorDer;
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        return obtenerNivelRec(criterioBusqueda, 0);
    }

    private int obtenerNivelRec(Comparable<T> criterio, int nivelActual) {
        if (criterio.compareTo(this.dato) == 0) return nivelActual;
        if (criterio.compareTo(this.dato) < 0 && hijoIzquierdo != null) {
            return ((Elemento<T>) hijoIzquierdo).obtenerNivelRec(criterio, nivelActual + 1);
        }
        if (criterio.compareTo(this.dato) > 0 && hijoDerecho != null) {
            return ((Elemento<T>) hijoDerecho).obtenerNivelRec(criterio, nivelActual + 1);
        }
        return -1;
    }

    public TDALista<TDAElemento<T>> completos() {
        TDALista<TDAElemento<T>> lista = new ListaEnlazada<>();

        if (hijoIzquierdo != null && hijoDerecho != null) {
            lista.agregar(this);
        }

        if (hijoIzquierdo != null) {
            TDALista<TDAElemento<T>> listaIzq = ((Elemento<T>) hijoIzquierdo).completos();
            for (int i = 0; i < listaIzq.tamaño(); i++) {
                lista.agregar(listaIzq.obtener(i));
            }
        }

        if (hijoDerecho != null) {
            TDALista<TDAElemento<T>> listaDer = ((Elemento<T>) hijoDerecho).completos();
            for (int i = 0; i < listaDer.tamaño(); i++) {
                lista.agregar(listaDer.obtener(i));
            }
        }

        return lista;
    }

    public TDALista<TDAElemento<T>> enNivel(int nivel) {
        TDALista<TDAElemento<T>> lista = new ListaEnlazada<>();

        if (nivel == 0) {
            lista.agregar(this);
            return lista;
        }

        if (hijoIzquierdo != null) {
            TDALista<TDAElemento<T>> listaIzq = ((Elemento<T>) hijoIzquierdo).enNivel(nivel - 1);
            for (int i = 0; i < listaIzq.tamaño(); i++) {
                lista.agregar(listaIzq.obtener(i));
            }
        }

        if (hijoDerecho != null) {
            TDALista<TDAElemento<T>> listaDer = ((Elemento<T>) hijoDerecho).enNivel(nivel - 1);
            for (int i = 0; i < listaDer.tamaño(); i++) {
                lista.agregar(listaDer.obtener(i));
            }
        }

        return lista;
    }
}