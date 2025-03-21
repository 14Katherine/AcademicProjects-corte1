/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Coordinator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katherine
 */
public class CoordinatorRepository implements ICoordinatorRepository {
    private Connection conn;

    public CoordinatorRepository() {
        initDatabase();
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Coordinator ("
                + " identificacion TEXT PRIMARY KEY,"
                + " nombre TEXT NOT NULL,"
                + " apellidos TEXT NOT NULL,"
                + " correo TEXT NOT NULL,"
                + " usuario TEXT UNIQUE NOT NULL,"
                + " contraseña TEXT NOT NULL"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            Logger.getLogger(CoordinatorRepository.class.getName()).info("Coordinator table created or already exists.");
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error creating Coordinator table", ex);
        }
    }

    public boolean save(Coordinator coordinator) {
        if (coordinator == null || coordinator.getIdentificacion().isEmpty() || coordinator.getNombre().isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO Coordinator (identificacion, nombre, apellidos, correo, usuario, contraseña) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, coordinator.getIdentificacion());
            pstmt.setString(2, coordinator.getNombre());
            pstmt.setString(3, coordinator.getApellidos());
            pstmt.setString(4, coordinator.getCorreo());
            pstmt.setString(5, coordinator.getUsername());
            pstmt.setString(6, coordinator.getPassword());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error saving coordinator", ex);
        }
        return false;
    }

    public List<Coordinator> list() {
        List<Coordinator> coordinators = new ArrayList<>();
        String sql = "SELECT * FROM Coordinator";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                coordinators.add(mapResultSetToCoordinator(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error listing coordinators", ex);
        }
        return coordinators;
    }

    public Coordinator findByUsername(String username) {
        Coordinator coordinator = null;
        String sql = "SELECT * FROM Coordinator WHERE usuario = ?";

        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                coordinator = mapResultSetToCoordinator(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error finding coordinator by username", ex);
        }
        return coordinator;
    }

    private Coordinator mapResultSetToCoordinator(ResultSet rs) throws SQLException {
        return new Coordinator(
                rs.getString("identificacion"),
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("correo"),
                rs.getString("usuario"),
                rs.getString("contraseña")
        );
    }

    private void connect() {
        String url = "jdbc:sqlite:./mydatabase.db";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }

    // Método para cerrar recursos automáticamente
    private void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorRepository.class.getName()).log(Level.SEVERE, "Error closing connection", ex);
        }
    }
}