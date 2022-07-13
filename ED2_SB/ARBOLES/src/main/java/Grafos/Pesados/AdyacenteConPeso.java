/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

/**
 *
 * @author Jonathan
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{

    private int indiceVertice;
    private double peso;

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public AdyacenteConPeso(int indiceVertice, double peso) {
        this.indiceVertice = indiceVertice;
        this.peso = peso;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
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
    public int compareTo(AdyacenteConPeso elOtroAdyacente) {
        Integer esteVertice=this.indiceVertice;
        Integer elOtroVertice=elOtroAdyacente.indiceVertice;
        return esteVertice.compareTo(elOtroVertice);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.indiceVertice;
        return hash;
    }
    
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
        final AdyacenteConPeso other = (AdyacenteConPeso) otroAdyacente;
        if (this.indiceVertice != other.indiceVertice) {
            return false;
        }
        return true;
    }
    
    
}
