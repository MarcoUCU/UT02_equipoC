package com.example;
import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T> {

    private TDAElemento<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public ArbolBinario(T datoRaiz) {
        this.raiz = new ElementoBinario<>(datoRaiz);
    }

    @Override
    public T buscar(Comparable<T> criterioBusqueda) {
        if (raiz == null) return null;

        TDAElemento<T> encontrado = raiz.buscar(criterioBusqueda);
        return encontrado == null ? null : encontrado.getDato();
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    public void setRaiz(TDAElemento<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new ElementoBinario<>(ElementoBinario.comoT(dato));
            return true;
        }

        return raiz.insertar(dato);
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        return false; 
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrder(n -> consumidor.accept(n.getDato()));
        }
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.preOrder(n -> consumidor.accept(n.getDato()));
        }
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.postOrder(n -> consumidor.accept(n.getDato()));
        }
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int cantidadNodos() {
        return raiz == null ? 0 : raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        return raiz == null ? 0 : raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        return raiz == null ? 0 : raiz.cantidadNodosInternos();
    }
public boolean sustituirNodo(T valor, T valorNuevo, TDAElemento<T> elemento) {
    if (elemento == null) {
        return false;
    }

    if (elemento.getDato().equals(valor)) {
        elemento.setDato(valorNuevo);
        return true;
    }

    if (sustituirNodo(valor, valorNuevo, elemento.getHijoIzquierdo())) {
        return true;
    }

    return sustituirNodo(valor, valorNuevo, elemento.getHijoDerecho());
}


public double evaluar(TDAElemento<String> nodo) {
    if (nodo == null) {
        return 0;
    }

    String dato = nodo.getDato();

    
    if (nodo.esHoja()) {
        return Double.parseDouble(dato);
    }

    double izq = evaluar(nodo.getHijoIzquierdo());
    double der = evaluar(nodo.getHijoDerecho());

    switch (dato) {
        case "+":
            return izq + der;
        case "-":
            return izq - der;
        case "*":
            return izq * der;
        case "/":
            return izq / der;
        default:
            throw new IllegalArgumentException("Operador inválido: " + dato);
    }
}

    }
