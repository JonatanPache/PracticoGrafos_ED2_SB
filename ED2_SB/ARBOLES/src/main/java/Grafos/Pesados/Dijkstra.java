/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.utilitario.RecorridoUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class Dijkstra {
    private DiGrafoPesado grafo;
    private double[] costos;
    private int[] predecesores;
    private RecorridoUtils marcados;
    List<Integer> caminoMinimo;
    Double costoMinimo;
    
    public Dijkstra(DiGrafoPesado grafo ,int origen ,int destino) throws ExcepcionAristaNoExiste{
        this.grafo=grafo;
        this.costos=new double[grafo.cantidadDeVertices()];
        this.marcados=new RecorridoUtils(grafo.cantidadDeVertices());
        this.predecesores=new int[grafo.cantidadDeVertices()];
        for(int i=0;i<grafo.cantidadDeVertices();i++){
            this.costos[i]=Double.POSITIVE_INFINITY;
            this.predecesores[i]=-1;
        }
        this.costos[origen]=0;
        ejecutar(origen,destino);
        caminoMinimo(destino);
        costoMinimo(destino);
    }
    
    private void ejecutar(int a, int b) throws ExcepcionAristaNoExiste{
        int verticeCostoMin;
        while(!this.marcados.estaVerticeMarcado(b)){
            verticeCostoMin=this.verticeMenorCostoNoMarcado();
            Iterable<Integer> adyacenteDelVertice=this.grafo.adyacentesDeVertice(verticeCostoMin);
            for(Integer adyacente:adyacenteDelVertice){
                if(!this.marcados.estaVerticeMarcado(adyacente)){
                    if(this.costos[adyacente]>
                            this.costos[verticeCostoMin]+this.grafo.peso(verticeCostoMin,adyacente)){
                        this.costos[adyacente]=this.costos[verticeCostoMin]+this.grafo.peso(verticeCostoMin,adyacente);
                        this.predecesores[adyacente]=verticeCostoMin;
                    }
                }
            }
        }
    }
    
    private int verticeMenorCostoNoMarcado(){
        double costoMin=Double.POSITIVE_INFINITY;
        int vertice=0;
        for(int i=0;i<this.costos.length;i++){
            if(!this.marcados.estaVerticeMarcado(i) 
                    && this.costos[i]<costoMin){
                costoMin=this.costos[i];
                vertice=i;
            }
        }
        this.marcados.marcarVertice(vertice);
        return vertice;
    }
    
    public void caminoMinimo(int b){
        List<Integer> caminoMinimo=new ArrayList<>();
        Stack<Integer> pilaVertices=new Stack<>();
        pilaVertices.add(b);
        while(this.predecesores[b]!=-1){
            pilaVertices.add(this.predecesores[b]);
            b=this.predecesores[b];
        }
        while(!pilaVertices.isEmpty()){
            caminoMinimo.add(pilaVertices.pop());
        }
        this.caminoMinimo=caminoMinimo;
    }
    
    private void costoMinimo(int b){
        this.costoMinimo=this.costos[b];
    }

    public List<Integer> getCaminoMinimo() {
        return caminoMinimo;
    }

    public Double getCostoMinimo() {
        return costoMinimo;
    }
    
    
}
