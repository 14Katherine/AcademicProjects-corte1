/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain.service;



import co.edu.unicauca.layersmvc.access.CompanyRepository;
import co.edu.unicauca.layersmvc.access.ICompanyRepository;
import co.edu.unicauca.layersmvc.access.IProjectRepository;

import co.edu.unicauca.layersmvc.domain.Company; // Asegúrate de importar la clase Empresa
import co.edu.unicauca.layersmvc.domain.Project;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katherine
 */
public class ServiceCompany  {

  private ICompanyRepository companyRepository;

     // Constructor que recibe el repositorio
    public ServiceCompany(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    /**
     * Guarda una nueva empresa en el repositorio
     */
    public boolean saveCompany(Company newCompany) {
        if (newCompany == null) {
            return false;  
        }

        // Validaciones de datos
        if (newCompany.getNit() <= 0) {
            return false;  // NIT inválido
        }
        if (newCompany.getNombre() == null || newCompany.getNombre().trim().isEmpty()) {
            return false;  // Nombre vacío
        }
        if (newCompany.getCorreo() == null || !newCompany.getCorreo().contains("@")) {
            return false;  // Correo inválido
        }

        // Validar que la empresa no exista
        if (companyRepository.findByNit(newCompany.getNit()) != null) {
            return false;  // Empresa ya existe
        }

        // Guardar empresa
        return companyRepository.save(newCompany);
    }

    /**
     * Obtiene la lista de empresas
     */
    public List<Company> listCompanies() {
        List<Company> companies = companyRepository.list();
        return companies != null ? companies : new ArrayList<>();  // Evitar null
    }

    /**
     * Busca una empresa por su NIT
     */
    public Company findCompanyByNit(int nit) {
        return companyRepository.findByNit(nit);
    }

    /**
     * Busca una empresa por su nombre de usuario
     */
    public Company findCompanyByUsername(String username) {
        return companyRepository.findByUsername(username);
    }
}
