/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class empresa {
        public empresa(int Id, String Nombre, String Descripcion, String Pais, int Empleados) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Pais = Pais;
        this.Empleados = Empleados;
    }
    
     public empresa(){
    }

    int Id; 
    String Nombre; 
    String Descripcion;
    String Pais;
    int Empleados;
    
   
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public int getEmpleados() {
        return Empleados;
    }

    public void setEmpleados(int empleados) {
        this.Empleados = empleados;
    }
    
    public void GuardarEmpresa(){
        Connection conexiondb = Conexiondb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO empresa (Id,Nombre,Descripcion,Pais,Empleados) VALUES (null,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexiondb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getNombre());
            parametro.setString(2, this.getDescripcion());
            parametro.setString(3, this.getPais());
            parametro.setInt(4, this.getEmpleados());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexiondb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ModificarEmpresa(){
        Connection conexiondb = Conexiondb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE empresa SET Nombre=?,Descripcion=?,Pais=?,Empleados=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexiondb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getNombre());
            parametro.setString(2, this.getDescripcion());
            parametro.setString(3, this.getPais());
            parametro.setInt(4, this.getEmpleados());
            parametro.setInt(7, this.getId());


            //Ejecutar la sentecia SQL
            parametro.execute();
            conexiondb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
     public ArrayList<empresa> ObtenerEmpresa(){
        Connection conexiondb = Conexiondb.getConnection();
        ResultSet rsEmpresa;
        
        var empresa = new ArrayList<empresa>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM empresa";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexiondb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsEmpresa=parametro.executeQuery();           
            
            while(rsEmpresa.next()){              
                    empresa.add(new empresa(rsEmpresa.getInt("Id"),rsEmpresa.getString("Nombre"),rsEmpresa.getString("Descripcion"),rsEmpresa.getString("Pais"),rsEmpresa.getInt("Empleados")));

            }
            
            conexiondb.close();
            return empresa;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }
     
    void EliminarEmpresa() {
    Connection conexiondb = Conexiondb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM empresa WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexiondb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexiondb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}


