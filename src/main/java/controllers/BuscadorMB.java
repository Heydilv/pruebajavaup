/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "buscadorMB")
@ViewScoped
public class BuscadorMB implements Serializable {
    
    private String tipo;
    private String texto;

    public BuscadorMB() {
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
    
    public void buscar() {
        System.out.println(this.tipo);
        System.out.println(this.texto);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(String.format("publicaciones.xhtml?tipo=%s&texto=%s", this.tipo, this.texto));
        } catch (IOException ex) {
            Logger.getLogger(BuscadorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
