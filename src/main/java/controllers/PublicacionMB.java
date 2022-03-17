package controllers;

import dao.PublicacionDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import models.Publicacion;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "publicacionMB")
@RequestScoped
public class PublicacionMB implements Serializable {

    private List<Publicacion> publicaciones;
    private String titulo;

    @ManagedProperty("#{param.tipo}")
    private String tipo;

    @ManagedProperty("#{param.texto}")
    private String texto;

    public PublicacionMB() {
        this.publicaciones = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        this.cargarDatos();
    }
    
    
    public void cargarDatos() {
        PublicacionDAO pubDAO = new PublicacionDAO();
        HttpSession session = SessionUtils.getSession();
        System.out.println(session.getAttribute("username"));
        System.out.println(session.getAttribute("role"));

        if (this.tipo == null && this.texto == null) {
            System.out.println("Parametros nulos");
            this.publicaciones = pubDAO.ObtenerPublicacionesPrevista();

        } else {
            this.publicaciones = pubDAO.ObtenerPublicacionesPrevistaFiltro(this.tipo, this.texto);

        }

        // si el usuario es un visitante solo se muestran las aprobadas
        if (session.getAttribute("role").equals("investigador")) {
            // Filtra todas las aprobadas

            this.publicaciones = this.pubAprobadas();
        } else if (session.getAttribute("role").equals("administrador")) {
            System.out.println("Es un administrador tiene todo el acesso");
        }

        System.out.println(this.tipo);
        System.out.println(this.texto);
        
        addMessage(FacesMessage.SEVERITY_INFO, "Datos", "Datos cargados con exito.");
        //PrimeFaces.current().ajax().update("growl");
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<Publicacion> pubAprobadas() {
        List<Publicacion> aprobados = new ArrayList<>();
        for (int i = 0; i < this.publicaciones.size(); i++) {
            if (this.publicaciones.get(i).getAprobado()) {
                aprobados.add(this.publicaciones.get(i));
            }
        }
        return aprobados;
    }
    
    // aprobar aprueba la publicación que se el pasa por parametro.
    public void aprobar(int idPublicacion) {
        PublicacionDAO pubDAO = new PublicacionDAO();
        pubDAO.AprobarPublicacion(idPublicacion);
        System.out.println("Aprobar publicación: " + idPublicacion);
        
        this.cargarDatos();
    }
    
    public void saberMas(int id) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(String.format("publicacion.xhtml?id=%s", id));
        } catch (IOException ex) {
            Logger.getLogger(PublicacionMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
