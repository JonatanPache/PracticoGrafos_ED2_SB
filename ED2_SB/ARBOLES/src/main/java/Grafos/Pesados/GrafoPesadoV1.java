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
 * @param <E>
 */
public class GrafoPesadoV1<E extends Comparable<E>>{
    protected List<E> listaDeVertices;
    protected List<List<AdyacenteConPesoV1>> listasDeAdyacencias;

    
    public GrafoPesadoV1(){
       this.listasDeAdyacencias=new LinkedList<>() ;
       this.listaDeVertices=new LinkedList<>();
    }
    public GrafoPesadoV1(E vertice){
       this.listasDeAdyacencias=new LinkedList<>() ;
       this.listaDeVertices=new LinkedList<>();
       this.insertarVertice(vertice);
    }
    /**
     * Constructor del grafo con lista de vertices
     * @param listaDeVertices
     * @throws ExcepcionNroVerticeInvalido 
     */
    public GrafoPesadoV1(List<E> listaDeVertices) throws ExcepcionNroVerticeInvalido{
       if(listaDeVertices.size()<=0){
           throw new ExcepcionNroVerticeInvalido();
       }
       this.listasDeAdyacencias=new LinkedList<>();
       this.listaDeVertices=new LinkedList<>();
       for(int i=0;i<listaDeVertices.size();i++){
           E vertice=listaDeVertices.get(i);
           this.insertarVertice(vertice);
       }
    }
    /**
     * 
     * @param vertice 
     */
    public void insertarVertice(E vertice){
        this.listaDeVertices.add(vertice);
        List<AdyacenteConPesoV1> listaDeAdyacentesDelVerticeInsertado=new LinkedList<>();
        this.listasDeAdyacencias.add(listaDeAdyacentesDelVerticeInsertado);
        
    }
    
    public int cantidadDeVertices(){
        return this.listaDeVertices.size();
    }
    
    /**
     * Valida el vertice
     */
    public void validarVertice(E posDeVertice){
        /*if(posDeVertice==null || 
                this.listaDeVertices.indexOf(posDeVertice)!=-1){
            throw new IllegalArgumentException("VERTICE NULL. Por favor ingrese un nuevo vertice");
        }*/
    }
    
    
    public int gradoDeVertice(E vertice){
        validarVertice(vertice);
        int indiceDelVertice=this.indexOfVertice(vertice);
        List<AdyacenteConPesoV1> listaAdyacentesDelVertice=this.listasDeAdyacencias.get(indiceDelVertice);
        return listaAdyacentesDelVertice.size();
    }
    
    public int indexOfVertice(E vertice){
        return this.listaDeVertices.indexOf(vertice);
    }
    
    public E getVertice(int pos){
        return this.listaDeVertices.get(pos);
    }
    
    public boolean existeAdyacencia(E verticeOrigen,E verticeDestino){
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        List<AdyacenteConPesoV1> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(verticeOrigen));
        AdyacenteConPesoV1 adyacenteVerticeDestino=new AdyacenteConPesoV1(verticeDestino);
        return listaAdyacentesDelVerticeOrigen.contains(adyacenteVerticeDestino);
    }
    
    public Iterable<E> adyacentesDeVertice(E vertice){
        validarVertice(vertice);
        List<AdyacenteConPesoV1> listaAdyacentesDelVertice=
            this.listasDeAdyacencias.get(this.indexOfVertice(vertice));
        List<E> listaDeSoloPosDeVertice=new ArrayList<>();
        for(AdyacenteConPesoV1 unAdyacente:listaAdyacentesDelVertice){
            listaDeSoloPosDeVertice.add((E) unAdyacente.getIndiceVertice());
        }
        Iterable<E> iterableDeAdyacentesDeVertice=listaDeSoloPosDeVertice;
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
        // obtenemos su lista adyacentes del vertice origen
        List<AdyacenteConPesoV1> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(this.indexOfVertice(posVerticeOrigen));
        // creamos la adyacencia 
        AdyacenteConPesoV1 adyacenteDelOrigen=
                new AdyacenteConPesoV1(posVerticeDestino,peso);
        // insertamos en la lista de ayacencia del origen
        listaAdyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
        // verifico si es lazo o si la arista apunta al mismo vertice
        if(posVerticeOrigen!=posVerticeDestino){
            List<AdyacenteConPesoV1> listaAdyacentesDelVerticeDestino=
                    this.listasDeAdyacencias.get(indexOfVertice(posVerticeDestino));
            AdyacenteConPesoV1 adyacenteDelDestino=
                    new AdyacenteConPesoV1(posVerticeOrigen,peso);
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
     *//*
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
    }*/
    /*
    public int cantidadDeAristas(){
        int cant=0;
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return (cant+1)/2;
    }*/
    /*
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
    */
    public double peso(E posVerticeOrigen,E posVerticeDestino)throws ExcepcionAristaNoExiste{
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPesoV1> listaAdyacentesDelVerticeOrigen=
                this.listasDeAdyacencias.get(indexOfVertice(posVerticeOrigen));
        AdyacenteConPesoV1 adyacenteDelOrigen=
                new AdyacenteConPesoV1(posVerticeDestino);
        int posicionDeAdyacencia=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
        AdyacenteConPesoV1 adyacenteDelOrigenAlmacenado=listaAdyacentesDelVerticeOrigen.get(posicionDeAdyacencia);
        return adyacenteDelOrigenAlmacenado.getPeso();
    }
    /*
    public String algoritmoKruskal() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        Kruskal kr=new Kruskal(this);
        return "GRAFO RESULTANTE \n"+kr.getGrafoResultante().toString();
    }
    */
    public String algoritmoPrim(E verticeInicial){
        try {
            Prim p;
            p = new Prim(this,verticeInicial);
            return "GRAFO RESULTANTE \n"+p.getGrafoResultante().toString();
        } catch (ExcepcionNroVerticeInvalido ex) {
            Logger.getLogger(GrafoPesadoV1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionAristaNoExiste ex) {
            Logger.getLogger(GrafoPesadoV1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionAristaYaExiste ex) {
            Logger.getLogger(GrafoPesadoV1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean existeVertice(E vertice){
        return this.listaDeVertices.indexOf(vertice)>=0;
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
                try {
                    C=C+adyVerticeTurno+
                            "{"+this.peso(this.listaDeVertices.get(i),adyVerticeTurno)+"}"+
                            ", ";
                } catch (ExcepcionAristaNoExiste ex) {
                    Logger.getLogger(GrafoPesadoV1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            S=S+C+"\n";
        }
        return S;
    }


    
}

