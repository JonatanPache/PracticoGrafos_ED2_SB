/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.utilitario.RecorridoUtils;

/**
 *
 * @author Jonathan
 * @param <E>
 */
public class Prim<E extends Comparable> {
    private GrafoPesadoV1 grafoOriginal;
    private GrafoPesadoV1 grafoAuxiliar;
    private RecorridoUtils marcados;
    
    public Prim(GrafoPesadoV1 unGrafo,E verticePartida) throws ExcepcionNroVerticeInvalido, ExcepcionAristaNoExiste, ExcepcionAristaYaExiste{
        this.grafoOriginal=unGrafo;
        this.grafoAuxiliar=new GrafoPesadoV1(verticePartida);
        this.marcados=new RecorridoUtils(unGrafo.cantidadDeVertices());
        this.marcados.marcarVertice(unGrafo.indexOfVertice(verticePartida));
        this.ejecutarPrim();
    }
    
    private void ejecutarPrimV2() throws ExcepcionAristaNoExiste, ExcepcionAristaYaExiste{
        double pesoMinimo = Double.MAX_VALUE;
        while (!this.marcados.estanTodosMarcados()) {
            E vertice1 = null;
            E vertice2 = null;
            pesoMinimo = Double.MAX_VALUE;
            this.buscarUnaAristaMenorCosto(vertice1, vertice2, pesoMinimo);
            this.grafoAuxiliar.insertarVertice(vertice2);
            this.grafoAuxiliar.insertarArista(vertice1,
                    vertice2,
                    pesoMinimo);
            
            //this.marcados.marcarVertice(this.grafoOriginal.indexOfVertice(vertice2));
        }
    }
    
    private void ejecutarPrim() throws ExcepcionAristaNoExiste, ExcepcionAristaYaExiste {
        double pesoMinimo = Double.MAX_VALUE;
        while (!this.marcados.estanTodosMarcados()) {
            E vertice1 = null;
            E vertice2 = null;
            pesoMinimo = Double.MAX_VALUE;
            Iterable<E> vertices = this.grafoAuxiliar.iterablesVertices();
            for (E verticeTurno : vertices) {
                Iterable<E> iterableAdyacentes = this.grafoOriginal.adyacentesDeVertice(verticeTurno);
                for (E adyacente : iterableAdyacentes) {
                    if (verticeTurno.compareTo(adyacente) != 0
                            && !this.marcados.estaVerticeMarcado(this.grafoOriginal.indexOfVertice(adyacente))) {
                        if (this.grafoOriginal.peso(verticeTurno, adyacente) < pesoMinimo) {
                            pesoMinimo = this.grafoOriginal.peso(verticeTurno, adyacente);
                            vertice1 = verticeTurno;
                            vertice2 = adyacente;
                        }
                    }
                }
            }
            
            this.grafoAuxiliar.insertarVertice(vertice2);
            this.grafoAuxiliar.insertarArista(vertice1,
                    vertice2,
                    pesoMinimo);
            this.marcados.marcarVertice(this.grafoOriginal.indexOfVertice(vertice2));
        }

    }

    private void buscarUnaAristaMenorCosto(E vertice1, E vertice2,double pesoMinimo) throws ExcepcionAristaNoExiste{
        Iterable<E> vertices=this.grafoAuxiliar.iterablesVertices();
        for(E verticeTurno:vertices){
            //if(this.marcados.estaVerticeMarcado(this.grafoAuxiliar.indexOfVertice(verticeTurno))){
                Iterable<E> iterableAdyacentes=this.grafoOriginal.adyacentesDeVertice(verticeTurno);
                for(E adyacente:iterableAdyacentes){
                    if(verticeTurno.compareTo(adyacente)!=0 &&
                        !this.marcados.estaVerticeMarcado(this.grafoOriginal.indexOfVertice(adyacente)) ) {
                        if(this.grafoOriginal.peso(verticeTurno,adyacente)<pesoMinimo){
                            pesoMinimo=this.grafoOriginal.peso(verticeTurno,adyacente);
                            vertice1= verticeTurno;
                            vertice2= adyacente;
                        }
                    }
                }
            //}
        }
        this.marcados.marcarVertice(this.grafoOriginal.indexOfVertice(vertice2));
    }
    
    public GrafoPesadoV1 getGrafoResultante(){
        return this.grafoAuxiliar;
    }
}
