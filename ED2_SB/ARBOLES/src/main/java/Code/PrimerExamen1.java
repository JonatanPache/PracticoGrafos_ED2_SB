/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;
/**
 * Para resolver cada pregunta puede crear una nueva clase y heredar de las 
 * clases compartidas o empezar desde cero.

1. Para un árbol mvias de búsqueda implementar un método que reciba una clave, 
* la busque en el árbol, en caso de encontrar la llave que retorne en que nivel
* está. Que retorne -1 en caso de no estar la clave en el árbol. La implementación
* debe ser recursiva.

2. Para un árbol binario de búsqueda implemente un método que retorne otro arbol
* que sea el reflejo del árbol original.

3.  Para un árbol binario implementar un método que retorne verdadero si el árbol
* es zurdo, falso en caso contrario. Diremos que el árbol binario es zurdo si se
* cumple lo siguiente:

   3.1. Si el árbol es vacio; o

   3.2. Si el árbol es una hoja; o

   3.3. Si para cualquier nodo, su hijo izquierdo y derecho son zurdos y el 
   * número de nodos descendientes no vacios del hijo izquierdo son mayores que 
   * el número de nodos descencientes no vacios del hijo derecho.
 */
/**
 *
 * @author Jonathan
 */
public class PrimerExamen1<K extends Comparable<K>,V> extends ArbolBinarioBusqueda<K,V>{
    public boolean esArbolZurdo(){
        return esArbolZurdo(this.raiz);
    }
    
    private boolean esArbolZurdo(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return true;
        }
        
        if(nodoActual.esHoja()){
            return true;
        }
        
        boolean hijoIzq=esArbolZurdo(nodoActual.getHijoIzquierdo());
        boolean hijoDer=esArbolZurdo(nodoActual.getHijoDerecho());
        
        // para el nodo actual
        int alturaPorIzquierda=this.altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha=this.altura(nodoActual.getHijoDerecho());
        if(hijoIzq && hijoDer && (alturaPorIzquierda>alturaPorDerecha)){
            return true;
        } 
        return false;
    }
    public static void main(String[] args){
        PrimerExamen1<Integer,String> arbol2=new PrimerExamen1<>();
        arbol2.Insertar(100,"juan");
        arbol2.Insertar(200,"juan");
        arbol2.Insertar(300,"juan");
        arbol2.Insertar(50,"juan");
        arbol2.Insertar(60,"juan");
        arbol2.Insertar(70,"juan");
        arbol2.Insertar(65,"juan");
        arbol2.Insertar(250,"juan");
        System.out.println("recorrido inorden: "+ arbol2.recorridoEnInOrden());
        System.out.println("es zurdo:  "+ arbol2.esArbolZurdo());
    }
}
