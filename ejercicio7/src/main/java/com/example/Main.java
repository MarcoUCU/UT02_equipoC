package com.example;


public class Main {

    public static void main(String[] args) {

   

    //creo nodos
        TDAElemento<String> raiz = new ElementoBinario<>("*");
        TDAElemento<String> suma = new ElementoBinario<>("+");
        TDAElemento<String> x = new ElementoBinario<>("x");
        TDAElemento<String> tres = new ElementoBinario<>("3");
        TDAElemento<String> y = new ElementoBinario<>("y");

       //conecto el arbol
        raiz.setHijoIzquierdo(suma);
        raiz.setHijoDerecho(y);

        suma.setHijoIzquierdo(x);
        suma.setHijoDerecho(tres);

         ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.setRaiz(raiz);

        // mostrar inOrder
        System.out.print("Árbol original: ");
        arbol.inOrder(System.out::print);
        System.out.println();

        //sustituyo las x e y´s por valores numericos
        arbol.sustituirNodo("x", "2", arbol.obtenerRaiz());
        arbol.sustituirNodo("y", "4", arbol.obtenerRaiz());

        System.out.print("Árbol sustituido: ");
        arbol.inOrder(System.out::print);
        System.out.println();

        //evaluo expresion final
        double resultado = arbol.evaluar(arbol.obtenerRaiz());

        System.out.println("Resultado = " + resultado);
    }
}