/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos.Pesados;

import Grafos.Excepciones.ExcepcionAristaNoExiste;
import Grafos.Excepciones.ExcepcionAristaYaExiste;
import Grafos.Excepciones.ExcepcionNroVerticeInvalido;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class Kruskal {
    GrafoPesado grafoOriginal;
    GrafoPesado grafoCopia;
    
    public Kruskal(GrafoPesado unGrafo) throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        this.grafoOriginal=unGrafo;
        UtilitarioGP utilitario=new UtilitarioGP(unGrafo);
        this.grafoCopia=utilitario.copiarSoloVertices();
        ejecutar();
    }
    public GrafoPesado getGrafoResultante(){
        return this.grafoCopia;
    }
    private void ejecutar() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        List<Adyacente> listaDeAdyacentes=this.ordenarPorPesoAsc();
        Iterable<Adyacente> iterableDeAdyacentes=listaDeAdyacentes;
        for(Adyacente adyacenteTurno:iterableDeAdyacentes){
            this.grafoCopia.insertarArista(
                      adyacenteTurno.getIndiceVerticeOrigen()
                    , adyacenteTurno.getIndiceVerticeDestino()
                    , adyacenteTurno.getPeso());
            UtilitarioGP utilitario=new UtilitarioGP(this.grafoCopia);
            if(utilitario.hayCiclos()){
                this.grafoCopia.elimnarArista(
                    adyacenteTurno.getIndiceVerticeOrigen(),
                    adyacenteTurno.getIndiceVerticeDestino());
            }
        }
    }
    
    private List<Adyacente> ordenarPorPesoAsc() throws ExcepcionNroVerticeInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste{
        UtilitarioGP utilitario=new UtilitarioGP(this.grafoOriginal);
        GrafoPesado grafoPesadoTemp=utilitario.copiar();
        List<Adyacente> listaDeAdyacentesOrdenadaPorPeso=new LinkedList<>();
        Adyacente adyacenteTurno;
        while(grafoPesadoTemp.cantidadDeAristas()>0){
            adyacenteTurno=this.encontrarMenorAdyacente(grafoPesadoTemp);
            listaDeAdyacentesOrdenadaPorPeso.add(adyacenteTurno);
        }
        return listaDeAdyacentesOrdenadaPorPeso;
    }
    
    private Adyacente encontrarMenorAdyacente(GrafoPesado unGrafoPesado) throws ExcepcionAristaNoExiste{
        int verticeOrigen=-1;
        int verticeDestino=-1;
        int i=0;
        Adyacente adyacenteMenor;
        double pesoMinimo=Double.MAX_VALUE;
        for(List<AdyacenteConPeso> listaAdyacenteDeUnVertice:unGrafoPesado.listasDeAdyacencias){
            for(AdyacenteConPeso adyacenteTurno:listaAdyacenteDeUnVertice){
                if(adyacenteTurno.getPeso()<pesoMinimo){
                    pesoMinimo=adyacenteTurno.getPeso();
                    verticeOrigen=i;
                    verticeDestino=adyacenteTurno.getIndiceVertice();
                }
            }
            i++;
        }
        adyacenteMenor=new Adyacente(verticeDestino,verticeOrigen,pesoMinimo);
        unGrafoPesado.elimnarArista(verticeOrigen, verticeDestino);
        return adyacenteMenor;
    }
    
    
}
