/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.NoPesados.*;
import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class DiGrafoPesado extends GrafoPesado{
    private double[][] matrizAdyacencia;
    
    public DiGrafoPesado(){
        super();
    }
    
    public DiGrafoPesado(int nroDeVertice) throws ExcepcionNroVerticeInvalido{
        super(nroDeVertice);
        this.matrizAdyacencia=new double[nroDeVertice][nroDeVertice];
        for(int i=0;i<nroDeVertice;i++){
            for(int j=0;j<nroDeVertice;j++){
                this.matrizAdyacencia[i][j]=Double.POSITIVE_INFINITY;
            }
        }
    }
    
    @Override
    public int cantidadDeAristas(){
        int cant=0;
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            cant+=listaAdyacentesDeUnVertice.size();
        }
        return cant;
    }
    
    @Override
    public void insertarArista(int posVerticeOrigen,int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste{
        // verificamos si existe ya la arista en el grafo
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        // empezamos a insertar la arista
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino,peso);
        listaAdyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        // ordenamos
        Collections.sort(listaAdyacentesDelVerticeOrigen);
    }
    
    public void eliminarArista(int posVerticeOrigen,int posVerticeDestino) throws ExcepcionAristaNoExiste{
        if(this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> listaAdyacentesDelVerticeOrigen=this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen=new AdyacenteConPeso(posVerticeDestino);
        int posicionVerticeDestinoEnListaOrigen=listaAdyacentesDelVerticeOrigen.indexOf(adyacenteDelOrigen);
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
        AdyacenteConPeso adyacenteDelVertice=new AdyacenteConPeso(posDeVertice);
        for(List<AdyacenteConPeso> listaAdyacentesDeUnVertice:this.listasDeAdyacencias){
            if(listaAdyacentesDeUnVertice.contains(adyacenteDelVertice)){
                cantidad+=1;
            }
        }
        return cantidad;
    }
    
    public double[][] obtenerMatrizAdyacencia() throws ExcepcionAristaNoExiste{
        this.generarMatrizAdyacencia();
        return this.matrizAdyacencia;
    }
    
    private void generarMatrizAdyacencia() throws ExcepcionAristaNoExiste {
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            Iterable<Integer> iterable = this.adyacentesDeVertice(i);
            for (Integer adyacencia : iterable) {
                this.matrizAdyacencia[i][adyacencia] = this.peso(i, adyacencia);
            }
            this.matrizAdyacencia[i][i] = 0;
        }
    }
    
    public String algoritmoFloydWarshall() throws ExcepcionAristaNoExiste{
        Floyd_Warshall fw=new Floyd_Warshall(this);
        return "MATRIZ P\n"+fw.obtenerMatrizPToString()
                +"MATRIZ PRED\n"+fw.obtenerMatrizPredToString()+"\n"
                + fw.obtenerTodosLosCaminos();
    }
    public String AFW_obtenerTodosLosCaminos() throws ExcepcionAristaNoExiste{
        Floyd_Warshall fw=new Floyd_Warshall(this);
        return fw.obtenerTodosLosCaminos();
    }
    
    public String algoritmoDijkstra(int a,int b) throws ExcepcionAristaNoExiste{        
        Dijkstra dj=new Dijkstra(this,a,b);
        return "CAMINO MINIMO: "+dj.getCaminoMinimo()+"\n"
                +"COSTO: "+dj.getCostoMinimo();
    }
    
    @Override
    public String toString() {
        String S="";
        for(int i=0;i<this.listasDeAdyacencias.size();i++){
            List<AdyacenteConPeso> adyVert=this.listasDeAdyacencias.get(i);
            S=S+i+": ";
            String C="";
                C+=adyVert.toString();
            
            S=S+C+"\n";
        }
        return S;
    }
}
