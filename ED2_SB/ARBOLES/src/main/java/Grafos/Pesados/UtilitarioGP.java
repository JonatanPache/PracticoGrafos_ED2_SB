/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.utilitario.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jonathan
 */
public class UtilitarioGP {
    private GrafoPesado grafo;
    private List<Integer> recorrido;
    private RecorridoUtils controlDeMarcados;
    
    public UtilitarioGP(GrafoPesado unGrafo){
        this.grafo=unGrafo;
    }
    
    public GrafoPesado copiar() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        GrafoPesado nuevoGrafo=new GrafoPesado(this.grafo.cantidadDeVertices());
        for(int i=0;i<nuevoGrafo.cantidadDeVertices();i++){
            List<AdyacenteConPeso> listaAdyacentesDelVertice=this.grafo.listasDeAdyacencias.get(i);
            for(AdyacenteConPeso unAdyacente:listaAdyacentesDelVertice){
                int posicionVerticeAdyacente=unAdyacente.getIndiceVertice();
                double pesoVerticeAdyacente=unAdyacente.getPeso();
                if(!nuevoGrafo.existeAdyacencia(i, posicionVerticeAdyacente)){
                    nuevoGrafo.insertarArista(i, posicionVerticeAdyacente, pesoVerticeAdyacente);
                }
            }
        }
        return nuevoGrafo;
    }
    
    public GrafoPesado copiarSoloVertices() throws ExcepcionNroVerticeInvalido{
        GrafoPesado grafoPesado=new GrafoPesado(this.grafo.cantidadDeVertices());
        return grafoPesado;
    }
    
    /**
     * Exclusivo para GRAFOS PESADOS NO DIRIGIDOS
     * @return
     * @throws ExcepcionAristaYaExiste
     * @throws ExcepcionNroVerticeInvalido
     * @throws ExcepcionAristaNoExiste 
     */
    public boolean hayCiclos() throws ExcepcionAristaYaExiste, ExcepcionNroVerticeInvalido, ExcepcionAristaNoExiste{
        GrafoPesado grafoPesadoCopia=this.copiarSoloVertices();
        this.recorrido=new LinkedList<>();
        this.controlDeMarcados=new  RecorridoUtils(this.grafo.cantidadDeVertices());
        for(int i=0;i<this.grafo.cantidadDeVertices();i++){
            this.grafo.validarVertice(i);
            if(this.ejecutarDFS(i,grafoPesadoCopia)){
                return true;
            }
        }
        return false;
        
    }
    
    private boolean ejecutarDFS(int posDeVerticeDePartida,GrafoPesado grafoPesadoCopia) throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
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
                    grafoPesadoCopia.insertarArista(posDeVerticeEnTurno,
                            posDeVerticeAdyacente,
                            this.grafo.peso(posDeVerticeEnTurno,posDeVerticeAdyacente));
                    pila.push(posDeVerticeAdyacente);
                    this.controlDeMarcados.marcarVertice(posDeVerticeAdyacente);
                    
                }
                // si ya esta marcado preguntamos si no tiene arista
                
                if(!grafoPesadoCopia.existeAdyacencia(posDeVerticeEnTurno,posDeVerticeAdyacente)){
                    // si no hay arista y esta marcado entonces hay ciclo
                    grafoPesadoCopia.insertarArista(posDeVerticeEnTurno,
                            posDeVerticeAdyacente,
                            this.grafo.peso(posDeVerticeEnTurno,posDeVerticeAdyacente));
                    return true;
                }
                // si ya hay arista y esta marcado no hacemos nada
            }
        }while(!pila.isEmpty());
        return false;
    }
}
