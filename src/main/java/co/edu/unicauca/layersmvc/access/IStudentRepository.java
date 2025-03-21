/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Application;
import co.edu.unicauca.layersmvc.domain.Student;
import java.util.List;

/**
 *
 * @author Katherine
 */
public interface IStudentRepository {

    boolean save(Student student);

    List<Student> list();

    Student findByIdentificacion(int identificacion);

    Student findByUsername(String username);

    boolean checkPostulacion(int identificacion, int companyNit);
List<Application> listStudentApplications();
void updateApplicationStatus(int studentId, int projectId, String status);
void updateStudentStatus(int projectId, int studentId, String status);
    boolean postularAProyecto(int identificacion, int companyNit);
}
