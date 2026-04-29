package com.example.clases;

import java.util.function.Consumer;

import com.example.tda.TDAElemento;

public class Elemento<T> implements TDAElemento<T> {

    private T dato;
    private TDAElemento<T> izquierdo;
    private TDAElemento<T> derecho;


    public Elemento(T dato) {
        this.dato = dato;
    }


    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.izquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.derecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return izquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return derecho;
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
    public TDAElemento<T> buscar(Comparable<T> criterio){
        
        if(criterio.compareTo(dato) == 0){
            return this;
        }
        if(criterio.compareTo(dato) < 0 && izquierdo != null){
            return izquierdo.buscar(criterio);
        }
        if(criterio.compareTo(dato) > 0 && derecho != null){
            return derecho.buscar(criterio);
        }

        return null;
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato){
        int cmp = nuevoDato.compareTo(dato);
        if(cmp == 0) return false;
        if(cmp < 0){
            if(izquierdo == null){
                izquierdo = new Elemento<T>((T) nuevoDato);
                return true;
            }
            return izquierdo.insertar(nuevoDato);
        } else{
            if(derecho == null){
                derecho = new Elemento<T>((T) nuevoDato);
                return true;
            }
            return derecho.insertar(nuevoDato);
        }
    }

    public TDAElemento<T> eliminar(Comparable<T> criterio){
        int cmp = criterio.compareTo(dato);

        if(cmp < 0 && izquierdo != null){
            izquierdo = izquierdo.eliminar(criterio);
        } else if (cmp > 0 && derecho != null){
            derecho = derecho.eliminar(criterio);
        } else if(cmp == 0){

            if(derecho == null && izquierdo == null){
                return null; // Si eliminas una hoja el padre tomara como que este hijo es null
            }

            // Si tiene solo un hijo se borra ese
            if(izquierdo == null){
                return derecho;
            }
            if(derecho == null){
                return izquierdo;
            }

            // Cuando tiene dos hijos se elige el que va a ser la nueva raiz
            TDAElemento<T> predecesor = max(izquierdo); // Consigue el maximo de la subrama izquierda
            this.dato = predecesor.getDato();
            izquierdo  = izquierdo .eliminar((Comparable<T>) predecesor.getDato());
        }

        return this;
    }

    private TDAElemento<T> max(TDAElemento<T> nodo) {
    if (nodo == null) return null;
    while (nodo.getHijoDerecho() != null) {
        nodo = nodo.getHijoDerecho();
    }
    return nodo;
    }

    // Recorridos

    @Override
    public void inOrder(Consumer<T> consumidor){
        if(izquierdo != null){
            izquierdo.inOrder(consumidor);
        }
        
        consumidor.accept(dato);
        
        if(derecho != null){
            derecho.inOrder(consumidor);
        }
    }

    public void preOrder(Consumer<T> consumidor){
        
        consumidor.accept(dato);
        
        if(izquierdo != null){
            izquierdo.preOrder(consumidor);
        }
        
        if(derecho != null){
            derecho.preOrder(consumidor);
        }
    }

    public void postOrder(Consumer<T> consumidor){
        
        if(izquierdo != null){
            izquierdo.postOrder(consumidor);
        }
        
        if(derecho != null){
            derecho.postOrder(consumidor);
        }

        consumidor.accept(dato);
    }

    public boolean esHoja(){
        if(izquierdo == null && derecho == null){
            return true;
        }
        return false;
    }

    public int cantidadHojas(){
        if(esHoja()) return 1;

        int izq = (izquierdo != null) ? izquierdo.cantidadHojas() : 0;
        int der = (derecho != null) ? derecho.cantidadHojas() : 0;

        return izq + der;
    }

    public int cantidadNodosInternos() {
        if (esHoja()) return 0;

        int izq = (izquierdo != null) ? izquierdo.cantidadNodosInternos() : 0;
        int der = (derecho != null) ? derecho.cantidadNodosInternos() : 0;

        return 1 + izq + der;
    }

    public int cantidadNodos(){
        int izq = (izquierdo != null) ? izquierdo.cantidadNodos() : 0;
        int der = (derecho != null) ? derecho.cantidadNodos() : 0;

        return 1 + izq + der;
    }

    public int altura() {
        int izq = (izquierdo != null) ? izquierdo.altura() : -1;
        int der = (derecho != null) ? derecho.altura() : -1;

        return 1 + Math.max(izq, der);
    }

    public int obtenerNivel(Comparable<T> criterio) {
        return obtenerNivelRec(criterio, 0);
    }

    public int obtenerNivelRec(Comparable<T> criterio, int nivel) {
        int cmp = criterio.compareTo(dato);

        if (cmp == 0) return nivel;

        if (cmp < 0 && izquierdo != null) {
            return ((Elemento<T>) izquierdo).obtenerNivelRec(criterio, nivel + 1);
        }

        if (cmp > 0 && derecho != null) {
            return ((Elemento<T>) derecho).obtenerNivelRec(criterio, nivel + 1);
        }

        return -1; // no encontrado
    }
}
