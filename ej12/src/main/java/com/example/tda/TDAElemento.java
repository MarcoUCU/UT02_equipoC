package com.example.tda;

import java.util.function.Consumer;

/**
 * Modela un nodo del árbol binario.
 * La implementación de esta estructura debe ser recursiva.
 */
public interface TDAElemento <T>{

   
    void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo);
    void setHijoDerecho(TDAElemento<T> hijoDerecho);

    TDAElemento<T> getHijoIzquierdo(); // Ambas pueden ser null
    TDAElemento<T> getHijoDerecho();

    void setDato(T dato);
    T getDato();

    TDAElemento<T> buscar(Comparable<T> criterioBusqueda); // Busca un nodo por criterio, si no se encuentra retorna nulo
    TDAElemento<T> eliminar(Comparable<T> criterioBusqueda); // Elimina un nodo del arbol segun el criterio, si se encuentra retorna el nodo borrado, si no null
    boolean insertar(Comparable<T> nuevoDato); // Agrega un nuevo elemento al arbol, si nuevoDato existe no se agrega

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void inOrder(Consumer<T> consumidor); //

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.preOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void preOrder(Consumer<T> consumidor);

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.postOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void postOrder(Consumer<T> consumidor);

    boolean esHoja(); // True si el nodo es hoja

    int cantidadHojas(); // La cantidad de nodos hojas
    int cantidadNodosInternos(); // La cantidad de nodos que no son hojas
    int cantidadNodos(); // La cantidad de nodos que lo componen

    int altura(); // La altura de este nodo
    int obtenerNivel(Comparable<T> criterioBusqueda); // Retorna el nivel del nodo que coinicide con el criterio de busqueda
}
