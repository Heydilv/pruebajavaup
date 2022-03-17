package dao;

import database.Conexion;
import idao.IDato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Dato;
import models.Hallazgo;
import models.Publicacion;

public class DatoDAO implements IDato {

    @Override
    public int GuardarDato(Dato d) {
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
                    .prepareStatement("insert into dato(titulo, descripcion, id_hallazgo) values (?, ?, ?) returning id_dato");

            // establecer valores de la consulta
            stmt.setString(1, d.getTitulo());
            stmt.setString(2, d.getDescripcion());
            stmt.setInt(3, d.getIdHallazgo());

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_dato");
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
    public Dato ObtenerDatoByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Dato> ObtenerDatosByIDHallazgo(int idhallazgo) {
        
        List<Dato> datos = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("select id_dato, titulo, descripcion, id_hallazgo from dato where id_hallazgo=?;");
            stmt.setInt(1, idhallazgo);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Dato dato = new Dato();
                
                dato.setIdDato(resultado.getInt("id_dato"));
                dato.setTitulo(resultado.getString("titulo"));
                dato.setDescripcion(resultado.getString("descripcion"));
                dato.setIdHallazgo(resultado.getInt("id_hallazgo"));
                

                datos.add(dato);
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

        return datos;
    }
    
}
