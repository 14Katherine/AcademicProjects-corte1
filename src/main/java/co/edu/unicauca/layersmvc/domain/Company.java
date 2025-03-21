/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain;

/**
 *
 * @author Katherine
 */

public class Company extends User {
    private int nit;
    private String nombre;
    private String correo;
    private String sector;
    private String contacto;
    private String nombreContacto;
    private String apellidosContacto;
    private String cargoContacto;

    // Constructor que incluye usuario y contraseña
    public Company(int nit, String nombre, String correo, String sector, 
                   String contacto, String nombreContacto, String apellidosContacto, 
                   String cargoContacto, String usuario, String contraseña) {
        super(usuario, contraseña, Role.EMPRESA); // Llamada al constructor de User
        this.nit = nit;
        this.nombre = nombre;
        this.correo = correo;
        this.sector = sector;
        this.contacto = contacto;
        this.nombreContacto = nombreContacto;
        this.apellidosContacto = apellidosContacto;
        this.cargoContacto = cargoContacto;
    }

    // Getters y Setters
    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidosContacto() {
        return apellidosContacto;
    }

    public void setApellidosContacto(String apellidosContacto) {
        this.apellidosContacto = apellidosContacto;
    }

    public String getCargoContacto() {
        return cargoContacto;
    }

    public void setCargoContacto(String cargoContacto) {
        this.cargoContacto = cargoContacto;
    }

    // Método para obtener solo los datos accesibles para el coordinador
    public String getPublicCompanyDetails() {
        return "NIT: " + nit + ", Nombre: " + nombre + ", Correo: " + correo + 
               ", Sector: " + sector + ", Contacto: " + contacto + 
               ", Nombre del Contacto: " + nombreContacto + 
               ", Apellidos del Contacto: " + apellidosContacto + 
               ", Cargo del Contacto: " + cargoContacto;
    }
}
