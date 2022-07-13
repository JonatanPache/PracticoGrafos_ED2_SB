/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.utilitario.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Jonathan
 */
public class BFS {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;


    public BFS(Grafo unGrafo,int unaPosDeVerticeDePartida){
        this.grafo=unGrafo;
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido=new LinkedList<>();
        controlDeMarcados=new  RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarBFS(unaPosDeVerticeDePartida);
    }
    
    public void ejecutarBFS(int posDeVerticeDePartida){
        Queue<Integer> cola=new  LinkedList<>();
        cola.offer(posDeVerticeDePartida);
        this.controlDeMarcados.marcarVertice(posDeVerticeDePartida);
        do{
            int posDeVerticeEnTurno=cola.poll();
            recorrido.add(posDeVerticeEnTurno);
            Iterable<Integer> itAdyacentesDeVerticeEnTurno=this.grafo.adyacentesDeVertice(posDeVerticeEnTurno);
            for(Integer posDeVerticeAdyacente:itAdyacentesDeVerticeEnTurno){
                if(!this.controlDeMarcados.estaVerticeMarcado(posDeVerticeAdyacente)){
                    cola.offer(posDeVerticeAdyacente);
                    this.controlDeMarcados.marcarVertice(posDeVerticeAdyacente);
                }
            }
        }while(!cola.isEmpty());

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
    
    public boolean estanTodosVerticesMarcados(){
        return this.controlDeMarcados.estanTodosMarcados();
    }
    
    /**
     * true: si es conexo en un Grafo
     * @return 
     */
    public boolean esConexo(){
        return this.estanTodosVerticesMarcados();
    }
    
    public boolean esArbol() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        UtilitarioGrafo cg=new UtilitarioGrafo(this.grafo);
        return (this.esConexo() && !cg.hayCiclos() );
    }
    
    public RecorridoUtils getControlMarcados(){
        return this.controlDeMarcados;
    }
    
}


