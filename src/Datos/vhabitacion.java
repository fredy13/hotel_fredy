/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author usuario
 */
public class vhabitacion {
  private int idhabitacion;
  private String nuemro;
  private String piso;
  private String descripcion;
  private String caracteristicas;
  private Double precio_diario;
  private String tipo_habitacion;

    public vhabitacion(int idhabitacion, String nuemro, String piso, String descripcion, String caracteristicas, Double precio_diario, String tipo_habitacion) {
        this.idhabitacion = idhabitacion;
        this.nuemro = nuemro;
        this.piso = piso;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.precio_diario = precio_diario;
        this.tipo_habitacion = tipo_habitacion;
    }

    public vhabitacion() {
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getNuemro() {
        return nuemro;
    }

    public void setNuemro(String nuemro) {
        this.nuemro = nuemro;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Double getPrecio_diario() {
        return precio_diario;
    }

    public void setPrecio_diario(Double precio_diario) {
        this.precio_diario = precio_diario;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }
  
  
  
}
