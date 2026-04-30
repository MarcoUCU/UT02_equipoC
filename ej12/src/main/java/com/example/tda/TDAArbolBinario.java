package com.example.tda;

import java.util.function.Consumer;

/**
 * Define un Tipo de Dato Abstracto (TDA) Árbol Binario genérico.
 *
 * <p>Un árbol binario es una estructura de datos jerárquica en la que cada nodo
 * puede tener como máximo dos hijos: un hijo izquierdo y un hijo derecho.</p>
 * @param <T> el tipo de los elementos almacenados en el árbol
 */
public interface TDAArbolBinario<T> {

    T buscar(Comparable<T> predicate); // Busca y retorna el primer elemento que cumple con el predicado, null si ninguno.


    TDAElemento<T> obtenerRaiz(); // Retorna la raiz del arbol o null si esta vacio

    boolean eliminar(Comparable<T> criterioBusqueda); // Elimina el/los nodos segun el criterio de busqueda, true si se elimina al menos uno, false lo contrario

    boolean insertar(Comparable<T> dato); // Agrega un dato al arbol, si ya existe no se agrega de nuevo, true si se agrega, false si no.

    /**
     * Recorre el árbol en in-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void inOrder(Consumer<T> consumidor); // Recorre el arbol inOrder

    /**
     * Recorre el árbol en pre-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.preOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void preOrder(Consumer<T> consumidor); // Recorre el arbol en PreOrder

    /**
     * Recorre el árbol en post-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.postOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void postOrder(Consumer<T> consumidor); // Recorre el arbol PostOrder
    boolean esVacio(); // True si es vacio
    int cantidadNodos(); // Devuelve la cantidad de nodos hasta el final
    int cantidadHojas(); // Devuelve la cantidad de nodos que son hojas
    int cantidadNodosInternos(); // Devuelve la cantidad de nodos que no son hojas
}
