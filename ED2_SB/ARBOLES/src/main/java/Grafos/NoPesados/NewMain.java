/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;

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
        DiGrafo gra=new DiGrafo(5);
        gra.insertarArista(0, 1);
        gra.insertarArista(1, 3);
        gra.insertarArista(1, 4);
        gra.insertarArista(2, 2);
        gra.insertarArista(2, 4);
        gra.insertarArista(3, 1);
        gra.insertarArista(4, 2);
        //gra.insertarArista(5, 6);
        System.out.println(gra);
        System.out.println(gra.getMatrizAdyacenciaString());  
        System.out.println(gra.obtenerComponentesDeLasIslas());
        System.out.println(gra.algoritmoWarshall());
        System.out.println(gra.mostrarCaminos());
    }
    
}
