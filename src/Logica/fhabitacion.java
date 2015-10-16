/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
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
            
            String [] titulos = {"ID","Numero","Descricion","CaracteristicaS","Precio","Estado","Tipo habitacion"};
            
            String [] registro = new String [8];
          
            totalregistros=0;
            modelo = new DefaultTableModel(null,titulos);
            
            sSQL="select*from habitacion where piso like*&"+buscar + "&*order by idhabitacion";
            
            trycat          
    }
            
            
}
