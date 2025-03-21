/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain.service;

import co.edu.unicauca.layersmvc.access.ICoordinatorRepository;
import co.edu.unicauca.layersmvc.domain.Coordinator;
import java.util.List;

/**
 *
 * @author Katherine
 */
public class ServiceCoordinator {
    private ICoordinatorRepository coordinatorRepository;

    // Constructor que acepta un repositorio
    public ServiceCoordinator(ICoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }
 
    // Método para registrar un coordinador
    public boolean registerCoordinator(Coordinator coordinator) {
        // Aquí podrías agregar lógica adicional si es necesario (como validaciones adicionales)
        return coordinatorRepository.save(coordinator);  // Guardar el coordinador en el repositorio
    }

    public List<Coordinator> listCoordinator() {
    List<Coordinator> coordinators = coordinatorRepository.list();
    System.out.println("Número de coordinadores: " + coordinators.size());
    return coordinators;
}

}
