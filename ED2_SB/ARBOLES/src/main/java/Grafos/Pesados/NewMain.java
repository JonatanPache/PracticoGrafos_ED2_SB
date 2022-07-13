/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Grafos.Pesados;

import Grafos.NoPesados.*;
import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        // TODO code application logic here
        List<String > lista=new LinkedList<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");
        lista.add("D");
        lista.add("E");
        lista.add("F");
        lista.add("G");
        lista.add("H");
        
        DiGrafoPesadoV1<String> gra=new DiGrafoPesadoV1(lista);
        gra.insertarArista("G", "A", 20);
        gra.insertarArista("G", "B", 40);
        gra.insertarArista("G", "C", 10);
        gra.insertarArista("B", "A", 20);
        gra.insertarArista("B", "F", 20);
        gra.insertarArista("A", "D", 50);
        gra.insertarArista("C", "E", 20);
        gra.insertarArista("C", "F", 30);
        gra.insertarArista("F", "H", 10);
        gra.insertarArista("E", "D", 45);
        gra.insertarArista("D", "E", 15);
        gra.insertarArista("E", "H", 40);
        gra.insertarArista("D", "H", 70);
        
        System.out.println(gra);
        System.out.println(gra.existeAdyacencia("B","F"));
        System.out.println(gra.indexOfVertice("H"));
        System.out.println(gra.algoritmoFordFulkerson("G", "H"));
        
        
        /*DiGrafo graf=new DiGrafo(5);
        graf.insertarArista(0, 1);
        graf.insertarArista(1, 3);
        graf.insertarArista(1, 4);
        graf.insertarArista(2, 1);
        graf.insertarArista(2, 4);
        graf.insertarArista(2, 0);
        graf.insertarArista(3, 0);
        graf.insertarArista(3, 4);
        graf.insertarArista(4, 3);
        System.out.println(graf);
        System.out.println(graf.obtenerComponentesFuertementesConexas());
        
        DiGrafo graf0=new DiGrafo(9);
        graf0.insertarArista(0, 1);
        graf0.insertarArista(0, 2);
        
        graf0.insertarArista(3, 4);
        graf0.insertarArista(4, 3);
        
        graf0.insertarArista(5, 7);
        graf0.insertarArista(7, 5);
        graf0.insertarArista(7, 8);
        graf0.insertarArista(8, 7);
        graf0.insertarArista(8, 6);
        graf0.insertarArista(6, 8);
        graf0.insertarArista(6, 5);
        graf0.insertarArista(5, 6);
        
        System.out.println(graf0);
        System.out.println(graf0.obtenerComponentesFuertementesConexas());*/
        /*
        List<Integer> lista=new LinkedList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        
        
        DiGrafoV1<Integer> gra=new DiGrafoV1(lista);
        gra.insertarArista(1, 2);
        gra.insertarArista(1, 3);
        gra.insertarArista(1, 4);
        gra.insertarArista(1, 5);
        gra.insertarArista(2, 4);
        gra.insertarArista(3, 2);
        gra.insertarArista(3, 5);
        gra.insertarArista(4, 5);
        /*gra.insertarArista(4, 6);
        gra.insertarArista(4, 5);
        gra.insertarArista(5, 8);
        gra.insertarArista(5, 7);
        gra.insertarArista(6, 7);
        gra.insertarArista(6, 9);
        gra.insertarArista(7, 9);
        gra.insertarArista(7, 8);
        gra.insertarArista(7, 10);
        gra.insertarArista(8, 10);
        gra.insertarArista(9, 10);
        gra.insertarArista(4, 3, 3);
        System.out.println(gra);
        System.out.println(gra.existeAdyacencia(1,10));
        System.out.println(gra.indexOfVertice(5));
        System.out.println(gra.ordTopologico());
        */
    }
    
}
