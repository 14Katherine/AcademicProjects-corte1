/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Company;
import java.util.List;

/**
 *
 * @author Katherine
 */
public interface ICompanyRepository {
     boolean save(Company company);
    List<Company> list();
    Company findByNit(int CompanyNit);
    Company findByUsername(String username);
    
}
