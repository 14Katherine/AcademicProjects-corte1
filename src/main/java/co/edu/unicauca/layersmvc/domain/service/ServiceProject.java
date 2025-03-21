/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain.service;

import co.edu.unicauca.layersmvc.access.IProjectRepository;
import co.edu.unicauca.layersmvc.access.IStudentRepository;

import co.edu.unicauca.layersmvc.access.StudentRepository;
import co.edu.unicauca.layersmvc.domain.Project;
import co.edu.unicauca.layersmvc.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katherine
 */
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class ServiceProject {
 
    private static final Logger logger = Logger.getLogger(ServiceProject.class.getName());
    private final IProjectRepository projectRepository;
    private final IStudentRepository studentRepository;

    public ServiceProject(IProjectRepository projectRepository, IStudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    public boolean saveProject(Project newProject) {
        try {
            // Validar datos
            String validationError = validateProject(newProject);
            if (validationError != null) {
                logger.warning("Error de validación: " + validationError);
                return false;
            }

            // Normalizar nombre para evitar duplicados por mayúsculas/minúsculas
            newProject.setNombreProyecto(newProject.getNombreProyecto().toUpperCase());

            // Intentar guardar el proyecto
            return projectRepository.save(newProject);
        } catch (Exception e) {
            logger.severe("Error al guardar el proyecto: " + e.getMessage());
            return false;
        }
    }

    public boolean updateProject(Project newProject) {
        try {
            // Validar datos
            String validationError = validateProject(newProject);
            if (validationError != null) {
                logger.warning("Error de validación: " + validationError);
                return false;
            }
            return projectRepository.update(newProject);
        } catch (Exception e) {
            logger.severe("Error al actualizar el proyecto: " + e.getMessage());
            return false;
        }
    }

    private String validateProject(Project project) {
        if (project == null) return "El proyecto no puede ser nulo.";
        if (project.getCompanyNit() <= 0) return "El NIT de la empresa debe ser mayor a 0.";
        if (project.getNombreProyecto() == null || project.getNombreProyecto().isEmpty()) return "El nombre del proyecto no puede estar vacío.";
        return null;
    }

    public Project findProject(String projectName) {
        return projectRepository.find(projectName.toUpperCase());
    }

    public List<Project> listProjects() {
        List<Project> projects = projectRepository.list();
        if (projects == null || projects.isEmpty()) {
            logger.info("No hay proyectos disponibles.");
            return new ArrayList<>();
        }
        return projects;
    }


    public boolean postularEstudianteAProyecto(int studentId, int projectId) {
        try {
            // Optimizar consultas: obtener proyecto y estudiante en una sola transacción
            Project proyecto = projectRepository.findById(projectId);
            Student estudiante = studentRepository.findByIdentificacion(studentId);

            if (proyecto == null) {
                logger.warning("El proyecto no existe.");
                return false;
            }
            if (estudiante == null) {
                logger.warning("El estudiante no existe.");
                return false;
            }
            if (studentRepository.checkPostulacion(studentId, projectId)) {
                logger.info("El estudiante ya está postulado a este proyecto.");
                return false;
            }

            boolean success = studentRepository.postularAProyecto(studentId, projectId);
            if (success) {
                logger.info("Estudiante postulado exitosamente.");
            } else {
                logger.warning("Error al postular al estudiante.");
            }
            return success;
        } catch (Exception e) {
            logger.severe("Error al procesar la postulación: " + e.getMessage());
            return false;
        }
    }
public List<Project> getProjectsByStudent(int studentId) {
    return projectRepository.listProjectsByStudent(studentId);
}
public List<Student> getStudentsByProject(int projectId) {
    List<Integer> studentIds = projectRepository.getStudentIdsByProject(projectId);
    List<Student> students = new ArrayList<>();

    for (Integer id : studentIds) {
        Student student = studentRepository.findByIdentificacion(id);
        if (student != null) {
            students.add(student);
        }
    }
    return students;
}

    public List<Project> getProyectosPostulados(int studentId) {
        return projectRepository.getProjectsByStudentId(studentId);
    }
    
    public void updateStudentStatus(int projectId, int studentId, String status) {
    studentRepository.updateStudentStatus(projectId, studentId, status);
}
public void updateProjectStatus(int projectId, String status) {
    projectRepository.updateProjectStatus(projectId, status);
}

}
