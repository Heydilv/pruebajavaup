/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Conexion;
import idao.IUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO implements IUsuario {

    @Override
    public Usuario ObtenerUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Debe conectarse a la base de datos y verificar que es un usuario legitimo
    @Override
    public Usuario Login(String username, String password) {
        Usuario usuario = null;
        Conexion conn = new Conexion();
        
        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* ejemplo
            select id_usuario, username, password, role 
                from usuario
                where username='roberto' AND password='facil';
                
            */
            PreparedStatement stmt = conn.connection.prepareStatement("select id_usuario, username, password, role FROM usuario WHERE username=? AND password=?;");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                usuario = new Usuario();
                // Establecemos los valores
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setUsername(resultado.getString("username"));
                usuario.setPassword(resultado.getString("password"));
                usuario.setRole(resultado.getString("role"));
            }
            
            System.out.println("usuario traido con exito");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return usuario;
    }
    
}
