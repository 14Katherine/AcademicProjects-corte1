/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Project;
import java.util.List;

/**
 *
 * @author Katherine
 */
public interface IProjectRepository {

    boolean save(Project project);

    boolean update(Project project);

    List<Project> list();

    Project findById(int projectId);

    Project find(String projectName);

    // Método para obtener proyectos de un estudiante
    List<Project> getProjectsByStudentId(int studentId);
    List<Project> listProjectsByStudent(int studentId);
    public List<Integer> getStudentIdsByProject(int projectId);
    void updateProjectStatus(int projectId, String status);



}
