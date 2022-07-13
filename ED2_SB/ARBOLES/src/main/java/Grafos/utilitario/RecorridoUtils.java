/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.utilitario;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class RecorridoUtils {
    private List<Boolean> marcados;
    
    public RecorridoUtils(int NroDeVertices){
        marcados=new LinkedList<>();
        for(int i=0;i<NroDeVertices;i++){
            marcados.add(Boolean.FALSE);
        }
    }
    public int getSize(){
        return this.marcados.size();
    }
    public void desmarcarTodos(){
        for(int i=0;i<this.marcados.size();i++){
            this.marcados.set(i, Boolean.FALSE);
        }
    }
    
    public boolean estanTodosMarcados(){
        for(Boolean marcados:this.marcados){
            if(!marcados){
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     */
    public boolean estaVerticeMarcado(int posDeVertice){
        return this.marcados.get(posDeVertice);
    }
    
    public void marcarVertice(int posDeVertice){
        this.marcados.set(posDeVertice, Boolean.TRUE);
    }
    
    public int cantidadMarcados(){
        int cantidad=0;
        for(int i=0;i<this.marcados.size();i++){
            if(this.estaVerticeMarcado(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
}
