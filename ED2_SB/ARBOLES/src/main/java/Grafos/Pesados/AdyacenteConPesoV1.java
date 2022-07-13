/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

/**
 *
 * @author Jonathan
 */
public class AdyacenteConPesoV1<E extends Comparable<E>> implements Comparable<AdyacenteConPesoV1<E>>{

    private E indiceVertice;
    private double peso;

    public AdyacenteConPesoV1(E indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public AdyacenteConPesoV1(E indiceVertice, double peso) {
        this.indiceVertice = indiceVertice;
        this.peso = peso;
    }

    public E getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(E indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    @Override
    public String toString() {
        return this.indiceVertice+","+Double.toString(this.peso);
    }
    
    
    @Override
    public int compareTo(AdyacenteConPesoV1<E> elOtroAdyacente) {
        E esteVertice=this.indiceVertice;
        E elOtroVertice=elOtroAdyacente.getIndiceVertice();
        return esteVertice.compareTo(elOtroVertice);
    }
    
    /*
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.indiceVertice;
        return hash;
    }
    */
    // sino iguala las referencias sino se override
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
        final AdyacenteConPesoV1<E> other = (AdyacenteConPesoV1) otroAdyacente;
        if (this.indiceVertice.compareTo(other.indiceVertice)!=0) {
            return false;
        }
        return true;
    }
    
    
}
