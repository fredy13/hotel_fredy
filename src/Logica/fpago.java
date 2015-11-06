/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import Datos.vpago;
import Datos.vproducto;
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
public class fpago {
   
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
            DefaultTableModel modelo;
            
            String [] titulos = {"ID","idreserva","Comprobante","numero", "igv","total","fecha emision","fehca pago"};
            
            String [] registro = new String [8];
          
            totalregistros=0;
            modelo = new DefaultTableModel(null,titulos);
            
            sSQL="select*from pago where idreserva="+buscar + "%'order by idpago desc";
            
             try {
                 Statement st = cn.createStatement();
                 ResultSet rs=st.executeQuery(sSQL);
                 
                 while(rs.next()){
                 registro [0]=rs.getNString("idpago");
                 registro [1]=rs.getNString("idreserva");
                 registro [2]=rs.getNString("tipo_comprobante");
                 registro [3]=rs.getNString("numero_comprobante");
                 registro [4]=rs.getNString("igv");
                 registro [5]=rs.getNString("total_pago");
                 registro [6]=rs.getNString("Fecha_emision");
                 registro [7]=rs.getNString("fecha_pago");
                
                 
                 totalregistros=totalregistros+1;
                 modelo.addRow(registro);
          
                 }
          
                 return modelo;
                         
        } catch (Exception e) {
                 JOptionPane.showConfirmDialog(null, e);
                 return null;
                 
        }
        
    }
            
   public boolean insertar (vpago dts){
       sSQL="insert into pago(idreserva,tipo_comprobante,num_comprobante,igv,total_pago,fecha_emision,fecha_pago)"+
      "values(?,?,?,?,?,?,?)";        
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv()); 
           pst.setDouble(5, dts.getTotal_pago()); 
           pst.setDate(6, dts.getFecha_emision()); 
           pst.setDate(7, dts.getFecha_pago()); 
            
           
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
   
   public boolean editar (vpago dts){
       sSQL="update pago set idreserva=?,tipo_comprobante=?,num_comprobante=?,igv=?,total_pago=?,fecha_emision=?,fecha_pago=?"+
               "where idpago=?";
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv()); 
           pst.setDouble(5, dts.getTotal_pago()); 
           pst.setDate(6, dts.getFecha_emision()); 
           pst.setDate(7, dts.getFecha_pago()); 
                     
           pst.setInt(8, dts.getIdpago()); 
           
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
   
   public boolean eliminar (vpago dts){
       sSQL="delete from pago where idpago=?=";
       
       try {
         PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdpago());
           
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
