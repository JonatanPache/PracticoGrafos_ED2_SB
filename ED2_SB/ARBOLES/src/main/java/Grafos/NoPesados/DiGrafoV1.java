/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.Pesados.GrafoPesadoV1;
import Grafos.utilitario.RecorridoUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class DiGrafoV1<E extends Comparable<E>>{
    protected List<E> listaDeVertices;
    protected List<List<E> > listasDeAdyacencias;
    
    public DiGrafoV1(){
        this.listasDeAdyacencias=new LinkedList<>() ;
        this.listaDeVertices=new LinkedList<>();
    }
    
    public DiGrafoV1(List<E> listaDeVertices) throws ExcepcionNroVerticeInvalido{
        if (listaDeVertices.size() <= 0) {
            throw new ExcepcionNroVerticeInvalido();
        }
        this.listasDeAdyacencias = new LinkedList<>();
        this.listaDeVertices = new LinkedList<>();
        for (int i = 0; i < listaDeVertices.size(); i++) {
            E vertice = listaDeVertices.get(i);
            this.insertarVertice(vertice);
        }
    }
    
    public void insertarVertice(E vertice){
        this.listaDeVertices.add(vertice);
        List<E> listaDeAdyacentesDelVerticeInsertado=new LinkedList<>();
        this.listasDeAdyacencias.add(listaDeAdyacentesDelVerticeInsertado);
        
    }
    
    public int cantidadDeVertices(){
        return this.listaDeVertices.size();
    }
    
    public void validarVertice(E posDeVertice){
        if(posDeVertice==null || 
                this.listaDeVertices.indexOf(posDeVertice)==-1){
            throw new IllegalArgumentException("VERTICE NULL. Por favor ingrese un nuevo vertice");
        }
    }
    
    public int indexOfVertice(E vertice){
        return this.listaDeVertices.indexOf(vertice);
    }
    
    public Iterable<E> adyacentesDeVertice(E vertice){
        validarVertice(vertice);
        List<E> listaAdyacentesDelVertice=
            this.listasDeAdyacencias.get(this.indexOfVertice(vertice));
        //List<E> listaDeSoloPosDeVertice=new ArrayList<>();
        //for(E unAdyacente:listaAdyacentesDelVertice){
        //    listaDeSoloPosDeVertice.add((E) unAdyacente.getIndiceVertice());
        //}
        Iterable<E> iterableDeAdyacentesDeVertice=listaAdyacentesDelVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    public boolean existeAdyacencia(E verticeOrigen,E verticeDestino){
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        List<E> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(verticeOrigen));
        return listaAdyacentesDelVerticeOrigen.contains(verticeDestino);
    }
    
    public void insertarArista(E posVerticeOrigen,E posVerticeDestino) throws ExcepcionAristaYaExiste{
        // verificamos si existe ya la arista en el grafo
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        // empezamos a insertar la arista
        List<E> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(posVerticeOrigen));
        listaAdyacentesDelVerticeOrigen.add(posVerticeDestino);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
    }
    /*
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.lastIndexOf(posVerticeDestino);
        listaAdyacentesDelVerticeOrigen.remove(posicionVerticeDestinoEnListaOrigen);
    }*/
    
    public void eliminarVertice(E verticeAEliminar){
        validarVertice(verticeAEliminar);
        int indice=this.indexOfVertice(verticeAEliminar);
        this.listaDeVertices.remove(indice);
        this.listasDeAdyacencias.remove(indice);
   }
    
    /*
    public int gradoDeSalidaDeVertice(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
    }
    */
    public int gradoDeEntradaDeVertice(E posDeVertice){
        validarVertice(posDeVertice);
        int cantidad=0;
        for(List<E> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            if(listaAdyacentesDeUnVertice.contains(posDeVertice)){
                cantidad+=1;
            }
        }
        return cantidad;
    }

    public List<E> getListaDeVertices() {
        return listaDeVertices;
    }
    
    public String ordTopologico(){
        OrdenamientoTopologico ord=new OrdenamientoTopologico(this);
        return "Ordenamiento Topologico: "+ord.getLista().toString();
    }
    
    @Override
    public String toString(){
        String S="";
        for(int i=0;i<this.listaDeVertices.size();i++){
            Iterable<E> adyVertice=
                    this.adyacentesDeVertice(this.listaDeVertices.get(i));
            S=S+this.listaDeVertices.get(i)+": ";
            String C="";
            for(E adyVerticeTurno:adyVertice){
                C=C+adyVerticeTurno+", ";
            }
            S=S+C+"\n";
        }
        return S;
    }
    
}
