package dao;

import database.Conexion;
import idao.IHallazgo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Hallazgo;
import models.Publicacion;


public class HallazgoDAO implements IHallazgo {

    @Override
    public int GuardarHallazgo(Hallazgo h) {
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
                    .prepareStatement("insert into hallazgo(objetivo) values(?) returning id_hallazgo");

            // establecer valores de la consulta
            stmt.setString(1, h.getObjetivo());

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_hallazgo");
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
    public Hallazgo ObtenerHallazgoByID(int id) {
        Hallazgo hallazgo = null;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Consutlado hallazgo");
            PreparedStatement stmt = conn.connection.prepareStatement("select id_hallazgo, objetivo from hallazgo where id_hallazgo=? limit 1;");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                hallazgo = new Hallazgo();

                hallazgo.setIdHallazgo(resultado.getInt("id_hallazgo"));
                hallazgo.setObjetivo(resultado.getString("objetivo"));
                
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

        return hallazgo;
    }
    
}
