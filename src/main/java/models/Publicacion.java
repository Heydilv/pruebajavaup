package models;


public class Publicacion {
    private int idPublicacion;
    private String autor; // autor <-> Usuario
    private Usuario usuario;
    private int anio;
    private String tipoPublicacion; // articulo de revistas, conferencias, capítulos de libros
    private String fuente; // nombres de revistars, libros
    private int citasRecibidas; // numero de citas recibidas
    private String resumen;
    private Boolean aprobado;
    
    private int idHallazgo;
    private Hallazgo hallazgo;
    

    public Publicacion() {
        this.idPublicacion = 0;
        this.autor = "";
        this.anio = 0;
        this.tipoPublicacion = "";
        this.fuente = "";
        this.citasRecibidas = 0;
        this.resumen = "";
        this.aprobado = false;
        this.idHallazgo = 0;
        this.hallazgo = new Hallazgo();
    }
    
    // Datos sobre los hallazgos en el doucmento.
    
    // OBJETIVOS
    //private String objetivo1;
    //private String objetivo2;
    
    //Los objetivos en el DAO

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public int getCitasRecibidas() {
        return citasRecibidas;
    }

    public void setCitasRecibidas(int citasRecibidas) {
        this.citasRecibidas = citasRecibidas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public int getIdHallazgo() {
        return idHallazgo;
    }

    public void setIdHallazgo(int idHallazgo) {
        this.idHallazgo = idHallazgo;
    }

    public Hallazgo getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(Hallazgo hallazgo) {
        this.hallazgo = hallazgo;
    }
    
    
    @Override
    public String toString() {
        return String.format("id: %d, autor: %s, año: %d, Tipo de publicación: %s, Fuente: %s, Citas recibidas: %d, Resumen: %s, Aprobado: %b", 
                this.idPublicacion, 
                this.autor, 
                this.anio, 
                this.tipoPublicacion, 
                this.fuente, 
                this.citasRecibidas, 
                this.resumen, 
                this.aprobado);
    }
}
