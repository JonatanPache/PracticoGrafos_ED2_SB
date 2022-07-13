/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class MatrizDeCaminos {
    private Grafo grafo;
    private int matrzP[][];
    
    public MatrizDeCaminos(Grafo grafo){
        this.grafo=grafo;
        ejecutar();  
    }
    
    public void ejecutar(){
        List<int [][]> listaPotenciasMatrices=this.generarPotenciasDeMatrizAdyacencia(this.grafo);
        this.matrzP=this.sumarMatrices(listaPotenciasMatrices);
    }
    
    public int[][] getMatrizP(){
        return this.matrzP;
    }
    private int [][] sumarMatrices(List<int [][]> listaPotenciasMatrices){
        int matrizSumado[][]=new int[this.grafo.cantidadDeVertices()][this.grafo.cantidadDeVertices()];
        for(int i=0;i<matrizSumado.length;i++){
            for(int j=0;j<matrizSumado.length;j++){
                matrizSumado[i][j]=0;
            }
        }
        int matrizTurno[][];
        // lista de las matrices desde la segunda matriz
        for(int x=0;x<listaPotenciasMatrices.size();x++){
            matrizTurno=listaPotenciasMatrices.get(x);
            for(int n=0;n<matrizTurno.length;n++){
                for(int m=0;m<matrizTurno.length;m++){
                    matrizSumado[n][m]=matrizSumado[n][m]+matrizTurno[n][m];
                }
            }
            listaPotenciasMatrices.set(x,matrizTurno);
        }
        return matrizSumado;
    }
    private List<int [][]> generarPotenciasDeMatrizAdyacencia(Grafo grafo){
        int A[][]=grafo.getMatrizAdyacencia();
        List<int [][]> listaMatrices=this.inicializarMatrices(grafo.cantidadDeVertices()-1);
        listaMatrices.set(0, A);
        for(int i=1;i<listaMatrices.size();i++){
            int matrizTurnoAnterior[][]=listaMatrices.get(i-1);
            int matrizTurno[][]=this.multiplicarMatriz(matrizTurnoAnterior,A);
            listaMatrices.set(i, matrizTurno);
            listaMatrices.set(i-1, matrizTurnoAnterior);
        }
        return listaMatrices;
    }
    
    private int [][] multiplicarMatriz(int matrizTurnoAnterior[][],int A[][]){
        int elementoDeLaMatriz=0;
        int matrizResultante[][]= new int[A.length][A.length];
        for(int j=0;j<A.length;j++){
            for(int i=0;i<A.length;i++){
                for(int x=0;x<A.length;x++){
                    int elemento=matrizTurnoAnterior[i][x]*A[x][j];
                    elementoDeLaMatriz=elementoDeLaMatriz+elemento;
                }
                matrizResultante[i][j]=elementoDeLaMatriz;
                elementoDeLaMatriz=0;
            }
        }
        return matrizResultante;
    }
    
    private List<int [][]> inicializarMatrices(int cantidadVertices){
        List<int [][]> lista=new LinkedList<>();
        for(int i=0;i<cantidadVertices;i++){
            int matriz[][]=new int[cantidadVertices][cantidadVertices];
            lista.add(matriz);           
        }
        return lista;
    }
}
