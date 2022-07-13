/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.NoPesados.*;
import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class DiGrafoPesadoV1<E extends Comparable<E>> {
    protected List<E> listaDeVertices;
    protected List<List<AdyacenteConPesoV1<E>> > listasDeAdyacencias;    
    private double[][] matrizAdyacencia;
    
    public DiGrafoPesadoV1(){
        this.listasDeAdyacencias=new LinkedList<>() ;
        this.listaDeVertices=new LinkedList<>();
    }
    
    public DiGrafoPesadoV1(List<E> listaDeVertices) throws ExcepcionNroVerticeInvalido {
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
        List<AdyacenteConPesoV1<E>> listaDeAdyacentesDelVerticeInsertado=new LinkedList<>();
        this.listasDeAdyacencias.add(listaDeAdyacentesDelVerticeInsertado);
        
    }
    /*
    @Override
    public int cantidadDeAristas(){
        int cant=0;
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return cant;
    }
    */
    
    public int cantidadDeVertices(){
        return this.listaDeVertices.size();
    }
    
    public void validarVertice(E posDeVertice){
        /*if(posDeVertice==null || 
                this.listaDeVertices.indexOf(posDeVertice)==-1){
            throw new IllegalArgumentException("VERTICE NULL. Por favor ingrese un nuevo vertice");
        }*/
    }
    
    public int indexOfVertice(E vertice){
        return this.listaDeVertices.indexOf(vertice);
    }
    
    public boolean existeAdyacencia(E verticeOrigen,E verticeDestino){
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(verticeOrigen));
        AdyacenteConPesoV1<E> adyacenteVerticeDestino=new AdyacenteConPesoV1<E>(verticeDestino);
        return listaAdyacentesDelVerticeOrigen.contains(adyacenteVerticeDestino);
    }
    
    public Iterable<E> adyacentesDeVertice(E vertice){
        validarVertice(vertice);
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVertice=
            this.listasDeAdyacencias.get(this.indexOfVertice(vertice));
        List<E> listaDeSoloPosDeVertice=new ArrayList<>();
        for(AdyacenteConPesoV1<E> unAdyacente:listaAdyacentesDelVertice){
            listaDeSoloPosDeVertice.add((E) unAdyacente.getIndiceVertice());
        }
        Iterable<E> iterableDeAdyacentesDeVertice=listaDeSoloPosDeVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    public Iterable<AdyacenteConPesoV1<E>> adyacentesConPesoDeVertice(E vertice){
        validarVertice(vertice);
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVertice=
            this.listasDeAdyacencias.get(this.indexOfVertice(vertice));
        
        Iterable<AdyacenteConPesoV1<E>> iterableDeAdyacentesDeVertice=listaAdyacentesDelVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    public Iterable<E> iterablesVertices(){        
        Iterable<E> iterableVertice=this.listaDeVertices;
        return iterableVertice;
    }
    
    public void insertarArista(E posVerticeOrigen,E posVerticeDestino, double peso) throws ExcepcionAristaYaExiste{
        // verificamos si existe ya la arista en el grafo
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        // empezamos a insertar la arista
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(posVerticeOrigen));
        AdyacenteConPesoV1<E> adyacenteDelOrigen=
                new AdyacenteConPesoV1<E>(posVerticeDestino,peso);
        listaAdyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
    }
    /*
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        listaAdyacentesDelVerticeOrigen.remove(posicionVerticeDestinoEnListaOrigen);
    }
    */
    
    /*
    public int gradoDeSalidaDeVertice(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
    }
    
    public int gradoDeEntradaDeVertice(int posDeVertice){
        super.validarVertice(posDeVertice);
        int cantidad=0;
        AdyacenteConPeso adyacenteDelVertice=new AdyacenteConPeso(posDeVertice);
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            if(listaAdyacentesDeUnVertice.contains(adyacenteDelVertice)){
                cantidad+=1;
            }
        }
        return cantidad;
    }
    */
    
    public double peso(E posVerticeOrigen,E posVerticeDestino)throws ExcepcionAristaNoExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        /*if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }*/
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(indexOfVertice(posVerticeOrigen));
        AdyacenteConPesoV1<E> adyacenteDelOrigen=
                new AdyacenteConPesoV1<E>(posVerticeDestino);
        int posicionDeAdyacencia=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        AdyacenteConPesoV1<E> adyacenteDelOrigenAlmacenado=listaAdyacentesDelVerticeOrigen.get(posicionDeAdyacencia);
        return adyacenteDelOrigenAlmacenado.getPeso();
    }
    
    public E getVertice(int pos){
        return this.listaDeVertices.get(pos);
    }
    public void modificarArista(E posVerticeOrigen,E posVerticeDestino, double peso) throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        
        List<AdyacenteConPesoV1<E>> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(posVerticeOrigen));;
        AdyacenteConPesoV1<E> adyacenteDelOrigen=
                new AdyacenteConPesoV1<E>(posVerticeDestino);
        int posicionDeAdyacencia=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        double pesoAnterior=listaAdyacentesDelVerticeOrigen.get(posicionDeAdyacencia).getPeso();
        AdyacenteConPesoV1<E> adyacenteNuevo=
                new AdyacenteConPesoV1<E>(posVerticeDestino,peso+pesoAnterior);
        listaAdyacentesDelVerticeOrigen.set(posicionDeAdyacencia,adyacenteNuevo);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
    }
    
    public String algoritmoFordFulkerson(E fuente, E sumidero) throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        Ford_Fulkerson ford=new Ford_Fulkerson(this,fuente,sumidero);
        return ford.getCapacidad();
    }
    
    @Override
    public String toString() {
        String S="";
        for(int i=0;i<this.listasDeAdyacencias.size();i++){
            List<AdyacenteConPesoV1<E>> adyVert=this.listasDeAdyacencias.get(i);
            S=S+this.listaDeVertices.get(i)+": ";
            String C="";
                C+=adyVert.toString();
            
            S=S+C+"\n";
        }
        return S;
    }
}
