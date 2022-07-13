/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.utilitario.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jonathan
 * @param <E>
 */
public class Ford_Fulkerson<E extends Comparable<E>>  {
    private DiGrafoPesadoV1<E> grafoOriginal;
    private List<AdyacenteConPesoV1> listaEtiquetas;
    private RecorridoUtils controlMarcados;
    private RecorridoUtils controlDescartados;
    private double capacidad;
    private E fuente;
    private E sumidero;
    
    public Ford_Fulkerson(DiGrafoPesadoV1 grafo,E fuente,E sumidero) throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        this.fuente=fuente;
        this.sumidero=sumidero;
        this.grafoOriginal=grafo;
        arreglar();
        this.listaEtiquetas=new LinkedList<>();
        this.controlMarcados=new RecorridoUtils(grafo.cantidadDeVertices());
        this.controlDescartados=new RecorridoUtils(grafo.cantidadDeVertices());
        for(int i=0;i<grafo.cantidadDeVertices();i++){
            this.listaEtiquetas.add(null);
        }
                
        Stack<E> pila=new Stack<>();
        pila.push(fuente);
        this.controlMarcados.marcarVertice(this.grafoOriginal.indexOfVertice(fuente));
        AdyacenteConPesoV1<E> adyacente=new AdyacenteConPesoV1<>(fuente,Double.POSITIVE_INFINITY);
        this.listaEtiquetas.set(this.grafoOriginal.indexOfVertice(fuente), adyacente);
        while(!pila.isEmpty()){
            while(!this.controlMarcados.estaVerticeMarcado(this.grafoOriginal.indexOfVertice(sumidero)) &&
                    !pila.isEmpty()){
                E verticeTurno=pila.pop();
                while(!existeAdyacenteNoMarcadoConPeso(verticeTurno) && !pila.isEmpty()){
                    this.controlDescartados.marcarVertice(this.grafoOriginal.indexOfVertice(verticeTurno));
                    verticeTurno=pila.pop();
                }
                if (existeAdyacenteNoMarcadoConPeso(verticeTurno)) {
                    AdyacenteConPesoV1<E> adyacenteTurno = obtenerAdyacenteConPesoMayor(verticeTurno);
                    E verticeAdyacente = adyacenteTurno.getIndiceVertice();
                    this.controlMarcados.marcarVertice(this.grafoOriginal.indexOfVertice(verticeAdyacente));
                    AdyacenteConPesoV1<E> adyacenteMayor = new AdyacenteConPesoV1(verticeTurno, adyacenteTurno.getPeso());
                    this.listaEtiquetas.set(this.grafoOriginal.indexOfVertice(verticeAdyacente), adyacenteMayor);
                    System.out.println(adyacenteMayor.toString()+" to "+ verticeAdyacente);
                    
                    if(verticeAdyacente.compareTo(sumidero)!=0){
                        pila.push(verticeTurno);
                        pila.push(verticeAdyacente);
                    }
                    
                }
            }
            double minimoIterable=Double.MAX_VALUE;
            if(this.controlMarcados.estaVerticeMarcado(this.grafoOriginal.indexOfVertice(sumidero))){
                for(int i=0;i<this.listaEtiquetas.size();i++){
                    if(this.controlMarcados.estaVerticeMarcado(i) && 
                            !this.controlDescartados.estaVerticeMarcado(i)){
                        if(this.listaEtiquetas.get(i).getPeso()<minimoIterable){
                            minimoIterable=this.listaEtiquetas.get(i).getPeso();
                        }                        
                    }
                }
                System.out.println(minimoIterable);
                this.capacidad+= minimoIterable;
                actualizar(minimoIterable);
                limpiar();
                if (!pila.isEmpty()) {
                    pila = new Stack<>();
                    pila.push(fuente);
                    this.controlMarcados.marcarVertice(this.grafoOriginal.indexOfVertice(fuente));
                    adyacente = new AdyacenteConPesoV1<E>(fuente, Double.POSITIVE_INFINITY);
                    this.listaEtiquetas.set(this.grafoOriginal.indexOfVertice(fuente), adyacente);
                }
            }
        }
    }
    public String getCapacidad(){
        return "Capacidad "+Double.toString(this.capacidad);
    }
    
    private void actualizar(double min) throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        for(int i=0;i<this.controlMarcados.getSize();i++){
            if(this.controlMarcados.estaVerticeMarcado(i) 
                    && !this.controlDescartados.estaVerticeMarcado(i) 
                    && this.grafoOriginal.getVertice(i).compareTo(this.fuente)!=0){
                AdyacenteConPesoV1<E> adyacenteTurno=this.listaEtiquetas.get(i);
                E verticeActual=this.grafoOriginal.getVertice(i);
                E verticeAdyacente=adyacenteTurno.getIndiceVertice();
                this.grafoOriginal.modificarArista(verticeActual,verticeAdyacente,min);
                this.grafoOriginal.modificarArista(verticeAdyacente,verticeActual,-min);
            }
        }
    }
    
    private void limpiar(){
        for(int i=0;i<this.listaEtiquetas.size();i++){
            this.listaEtiquetas.set(i, null);
        }
        this.controlDescartados.desmarcarTodos();
        this.controlMarcados.desmarcarTodos();       
    }
    
    private AdyacenteConPesoV1 obtenerAdyacenteConPesoMayor(E verticeTurno) throws ExcepcionAristaNoExiste{
        Iterable<E> iterableAdyacente=this.grafoOriginal.adyacentesDeVertice(verticeTurno);
        double max=0;
        AdyacenteConPesoV1<E> adyacenteMayor=new AdyacenteConPesoV1<E>(null,0);
        for(E adyacenteTurno:iterableAdyacente){
            int indice=this.grafoOriginal.indexOfVertice(adyacenteTurno);
            double peso=this.grafoOriginal.peso(verticeTurno, adyacenteTurno);
            if(!this.controlMarcados.estaVerticeMarcado(indice) && 
                    peso>max){
                max=peso;
                adyacenteMayor=new AdyacenteConPesoV1<E>(adyacenteTurno,peso);
            }
        }
        return adyacenteMayor;
    }
    
    private boolean existeAdyacenteNoMarcadoConPeso(E verticeTurno) throws ExcepcionAristaNoExiste{
        Iterable<E> iterableAdyacente=this.grafoOriginal.adyacentesDeVertice(verticeTurno);
        for(E adyacenteTurno:iterableAdyacente){
            int indice=this.grafoOriginal.indexOfVertice(adyacenteTurno);
            double peso=this.grafoOriginal.peso(verticeTurno, adyacenteTurno);
            if(!this.controlMarcados.estaVerticeMarcado(indice) && 
                    peso>0 && !this.controlDescartados.estaVerticeMarcado(indice)){
                return true;
            }
        }
        return false;
    }
    
    private void arreglar() throws ExcepcionAristaYaExiste{
        Iterable<E> iterableVertices=this.grafoOriginal.iterablesVertices();
        for(E verticeTurno:iterableVertices){
            Iterable<E> ady=this.grafoOriginal.adyacentesDeVertice(verticeTurno);
            for(E adyacenteTurno:ady){
                if(!this.grafoOriginal.existeAdyacencia(adyacenteTurno, verticeTurno)){
                    this.grafoOriginal.insertarArista(adyacenteTurno, verticeTurno, 0);
                }
            }
        }
    }
}
