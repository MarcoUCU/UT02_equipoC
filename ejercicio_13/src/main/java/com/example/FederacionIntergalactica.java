package com.example;

import java.util.ArrayList;

public class FederacionIntergalactica extends AVL<Nave> {
    
    public ArrayList<Integer> exploradoras(){
        ArrayList<Integer> naves = new ArrayList<Integer>();
        exploradoras(naves, this.raiz);
        return naves;
    }
    private void exploradoras(ArrayList<Integer> lista, NodoArbol<Nave> nodo){
        if (nodo==null){
            return;
        }
        if(nodo.getDato().getClase().equals("Explorador")){
            lista.add(nodo.getDato().getCodigo());
        }
        exploradoras(lista, nodo.getIzquierdo());
        exploradoras(lista,nodo.getDerecho());
    }

    public int combustiblePromedio(){
        ArrayList<Integer> combustibles = new ArrayList<Integer>();
        int total = 0;
        combustiblePromedio(combustibles, this.raiz);
        for(int i = 0; i<combustibles.size();i++){
            total+=combustibles.get(i);
        }
        if(combustibles.size()==0){return 0;}
        return total/combustibles.size();
    }

    private void combustiblePromedio(ArrayList<Integer> combustible, NodoArbol<Nave> nodo){
        if (nodo==null){
            return;
        }
        if(nodo.getDato().getClase().equals("Explorador")){
            combustible.add(nodo.getDato().getCombustible());
        }
        combustiblePromedio(combustible, nodo.getIzquierdo());
        combustiblePromedio(combustible, nodo.getDerecho());
    }
}
