package models;

public class Dato {
    private int idDato;
    private int idHallazgo;
    private String titulo;
    private String descripcion;

    public Dato() {
    }

    public Dato(String titulo) {
        this.titulo = titulo;
    }
    

    public int getIdDato() {
        return idDato;
    }

    public void setIdDato(int idDato) {
        this.idDato = idDato;
    }

    public int getIdHallazgo() {
        return idHallazgo;
    }

    public void setIdHallazgo(int idHallazgo) {
        this.idHallazgo = idHallazgo;
    }

   
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
