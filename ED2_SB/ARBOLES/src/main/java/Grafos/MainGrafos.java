/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Grafos;

import Code.ArbolBinarioBusqueda;
import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.NoPesados.BFS;
import Grafos.NoPesados.Conexo;
import Grafos.NoPesados.DiGrafo;
import Grafos.NoPesados.DiGrafoV1;
import Grafos.NoPesados.Grafo;
import Grafos.NoPesados.UtilitarioGrafo;
import Grafos.Pesados.DiGrafoPesado;
import Grafos.Pesados.DiGrafoPesadoV1;
import Grafos.Pesados.GrafoPesado;
import Grafos.Pesados.GrafoPesadoV1;
import Grafos.Pesados.Kruskal;
import Grafos.Pesados.NewMain;
import Grafos.Pesados.Prim;
import Grafos.Pesados.UtilitarioGP;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class MainGrafos<E extends Comparable<E>>{
    
    private static Grafo generarGrafo(int cantidadVertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        Grafo grafoRandom=new Grafo(cantidadVertices);  
        for(int i=0;i<cantidadVertices;i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((cantidadVertices-2) - 0))) + 0;
            int rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;
            for(int j=0;j<rand_cantidadAristasVertice;j++){
                rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;
                if(!grafoRandom.existeAdyacencia(i, rand_VerticeDestino)){
                    grafoRandom.insertarArista(i, rand_VerticeDestino);
                }
            }
        }
        return grafoRandom;
    }
    
    private static DiGrafo generarDiGrafo(int cantidadVertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        DiGrafo DiGrafoRandom=new DiGrafo(cantidadVertices);        
        for(int i=0;i<cantidadVertices;i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((cantidadVertices-2) - 0))) + 0;
            int rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;
            for (int j = 0; j < rand_cantidadAristasVertice; j++) {
                rand_VerticeDestino = ((int) (Math.random() * (cantidadVertices - 0))) + 0;
                /*while (rand_VerticeDestino == i) {
                    rand_VerticeDestino = ((int) (Math.random() * (cantidadVertices - 0))) + 0;
                }*/
                if (!DiGrafoRandom.existeAdyacencia(i, rand_VerticeDestino)) {
                    DiGrafoRandom.insertarArista(i, rand_VerticeDestino);
                }

            }
        }
        return DiGrafoRandom;
    }
    
    private static DiGrafoV1<Integer> generarDiGrafoV1(List<Integer> lista) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        DiGrafoV1<Integer> DiGrafoRandom=new DiGrafoV1<Integer>(lista);        
        for(int i=0;i<lista.size();i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((lista.size()-2) - 0))) + 0;
            for (int j = 0; j < rand_cantidadAristasVertice; j++) {
                int rand_VerticeDestino = lista.get((int) (Math.random() * (lista.size() - 0)) + 0);
                while (rand_VerticeDestino == lista.get(i)) {
                    rand_VerticeDestino = lista.get(((int) (Math.random() * (lista.size() - 0))) + 0 );
                }
                if (!DiGrafoRandom.existeAdyacencia(lista.get(i), rand_VerticeDestino)) {
                    DiGrafoRandom.insertarArista(lista.get(i), rand_VerticeDestino);
                }

            }
        }
        return DiGrafoRandom;
    }
    
    private static GrafoPesado generarGrafoPesado(int cantidadVertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        GrafoPesado GrafoRandom=new GrafoPesado(cantidadVertices);        
        for(int i=0;i<cantidadVertices;i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((cantidadVertices-2) - 0))) + 0;
            int rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;            
            for(int j=0;j<rand_cantidadAristasVertice;j++){
                rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;
                while (rand_VerticeDestino == i) {
                    rand_VerticeDestino = ((int) (Math.random() * (cantidadVertices - 0))) + 0;
                }
                double peso=round(((Math.random()*(999 - 10)) + 10),2);
                if(!GrafoRandom.existeAdyacencia(i, rand_VerticeDestino)){
                    GrafoRandom.insertarArista(i, rand_VerticeDestino,peso);
                }
            }
        }
        return GrafoRandom;
    }
    
    private static GrafoPesadoV1<Integer> generarGrafoPesadoV1(List<Integer> vertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        GrafoPesadoV1<Integer> GrafoRandom=new GrafoPesadoV1<Integer>(vertices);        
        for(int i=0;i<vertices.size();i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((vertices.size()-2) - 0))) + 0;
            int indexVerticeDestino = ((int) (Math.random()*(vertices.size() - 0))) + 0;            
            for(int j=0;j<rand_cantidadAristasVertice;j++){
                indexVerticeDestino = ((int) (Math.random()*(vertices.size()- 0))) + 0;
                while ( vertices.get(indexVerticeDestino).compareTo(vertices.get(i))!=0  && 
                        GrafoRandom.existeVertice(vertices.get(indexVerticeDestino))) {
                    indexVerticeDestino = ((int) (Math.random() * (vertices.size() - 0))) + 0;
                }
                double peso=round(((Math.random()*(999 - 10)) + 10),2);
                if(!GrafoRandom.existeAdyacencia(vertices.get(i), vertices.get(indexVerticeDestino)) ){
                    GrafoRandom.insertarArista(vertices.get(i), vertices.get(indexVerticeDestino),peso);
                }
            }
        }
        return GrafoRandom;
    }
    
    private static GrafoPesadoV1<String> generarDiGrafoPesadoV1Str(List<String> vertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        GrafoPesadoV1<String> GrafoRandom=new GrafoPesadoV1<String>(vertices);        
        for(int i=0;i<vertices.size();i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((vertices.size()-2) - 0))) + 0;
            int indexVerticeDestino = ((int) (Math.random()*(vertices.size() - 0))) + 0;            
            for(int j=0;j<rand_cantidadAristasVertice;j++){
                indexVerticeDestino = ((int) (Math.random()*(vertices.size()- 0))) + 0;
                while ( vertices.get(indexVerticeDestino).compareTo(vertices.get(i))!=0  && 
                        GrafoRandom.existeVertice(vertices.get(indexVerticeDestino))) {
                    indexVerticeDestino = ((int) (Math.random() * (vertices.size() - 0))) + 0;
                }
                double peso=round(((Math.random()*(999 - 10)) + 10),2);
                if(!GrafoRandom.existeAdyacencia(vertices.get(i), vertices.get(indexVerticeDestino)) ){
                    GrafoRandom.insertarArista(vertices.get(i), vertices.get(indexVerticeDestino),peso);
                }
            }
        }
        return GrafoRandom;
    }
    
    private static DiGrafoPesado generarDiGrafoPesado(int cantidadVertices) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        DiGrafoPesado DiGrafoRandom=new DiGrafoPesado(cantidadVertices);        
        for(int i=0;i<cantidadVertices;i++){
            int rand_cantidadAristasVertice = ((int) (Math.random()*((cantidadVertices-2) - 0))) + 0;
            int rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;            
            for(int j=0;j<rand_cantidadAristasVertice;j++){
                rand_VerticeDestino = ((int) (Math.random()*(cantidadVertices - 0))) + 0;
                while (rand_VerticeDestino == i) {
                    rand_VerticeDestino = ((int) (Math.random() * (cantidadVertices - 0))) + 0;
                }
                double peso=round(((Math.random()*(999 - 10)) + 10),2);
                if(!DiGrafoRandom.existeAdyacencia(i, rand_VerticeDestino)){
                    DiGrafoRandom.insertarArista(i, rand_VerticeDestino,peso);
                }
            }
        }
        return DiGrafoRandom;
    }
    
    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        UtilitarioGrafo uti;
        Scanner entrada = new Scanner(System.in);
        System.out.println("PRACTICO #2 GRAFOS\n "
                + "Por favor los ejercicios del 1,2 se asume que estan hechos"
                + "porque estan siendo utilizados en los otros ejercicios. Gracias"
                + "\nElija un ejercicio(3-19)");
        String tipoEjercicio = entrada.next();

        switch (tipoEjercicio) {
            case "3":
                System.out.println("3.- METODO SI HAY CICLOS EN UN GRAFO DIRIGIDO SIN USAR"
                        + " LA MATRIZ DE CAMINO");
                Scanner entrada1 = new Scanner(System.in);
                System.out.println("Generando DiGrafo \n Cantidad De Vertices: ");
                String cantidadVertices = entrada1.next();
                cantidadVertices = cantidadVertices.toUpperCase();
                int cantidad = Integer.parseInt(cantidadVertices);
                DiGrafo Grafo = generarDiGrafo(cantidad);
                System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n"+Grafo.toString());
                uti = new UtilitarioGrafo(Grafo);
                System.out.println("HAY CICLOS: " + uti.hayCiclosGrafoDirigido());
                break;
            case "4":
                System.out.println("4.- METODO SI HAY CICLOS EN UN GRAFO DIRIGIDO USANDO"
                        + " LA MATRIZ DE CAMINO");
                Scanner entrada2 = new Scanner(System.in);
                System.out.println("Generando DiGrafo \n Cantidad De Vertices: ");
                String cantidadVertices2 = entrada2.next();
                cantidadVertices2 = cantidadVertices2.toUpperCase();
                int cantidad2 = Integer.parseInt(cantidadVertices2);
                DiGrafo Grafo2 = generarDiGrafo(cantidad2);
                System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n"+Grafo2.toString());
                System.out.println("HAY CICLOS: "+Grafo2.hayCiclo());
                break;
            case "5":
                System.out.println("5.- METODO QUE RETORNE LOS COMPONENTES DE LAS ISLAS QUE EXISTEN "
                        + "EN EL GRAFO");
                break;
            case "6":
                System.out.println("6.- METODO QUE DEVUELVE LA MATRIZ DE CAMINO DE UNA GRAFO DIRIGIDO");
                Scanner entrada3 = new Scanner(System.in);
                System.out.println("Generando DiGrafo \n Cantidad De Vertices: ");
                String cantidadVertices3 = entrada3.next();
                cantidadVertices3 = cantidadVertices3.toUpperCase();
                int cantidad3 = Integer.parseInt(cantidadVertices3);
                DiGrafo Grafo3 = generarDiGrafo(cantidad3);
                System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n"+Grafo3.toString());
                System.out.println("MATRIZ DE CAMINOS: \n"+Grafo3.obtenerMatrizCaminoToString());
                break;
            case "7":
                System.out.println("7.- METODO QUE VERIFICA SI EL GRAFO DIRIGIDO ES DEBILMENTE"
                        + " CONEXO");
                boolean quiereContinuar = true;
                while (quiereContinuar==true) {
                    Scanner entrada4 = new Scanner(System.in);
                    System.out.println("Generando DiGrafo \n Cantidad De Vertices: ");
                    String cantidadVertices4 = entrada4.next();
                    cantidadVertices = cantidadVertices4.toUpperCase();
                    int cantidad4 = Integer.parseInt(cantidadVertices);
                    DiGrafo Grafo4 = generarDiGrafo(cantidad4);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo4.toString());
                    System.out.println("ES FUERTEMENTE CONEXO?  " + Grafo4.esFuertementeConexo());
                    
                    Scanner entrada6 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir = entrada6.next();
                    quiereSeguir = quiereSeguir.toUpperCase();
                    if ("NO".equals(quiereSeguir)) {
                        quiereContinuar = false;
                    }
                }
                break;
            case "8":
                System.out.println("8.- METODO QUE VERIFICA SI EL GRAFO DIRIGIDO ES DEBILMENTE"
                        + " CONEXO");
                boolean quiereContinuar2 = true;
                while (quiereContinuar2) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando DiGrafo \n Cantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafo Grafo5 = generarDiGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("ES DEBILMENTE CONEXO?  " + Grafo5.esDebilmenteConexo());

                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar2 = false;
                    }
                }
                break;
            case "9":
                System.out.println("9.- METODO QUE ENCUENTRA SI HAY CICLOS EN UN GRAFO NO"
                        + " DIRIGIDO");
                boolean quiereContinuar3 = true;
                while (quiereContinuar3) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo No Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    Grafo Grafo5 = generarGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("HAY CICLOS:  " + Grafo5.hayCiclo());

                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar3 = false;
                    }
                }
                break;
            case "10":
                System.out.println("10.- (GRAFO NO DIRIGIDO) METODO QUE ENCUENTRA LOS \n"
                        + "             COMPONENTES DE LAS DIFERENTES ISLAS");
                boolean quiereContinuar4 = true;
                while (quiereContinuar4) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo No Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    Grafo Grafo5 = generarGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("COMPONENTES DE TODAS LAS ISLAS:  \n" + Grafo5.obtenerComponentesDeLasIslas());

                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar4 = false;
                    }
                }
                break;
            case "11":
                System.out.println("11.- (GRAFO DIRIGIDOS) METODO QUE DEVUELVE LA CNATIDAD DE ISLAS");
                boolean quiereContinuar5 = true;
                while (quiereContinuar5) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafo Grafo5 = generarDiGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("CANTIDAD DE ISLAS: "+Grafo5.cantidadIslas());

                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar5 = false;
                    }
                }
                break;
            case "12":
                System.out.println("12.- (GRAFO DIRIGIDOS) ALGORITMO WARSHALL");
                boolean quiereContinuar6 = true;
                while (quiereContinuar6) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafo Grafo5 = generarDiGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("MATRIZ DE CAMINOS: \n"+Grafo5.algoritmoWarshall());
                    System.out.println("CAMINOS: \n"+Grafo5.mostrarCaminos());
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar6 = false;
                    }
                }
                break;
            case "13":
                System.out.println("13.- (GRAFO DIRIGIDOS) ALGORITMO FLOYD-WARSHALL \n"
                        + "CAMINOS CON COSTOS MINIMOS");
                boolean quiereContinuar7 = true;
                while (quiereContinuar7) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafoPesado Grafo5 = generarDiGrafoPesado(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("ALGORTIMO FLOYD-WARSHALL: \n"+Grafo5.algoritmoFloydWarshall());
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar7 = false;
                    }
                }
                break;
            case "14":
                System.out.println("14.- (GRAFO DIRIGIDOS) CANTIDAD DE COMPONENTES \n"
                        + "FUERTEMENTE CONEXAS");
                boolean quiereContinuar8 = true;
                while (quiereContinuar8) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafo Grafo5 = generarDiGrafo(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println("COMPONENTES FUERTEMENTES CONEXAS: \n"
                            +Grafo5.obtenerComponentesFuertementesConexas());
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar8 = false;
                    }
                }
                break;
            case "15":
                System.out.println("15.- (GRAFO DIRIGIDOS) ALGORITMO DIJKSTRA");
                boolean quiereContinuar9 = true;
                while (quiereContinuar9) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo Dirigido\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    DiGrafoPesado Grafo5 = generarDiGrafoPesado(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    
                    System.out.println("Por favor ingrese el vertice de inicio: ");
                    entrada5 = new Scanner(System.in);
                    cantidadVertices5 = entrada5.next();
                    int a = Integer.parseInt(cantidadVertices5);
                    System.out.print("Por favor ingrese el vertice destino: \n");
                    entrada5 = new Scanner(System.in);
                    cantidadVertices5 = entrada5.next();
                    int b = Integer.parseInt(cantidadVertices5);
                    System.out.println("-------------------------------------------");
                    System.out.println(Grafo5.algoritmoDijkstra(a, b));
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar9 = false;
                    }
                }
                break;
                
            case "16":
                System.out.println("16.- (GRAFO PESADO NO DIRGIDO) ALGORITMO KRUSKAL");
                boolean quiereContinuar10 = true;
                while (quiereContinuar10) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo No Dirigido Pesado\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    GrafoPesado Grafo5 = generarGrafoPesado(cantidad5);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println(Grafo5.algoritmoKruskal());
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar10 = false;
                    }
                }
                break;
            case "17":
                System.out.println("17.- (GRAFO PESADO NO DIRGIDO) ALGORITMO PRIM");
                boolean quiereContinuar11 = true;
                while (quiereContinuar11) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo No Dirigido Pesado\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices);
                    List<Integer> lista=new LinkedList<>();
                    while (cantidad5 > 0) {
                        Scanner entrada6 = new Scanner(System.in);
                        System.out.println("Ingrese el vertice: ");
                        String cantidadVertices6 = entrada6.next();
                        cantidadVertices = cantidadVertices5.toUpperCase();
                        int cantidad6 = Integer.parseInt(cantidadVertices);
                        lista.add(cantidad6);
                        cantidad5--;
                    }
                    GrafoPesadoV1 Grafo5 = generarGrafoPesadoV1(lista);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    
                    Scanner entrada8 = new Scanner(System.in);
                    System.out.println("INTRODUZCA EL VERTICE DE INICIO: ");
                    String cantidadVertices6 = entrada8.next();
                    cantidadVertices = cantidadVertices6.toUpperCase();
                    int cantidad6 = Integer.parseInt(cantidadVertices);
                    
                    System.out.println(Grafo5.algoritmoPrim(cantidad6));
                    
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar11 = false;
                    }
                }
                break;
            case "18":
                System.out.println("18.- (GRAFO DIRGIDO) ALGORITMO ORDENAMIENTO TOPOLOGICO");
                boolean quiereContinuar12 = true;
                while (quiereContinuar12) {
                    Scanner entrada5 = new Scanner(System.in);
                    System.out.println("Generando Grafo No Dirigido Pesado\nCantidad De Vertices: ");
                    String cantidadVertices5 = entrada5.next();
                    cantidadVertices5 = cantidadVertices5.toUpperCase();
                    int cantidad5 = Integer.parseInt(cantidadVertices5);
                    
                    List<Integer> lista=new LinkedList<>();
                    for(int i=0;i<cantidad5;i++){
                        lista.add(null);
                    }
                    int j=0;
                    Scanner entrada6 = new Scanner(System.in);
                    while (j < cantidad5) {                        
                        System.out.println("Ingrese el vertice: ");
                        String cantidadVertices6 = entrada6.next();
                        cantidadVertices6 = cantidadVertices6.toUpperCase();
                        int cantidad6 = Integer.parseInt(cantidadVertices6);
                        lista.set(j,cantidad6);
                        j++;
                    }
                    DiGrafoV1<Integer> Grafo5 = generarDiGrafoV1(lista);
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                    System.out.println(Grafo5.ordTopologico());                   
                    Scanner entrada7 = new Scanner(System.in);
                    System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                    String quiereSeguir2 = entrada7.next();
                    quiereSeguir2 = quiereSeguir2.toUpperCase();
                    if ("NO".equals(quiereSeguir2)) {
                        quiereContinuar12 = false;
                    }
                }
                break;
            case "19":
                System.out.println("19.- (GRAFO PESADOS DIRGIDOS) ALGORITMO FORD-FULKERSON");
                //Scanner generar = new Scanner(System.in);
               // System.out.println("Desea generar un grafo? (Yes/No)\n");
                //String deseaGenerar = generar.next();
                //deseaGenerar = deseaGenerar.toUpperCase();
                /*
                if ("YES".equals(deseaGenerar)) {
                    boolean quiereContinuar13 = true;
                    while (quiereContinuar13) {
                        Scanner entrada5 = new Scanner(System.in);
                        System.out.println("PORFAVOR INTRODUZCA EL TIPO DE VARIABLE (STRING/INTEGER)\n");
                        String type = entrada5.next();
                        type = type.toUpperCase();
                        List<Integer> listaInt = new LinkedList<>();
                        List<String> listaStr = new LinkedList<>();
                        if("STRING".equals(type)){
                            entrada5 = new Scanner(System.in);
                            System.out.println("PORFAVOR INTRODUZCA CANTIDAD DE VERTICES)\n");
                            String cantidadVertices5 = entrada5.next();
                            int cantidad5 = Integer.parseInt(cantidadVertices5);
                            listaStr = new LinkedList<>();
                            Scanner entrada6;
                            while (cantidad5 > 0) {
                                entrada6 = new Scanner(System.in);
                                System.out.println("Ingrese el vertice: ");
                                String cantidadVertices6 = entrada6.next();
                                cantidadVertices6  = cantidadVertices6.toUpperCase();
                                listaStr.add(cantidadVertices6);
                                cantidad5--;
                            }
                            DiGrafoPesadoV1<String> Grafo5 = generarDiGrafoPesadoV1Str(listaStr);
                            System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                        }
                        if ("INTEGER".equals(type)) {
                            entrada5 = new Scanner(System.in);
                            System.out.println("PORFAVOR INTRODUZCA CANTIDAD DE VERTICES)\n");
                            String cantidadVertices5 = entrada5.next();
                            int cantidad5 = Integer.parseInt(cantidadVertices5);
                            listaInt = new LinkedList<>();
                            Scanner entrada6;
                            while (cantidad5 > 0) {
                                entrada6 = new Scanner(System.in);
                                System.out.println("Ingrese el vertice: ");
                                String cantidadVertices6 = entrada6.next();
                                int cantidad6 = Integer.parseInt(cantidadVertices6);
                                listaInt.add(cantidad6);
                                cantidad5--;
                            }
                            DiGrafoPesadoV1<Integer> Grafo5 = generarDiGrafoPesadoV1(listaInt);
                            System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());
                        }
                        DiGrafoPesadoV1 Grafo5 = generarDiGrafoPesadoV1(lista);
                        System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + Grafo5.toString());

                        Scanner entrada8 = new Scanner(System.in);
                        System.out.println("INTRODUZCA EL VERTICE DE INICIO: ");
                        String cantidadVertices6 = entrada8.next();
                        cantidadVertices = cantidadVertices6.toUpperCase();
                        int cantidad6 = Integer.parseInt(cantidadVertices);

                        System.out.println(Grafo5.algoritmoPrim(cantidad6));

                        Scanner entrada7 = new Scanner(System.in);
                        System.out.println("QUIERE INTENTAR DE NUEVO? (SI/NO):");
                        String quiereSeguir2 = entrada7.next();
                        quiereSeguir2 = quiereSeguir2.toUpperCase();
                        if ("NO".equals(quiereSeguir2)) {
                            quiereContinuar13 = false;
                        }
                    }
                }*/
                //if ("NO".equals(deseaGenerar)) {
                    System.out.println("Porfavor si da error, hay una archivo\n"
                            + "llamado NewMain.java en la carpeta Pesados, \n"
                            + "en donde funciona correctamente el algoritmo");
                    System.out.println("UTILIZANDO GRAFO DE EJEMPLO\n");
                    List<String> lista = new LinkedList<>();
                    lista.add("A");
                    lista.add("B");
                    lista.add("C");
                    lista.add("D");
                    lista.add("E");
                    lista.add("F");
                    lista.add("G");
                    lista.add("H");

                    DiGrafoPesadoV1<String> gra = new DiGrafoPesadoV1(lista);
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
                    System.out.println("REPRESENTACION DE GRAFO MEDIANTE LISTAS \n" + gra.toString());
                    Scanner entrada8 = new Scanner(System.in);
                    System.out.println("INTRODUZCA EL VERTICE FUENTE (A/B/C/D/E/F/G/H): ");
                    String fuente = entrada8.next();
                    fuente=fuente.toUpperCase();
                    Scanner entrada9 = new Scanner(System.in);
                    System.out.println("INTRODUZCA EL VERTICE DESTINO (A/B/C/D/E/F/G/H): ");
                    String sumidero = entrada9.next();
                    sumidero=sumidero.toUpperCase();
                    System.out.println(gra.algoritmoFordFulkerson(fuente, sumidero));                        
                //}
                break;
            default:
                System.out.println("Fin");
        }        
    }
}

    