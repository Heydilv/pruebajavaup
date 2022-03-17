/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Publicacion;

public interface IPublicacion {
    public Publicacion ObtenerPublicacionById(int id);
    public int GuardarPublicacion(Publicacion p);
    public List<Publicacion> ObtenerPublicaciones();
    public List<Publicacion> ObtenerPublicacionesPrevista();
    public List<Publicacion> ObtenerPublicacionesPrevistaFiltro(String tipo, String text);
    public void AprobarPublicacion(int idPublicacion);
}
