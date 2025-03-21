/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Project;

import co.edu.unicauca.layersmvc.domain.service.ServiceCompany;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katherine
 */
public class ProjectRepository implements IProjectRepository {

    private Connection conn;
    private List<Project> projects;

    public ProjectRepository() {
        initDatabase();
        this.projects = new ArrayList<>();  // Asegurar que projects está inicializado
    }

    private Connection connect() {
        String url = "jdbc:sqlite:./mydatabase.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Database connection error", ex);
            return null;
        }
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Project ("
                + " id INTEGER PRIMARY KEY,"
                + " No INTEGER NOT NULL,"
                + " NombreEmpresa TEXT NOT NULL,"
                + " NombreProyecto TEXT NOT NULL,"
                + " Objetivos TEXT NOT NULL,"
                + " Descripcion TEXT NOT NULL,"
                + " TiempoMax TEXT NOT NULL,"
                + " Presupuesto REAL NOT NULL,"
                + " estado TEXT NOT NULL"
                + ")";

        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error creating Project table", ex);
        }
    }

    @Override
    public boolean save(Project newProject) {
        if (newProject == null || newProject.getCompanyNit() < 0 || newProject.getNombreProyecto().isEmpty()) {
            System.out.println("Error: Datos del proyecto inválidos.");
            return false;
        }

        String sql = "INSERT INTO Project (id, No, NombreEmpresa, NombreProyecto, Objetivos, Descripcion, TiempoMax, Presupuesto, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newProject.getId());
            pstmt.setInt(2, newProject.getCompanyNit());
            pstmt.setString(3, newProject.getNombreEmpresa());
            pstmt.setString(4, newProject.getNombreProyecto());
            pstmt.setString(5, newProject.getObjetivos());
            pstmt.setString(6, newProject.getDescripcion());
            pstmt.setString(7, newProject.getTiempoMax());
            pstmt.setDouble(8, newProject.getPresupuesto());
            pstmt.setString(9, newProject.getEstado().name());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Proyecto guardado correctamente con ID: " + newProject.getId());
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error saving project", ex);
        }
        return false;
    }

    @Override
    public List<Project> list() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Project";

        try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getInt("No"),
                        rs.getString("NombreEmpresa"),
                        rs.getString("NombreProyecto"),
                        rs.getString("Objetivos"),
                        rs.getString("Descripcion"),
                        rs.getString("TiempoMax"),
                        rs.getDouble("Presupuesto"),
                        Project.ProjectState.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error fetching projects", ex);
        }

        System.out.println("Proyectos encontrados en BD: " + projects.size());
        return projects;
    }

    @Override
    public boolean update(Project newProject) {
        if (newProject == null || newProject.getCompanyNit() < 0 || newProject.getNombreProyecto().isEmpty()) {
            return false;
        }

        String sql = "UPDATE Project SET NombreEmpresa = ?, NombreProyecto = ?, Objetivos = ?, Descripcion = ?, "
                + "TiempoMax = ?, Presupuesto = ?, estado = ? WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newProject.getNombreEmpresa());
            pstmt.setString(2, newProject.getNombreProyecto());
            pstmt.setString(3, newProject.getObjetivos());
            pstmt.setString(4, newProject.getDescripcion());
            pstmt.setString(5, newProject.getTiempoMax());
            pstmt.setDouble(6, newProject.getPresupuesto());
            pstmt.setString(7, newProject.getEstado().name());
            pstmt.setInt(8, newProject.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Proyecto actualizado correctamente con ID: " + newProject.getId());
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error updating project", ex);
        }
        return false;
    }

    @Override
    public Project findById(int id) {
        String sql = "SELECT * FROM Project WHERE id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Project(
                        rs.getInt("id"),
                        rs.getInt("No"),
                        rs.getString("NombreEmpresa"),
                        rs.getString("NombreProyecto"),
                        rs.getString("Objetivos"),
                        rs.getString("Descripcion"),
                        rs.getString("TiempoMax"),
                        rs.getDouble("Presupuesto"),
                        Project.ProjectState.valueOf(rs.getString("estado"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error fetching project by ID", ex);
        }
        return null;
    }

    @Override
    public Project find(String projectName) {
        String sql = "SELECT * FROM Project WHERE NombreProyecto = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Project(
                        rs.getInt("id"),
                        rs.getInt("No"),
                        rs.getString("NombreEmpresa"),
                        rs.getString("NombreProyecto"),
                        rs.getString("Objetivos"),
                        rs.getString("Descripcion"),
                        rs.getString("TiempoMax"),
                        rs.getDouble("Presupuesto"),
                        Project.ProjectState.valueOf(rs.getString("estado"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error fetching project by name", ex);
        }
        return null;
    }

    @Override
    public List<Project> getProjectsByStudentId(int studentId) {
        List<Project> studentProjects = new ArrayList<>();
        String sql = "SELECT p.* FROM Project p "
                + "JOIN StudentProject sp ON p.id = sp.project_id "
                + "WHERE sp.student_id = ?";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                studentProjects.add(new Project(
                        rs.getInt("id"),
                        rs.getInt("No"),
                        rs.getString("NombreEmpresa"),
                        rs.getString("NombreProyecto"),
                        rs.getString("Objetivos"),
                        rs.getString("Descripcion"),
                        rs.getString("TiempoMax"),
                        rs.getDouble("Presupuesto"),
                        Project.ProjectState.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error fetching projects by student ID", ex);
        }

        return studentProjects;
    }

    @Override
    public List<Project> listProjectsByStudent(int studentId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.* FROM Project p "
                + "JOIN Postulacion po ON p.id = po.projectId "
                + "WHERE po.studentId = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getInt("No"),
                        rs.getString("NombreEmpresa"),
                        rs.getString("NombreProyecto"),
                        rs.getString("Objetivos"),
                        rs.getString("Descripcion"),
                        rs.getString("TiempoMax"),
                        rs.getDouble("Presupuesto"),
                        Project.ProjectState.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public List<Integer> getStudentIdsByProject(int projectId) {
    if (projects == null) {
        System.out.println("ERROR: projects es null");
        return Collections.emptyList();
    }

    if (projects.isEmpty()) {
        System.out.println("ERROR: projects está vacío");
        return Collections.emptyList();
    }

    for (Project p : projects) {
        if (p.getId() == projectId) {
            System.out.println("Proyecto encontrado: " + p.getNombreProyecto());
            System.out.println("Estudiantes postulados: " + p.getEstudiantesPostulados().size());
            System.out.println("Lista de IDs de estudiantes: " + p.getStudentIds());
            return p.getStudentIds();
        }
    }
    
    System.out.println("ERROR: No se encontró el proyecto con ID " + projectId);
    return Collections.emptyList();
}
    
    @Override
public void updateProjectStatus(int projectId, String status) {
    String sql = "UPDATE projects SET estado = ? WHERE id = ?";
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, status);
        stmt.setInt(2, projectId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        Logger.getLogger(ProjectRepository.class.getName()).log(Level.SEVERE, "Error updating project status", e);
    }
}



}
