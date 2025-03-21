/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain.service;

import co.edu.unicauca.layersmvc.access.IStudentRepository;
import co.edu.unicauca.layersmvc.domain.Student;
import java.util.List;

/**
 *
 * @author Katherine
 */
public class ServiceStudent {
    private IStudentRepository repository;

    public ServiceStudent(IStudentRepository repository) {
        this.repository = repository;
    }

    public boolean save(Student student) {
        if (student == null || student.getIdentificacion() < 0 || student.getNombre().isEmpty()) {
            return false; // Validación básica
        }
        return repository.save(student);
    }

    public List<Student> listAll() {
        return repository.list();
    }

    public Student findByIdentificacion(int id) {
        return repository.findByIdentificacion(id);
    }

    public Student findByUsername(String username) {
        return repository.findByUsername(username);
    }
     public List<Student> listStudents() {
        return repository.list();
    }
     
     
}
