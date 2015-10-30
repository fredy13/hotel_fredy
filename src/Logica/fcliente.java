/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
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
public class fcliente {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "nombre", "apaterno", "amaterno", "doc", "numero documento", "direccion", "telefono", "email", "codigo"};

        String[] registro = new String[10];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombre,p.apaterno,p.amaterno,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,c.codigo_clientefrom perosona p inner join cliente c "
                + "on p.idpersona=c.idpersona where num_documento like*&"
                + buscar + "&*order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getNString("idpersona");
                registro[1] = rs.getNString("nombre");
                registro[2] = rs.getNString("apaterno");
                registro[3] = rs.getNString("amaterno");
                registro[4] = rs.getNString("tipo_documento");
                registro[5] = rs.getNString("num_documento");
                registro[6] = rs.getNString("direcion");
                registro[7] = rs.getNString("telefono");
                registro[8] = rs.getNString("email");
                registro[9] = rs.getNString("codigo_cliente");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }

            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;

        }

    }

    public boolean insertar(vcliente dts) {
        sSQL = "insert into persona(nombre,apaterno,amaterno,tipo_documento,num_documento,direccion,telefono,email)"
                + "values(?,?,?,?,?,?,?,?)";

        sSQL2 = "insert into cliente(idpersona,codigo_cliente)"
                + "values(select idpersona from persona order by idpersona desc limit 1),?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vcliente dts) {
        sSQL = "update persona set nombre=?,apaterno=?,amaterno=?,tipo_documento=?,num_documeto=?,"
                + "direccion=?,telefono=?,email=?,where idpersona=?";

        sSQL2 = "update clinte set codigo_cliente=?"
                + "where idpersona=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vcliente dts) {
        sSQL = "delete from cliente where idpersona=?=";
        sSQL2 = "delete from persona where idpersona=?=";
try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

           
            pst.setInt(1, dts.getIdpersona());

          
            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

}
