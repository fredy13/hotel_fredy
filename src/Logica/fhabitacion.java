/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class fhabitacion {
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
            DefaultTableModel modelo;
            
            String [] titulos = {"ID","Numero","piso","Descricion","CaracteristicaS","Precio","Estado","Tipo habitacion"};
            
            String [] registro = new String [8];
          
            totalregistros=0;
            modelo = new DefaultTableModel(null,titulos);
            
            sSQL="select*from habitacion where piso like'&"+buscar + "&'order by idhabitacion";
            
             try {
                 Statement st = cn.createStatement();
                 ResultSet rs=st.executeQuery(sSQL);
                 
                 while(rs.next()){
                 registro [0]=rs.getNString("idhabitacion");
                 registro [1]=rs.getNString("numero");
                 registro [2]=rs.getNString("piso");
                 registro [3]=rs.getNString("descripcion");
                 registro [4]=rs.getNString("caracteristicas");
                 registro [5]=rs.getNString("precio_diario");
                 registro [6]=rs.getNString("estado");
                 registro [7]=rs.getNString("tipo_habitacion");
                 
                 totalregistros=totalregistros+1;
                 modelo.addRow(registro);
          
                 }
          
                 return modelo;
                         
        } catch (Exception e) {
                 JOptionPane.showConfirmDialog(null, e);
                 return null;
                 
        }
        
    }
    
    public DefaultTableModel mostrarvista(String buscar){
            DefaultTableModel modelo;
            
            String [] titulos = {"ID","Numero","piso","Descricion","CaracteristicaS","Precio","Estado","Tipo habitacion"};
            
            String [] registro = new String [8];
          
            totalregistros=0;
            modelo = new DefaultTableModel(null,titulos);
            
            sSQL="select*from habitacion where piso like'&"+buscar + "&'and estado='Disponible' order by idhabitacion";
            
             try {
                 Statement st = cn.createStatement();
                 ResultSet rs=st.executeQuery(sSQL);
                 
                 while(rs.next()){
                 registro [0]=rs.getNString("idhabitacion");
                 registro [1]=rs.getNString("numero");
                 registro [2]=rs.getNString("piso");
                 registro [3]=rs.getNString("descripcion");
                 registro [4]=rs.getNString("caracteristicas");
                 registro [5]=rs.getNString("precio_diario");
                 registro [6]=rs.getNString("estado");
                 registro [7]=rs.getNString("tipo_habitacion");
                 
                 totalregistros=totalregistros+1;
                 modelo.addRow(registro);
          
                 }
          
                 return modelo;
                         
        } catch (Exception e) {
                 JOptionPane.showConfirmDialog(null, e);
                 return null;
                 
        }
        
    }
    
    
    
    
            
   public boolean insertar (vhabitacion dts){
       sSQL="insert into habitacion(numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion)"+
      "values(?,?,?,?,?,?,?)";        
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNuemro());
           pst.setString(2, dts.getPiso());
           pst.setString(3, dts.getDescripcion());
           pst.setString(4, dts.getCaracteristicas()); 
           pst.setDouble(5, dts.getPrecio_diario());
           pst.setString(6, dts.getEstado()); 
           pst.setString(7, dts.getTipo_habitacion());  
           
           int n=pst.executeUpdate();
             
           if (n!=0){
              
       return true;
       }
           else{
              return false;  
          }    
         
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
         return false;  
       }
   }
   
   public boolean editar (vhabitacion dts){
       sSQL="update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=?"+
               "where idhabitacion=?";
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNuemro());
           pst.setString(2, dts.getPiso());
           pst.setString(3, dts.getDescripcion());
           pst.setString(4, dts.getCaracteristicas()); 
           pst.setDouble(5, dts.getPrecio_diario());
           pst.setString(6, dts.getEstado()); 
           pst.setString(7, dts.getTipo_habitacion());  
           pst.setInt(8, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
             
           if (n!=0) {
              
       return true;
       }
           else{
              return false;  
          }     
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
        return false;   
           
       }
   }      
   
   public boolean eliminar (vhabitacion dts){
       sSQL="delete from habitacion where idhabitacion=";
       
       try {
         PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdhabitacion());
           
           int n=pst.executeUpdate();
             
           if (n!=0) {
              
       return true;
       }
           else{
              return false;  
          }        
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }      
  
   
   
   
}

