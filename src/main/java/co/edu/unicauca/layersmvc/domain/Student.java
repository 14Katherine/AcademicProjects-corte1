/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain;

/**
 *
 * @author Katherine
 */
public class Student extends User {

    

    
    public enum StudentState {
        POSTULADO,NOPOSTULADO, ACEPTADO, RECHAZADO
    }

    private int identificacion;
    private String nombre;
    private String apellidos;
   
    private String correo;
    private StudentState estado;  // El estado de la postulación del estudiante

    public Student(int identificacion, String nombre, String apellidos,  String correo, String usuario, String contraseña) {
        super(usuario, contraseña, Role.ESTUDIANTE); // Llamar al constructor de User
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        
        this.correo = correo;
        this.estado = StudentState.NOPOSTULADO;  // Asignar un valor por defecto
  
    }
    
    

    // Getters y Setters
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

   

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public StudentState getEstado() {
        return estado;
    }

    public void setEstado(StudentState estado) {
        this.estado = estado;
    }
}
