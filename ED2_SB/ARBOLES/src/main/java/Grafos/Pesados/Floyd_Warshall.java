/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;


/**
 *
 * @author Jonathan
 */
public class Floyd_Warshall {
    private DiGrafoPesado diGrafoPesado;
    private double[][] matrizP;
    private int[][] matrizPred;
    
    public Floyd_Warshall(DiGrafoPesado digrafo) throws ExcepcionAristaNoExiste{
        this.diGrafoPesado=digrafo;
        ejecutarV2();
    }
    
    private void ejecutarV2() throws ExcepcionAristaNoExiste{
        double A[][]=this.diGrafoPesado.obtenerMatrizAdyacencia();
        double P[][]=A;
        int Pred[][]=this.inicializarMatrizPred(A.length);
        for(int k=0;k<A.length;k++){
            for(int i=0;i<A.length;i++){
                for (int j = 0; j < A.length; j++) {
                    if(P[i][k] + P[k][j]<P[i][j]){
                        P[i][j] = P[i][k] + P[k][j];
                        Pred[i][j] =k;
                    }
                }
            }
        }
        this.matrizP=P;
        this.matrizPred=Pred;
    }
    
    private void ejecutarV1() throws ExcepcionAristaNoExiste{
        double A[][]=this.diGrafoPesado.obtenerMatrizAdyacencia();
        double P[][]=A;
        int Pred[][]=this.inicializarMatrizPred(A.length);
        for(int k=0;k<A.length;k++){
            for(int i=0;i<A.length;i++){
                for (int j = 0; j < A.length; j++) {
                    P[i][j] = this.min(P[i][j], P[i][k] + P[k][j]);
                    Pred[i][j] = (this.min(P[i][j], P[i][k] + P[k][j]) == (P[i][k] + P[k][j]))?k:-1;
                }
            }
        }
        this.matrizP=P;
        this.matrizPred=Pred;
    }
    
    public int[][] obtenerMatrizP(){
        return this.matrizPred;
    }
    public String obtenerMatrizPToString(){
        String S = "";
        for (int i = 0; i < this.matrizP.length; i++) {
            S = S + i + ": ";
            String C = "";
            for (int j = 0; j < this.matrizP.length; j++) {
                C = C + this.matrizP[i][j] + ", ";
            }
            S = S + C + "\n";
        }
        return S;
    }
    
    public String obtenerMatrizPredToString(){
        String S = "";
        for (int i = 0; i < this.matrizPred.length; i++) {
            S = S + i + ": ";
            String C = "";
            for (int j = 0; j < this.matrizPred.length; j++) {
                C = C + this.matrizPred[i][j] + ", ";
            }
            S = S + C + "\n";
        }
        return S;
    }
    
    private double min(double a, double b) {
        return (a < b) ? a : b;
    }
    
    private int[][] inicializarMatrizPred(int size){
        int Pred[][]=new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Pred[i][j]=-1;
            }
        }
        return Pred;
    }
    
    public String obtenerTodosLosCaminos(){
        String S = "Todos Los Caminos Con Costos Minimos\n";
        S+="Vertices    | Camino         | Costo \n";
        for (int i = 0; i < this.matrizP.length; i++) {
            for (int j = 0; j < this.matrizP.length; j++) {
                if (i != j && this.matrizP[i][j] != Double.POSITIVE_INFINITY) {
                    S+=i+"-->"+j+"        "+this.obtenerCamino(i,j)+"            "+this.matrizP[i][j]+"\n";
                }
            }
        }
        return S;
    }
    
    public String obtenerCamino(int origen, int destino){
        String St="{"+origen+",";
        St+=this.obtenerIntermedios(origen,destino);
        return St+=destino+"}";
        
    }
    
    private String obtenerIntermedios(int origen, int destino) {
        if (this.matrizPred[origen][destino] == -1) {
            return "";
        } else {
            String S = "";
            int pivote = this.matrizPred[origen][destino];
            S += obtenerIntermedios(origen, pivote) ;
            S+=pivote+",";
            S += obtenerIntermedios(pivote, destino);
            return S;
        }

    }
}
