/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.User;
import java.util.List;

/**
 *
 * @author Katherine
 */
public interface IUserRepository {
    boolean save(User user);
    User findByUsername(String username);
    List<User> list();
}
