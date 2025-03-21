// ControladorPrincipal.java
package co.edu.unicauca.layersmvc.presentacion;

import co.edu.unicauca.layersmvc.access.CompanyRepository;
import co.edu.unicauca.layersmvc.access.CoordinatorRepository;
import co.edu.unicauca.layersmvc.access.IProjectRepository;
import co.edu.unicauca.layersmvc.access.IStudentRepository;
import co.edu.unicauca.layersmvc.access.ICoordinatorRepository;
import co.edu.unicauca.layersmvc.access.ICompanyRepository;
import co.edu.unicauca.layersmvc.access.ProjectRepository;
import co.edu.unicauca.layersmvc.access.StudentRepository;
import co.edu.unicauca.layersmvc.domain.Project;
import co.edu.unicauca.layersmvc.domain.Student;
import co.edu.unicauca.layersmvc.domain.User;
import co.edu.unicauca.layersmvc.domain.service.ServiceCompany;
import co.edu.unicauca.layersmvc.domain.service.ServiceProject;
import co.edu.unicauca.layersmvc.domain.service.ServiceStudent;
import co.edu.unicauca.layersmvc.domain.service.ServiceUser;
import co.edu.unicauca.layersmvc.presentacion.GUIAUserRegistrationView;
import co.edu.unicauca.layersmvc.presentacion.GUICompanyView;
import co.edu.unicauca.layersmvc.presentacion.GUIStudentView;
import co.edu.unicauca.layersmvc.presentacion.GUIUserView;
import co.edu.unicauca.layersmvc.presentacion.GUIACoordinatorView;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GUIController {
    private GUIUserView userView;
    private GUIAUserRegistrationView registrationView;
    private GUICompanyView companyView;
    private GUIACoordinatorView coordinatorView;
    private GUIStudentView studentView;
    private ServiceProject serviceProject;
    private ServiceCompany serviceCompany;
    private ServiceStudent serviceStudent;
    private ServiceUser serviceUser;

    public GUIController() {
        // Crear las instancias de los repositorios
        IProjectRepository projectRepository = new ProjectRepository();  // Asegúrate de tener un repositorio válido
        IStudentRepository studentRepository = new StudentRepository();  // Asegúrate de tener un repositorio válido
        ICompanyRepository companyRepository = new CompanyRepository();  // Asegúrate de tener un repositorio válido
        ICoordinatorRepository coordinatorRepository = new CoordinatorRepository();
        // Inicializar el servicio, pasando los repositorios
        this.serviceProject = new ServiceProject(projectRepository, studentRepository); 
        this.serviceCompany = new ServiceCompany(companyRepository);
        this.serviceStudent = new ServiceStudent(studentRepository);
        this.serviceUser = new ServiceUser(companyRepository, coordinatorRepository, studentRepository);  // Crear la instancia de ServiceUser
        List<Project> projectList = serviceProject.listProjects(); // Aquí debería funcionar ahora

        // Inicializar las vistas
        this.userView = new GUIUserView();
        this.registrationView = new GUIAUserRegistrationView(coordinatorRepository,companyRepository,studentRepository);
        this.companyView = new GUICompanyView(serviceProject, serviceCompany);
        this.coordinatorView = new GUIACoordinatorView(projectList, studentRepository, projectRepository,serviceProject);
        this.serviceStudent = new ServiceStudent(studentRepository);
        

        // Configurar el controlador de la vista de usuario
        this.userView.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarVistaRegistro();
            }
        });

        this.userView.getBtnIniciarSeccion().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    // Resto del código sigue igual

    // Método para mostrar la vista de registro
    private void mostrarVistaRegistro() {
        userView.setVisible(false);
        registrationView.setVisible(true);
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        String correo = userView.getCorreo(); // Obtener correo de la vista
        String contrasena = userView.getContrasena(); // Obtener contraseña de la vista
        // Validar las credenciales y obtener el usuario
        User user = serviceUser.validateUser(correo, contrasena); // Usar el servicio para la validación
        
        if (user == null) {
            // Mostrar mensaje de error si no se encuentra el usuario o las credenciales son incorrectas
            JOptionPane.showMessageDialog(userView, "Credenciales incorrectas");
            return;
        }

        String rol = user.getRole() != null ? user.getRole().toString() : null;
        
        if (rol == null) {
            // Mostrar mensaje de error si el rol no está asignado
            JOptionPane.showMessageDialog(userView, "El usuario no tiene un rol asignado");
            return;
        }

        // Mostrar la vista correspondiente según el rol
        if (rol.equals("COORDINADOR")) {
            userView.setVisible(false);
            coordinatorView.setVisible(true);
        } else if (rol.equals("EMPRESA")) {
            userView.setVisible(false);
            companyView.setVisible(true);
        } else if (rol.equals("ESTUDIANTE")) {
            userView.setVisible(false);
            studentView.setVisible(true); // Debes tener la vista de estudiante configurada correctamente
        } else {
            JOptionPane.showMessageDialog(userView, "Rol no reconocido");
        }
    
    }

    public static void main(String[] args) {
        // Crear el controlador y la vista principal
        GUIController controlador = new GUIController();
        controlador.userView.setVisible(true);
    }
}




