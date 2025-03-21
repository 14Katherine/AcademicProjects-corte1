/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Coordinator;
import java.util.List;

/**
 *
 * @author Katherine
 */
public interface ICoordinatorRepository {
     boolean save(Coordinator coordinator);
    List<Coordinator> list();
    Coordinator findByUsername(String username);
}
