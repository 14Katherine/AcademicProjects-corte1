/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

/**
 *
 * @author Katherine
 */
public class UserFactory {
    private static UserFactory instance;
    private IUserRepository repository;

    private UserFactory() {
        this.repository = new UserRepository(); // Implementación por defecto
    }

    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    public IUserRepository getRepository() {
        return repository;
    }

    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }
}
