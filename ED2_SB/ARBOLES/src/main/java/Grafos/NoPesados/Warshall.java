/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.NoPesados;

/**
 *
 * @author Jonathan
 */
public class Warshall {
    private DiGrafo diGrafo;
    private Boolean[][] matrizCamino;
    
    public Warshall(DiGrafo digrafo){
        this.diGrafo=digrafo;
        ejecutar();
    }
    
    private void ejecutar(){
        Boolean A[][]=this.diGrafo.getMatrizAdyacenciaBooleana();
        Boolean P[][]=A;
        for(int k=0;k<A.length;k++){
            for(int i=0;i<A.length;i++){
                for(int j=0;j<A.length;j++){
                    P[i][j]=P[i][j] || (P[i][k] && P[k][j]);
                }
            }
        }
        this.matrizCamino=P;
    }
    
    public String obtenerMatrizCamino(){
        String S = "";
        int elemento;
        for (int i = 0; i < this.matrizCamino.length; i++) {
            S = S + i + ": ";
            String C = "";
            for (int j = 0; j < this.matrizCamino.length; j++) {
                elemento=(this.matrizCamino[i][j])? 1:0;
                C = C + elemento + ", ";
            }
            S = S + C + "\n";
        }
        return S;
    }
    
    public String mostrarCaminos(){
        String S="";
        for(int i=0;i<this.matrizCamino.length;i++){
            for(int j=0;j<this.matrizCamino.length;j++){
                if(this.matrizCamino[i][j]){
                    S+="Hay camino del vertice "+i+" al "+j+"\n";
                }
            }
            
        }
        return S;
    }
}
