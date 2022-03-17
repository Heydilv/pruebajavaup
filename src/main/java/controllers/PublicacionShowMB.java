package controllers;

import dao.DatoDAO;
import dao.HallazgoDAO;
import dao.PublicacionDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import models.Dato;
import models.Hallazgo;
import models.Publicacion;


@ManagedBean(name = "publicacionShowMB")
@RequestScoped
public class PublicacionShowMB implements Serializable {
    
    @ManagedProperty("#{param.id}")
    private int id;
    
    private Publicacion pub;
    private Hallazgo hallazgo;
    private List<Dato> datos;

    public PublicacionShowMB() {
        
    }
    
    @PostConstruct
    public void init() {
        this.pub = new Publicacion();
        this.hallazgo = new Hallazgo();
        this.datos = new ArrayList<>();
        
        PublicacionDAO pubDAO = new PublicacionDAO();
        HallazgoDAO hdao = new HallazgoDAO();
        DatoDAO ddao = new DatoDAO();
        
        
        this.pub = pubDAO.ObtenerPublicacionById(this.id);
        this.hallazgo = hdao.ObtenerHallazgoByID(this.pub.getIdHallazgo());
        this.datos = ddao.ObtenerDatosByIDHallazgo(this.pub.getIdHallazgo());
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publicacion getPub() {
        return pub;
    }

    public void setPub(Publicacion pub) {
        this.pub = pub;
    }

    public Hallazgo getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(Hallazgo hallazgo) {
        this.hallazgo = hallazgo;
    }

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }
    
    
}
