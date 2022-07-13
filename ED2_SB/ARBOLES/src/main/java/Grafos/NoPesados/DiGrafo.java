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
public class DiGrafo extends Grafo{
   
    
    public DiGrafo(){
        super();
    }
    
    public DiGrafo(int nroDeVertice) throws ExcepcionNroVerticeInvalido{
        super(nroDeVertice);
    }
    
    @Override
    public int cantidadDeAristas(){
        int cant=0;
        for(List<Integer> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return cant;
    }
    
    @Override
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
    }
    
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.lastIndexOf(posVerticeDestino);
        listaAdyacentesDelVerticeOrigen.remove(posicionVerticeDestinoEnListaOrigen);
    }
    
    @Override
    public int gradoDeVertice(int posDeVertice){
        throw new UnsupportedOperationException("Metodo no soporta en grafos dirigidos");
    }
    
    public int gradoDeSalidaDeVertice(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
    }
    
    public int gradoDeEntradaDeVertice(int posDeVertice){
        super.validarVertice(posDeVertice);
        int cantidad=0;
        for(List<Integer> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            if(listaAdyacentesDeUnVertice.contains(posDeVertice)){
                cantidad+=1;
            }
        }
        return cantidad;
    }
    /**
     * Usando Matriz De Caminos pero tiene como regla
     * contar un ciclo si la longitud del camino es igual
     * o mayor de 2
     * @return 
     */
    public boolean hayCiclo(){
        MatrizDeCaminos matrizCamino=new MatrizDeCaminos(this);
        int matrizP[][]=matrizCamino.getMatrizP();
        for (int i = 0; i < matrizP.length; i++) {
            if (matrizP[i][i]>0) {
                return true;
            }
        }
        return false;
    }
    
    public int[][] obtenerMatrizCamino(){
        MatrizDeCaminos matrizCamino=new MatrizDeCaminos(this);
        return matrizCamino.getMatrizP();
    }
    
    public String obtenerMatrizCaminoToString(){
        MatrizDeCaminos matrizCamino=new MatrizDeCaminos(this);
        return getMatrizAdyacenciaString(matrizCamino.getMatrizP());
    }
    
    public boolean esFuertementeConexo(){
        Conexo cn=new Conexo(this);
        return cn.esFuertementeConexo();
    }
    
    public boolean esDebilmenteConexo() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste{
        Conexo cn=new Conexo(this);
        return cn.esDebilmenteConexo();
    }
    
    public int cantidadIslas(){
        Conexo cn=new Conexo(this);
        return cn.cantidadDeIslasGD();
    }
    
    public String algoritmoWarshall(){
        Warshall war=new Warshall(this);
        return war.obtenerMatrizCamino();
    }
    
    public String mostrarCaminos(){
        Warshall war=new Warshall(this);
        return war.mostrarCaminos();
    }
    public String obtenerComponentesFuertementesConexas(){
        List<List<Integer>> lista=this.obtenerComponentes();
        List<List<Integer>> listaFuertementeConexas=new LinkedList<>();
        for(int i=0;i<lista.size();i++){
            if(esComponenteFuertementeConexo(lista.get(i))){
                listaFuertementeConexas.add(lista.get(i));
            }
        }
        return mostrarComponenetesFuertementeConexas(listaFuertementeConexas);
    }
    private String mostrarComponenetesFuertementeConexas(List<List<Integer>> listaComponente){
        String S="";
        List<Integer> lista;
        for(int i=0;i<listaComponente.size();i++){
            S+="#"+i+" COMPONENTE: {";
            lista=listaComponente.get(i);
            for(int j=0;j<lista.size();j++){
                S+=lista.get(j)+", ";
            }
            S+="}"+"\n";
        }
        return S;
    }
    private boolean esComponenteFuertementeConexo(List<Integer> lista) {
        if (lista.size()>1) {
            DFS dfs;
            for (int i=1;i<lista.size();i++ ) {
                int vertice=lista.get(i);
                dfs = new DFS(this, vertice);
                lista.set(i, vertice);
                if(!estanTodosMarcados(dfs.getControlMarcados(),lista)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private boolean estanTodosMarcados(RecorridoUtils recorrido, List<Integer> lista){
        int vertice;
        for(int i=0;i<lista.size();i++){
            vertice=lista.get(i);
            lista.set(i, vertice);
            if(!recorrido.estaVerticeMarcado(vertice)){
                return false;
            }   
        }
        return true;
    }
    public List<List<Integer>> obtenerComponentes(){
        RecorridoUtils marcadosAnterior=new RecorridoUtils(this.cantidadDeVertices());
        RecorridoUtils marcadosActual;
        int x=0;
        DFS dfs=new DFS(this,x);
        List<List<Integer>> listaComponentes= new LinkedList<>();
        while(!marcadosAnterior.estanTodosMarcados()){
            marcadosActual=dfs.getControlMarcados();
            List<Integer> lista=new LinkedList<>();
            for(int j=0;j<this.cantidadDeVertices();j++){
                if(marcadosActual.estaVerticeMarcado(j)){
                    marcadosAnterior.marcarVertice(j);
                    lista.add(j);
                }
            }
            listaComponentes.add(lista);
            if(!marcadosAnterior.estanTodosMarcados()){
                x=buscarVerticeNoMarcado(marcadosAnterior);
                dfs=new DFS(this,x);
            }
        }
        return listaComponentes;
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
    
    
}
