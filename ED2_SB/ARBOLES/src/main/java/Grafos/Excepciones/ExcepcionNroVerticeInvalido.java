/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Grafos.Excepciones;

/**
 *
 * @author Jonathan
 */
public class ExcepcionNroVerticeInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionNroVerticeInvalido</code>
     * without detail message.
     */
    public ExcepcionNroVerticeInvalido() {
        this("Nro vertice para el grafo debe ser mayor a cero");
    }

    /**
     * Constructs an instance of <code>ExcepcionNroVerticeInvalido</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionNroVerticeInvalido(String msg) {
        super(msg);
    }
}
