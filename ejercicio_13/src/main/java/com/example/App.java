package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        FederacionIntergalactica federacion = new FederacionIntergalactica();

        federacion.insertar(new Nave(10, "Explorador", 0));
        federacion.insertar(new Nave(20, "Destructor", 90));
        federacion.insertar(new Nave(30, "Médica", 100));
        federacion.insertar(new Nave(40, "Explorador", 50));
        federacion.insertar(new Nave(50, "Carguero", 20));
        federacion.insertar(new Nave(60, "Destructor", 28));
        federacion.insertar(new Nave(70, "Explorador", 14));
        federacion.insertar(new Nave(80, "Médica", 7));
        federacion.insertar(new Nave(90, "Carguero", 23));
        federacion.insertar(new Nave(100, "Explorador", 26));

        System.out.println("Naves exploradoras: " + federacion.exploradoras());
        System.out.println("Combustible promedio: " + federacion.combustiblePromedio());
}
}
