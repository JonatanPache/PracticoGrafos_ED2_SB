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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class GrafoPesado{
    protected List<List<AdyacenteConPeso>> listasDeAdyacencias;

    public GrafoPesado(){
       this.listasDeAdyacencias=new LinkedList<>() ;
    }
    
    /**
     *
     * @param nroDeVertices
     * @throws ExcepcionNroVerticeInvalido
     */
    public GrafoPesado(int nroDeVertices) throws ExcepcionNroVerticeInvalido{
       if(nroDeVertices<=0){
           throw new ExcepcionNroVerticeInvalido();
       }
       
       this.listasDeAdyacencias=new LinkedList<>() ;
       for(int i=0;i<nroDeVertices;i++){
           this.insertarVertice();
       }
    }
    /**
     * Agrega una lista vacia en la posicion 
     */
    public void insertarVertice(){
        List<AdyacenteConPeso> listaDeAdyacentesDeVerticeAInsertar=new LinkedList<>();
        this.listasDeAdyacencias.add(listaDeAdyacentesDeVerticeAInsertar);
    }
    
    public void insertarVertice(int posicionVertice){
        if(posicionVertice>=this.listasDeAdyacencias.size()){
            int cantidad=(posicionVertice-this.listasDeAdyacencias.size())+1;
            for(int i=0;i<cantidad;i++){
                this.insertarVertice();
            }
        }
        
    }
    
    public int cantidadDeVertices(){
        return this.listasDeAdyacencias.size();
    }
    
    /**
     * Valida el vertice
     */
    public void validarVertice(int posDeVertice){
        if(posDeVertice<0 || posDeVertice>=this.cantidadDeVertices()){
            throw new IllegalArgumentException("No existe vertice en posicion "
                    +posDeVertice+" en el grafo");
        }
    }
    
    
    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> listaAdyacentesDelVertice=this.listasDeAdyacencias.get(posDeVertice);
        return listaAdyacentesDelVertice.size();
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteVerticeDestino=new AdyacenteConPeso(posVerticeDestino);
        return listaAdyacentesDelVerticeOrigen.contains(adyacenteVerticeDestino);
    }
    
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> listaAdyacentesDelVertice=this.listasDeAdyacencias.get(posDeVertice);
        List<Integer> listaDeSoloPosDeVertice=new ArrayList<>();
        for(AdyacenteConPeso unAdyacente:listaAdyacentesDelVertice){
            listaDeSoloPosDeVertice.add(unAdyacente.getIndiceVertice());
        }
        Iterable<Integer> iterableDeAdyacentesDeVertice=listaDeSoloPosDeVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste{
        // verificamos si existe ya la arista en el grafo
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        // empezamos a insertar la arista
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino,peso);
        listaAdyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
        // verifico si es lazo o al arista apunta al mismo vertice
        if(posVerticeOrigen!=posVerticeDestino){
            List<AdyacenteConPeso> listaAdyacentesDelVerticeDestino=this.listasDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino=new AdyacenteConPeso(posVerticeOrigen,peso);
            listaAdyacentesDelVerticeDestino.add(adyacenteDelDestino);
            Collections.sort(listaAdyacentesDelVerticeDestino);
        }
    }
    
    /**
     * Elimnar arista
     * Parametros: pos origen, pos destino
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws Grafos.Excepciones.ExcepcionAristaNoExiste
     */
    public void elimnarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        listaAdyacentesDelVerticeOrigen.remove(posicionVerticeDestinoEnListaOrigen);
        
        // verifico si es lazo o al arista apunta al mismo vertice
        if(posVerticeOrigen!=posVerticeDestino){
            List<AdyacenteConPeso> listaAdyacentesDelVerticeDestino=this.listasDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino=new AdyacenteConPeso(posVerticeOrigen);
            int posicionVerticeOrigenEnListaDestino=listaAdyacentesDelVerticeDestino.indexOf(adyacenteDelDestino);
            listaAdyacentesDelVerticeDestino.remove(posicionVerticeOrigenEnListaDestino);
        }
    }
    
    public int cantidadDeAristas(){
        int cant=0;
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return (cant+1)/2;
    }
    
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listasDeAdyacencias.remove(posVerticeAEliminar);
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice : this.listasDeAdyacencias){
            AdyacenteConPeso unAdyacente=new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeAEliminarEnAdyacencia=listaAdyacentesDeUnVertice.indexOf(unAdyacente);
            // verifcamos si existe indice del vertice a eliminar en cada iteracion
            // primerp elominamos el vertice a eliminar en las listas de adyacencias
            if(posicionDeVerticeAEliminarEnAdyacencia>=0){
                listaAdyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdyacencia);
            }
            // debo restar a los vertice adyacentes posteriores del vertice eliminado en esta lista actual
            for(int i=0;i<listaAdyacentesDeUnVertice.size();i++){
                AdyacenteConPeso adyacenteConPeso=listaAdyacentesDeUnVertice.get(i);
                int posicionDeAdyacencia=adyacenteConPeso.getIndiceVertice();
                if(posicionDeAdyacencia>posVerticeAEliminar){
                    posicionDeAdyacencia--;
                    // actualizamos
                    adyacenteConPeso.setIndiceVertice(posicionDeAdyacencia);
                }
                
            }
        }
    }
    
    public double peso(int posVerticeOrigen,int posVerticeDestino)throws ExcepcionAristaNoExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino);
        int posicionDeAdyacencia=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        AdyacenteConPeso adyacenteDelOrigenAlmacenado=listaAdyacentesDelVerticeOrigen.get(posicionDeAdyacencia);
        return adyacenteDelOrigenAlmacenado.getPeso();
    }
    
    public String algoritmoKruskal() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        Kruskal kr=new Kruskal(this);
        return "GRAFO RESULTANTE \n"+kr.getGrafoResultante().toString();
    }
    /*
    public String algoritmoPrim(){
        try {
            Prim p;
            p = new Prim(this,0);
            return "GRAFO RESULTANTE \n"+p.getGrafoResultante().toString();
        } catch (ExcepcionNroVerticeInvalido ex) {
            Logger.getLogger(GrafoPesado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionAristaNoExiste ex) {
            Logger.getLogger(GrafoPesado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionAristaYaExiste ex) {
            Logger.getLogger(GrafoPesado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
    @Override
    public String toString(){
        String S="";
        for(int i=0;i<this.listasDeAdyacencias.size();i++){
            Iterable<Integer> adyVertice=this.adyacentesDeVertice(i);
            S=S+i+": ";
            String C="";
            for(Integer adyVerticeTurno:adyVertice){
                try {
                    C=C+adyVerticeTurno+
                            "{"+this.peso(i,adyVerticeTurno)+"}"+
                            ", ";
                } catch (ExcepcionAristaNoExiste ex) {
                    Logger.getLogger(GrafoPesado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            S=S+C+"\n";
        }
        return S;
    }
}

