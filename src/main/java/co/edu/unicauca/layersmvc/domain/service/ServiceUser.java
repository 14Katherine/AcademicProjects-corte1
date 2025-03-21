/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain.service;

import co.edu.unicauca.layersmvc.access.CompanyRepository;
import co.edu.unicauca.layersmvc.access.CoordinatorRepository;
import co.edu.unicauca.layersmvc.access.ICompanyRepository;
import co.edu.unicauca.layersmvc.access.ICoordinatorRepository;
import co.edu.unicauca.layersmvc.access.IStudentRepository;
import co.edu.unicauca.layersmvc.access.IUserRepository;
import co.edu.unicauca.layersmvc.access.StudentRepository;
import co.edu.unicauca.layersmvc.access.UserFactory;
import co.edu.unicauca.layersmvc.access.UserRepository;
import co.edu.unicauca.layersmvc.domain.Company;
import co.edu.unicauca.layersmvc.domain.Coordinator;
import co.edu.unicauca.layersmvc.domain.Role;
import co.edu.unicauca.layersmvc.domain.Student;
import co.edu.unicauca.layersmvc.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katherine
 */
public class ServiceUser {
     private ICompanyRepository companyRepository;
    private ICoordinatorRepository coordinatorRepository;
    private IStudentRepository studentRepository;

    public ServiceUser(ICompanyRepository companyRepository, ICoordinatorRepository coordinatorRepository, IStudentRepository studentRepository) {
        this.companyRepository = companyRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.studentRepository = studentRepository;
    }

   /* public User validateUser(String username, String password) {
        // Buscar en los repositorios
        User user = findUserByUsername(username);

        // Validar contraseña
        if (user != null && user.getPassword().equals(password)) {
            return user; // Retornar usuario si la contraseña es correcta
        }

        return null; // Usuario no encontrado o contraseña incorrecta
    }*/
    public void printAllUsers() {
    System.out.println("Usuarios en los repositorios:");
    
    for (Company c : companyRepository.list()) {
        System.out.println("Empresa: " + c.getUsername());
    }

    for (Coordinator coor : coordinatorRepository.list()) {
        System.out.println("Coordinador: " + coor.getUsername());
    }

    for (Student s : studentRepository.list()) {
        System.out.println("Estudiante: " + s.getUsername());
    }
}

    public User validateUser(String username, String password) {
    User user = findUserByUsername(username);
    
    System.out.println("Usuario encontrado: " + (user != null ? user.getUsername() : "null"));

    if (user != null && user.getPassword().equals(password)) {
        return user;
    }

    System.out.println("Contraseña incorrecta o usuario no encontrado.");
    return null;
}

    

   public User findUserByUsername(String username) {
    // Buscar en el repositorio de empresas
    Company company = companyRepository.findByUsername(username);
    if (company != null) {
        return company;
    }

    // Buscar en el repositorio de coordinadores
    Coordinator coordinator = coordinatorRepository.findByUsername(username);
    if (coordinator != null) {
        return coordinator;
    }

    // Buscar en el repositorio de estudiantes
    Student student = studentRepository.findByUsername(username);
    if (student != null) {
        return student;
    }

    // Si no se encuentra en ningún repositorio, retorna null
    return null;
}
public boolean saveStudent(Student student) {
    if (student == null) {
        return false;
    }

    // Verificar si el usuario ya existe en algún repositorio
    if (findUserByUsername(student.getUsername()) != null) {
        return false;  // Usuario ya registrado
    }

    // Guardar en el repositorio de estudiantes
    return studentRepository.save(student);
}

}

