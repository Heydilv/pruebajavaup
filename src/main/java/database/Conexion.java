/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

/**
 *
 * @author elect
 */
public class Conexion {
    public Connection connection;
    

    //Nuevos parametros estandarizados
    //Credenciales para la conexion
    private String host = "jdbc:postgresql://ec2-52-7-115-250.compute-1.amazonaws.com:5432/d4na927a9aahk";
    private String user = "iwrmitcdgyqisn";
    private String password = "424113bce2295acf7757d1fb14261bc3b26981d77915897d1f59e7d4b1caa9a4";
    private String classForName = "org.postgresql.Driver";

    public Conexion() {
        
    }
    
    public void startConn() throws ClassNotFoundException, SQLException {
        
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(this.host, this.user, this.password);
        
        
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassForName() {
        return classForName;
    }

    public void setClassForName(String classForName) {
        this.classForName = classForName;
    }
    
    
    
}
