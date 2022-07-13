/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;

/**
 *
 * @author Jonathan
 */
public class Conexo {

    Grafo grafoOriginal;

    public Conexo(Grafo unGrafo) {
        this.grafoOriginal = unGrafo;
    }

    /**
     * Un grafo dirigido se denomina fuertemente conexo si existe un camino
     * desde cualquier vértice a cualquier otro vértice.
     */
    public boolean esFuertementeConexo() {
        BFS bsfG;
        // visitamos cada vertice del grafo
        for (int i = 0; i < this.grafoOriginal.cantidadDeVertices(); i++) {
            bsfG = new BFS(this.grafoOriginal, i);
            for (int j = 0; j < this.grafoOriginal.cantidadDeVertices(); j++) {
                if (!bsfG.hayCaminoAVertice(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean esDebilmenteConexo() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste {
        if (!this.esFuertementeConexo()) {
            Grafo grafoNoDirigidoCopia = this.crearGrafoNoDirigidoCopia(this.grafoOriginal);
            BFS bsfG;
            // visitamos cada vertice del grafo
            for (int i = 0; i < grafoNoDirigidoCopia.cantidadDeVertices(); i++) {
                bsfG = new BFS(grafoNoDirigidoCopia, i);
                for (int j = 0; j < grafoNoDirigidoCopia.cantidadDeVertices(); j++) {
                    if (!bsfG.hayCaminoAVertice(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Grafo crearGrafoNoDirigidoCopia(Grafo unGrafo) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste {
        Grafo grafoNoDirigidoCopia = new Grafo(unGrafo.cantidadDeVertices());
        for (int i = 0; i < this.grafoOriginal.cantidadDeVertices(); i++) {
            for (int j = 0; j < this.grafoOriginal.cantidadDeVertices(); j++) {
                if (this.grafoOriginal.existeAdyacencia(i, j)
                        && !grafoNoDirigidoCopia.existeAdyacencia(i, j)) {

                    grafoNoDirigidoCopia.insertarArista(i, j);
                }
            }
        }
        return grafoNoDirigidoCopia;
    }

    public boolean esDebilmenteConexoOpcion2() {
        // inicamos el recorrido desde el vertice 0
        DFS dfsDiGrafo = new DFS(this.grafoOriginal, 0);
        // iteramos si todos los vertices estan marcados
        while (!dfsDiGrafo.estanTodosVerticesMarcados()) {
            int vertice_x = this.buscarVerticeConAdjMarcado(dfsDiGrafo);
            if (vertice_x != -1) {
                // significa que hay un vertice no marcado con algun adj marcado
                // continuamos el recorrido
                dfsDiGrafo.ejecutarDFS(vertice_x);
            }
            // es decir no existe vertice con adj marcado
            if (vertice_x == -1) {
                // significa que no es debilmente conexo
                return false;
            }
        }
        // si llega a esta linea entonces todos los vertices estan marcados
        // significa que es conexo (no hay islas)
        return true;

    }

    private int buscarVerticeConAdjMarcado(DFS dfs) {
        int verticeEncontrado = -1;
        for (int i = 0; i < this.grafoOriginal.cantidadDeVertices(); i++) {
            if (!dfs.estaVerticeMarcado(i)) {
                if (this.tieneAlgunAdjMarcado(i, dfs)) {
                    // si cumple lo retornamos
                    return i;
                }
                // si ese vertice no tiene adjs marcados entonces buscamos otro vertice

            }
        }

        return verticeEncontrado;
    }

    /**
     * busca algun adj del vertice encontrado que esta marcado
     *
     * @param verticeEncontrado
     * @param dfs1
     * @return
     */
    private boolean tieneAlgunAdjMarcado(int verticeEncontrado, DFS dfs1) {
        boolean algunEstaAdjMarcado = false;
        // verificando adyacente marcado del vertice encontrado
        Iterable<Integer> itAdyacentesDeVerticeEncontrado = this.grafoOriginal.adyacentesDeVertice(verticeEncontrado);
        for (Integer posDeVerticeAdyacente : itAdyacentesDeVerticeEncontrado) {
            if (dfs1.estaVerticeMarcado(posDeVerticeAdyacente)) {
                algunEstaAdjMarcado = true;

            }
        }
        return algunEstaAdjMarcado;
    }

    /**
     * Para grafos no dirigidos
     *
     * @return
     */
    public int cantidadDeIslas() {
        int cantidadIslas = 0;
        DFS grafoDFS = new DFS(this.grafoOriginal, 0);
        cantidadIslas++;
        while (!grafoDFS.estanTodosVerticesMarcados()) {
            int vertice_x = this.buscarVerticeNoMarcado(grafoDFS);
            grafoDFS.ejecutarDFS(vertice_x);
            cantidadIslas++;
        }
        return cantidadIslas;
    }

    private int buscarVerticeNoMarcado(DFS dfs) {
        int verticeEncontrado = -1;
        for (int i = 0; i < this.grafoOriginal.cantidadDeVertices(); i++) {
            if (!dfs.estaVerticeMarcado(i)) {
                return i;
            }
        }

        return verticeEncontrado;
    }
    
    /**
     * Para grafos dirigidos
     * @return 
     */
    public int cantidadDeIslasGD(){
        int cantidadIslas = 0;
        DFS diGrafoDFS = new DFS(this.grafoOriginal, 0);
        while (!diGrafoDFS.estanTodosVerticesMarcados()) {
            int vertice_x = this.buscarVerticeConAdjMarcado(diGrafoDFS);
            if(vertice_x==-1){
                cantidadIslas++;
                vertice_x=this.buscarVerticeNoMarcado(diGrafoDFS);
            }
            diGrafoDFS.ejecutarDFS(vertice_x);
        }
        if(diGrafoDFS.estanTodosVerticesMarcados()){
            cantidadIslas++;
        }
        return cantidadIslas;
    }
}
