/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Company;
import co.edu.unicauca.layersmvc.domain.service.ServiceCompany;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyRepository implements ICompanyRepository {
   private Connection conn;

    public CompanyRepository() {
        initDatabase();
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Company ("
                + " nit INTEGER PRIMARY KEY,"
                + " nombre TEXT NOT NULL,"
                + " correo TEXT NOT NULL,"
                + " sector TEXT NOT NULL,"
                + " contacto TEXT NOT NULL,"
                + " nombreContacto TEXT NOT NULL,"
                + " apellidosContacto TEXT NOT NULL,"
                + " cargoContacto TEXT NOT NULL,"
                + " usuario TEXT UNIQUE NOT NULL,"
                + " contraseña TEXT NOT NULL"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            Logger.getLogger(CompanyRepository.class.getName()).info("Company table created or already exists.");
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error creating Company table", ex);
        }
    }

    public boolean save(Company company) {
        if (company == null || company.getNit() < 0 || company.getNombre().isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO Company (nit, nombre, correo, sector, contacto, "
                + "nombreContacto, apellidosContacto, cargoContacto, usuario, contraseña) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, company.getNit());
            pstmt.setString(2, company.getNombre());
            pstmt.setString(3, company.getCorreo());
            pstmt.setString(4, company.getSector());
            pstmt.setString(5, company.getContacto());
            pstmt.setString(6, company.getNombreContacto());
            pstmt.setString(7, company.getApellidosContacto());
            pstmt.setString(8, company.getCargoContacto());
            pstmt.setString(9, company.getUsername());
            pstmt.setString(10, company.getPassword());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error saving company", ex);
        }
        return false;
    }

    public List<Company> list() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Company";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("nit"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("sector"),
                        rs.getString("contacto"),
                        rs.getString("nombreContacto"),
                        rs.getString("apellidosContacto"),
                        rs.getString("cargoContacto"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
                companies.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error listing companies", ex);
        }
        return companies;
    }

    public Company findByNit(int nit) {
        Company company = null;
        String sql = "SELECT * FROM Company WHERE nit = ?";

        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nit);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                company = new Company(
                        rs.getInt("nit"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("sector"),
                        rs.getString("contacto"),
                        rs.getString("nombreContacto"),
                        rs.getString("apellidosContacto"),
                        rs.getString("cargoContacto"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error finding company by NIT", ex);
        }
        return company;
    }

    public Company findByUsername(String username) {
        Company company = null;
        String sql = "SELECT * FROM Company WHERE usuario = ?";

        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                company = new Company(
                        rs.getInt("nit"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("sector"),
                        rs.getString("contacto"),
                        rs.getString("nombreContacto"),
                        rs.getString("apellidosContacto"),
                        rs.getString("cargoContacto"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error finding company by username", ex);
        }
        return company;
    }

    private void connect() {
        String url = "jdbc:sqlite:./mydatabase.db";  
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }
}
