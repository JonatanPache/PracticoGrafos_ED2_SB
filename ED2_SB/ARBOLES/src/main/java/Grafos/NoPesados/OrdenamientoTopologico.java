/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Jonathan
 */
public class OrdenamientoTopologico<E extends Comparable<E>> {
    private DiGrafoV1<E> grafo;
    private List<E> lista;
    
    public OrdenamientoTopologico(DiGrafoV1<E> grafo){
        this.grafo=grafo;
        this.lista=new LinkedList<>();
        ejecutar();
    }
    
    public List<E> getLista(){
        return this.lista;
    }
    
    private void ejecutar(){
        Queue<E> cola=new LinkedList<>();
        E verticeGradoZero=this.buscarVertGradoZero(this.grafo.getListaDeVertices());
        System.out.println("vertice de gradozero: "+verticeGradoZero);
        cola.offer(verticeGradoZero);
        while(!cola.isEmpty()){
            verticeGradoZero=cola.poll();
            this.lista.add(verticeGradoZero);
            this.grafo.eliminarVertice(verticeGradoZero);
            if(!this.grafo.getListaDeVertices().isEmpty()){
                verticeGradoZero=this.buscarVertGradoZero(this.grafo.getListaDeVertices());
                System.out.println("vertice de gradozero: "+verticeGradoZero);
                cola.offer(verticeGradoZero);
            }
        }
    }
    
    private E buscarVertGradoZero(List<E> lista){
        E vertice=null;
        for(int i=0;i<lista.size();i++){
            vertice=lista.get(i);
            if(this.grafo.gradoDeEntradaDeVertice(vertice)==0){
                return vertice;
            }
        }
        return vertice;
    }
}
