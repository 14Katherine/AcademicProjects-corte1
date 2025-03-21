/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain;

import co.edu.unicauca.layersmvc.domain.Student.StudentState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katherine
 */

import java.util.ArrayList;
import java.util.List;

public class Project {
    public enum ProjectState {
        PROPUESTO, ASIGNADO, FINALIZADO
    }

   
    private int id;
    private int companyNit; // NIT de la empresa que registró el proyecto
    private String NombreEmpresa;
    private String NombreProyecto;
    private String Objetivos;
    private String Descripcion;
    private String TiempoMax;
    private Double Presupuesto;
    private ProjectState estado;  // Usar el enum ProjectState en lugar de String
    private List<Student> estudiantesPostulados;  // Cambié el ArrayList a List para generalidad
    private List<Integer> assignedStudents = new ArrayList<>();
    private List<Integer> studentIds = new ArrayList<>();



    // Constructor con todos los parámetros
    public Project(int id,int companyNit, String NombreEmpresa, String NombreProyecto, String Objetivos, String Descripcion, String TiempoMax, Double Presupuesto, ProjectState estado) {
        this.id = id;
        this.companyNit = companyNit;
        this.NombreEmpresa = NombreEmpresa;
        this.NombreProyecto = NombreProyecto;
        this.Objetivos = Objetivos;
        this.Descripcion = Descripcion;
        this.TiempoMax = TiempoMax;
        this.Presupuesto = Presupuesto;
        this.estado = estado;  // Inicializamos el estado
        this.estudiantesPostulados = new ArrayList<>();  // Inicializamos la lista de estudiantes
        this.studentIds = new ArrayList<>(); // Inicializar la lista
    }

    // Constructor sin parámetros
    public Project() {
        this.estudiantesPostulados = new ArrayList<>();  // Inicializamos la lista en el constructor vacío
    }

    public void postularEstudiante(Student estudiante) {
    estudiantesPostulados.add(estudiante);
    studentIds.add(estudiante.getIdentificacion()); // Agrega el ID del estudiante
}


    // Método para actualizar el estado de un estudiante
    public void actualizarEstadoEstudiante(Student estudiante, StudentState estado) {
        if (estudiantesPostulados.contains(estudiante)) {
            estudiante.setEstado(estado);  // Actualiza el estado del estudiante si está en la lista
        }
    }

    // Getters y Setters
    public int getCompanyNit() {
        return companyNit;
    }

    public void setCompanyNit(int companyNit) {
        this.companyNit = companyNit;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    public String getNombreProyecto() {
        return NombreProyecto;
    }

    public void setNombreProyecto(String NombreProyecto) {
        this.NombreProyecto = NombreProyecto;
    }

    public String getObjetivos() {
        return Objetivos;
    }

    public void setObjetivos(String Objetivos) {
        this.Objetivos = Objetivos;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getTiempoMax() {
        return TiempoMax;
    }

    public void setTiempoMax(String TiempoMax) {
        this.TiempoMax = TiempoMax;
    }

    public Double getPresupuesto() {
        return Presupuesto;
    }

    public void setPresupuesto(Double Presupuesto) {
        this.Presupuesto = Presupuesto;
    }

    public ProjectState getEstado() {
        return estado;
    }

    public void setEstado(ProjectState estado) {
        this.estado = estado;
    }

    public List<Student> getEstudiantesPostulados() {
        return estudiantesPostulados;
    }
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(List<Integer> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }
    // Método toString para mostrar información del proyecto
    @Override
    public String toString() {
        return "Proyecto: " + NombreProyecto + " (Estado: " + estado + ")";
    }
     public void addStudent(Student student) {
        // Agregar al estudiante a la lista de postulaciones
        estudiantesPostulados.add(student);
    }

    public boolean isStudentAlreadyApplied(Student student) {
        // Verificar si el estudiante ya está postulado
        return estudiantesPostulados.contains(student);
    }
    public boolean isStudentAssigned(int studentId) {
    return assignedStudents.contains(studentId);
}
    public List<Integer> getStudentIds() {
    System.out.println("Lista de IDs de estudiantes: " + studentIds); // Debug
    return studentIds;  
}

    

    public void addStudent(int studentId) {
        this.studentIds.add(studentId);
    }


}


