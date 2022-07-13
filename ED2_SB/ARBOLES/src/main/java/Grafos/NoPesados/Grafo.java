/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import Grafos.utilitario.RecorridoUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class Grafo{
    protected List<List<Integer> > listasDeAdyacencias;
    private int MatrizAdyacencia[][];
    private Boolean MatrizAdyacenciaBooleana[][];

    public Grafo(){
       this.listasDeAdyacencias=new LinkedList<>() ;
    }
    
    /**
     *
     * @param nroDeVertices
     * @throws ExcepcionNroVerticeInvalido
     */
    public Grafo(int nroDeVertices) throws ExcepcionNroVerticeInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNroVerticeInvalido();
        }

        this.listasDeAdyacencias = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();
        }
        this.MatrizAdyacencia = new int[nroDeVertices][nroDeVertices];
        for (int j = 0; j < nroDeVertices; j++) {
            for (int k = 0; k < nroDeVertices; k++) {
                this.MatrizAdyacencia[j][k] = 0;
            }
        }
        this.MatrizAdyacenciaBooleana = new Boolean[nroDeVertices][nroDeVertices];
        for (int j = 0; j < nroDeVertices; j++) {
            for (int k = 0; k < nroDeVertices; k++) {
                this.MatrizAdyacenciaBooleana[j][k] = false;
            }
        }
    }
    
    /**
     * Pregunta 1
     * Agrega una lista vacia en la posicion 
     */
    public void insertarVertice(){
        List<Integer> listaDeAdyacentesDeVerticeAInsertar=new LinkedList<>();
        this.listasDeAdyacencias.add(listaDeAdyacentesDeVerticeAInsertar);
    }
    
    /**
     * Pregunta 1
     * @return 
     */
    public int cantidadDeVertices(){
        return this.listasDeAdyacencias.size();
    }
    
    /**
     * Valida el vertice
     */
    public void validarVertice(int posDeVertice){
        if(posDeVertice<0 || posDeVertice>=this.cantidadDeVertices()){
            throw new IllegalArgumentException("No existe vertice en posicion "
                    +posDeVertice+" en el grafo");
        }
    }
    
    /**
     * Pregunta 1
     * @param posDeVertice
     * @return 
     */
    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> listaAdyacentesDelVertice=this.listasDeAdyacencias.get(posDeVertice);
        return listaAdyacentesDelVertice.size();
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen,int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        return listaAdyacentesDelVerticeOrigen.contains(posVerticeDestino);
    }
    
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> listaAdyacentesDelVertice=this.listasDeAdyacencias.get(posDeVertice);
        Iterable<Integer> iterableDeAdyacentesDeVertice=listaAdyacentesDelVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    /**
     * Pregunta 1
     * @param posVerticeOrigen
     * @param posVerticeDestino
     * @throws ExcepcionAristaYaExiste 
     */
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaYaExiste{
        // verificamos si existe ya la arista en el grafo
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        // empezamos a insertar la arista
        List<Integer> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        listaAdyacentesDelVerticeOrigen.add(posVerticeDestino);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
        // verifico si es lazo o al arista apunta al mismo vertice
        if(posVerticeOrigen!=posVerticeDestino){
            List<Integer> listaAdyacentesDelVerticeDestino=this.listasDeAdyacencias.get(posVerticeDestino);
            listaAdyacentesDelVerticeDestino.add(posVerticeOrigen);
            Collections.sort(listaAdyacentesDelVerticeDestino);
        }
    }
    
    /**
     * Pregunta 1
     * Elimnar arista
     * Parametros: pos origen, pos destino
     */
    public void elimnarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.lastIndexOf(posVerticeDestino);
        listaAdyacentesDelVerticeOrigen.remove(posicionVerticeDestinoEnListaOrigen);
        
        // verifico si es lazo o al arista apunta al mismo vertice
        if(posVerticeOrigen!=posVerticeDestino){
            List<Integer> listaAdyacentesDelVerticeDestino=this.listasDeAdyacencias.get(posVerticeDestino);
            int posicionVerticeOrigenEnListaDestino=listaAdyacentesDelVerticeDestino.lastIndexOf(posVerticeOrigen);
            listaAdyacentesDelVerticeDestino.remove(posicionVerticeOrigenEnListaDestino);
        }
    }
    
    /**
     * Pregunta 1
     * @return 
     */
    public int cantidadDeAristas(){
        int cant=0;
        for(List<Integer> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return (cant+1)/2;
    }
    
    /**
     * Pregunta 1
     * @param posVerticeAEliminar 
     */
    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listasDeAdyacencias.remove(posVerticeAEliminar);
        for(List<Integer> listaAdyacentesDeUnVertice : this.listasDeAdyacencias){
            int posicionDeVerticeAEliminarEnAdyacencia=listaAdyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            // verifcamos si existe indice del vertice a eliminar en cada iteracion
            // primerp elominamos el vertice a eliminar en las listas de adyacencias
            if(posicionDeVerticeAEliminarEnAdyacencia>=0){
                listaAdyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdyacencia);
            }
            // debo restar a los vertice adyacentes posteriores del vertice eliminado en esta lista actual
            for(int i=0;i<listaAdyacentesDeUnVertice.size();i++){
                int posicionDeAdyacencia=listaAdyacentesDeUnVertice.get(i);
                if(posicionDeAdyacencia>posVerticeAEliminar){
                    posicionDeAdyacencia--;
                    // actualizamos
                    listaAdyacentesDeUnVertice.set(i, posicionDeAdyacencia);
                }
                
            }
        }
    }
    
    public int[][] getMatrizAdyacencia(){
        this.generarMatrizAdyacencia();
        return this.MatrizAdyacencia;
    }
    
    public Boolean[][] getMatrizAdyacenciaBooleana(){
        this.generarMatrizAdyacenciaBooleana();
        return this.MatrizAdyacenciaBooleana;
    }
    
    public String getMatrizAdyacenciaString() {
        this.generarMatrizAdyacencia();
        String S = "";
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            S = S + i + ": ";
            String C = "";
            for (int j = 0; j < this.cantidadDeVertices(); j++) {
                C = C + this.MatrizAdyacencia[i][j] + ", ";
            }
            S = S + C + "\n";
        }
        return S;
    }
    
    public String getMatrizAdyacenciaString(int[][] matriz) {
        this.generarMatrizAdyacencia();
        String S = "";
        for (int i = 0; i < matriz.length; i++) {
            S = S + i + ": ";
            String C = "";
            for (int j = 0; j < matriz.length; j++) {
                C = C + matriz[i][j] + ", ";
            }
            S = S + C + "\n";
        }
        return S;
    }
    
    private void generarMatrizAdyacencia() {
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            Iterable<Integer> iterable = this.adyacentesDeVertice(i);
            for (Integer adyacencia : iterable) {
                this.MatrizAdyacencia[i][adyacencia] = 1;
            }
        }
    }
    
    private void generarMatrizAdyacenciaBooleana() {
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            Iterable<Integer> iterable = this.adyacentesDeVertice(i);
            for (Integer adyacencia : iterable) {
                this.MatrizAdyacenciaBooleana[i][adyacencia] = true;
            }
        }
    }
    
    /**
     * Solo para grafos no dirigidos
     * @return
     * @throws ExcepcionNroVerticeInvalido
     * @throws ExcepcionAristaYaExiste 
     */
    public boolean hayCiclo() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        UtilitarioGrafo uti=new UtilitarioGrafo(this);
        return uti.hayCiclos();
    }
    
    public String obtenerComponentesDeLasIslas(){
        RecorridoUtils marcadosAnterior=new RecorridoUtils(this.cantidadDeVertices());
        RecorridoUtils marcadosActual;
        String S="";
        int x=0;
        BFS bfs=new BFS(this,x);
        while(!marcadosAnterior.estanTodosMarcados()){
            S+="#"+x+" ISLA: ";
            marcadosActual=bfs.getControlMarcados();
            for(int j=0;j<this.cantidadDeVertices();j++){
                if(marcadosActual.estaVerticeMarcado(j)){
                    
                    S+=j+",";
                    marcadosAnterior.marcarVertice(j);
                }
            }
            if(!marcadosAnterior.estanTodosMarcados()){
                x=buscarVerticeNoMarcado(marcadosAnterior);
                bfs=new BFS(this,x);
                S+="\n";
            }
        }
        return S;
    }
    
    private int buscarVerticeNoMarcado(RecorridoUtils marcadosActual){
        int i=0;
        int vertice=-1;
        while(vertice==-1 && i<this.cantidadDeVertices()){
            if(!marcadosActual.estaVerticeMarcado(i)){
                vertice=i;
            }
            i++;
        }
        return vertice;
    }
    
    /*public String algoritmoWarshall(){
        Warshall war=new Warshall(this);
        return war.obtenerMatrizCamino();
    }*/
    @Override
    public String toString() {
        String S="";
        for(int i=0;i<this.listasDeAdyacencias.size();i++){
            List<Integer> adyVert=this.listasDeAdyacencias.get(i);
            S=S+i+": ";
            String C="";
            for(int j=0;j<adyVert.size();j++){
                C=C+adyVert.get(j)+", ";
            }
            S=S+C+"\n";
        }
        return S;
    }

}

