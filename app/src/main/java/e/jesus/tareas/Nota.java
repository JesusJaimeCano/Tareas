package e.jesus.tareas;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jesus on 14/12/2017.
 */

public class Nota implements Serializable {

    String tituloNota;
    String contenido;
    String categoria;

    public Nota(String tituloNota, String contenido, String categoria){
        this.tituloNota = tituloNota;
        this.contenido = contenido;
        this.categoria = categoria;
    }

    public String getTituloNota() {
        return tituloNota;
    }

    public String getContenido() {
        return contenido;
    }

    public String getCategoria() {
        return categoria;
    }
}
