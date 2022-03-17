/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Usuario;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario;
    private static final long serialVersionUID = 1094801825228386363L;
    private String pwd;

    public UsuarioMB() {
        this.usuario = new Usuario();
        System.out.println("Inici el usuario class");
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void iniciar() {
        UsuarioDAO udao = new UsuarioDAO();
        Usuario usuariodb = null;
        
        
        
        System.out.println("usuario: " + this.usuario.getUsername());
        System.out.println("password: " +this.usuario.getPassword());
        
        usuariodb = udao.Login(this.usuario.getUsername(), this.usuario.getPassword());
        
        if (usuariodb != null) {
            this.usuario = usuariodb;
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", this.usuario.getUsername());
            session.setAttribute("password", this.usuario.getPassword());
            session.setAttribute("role", this.usuario.getRole());
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("buscador.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error credenciales", "Usuario o contrseña incorrectos");
        }

        
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //logout event, invalidate session
    public void logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("app.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
