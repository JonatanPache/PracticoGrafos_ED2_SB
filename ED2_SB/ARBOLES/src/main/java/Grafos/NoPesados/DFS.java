/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.utilitario.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class DFS {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;


    public DFS(Grafo unGrafo,int unaPosDeVerticeDePartida){
        this.grafo=unGrafo;
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido=new LinkedList<>();
        controlDeMarcados=new  RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarDFS(unaPosDeVerticeDePartida);
    }
    
    public void ejecutarDFS(int posDeVerticeDePartida){
        Stack<Integer> pila=new  Stack<>();
        pila.push(posDeVerticeDePartida);
        this.controlDeMarcados.marcarVertice(posDeVerticeDePartida);
        do{
            int posDeVerticeEnTurno=pila.pop();
            recorrido.add(posDeVerticeEnTurno);
            Iterable<Integer> itAdyacentesDeVerticeEnTurno=this.grafo.adyacentesDeVertice(posDeVerticeEnTurno);
            for(Integer posDeVerticeAdyacente:itAdyacentesDeVerticeEnTurno){
                if(!this.controlDeMarcados.estaVerticeMarcado(posDeVerticeAdyacente)){
                    pila.push(posDeVerticeAdyacente);
                    this.controlDeMarcados.marcarVertice(posDeVerticeAdyacente);
                }
            }
        }while(!pila.isEmpty());

    }
    
    public void ejecutarDFS2(int posVerticeEnTurno){
        this.recorrido.add(posVerticeEnTurno);
        this.controlDeMarcados.marcarVertice(posVerticeEnTurno);           
        Iterable<Integer> itAdyacentesDeVerticeEnTurno = this.grafo.adyacentesDeVertice(posVerticeEnTurno);
        //verificar si tiene adyacente porque sino verificara si hay vertice marcado y dara error
        for (Integer posDeVerticeAdyacente : itAdyacentesDeVerticeEnTurno) {
            if (!this.controlDeMarcados.estaVerticeMarcado(posDeVerticeAdyacente)) {
                ejecutarDFS2(posDeVerticeAdyacente);
            }
        }
        

    }
    
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    
    /**
     * Hay camino
     */
    
    public boolean hayCaminoAVertice(int posDeVerticeDestino){
        this.grafo.validarVertice(posDeVerticeDestino);
        return this.controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }
    
    public boolean hayCaminoAVertice(){
        return this.controlDeMarcados.estanTodosMarcados();
    }
    
    public boolean estanTodosVerticesMarcados(){
        return this.controlDeMarcados.estanTodosMarcados();
    }
    
    public boolean estaVerticeMarcado(int posDeVertice){
        return this.controlDeMarcados.estaVerticeMarcado(posDeVertice);
    }
    
    public RecorridoUtils getControlMarcados(){
        return this.controlDeMarcados;
    }
}


