/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Grafos.Excepciones;

/**
 *
 * @author Jonathan
 */
public class ExcepcionAristaNoExiste extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionAristaNoExiste</code> without
     * detail message.
     */
    public ExcepcionAristaNoExiste() {
        this("No existe tal arista, porfavor ingrese una arista que exista en el grafo");
    }

    /**
     * Constructs an instance of <code>ExcepcionAristaNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAristaNoExiste(String msg) {
        super(msg);
    }
}
