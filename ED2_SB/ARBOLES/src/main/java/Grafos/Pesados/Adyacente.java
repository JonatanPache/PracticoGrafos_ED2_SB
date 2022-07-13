/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

/**
 *
 * @author Jonathan
 */
public class Adyacente implements Comparable<Adyacente>{
    
    private int indiceVerticeOrigen;
    private int indiceVerticeDestino;
    private double peso;
    
    public Adyacente(){
       
    }
    public Adyacente(int indVerticeOrigen,int indVerticeDestino, double peso){
        this.indiceVerticeOrigen=indVerticeOrigen;
        this.indiceVerticeDestino=indVerticeDestino;
        this.peso=peso;
    }

    public int getIndiceVerticeOrigen() {
        return indiceVerticeOrigen;
    }

    public void setIndiceVerticeOrigen(int indiceVerticeOrigen) {
        this.indiceVerticeOrigen = indiceVerticeOrigen;
    }

    public int getIndiceVerticeDestino() {
        return indiceVerticeDestino;
    }

    public void setIndiceVerticeDestino(int indiceVerticeDestino) {
        this.indiceVerticeDestino = indiceVerticeDestino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object otroAdyacente) {
        if (this == otroAdyacente) {
            return true;
        }
        if (otroAdyacente == null) {
            return false;
        }
        if (getClass() != otroAdyacente.getClass()) {
            return false;
        }
        final Adyacente other = (Adyacente) otroAdyacente;
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Adyacente elOtroAdyacente){
        Double esteVertice=this.peso;
        Double elOtroVertice=elOtroAdyacente.peso;
        return esteVertice.compareTo(elOtroVertice);
    }
    
    
}
