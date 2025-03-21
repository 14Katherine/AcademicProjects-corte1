/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.access;

import co.edu.unicauca.layersmvc.domain.Application;
import co.edu.unicauca.layersmvc.domain.Student;
import co.edu.unicauca.layersmvc.domain.Student.StudentState;
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
public class StudentRepository implements IStudentRepository {

    private Connection conn;
    private List<Application> applications = new ArrayList<>();

    public StudentRepository() {
        initDatabase();
    }

    private void initDatabase() {
        this.connect(); // Establecer la conexión antes de usarla

        if (this.conn == null) {  // Verificar si la conexión es válida
            System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
            return;
        }

        String sqlStudent = "CREATE TABLE IF NOT EXISTS Student ("
                + " identificacion INTEGER PRIMARY KEY,"
                + " nombre TEXT NOT NULL,"
                + " apellidos TEXT NOT NULL,"
             
                + " correo TEXT NOT NULL,"
                + " usuario TEXT UNIQUE NOT NULL,"
                + " contraseña TEXT NOT NULL"
                + ");";

        String sqlPostulacion = "CREATE TABLE IF NOT EXISTS Postulacion ("
        + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + " studentId INTEGER NOT NULL,"
        + " projectId INTEGER NOT NULL,"
        + " estado TEXT NOT NULL DEFAULT 'PENDIENTE',"  // Asegurar que la columna exista
        + " FOREIGN KEY (studentId) REFERENCES Student(identificacion),"
        + " FOREIGN KEY (projectId) REFERENCES Project(id)"
        + ");";

        try (Statement stmt = this.conn.createStatement()) { // Usar this.conn directamente
            stmt.execute(sqlStudent);
            stmt.execute(sqlPostulacion);
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error initializing database", ex);
        }
    }

    public boolean save(Student student) {
        if (student == null || student.getIdentificacion() < 0 || student.getNombre().isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO Student (identificacion, nombre, apellidos, semestre, correo, usuario, contraseña) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            this.connect();  // Establecer conexión

            if (this.conn == null) {  // Verificar si la conexión es válida
                System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {  // Manejo seguro del PreparedStatement
                pstmt.setInt(1, student.getIdentificacion());
                pstmt.setString(2, student.getNombre());
                pstmt.setString(3, student.getApellidos());
              
                pstmt.setString(5, student.getCorreo());
                pstmt.setString(6, student.getUsername());
                pstmt.setString(7, student.getPassword());

                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error saving student", ex);
        }
        return false;
    }

    public List<Student> list() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                     
                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error listing students", ex);
        }
        return students;
    }

    public Student findByIdentificacion(int identificacion) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE identificacion = ?";

        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, identificacion);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                    
                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error finding student by ID", ex);
        }
        return student;
    }

    public boolean postularAProyecto(int studentId, int projectId) {
        String sqlCheck = "SELECT 1 FROM Postulacion WHERE studentId = ? AND projectId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, projectId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false; // El estudiante ya está postulado
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error al comprobar postulación", ex);
            return false;
        }

        // Insertar postulación
        String sqlInsert = "INSERT INTO Postulacion (studentId, projectId) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, projectId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error al registrar postulación", ex);
        }
        return false;
    }

    public boolean checkPostulacion(int studentId, int projectId) {
        String sql = "SELECT 1 FROM Postulacion WHERE studentId = ? AND projectId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, projectId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Si existe una fila, el estudiante ya está postulado
        } catch (SQLException e) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error verificando postulación", e);
            return false;
        }
    }

    public Student findByUsername(String username) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE usuario = ?";

        try {
            this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),

                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error finding student by username", ex);
        }
        return student;
    }

    public List<Application> listStudentApplications() {
        return applications;
    }

    public void updateApplicationStatus(int studentId, int projectId, String status) {
        for (Application app : applications) {
            if (app.getStudentId() == studentId && app.getProjectId() == projectId) {
                app.setStatus(status);
                break;
            }
        }
    }
public void updateStudentStatus(int projectId, int studentId, String status) {
    String sql = "UPDATE Postulacion SET estado = ? WHERE projectId = ? AND studentId = ?";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, status);
        pstmt.setInt(2, projectId);
        pstmt.setInt(3, studentId);

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated == 0) {
            System.err.println("Advertencia: No se encontró ninguna postulación con los valores dados.");
        }
    } catch (SQLException e) {
        Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error actualizando estado de postulación", e);
    }
}


private Connection connect() {
    String url = "jdbc:sqlite:./mydatabase.db";
    try {
        if (this.conn == null || this.conn.isClosed()) {
            this.conn = DriverManager.getConnection(url);
        }
    } catch (SQLException ex) {
        Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
        this.conn = null;
    }
    return this.conn;  // DEVOLVER la conexión en lugar de solo asignarla
}

   /* private void connect() {
        String url = "jdbc:sqlite:./mydatabase.db";
        try {
            if (this.conn == null || this.conn.isClosed()) {
                this.conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
            this.conn = null;
        }
    }*/

}
