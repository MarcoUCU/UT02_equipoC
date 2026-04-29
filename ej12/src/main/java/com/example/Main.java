package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.example.clases.Arbol;
import com.example.clases.Grimorio;
import com.example.clases.Hechizo;
import com.example.tda.TDAElemento;

public class Main 
{
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
        Grimorio grimorio = new Grimorio();        
        InputStream is = Main.class.getResourceAsStream("/grimorio.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String linea;
        while ((linea = br.readLine()) != null) {

            String[] tuplas = linea.split("\\),");

            for (String t : tuplas) {

                t = t.replace("(", "")
                     .replace(")", "")
                     .trim(); // Saco los () de las tuplas que son id, nombre

                String[] partes = t.split(",");

                int id = Integer.parseInt(partes[0].trim());
                String nombre = partes[1].trim();

                Hechizo hechizo = new Hechizo(id, nombre);
                grimorio.getHechizos().insertar(hechizo);
            }
        }

        
        List<Hechizo> hechizosProhibidos = grimorio.obtenerProhibidos();
        System.out.println("Prohibidos:");
        for (Hechizo hechizo : hechizosProhibidos){
            System.out.println(hechizo.id + " - " + hechizo.nombre);
        }

        System.out.println("\nCántico:");
        System.out.println(grimorio.generarCantico());
    }

}
