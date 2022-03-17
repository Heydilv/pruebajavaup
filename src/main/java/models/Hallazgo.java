package models;

import java.util.List;

public class Hallazgo {
    private int idHallazgo;
    private String objetivo;
    
    private List<Dato> Datos; // Datos estadisticos

    public Hallazgo() {
    }

    public int getIdHallazgo() {
        return idHallazgo;
    }

    public void setIdHallazgo(int idHallazgo) {
        this.idHallazgo = idHallazgo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<Dato> getDatos() {
        return Datos;
    }

    public void setDatos(List<Dato> Datos) {
        this.Datos = Datos;
    }
    
    
}
