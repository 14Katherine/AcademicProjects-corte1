/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Role;
import co.edu.unicauca.layersmvc.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katherine
 */
public class UserRepository implements IUserRepository{
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        // Datos de ejemplo con el ENUM Role
        users.add(new User("admin", "1234", Role.COORDINADOR));
        users.add(new User("empresa1", "empresa123", Role.EMPRESA));
        users.add(new User("estudiante1", "estudiante123", Role.ESTUDIANTE));
    }

    @Override
    public boolean save(User user) {
        if (user == null || findByUsername(user.getUsername()) != null) {
            return false; // No permite duplicados ni valores nulos
        }
        return users.add(user);
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // No encontrado
    }

    @Override
    public List<User> list() {
        return new ArrayList<>(users); // Retorna una copia para evitar modificaciones externas
    }
}
