/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.utilitario.RecorridoUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class UtilitarioGrafo {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private DiGrafo grafoDirigido;
    private Grafo grafoCopia;
    private List<Integer> recorrido;
    boolean hayCiclo;


    public UtilitarioGrafo(Grafo unGrafo) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        this.grafo=unGrafo;
        this.grafoCopia=new Grafo(unGrafo.cantidadDeVertices());        
        recorrido=new LinkedList<>();
        controlDeMarcados=new  RecorridoUtils(this.grafo.cantidadDeVertices());
    }
    
    public UtilitarioGrafo(DiGrafo unGrafo) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        this.grafoDirigido=unGrafo;
        this.grafoCopia=new Grafo(unGrafo.cantidadDeVertices());        
        recorrido=new LinkedList<>();
        controlDeMarcados=new  RecorridoUtils(unGrafo.cantidadDeVertices());
    }
    
    /**
     * Para grafo dirigidos
     * @param grafoOriginal
     * @return
     * @throws ExcepcionNroVerticeInvalido
     * @throws ExcepcionAristaYaExiste 
     */
    public DiGrafo copiarDiGrafo(DiGrafo grafoOriginal) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        DiGrafo grafoCopia=new DiGrafo(this.grafoDirigido.cantidadDeVertices());
        
        for(int i=0;i<grafoCopia.cantidadDeVertices();i++){
            Iterable<Integer> iterableAdyacente=grafoOriginal.adyacentesDeVertice(i);
            for(Integer adyacente:iterableAdyacente){
                grafoCopia.insertarArista(i,adyacente);
            }
        }
        return grafoCopia;
    }
    
    /**
     * Para Grafos no dirigidos
     * @return
     * @throws ExcepcionAristaYaExiste 
     */
    public boolean hayCiclos() throws ExcepcionAristaYaExiste{
        for(int i=0;i<this.grafoCopia.cantidadDeVertices();i++){
            if(ejecutarDFS(i)){
                return true;
            }
        }
        return false;
    }
    
    private boolean ejecutarDFS(int posDeVerticeDePartida) throws ExcepcionAristaYaExiste{
        Stack<Integer> pila=new  Stack<>();
        pila.push(posDeVerticeDePartida);
        this.controlDeMarcados.marcarVertice(posDeVerticeDePartida);
        do{
            int posDeVerticeEnTurno=pila.pop();
            recorrido.add(posDeVerticeEnTurno);
            Iterable<Integer> itAdyacentesDeVerticeEnTurno=this.grafo.adyacentesDeVertice(posDeVerticeEnTurno);
            for(Integer posDeVerticeAdyacente:itAdyacentesDeVerticeEnTurno){
                // si no esta marcado entra por aca
                if(!this.controlDeMarcados.estaVerticeMarcado(posDeVerticeAdyacente)){
                    this.grafoCopia.insertarArista(posDeVerticeEnTurno,posDeVerticeAdyacente);
                    pila.push(posDeVerticeAdyacente);
                    this.controlDeMarcados.marcarVertice(posDeVerticeAdyacente);
                    
                }
                // si ya esta marcado preguntamos si no tiene arista               
                if(!this.grafoCopia.existeAdyacencia(posDeVerticeEnTurno,posDeVerticeAdyacente)){
                    // si no hay arista y esta marcado entonces hay ciclo
                    this.grafoCopia.insertarArista(posDeVerticeEnTurno,posDeVerticeAdyacente);
                    return true;
                }
                // si ya hay arista y esta marcado no hacemos nada
            }
        }while(!pila.isEmpty());
        return false;
    }
    
    
    public void ejecutarDFS2(int posVerticeEnTurno){
        this.recorrido.add(posVerticeEnTurno);
        this.controlDeMarcados.marcarVertice(posVerticeEnTurno);           
        Iterable<Integer> itAdyacentesDeVerticeEnTurno = this.grafo.adyacentesDeVertice(posVerticeEnTurno);
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
    /**
     * Para Grafos Dirigidos
     * @return
     * @throws ExcepcionAristaYaExiste 
     */
    public boolean hayCiclosGrafoDirigido() throws ExcepcionAristaYaExiste, ExcepcionNroVerticeInvalido, ExcepcionAristaNoExiste {
        DiGrafo grafoCopia = this.copiarDiGrafo(this.grafoDirigido);
        this.controlDeMarcados.desmarcarTodos();
        this.eliminarLazos(grafoCopia);
        int x = 0;
        Stack<Queue<Integer>> pila = new Stack<>();
        int verticePartida;
        for (int i = 0; i < grafoCopia.cantidadDeVertices(); i++) {
            verticePartida = i;
            this.controlDeMarcados.marcarVertice(i);
            Queue<Integer> colaAdyacenciaDelVertice = obtenerAdyacentesNoMarcados(i, verticePartida, grafoCopia);
            if (!colaAdyacenciaDelVertice.isEmpty()) {
                x = colaAdyacenciaDelVertice.poll();
                this.controlDeMarcados.marcarVertice(x);
                pila.push(colaAdyacenciaDelVertice);
            }
            while (!pila.isEmpty()) {
                if (x == i
                        || (this.existeEnLaColaVerticePartida(pila.peek(), verticePartida) && pila.size() > 2)) {
                    return true;
                }
                colaAdyacenciaDelVertice = obtenerAdyacentesNoMarcados(x, verticePartida, grafoCopia);
                if (colaAdyacenciaDelVertice.isEmpty()) {
                    colaAdyacenciaDelVertice = pila.pop();
                    while (colaAdyacenciaDelVertice.isEmpty() && !pila.isEmpty()) {
                        colaAdyacenciaDelVertice = pila.pop();
                    }
                    if (!pila.isEmpty() || (pila.isEmpty() && !colaAdyacenciaDelVertice.isEmpty())) {
                        x = colaAdyacenciaDelVertice.poll();
                        this.controlDeMarcados.marcarVertice(x);
                        if (!colaAdyacenciaDelVertice.isEmpty() || this.tieneAdyacencia(x, grafoCopia)) {
                            pila.push(colaAdyacenciaDelVertice);
                        }
                    }
                } else {
                    x = colaAdyacenciaDelVertice.poll();
                    this.controlDeMarcados.marcarVertice(x);
                    if (!colaAdyacenciaDelVertice.isEmpty() && !pila.isEmpty()) {
                        pila.push(colaAdyacenciaDelVertice);
                    }
                    if (colaAdyacenciaDelVertice.isEmpty() && this.tieneAdyacencia(x, grafoCopia)) {
                        pila.push(colaAdyacenciaDelVertice);
                    }
                }
            }

        this.controlDeMarcados.desmarcarTodos();
        }
        return false;
    }
    
    private boolean tieneAdyacencia(int vertice,DiGrafo grafo){
        return grafo.gradoDeSalidaDeVertice(vertice)>0;
    }
    private boolean existeEnLaColaVerticePartida(Queue<Integer> cola,int verticePartida){
        var array = cola.toArray();
        for(int i=0;i<array.length;i++){            
            if((int)array[i]==verticePartida){
                return true;
            }
        }
        return false;
    }
    
    private void eliminarLazos(DiGrafo grafoCopia) throws ExcepcionAristaNoExiste {
        for (int i = 0; i < grafoCopia.cantidadDeVertices(); i++) {
            if (grafoCopia.existeAdyacencia(i, i)) {
                grafoCopia.eliminarArista(i, i);
            }
        }
    }

    private Queue<Integer> obtenerAdyacentesNoMarcados(int vertice,int verticePartida,DiGrafo grafo){
        Queue<Integer> cola=new LinkedList<>();
        Iterable<Integer> iterable=grafo.adyacentesDeVertice(vertice);
        for(Integer adyacente:iterable){
            if(adyacente==verticePartida){
                if(this.sizeControlMarcados()>2){
                    cola.add(adyacente);
                }
               
            }
            if(adyacente!=verticePartida && !this.controlDeMarcados.estaVerticeMarcado(adyacente)){
                cola.add(adyacente);
            }
        }
        return cola;
    }
    
    private int sizeControlMarcados(){
        return this.controlDeMarcados.cantidadMarcados();
    }
}


