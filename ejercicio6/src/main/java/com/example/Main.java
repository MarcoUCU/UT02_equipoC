package com.example;

public class Main {
    public static void main(String[] args) {

        ArbolBinario<Integer> arbol = new ArbolBinario<>();

        // Insertar nodos
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(10);
        arbol.insertar(25);

        System.out.println("=== INORDER ===");
        arbol.inOrder(System.out::println);

        System.out.println("\n=== PREORDER ===");
        arbol.preOrder(System.out::println);

        System.out.println("\n=== POSTORDER ===");
        arbol.postOrder(System.out::println);

        //1
        System.out.println("\nAltura: " + arbol.altura());

        // 2
        System.out.println("Cantidad de nodos: " + arbol.cantidadNodos());

        // 3
        System.out.println("Cantidad de hojas: " + arbol.cantidadHojas());

        // 4
        System.out.println("Cantidad de nodos internos: " + arbol.cantidadNodosInternos());

        // 5
        System.out.println("\n=== NODOS COMPLETOS ===");
        TDALista<TDAElemento<Integer>> completos = arbol.completos();

        for (int i = 0; i < completos.tamaño(); i++) {
            System.out.println(completos.obtener(i).getDato());
        }

        // 6
        int nivelBuscado = 2;
        System.out.println("\n=== NODOS EN NIVEL " + nivelBuscado + " ===");
        TDALista<TDAElemento<Integer>> nivel = arbol.enNivel(nivelBuscado);

        for (int i = 0; i < nivel.tamaño(); i++) {
            System.out.println(nivel.obtener(i).getDato());
        }
    }
}