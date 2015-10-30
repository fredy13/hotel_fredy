/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import Datos.vproducto;
import Datos.vreserva;
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
public class freserva {
   
    private conexion mysql=new conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
            DefaultTableModel modelo;
            
            String [] titulos = {"ID","idhabitacion","Numero","Icliente","cliente","idtrabajador","trabajador","tipo reserva","fecha reserva","fecha ingreso","fecha salida","costo","estado"};
            
            String [] registro = new String [13];
          
            totalregistros=0;
            modelo = new DefaultTableModel(null,titulos);
            
            sSQL="select r.idreserva,r.idhabitacion,h.numero,r.idcliente"+
                    "(select nombre from persona where idpersona=r.idcliente)as clienten,"+
                    "(select apaterno from persona where idpersona=r.idcliente)as clienteap,"+
                    "r.idtrabajador,(select nombre from persona where idpersona=r.idtrabajador)as trabajadorn,"+
                    "(select apaterno from persona where idpersona=r.idtrabajador)as trabajadorap,"+
                    "r.tipo_reserva,r.fecha_reserva,r.fecha_ingresa,r.fecha_salida,"+
                    "r.costo_alojamiento,r.estado from reserva r inner join habitacion h on r.idhabitacion=h.idhabitacion where r.fecha_reserva like '%"+buscar + "%'order by idreserva desec";
            
             try {
                 Statement st = cn.createStatement();
                 ResultSet rs=st.executeQuery(sSQL);
                 
                 while(rs.next()){
                 registro [0]=rs.getNString("idreserva");
                 registro [1]=rs.getNString("idhabitacion");
                 registro [2]=rs.getNString("numero");
                 registro [3]=rs.getNString("idcliente");
                 registro [4]=rs.getNString("clienten") +" "+ rs.getNString("clienteap") ;
                 registro [5]=rs.getNString("idtrabajador");
                 registro [6]=rs.getNString("trabajadorn")+ " "+ rs.getNString("trabajadorap");
                 registro [7]=rs.getNString("tipo_reserva");
                 registro [8]=rs.getNString("fecha_reserva");
                 registro [9]=rs.getNString("fecha_ingresa");
                 registro [10]=rs.getNString("fecha_salida");
                 registro [11]=rs.getNString("costo_alojamiento");
                 registro [12]=rs.getNString("estado");
                     
                 
                 totalregistros=totalregistros+1;
                 modelo.addRow(registro);
          
                 }
          
                 return modelo;
                         
        } catch (Exception e) {
                 JOptionPane.showConfirmDialog(null, e);
                 return null;
                 
        }
        
    }
            
   public boolean insertar (vreserva dts){
       sSQL="insert into reserva(idhabitacion,idcliente,idtrabajador,tipo_reserva,fecha_reserva,fecha_ingresa,fecha_salida,costo_alojamiento,estado)"+
      "values(?,?,?,?,?,?,?,?,?)";        
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdhabitacion());
           pst.setInt(2, dts.getIdcliente());
           pst.setInt(3, dts.getIdtrabajador());
           pst.setString(4, dts.getTipo_reserva()); 
           pst.setDate(5, dts.getFecha_reserva()); 
           pst.setDate(6, dts.getFecha_ingresar());
           pst.setDate(7, dts.getFecha_salida());
           pst.setDouble(8, dts.getCosto_alojamiento());
           pst.setString(9, dts.getEstado()); 
              
           
            
           
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
   
   public boolean editar (vreserva dts){
       sSQL="update reserva set idhabitacion=?,idcliente=?,idtrabajador=?,tipo_reserva=?,fecha_reserva=?,fecha_ingresa=?,fecha_salida=?,costo_alojamiento=?,estado"+
               "where idreserva=?";
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());
           pst.setInt(2, dts.getIdcliente());
           pst.setInt(3, dts.getIdtrabajador());
           pst.setString(4, dts.getTipo_reserva()); 
           pst.setDate(5, dts.getFecha_reserva()); 
           pst.setDate(6, dts.getFecha_ingresar());
           pst.setDate(7, dts.getFecha_salida());
           pst.setDouble(8, dts.getCosto_alojamiento());
           pst.setString(9, dts.getEstado()); 
              
           pst.setInt(10, dts.getIdreserva()); 
           
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
   
   public boolean eliminar (vreserva dts){
       sSQL="delete from reserva where idreserva=?";
       
       try {
         PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdreserva());
           
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
