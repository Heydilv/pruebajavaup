package dao;

import database.Conexion;
import idao.IPublicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Publicacion;
import models.Usuario;

public class PublicacionDAO implements IPublicacion {

    @Override
    public Publicacion ObtenerPublicacionById(int id) {
        Publicacion pub = null;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo FROM publicacion WHERE id_publicacion=? LIMIT 1;");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                pub = new Publicacion();

                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pub;
    }

    @Override
    public int GuardarPublicacion(Publicacion p) {
        int id = 0;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection
                    .prepareStatement("insert into publicacion(id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo)"
                            + "	values (?, ?, ?, ?, ?, ?, ?, ?) "
                            + "    RETURNING id_publicacion;");

            // establecer valores de la consulta
            stmt.setString(1, p.getAutor());
            stmt.setInt(2, p.getAnio());
            stmt.setString(3, p.getTipoPublicacion());
            stmt.setString(4, p.getFuente());
            stmt.setInt(5, p.getCitasRecibidas());
            stmt.setString(6, p.getResumen());
            stmt.setBoolean(7, p.getAprobado());
            stmt.setInt(8, p.getIdHallazgo());

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_publicacion");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    @Override
    public List<Publicacion> ObtenerPublicaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Publicacion> ObtenerPublicacionesPrevista() {
        List<Publicacion> publicaciones = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion order by anio DESC;");
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));

                publicaciones.add(pub);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return publicaciones;
    }

    @Override
    public List<Publicacion> ObtenerPublicacionesPrevistaFiltro(String tipo, String text) {
        List<Publicacion> publicaciones = new ArrayList<>();
        int textInt = 0;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt;

            switch (tipo) {
                case "id":
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion WHERE id_publicacion=? order by id_publicacion;");
                    
                    try {
                        textInt = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el id");
                    }
                    stmt.setInt(1, textInt);
                    break;
                case "anio":
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion WHERE anio >= ? order by anio;");
                    try {
                        textInt = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el id");
                    }
                    
                    stmt.setInt(1, textInt);
                    break;
                case "fuente":
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion WHERE fuente LIKE ?;");
                    stmt.setString(1, text + '%');
                    break;

                case "autor":
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion WHERE id_autor like ?;");
                    
                    stmt.setString(1, text+"%");
                    break;
                case "hallazgo":
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion as p inner join hallazgo as h on p.id_hallazgo=h.id_hallazgo where h.objetivo like ? ORDER BY h.objetivo;");
                    
                    stmt.setString(1, text+"%");
                    System.out.println("hallazgo");
                    break;

                default:
                    stmt = conn.connection
                            .prepareStatement("SELECT id_publicacion, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado FROM publicacion ORDER BY anio;");
                    break;
            }

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));

                publicaciones.add(pub);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return publicaciones;
    }

    @Override
    public void AprobarPublicacion(int idPublicacion) {
        

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("UPDATE publicacion SET aprobado=true WHERE id_publicacion=?;");
            stmt.setInt(1, idPublicacion);
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    }

}
