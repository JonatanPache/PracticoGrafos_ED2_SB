/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Code;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonatan
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExceptionOrdenInvalido, ExceptionClaveNoExiste {
        // TODO code application logic here
        /*ARBOL BINARIO BUSQUEDA*/
        
        IArbolBusqueda<Integer,String> arbol=new ArbolBinarioBusqueda<>();
        /*
        arbol.InsertarR(10,"Jon");
        arbol.InsertarR(20,"Jon2");
        arbol.InsertarR(30,"Jon");*/
        /*arbol.InsertarR(25,"Jon");
        arbol.InsertarR(28,"Jon");
        arbol.InsertarR(5,"Jon");
        arbol.InsertarR(8,"Jon");
        arbol.InsertarR(39,"Jon");*/
        arbol.Insertar(41, "juan");
        arbol.Insertar(20, "juan");
        arbol.Insertar(65, "juan");

        arbol.Insertar(11, "juan");

        arbol.Insertar(29, "juan");
        arbol.Insertar(32, "juan");

        arbol.Insertar(50, "juan");
        arbol.Insertar(91, "juan");
        arbol.Insertar(72, "juan");

        arbol.Insertar(99, "juan");
        
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        System.out.println(arbol.cantidadNodosConUnHijoNoVacio());

        
        /*
        AVL
        */
        /*
        ArbolBinarioBusqueda<Integer,String> arbol=new AVL<>();
        arbol.Insertar(40, "JON");
        
        arbol.Insertar(50, "JON");
        arbol.Insertar(30, "JON");
        arbol.Insertar(60, "JON");
        arbol.Insertar(20, "JON");
        arbol.Insertar(25, "JON");
        
        
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        arbol.eliminar(60);
        arbol.eliminar(50);
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        */
        /*
        List<Integer> listaClavesInOrden=new LinkedList<>();
        listaClavesInOrden.add(5);
        listaClavesInOrden.add(10);
        listaClavesInOrden.add(15);
        listaClavesInOrden.add(20);
        listaClavesInOrden.add(25);
        List<String> listaValoresInOrden=new LinkedList<>();
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        List<Integer> listaClavesPreOrden=new LinkedList<>();
        listaClavesPreOrden.add(20);
        listaClavesPreOrden.add(10);
        listaClavesPreOrden.add(5);
        listaClavesPreOrden.add(15);
        listaClavesPreOrden.add(25);
        List<String> listaValoresPreOrden=new LinkedList<>();
        listaValoresPreOrden.add("juanito");
        listaValoresPreOrden.add("juanito");
        listaValoresPreOrden.add("juanito");
        listaValoresPreOrden.add("juanito");
        listaValoresPreOrden.add("juanito");
        */
        /*
        List<Integer> listaClavesInOrden=new LinkedList<>();
        listaClavesInOrden.add(20);
        listaClavesInOrden.add(30);
        listaClavesInOrden.add(40);
        listaClavesInOrden.add(42);
        listaClavesInOrden.add(45);
        
        listaClavesInOrden.add(50);
        listaClavesInOrden.add(60);
        listaClavesInOrden.add(80);
        
        List<String> listaValoresInOrden=new LinkedList<>();
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        listaValoresInOrden.add("juanito");
        
        List<Integer> listaClavesPostOrden=new LinkedList<>();
        listaClavesPostOrden.add(20);
        listaClavesPostOrden.add(30);
        listaClavesPostOrden.add(42);
        listaClavesPostOrden.add(45);
        listaClavesPostOrden.add(40);
        
        listaClavesPostOrden.add(60);
        listaClavesPostOrden.add(80);
        listaClavesPostOrden.add(50);
        
        List<String> listaValoresPostOrden=new LinkedList<>();
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        listaValoresPostOrden.add("juanito");
        
        IArbolBusqueda<Integer,String> arbol=new ArbolBinarioBusqueda<>(listaClavesInOrden,
                listaValoresInOrden,listaClavesPostOrden,listaValoresPostOrden,false);
        */
        //IArbolBusqueda<Integer,String> arbol=new ArbolMViasBusqueda<>(3);
        
        //ArbolMViasBusqueda<Integer,String> arbol=new ArbolB<>(4);
        /*
        arbol.Insertar(100, "juan");
        arbol.Insertar(300, "juan");
        arbol.Insertar(500, "juan");
        
        arbol.Insertar(50, "juan");

        arbol.Insertar(400, "juan"); 
        arbol.Insertar(800, "juan");

        arbol.Insertar(420, "juan");
        arbol.Insertar(450, "juan");
        arbol.Insertar(410, "juan");
        
        arbol.Insertar(850, "juan");
        arbol.Insertar(900, "juan");
        arbol.Insertar(870, "juan");
        */
        /*
        arbol.Insertar(70, "juan");    //3
        arbol.Insertar(90, "juan");    //4
        arbol.Insertar(91, "juan");    //5
        
        arbol.Insertar(92, "juan");    //6
        arbol.Insertar(95, "juan");    //7
        /*
        arbol.Insertar(700, "juan");    //8
        arbol.Insertar(509, "juan");    //9
        arbol.Insertar(520, "juan");    //10
        arbol.Insertar(550, "juan");    //11
        
        arbol.Insertar(515, "juan");    //12
        arbol.Insertar(519, "juan");    //13
        
        System.out.println(arbol.recorridoPorNiveles());
        /*
        System.out.println(arbol.getRaiz().estanClavesLlenas());
        System.out.println(arbol.getRaiz().listaDeClaves.size());
        System.out.println(arbol.getRaiz().listaDeValores.size());
        System.out.println(arbol.getRaiz().listaDeHijos.size());
*/
        //arbol.eliminar(550);    //16
        System.out.println(arbol.recorridoEnInOrden());
        System.out.println(arbol.cantidadNodosConUnHijoNoVacio());
        
        //System.out.println(arbol.recorridoEnInOrden());
    }
    
}
