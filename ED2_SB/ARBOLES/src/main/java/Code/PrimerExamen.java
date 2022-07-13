/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

/**
 *
 * @author Jonathan
 */
public class PrimerExamen<K extends Comparable<K>,V> extends ArbolMViasBusqueda<K,V>{
    
    public int buscarClaveNivel(K claveABuscar){
        if(this.esArbolVacio()){
            return -1;
        }
        return buscarClaveNivel(this.raiz,claveABuscar);
    }
    
    private int buscarClaveNivel(NodoMVias<K,V> nodoActual,K claveABuscar){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return -1;    
        }
        int nivelActual=-1;
        int nivelHijo=0;
        
        for(int i=0;i<nodoActual.cantidadClavesNoVacios();i++){
            if(claveABuscar.compareTo(nodoActual.getClave(i))==0){
                nivelActual++;
            }
            if(claveABuscar.compareTo(nodoActual.getClave(i))<0){
                nivelHijo++;
                nivelHijo=nivelHijo+buscarClaveNivel(nodoActual.getHijo(i),claveABuscar);
            }
        }
        if(!nodoActual.esHijoVacio(nodoActual.cantidadClavesNoVacios())){
            nivelHijo++;
            nivelHijo=nivelHijo+buscarClaveNivel(nodoActual.getHijo(nodoActual.cantidadClavesNoVacios()),claveABuscar);
        }
            
        if(nivelActual!=-1){
            return nivelActual;
        }
        return nivelHijo;
         
    }
    
    public static void main(String[] args){
        PrimerExamen<Integer,String> arbol2=new PrimerExamen<>();
        arbol2.Insertar(100,"juan");
        arbol2.Insertar(200,"juan");
        arbol2.Insertar(300,"juan");
        arbol2.Insertar(50,"juan");
        arbol2.Insertar(60,"juan");
        arbol2.Insertar(70,"juan");
        arbol2.Insertar(65,"juan");
        arbol2.Insertar(250,"juan");
        System.out.println("recorrido inorden: "+ arbol2.recorridoEnInOrden());
        System.out.println("nivel de la clave 65 es:  "+ arbol2.buscarClaveNivel(65));
    }
    
}
