/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.layersmvc.domain;

/**
 *
 * @author Katherine
 */
public class Application {
    private int studentId;
    private int projectId;
    private String status; // "Pendiente", "Aprobado", "Rechazado"

    public Application(int studentId, int projectId, String status) {
        this.studentId = studentId;
        this.projectId = projectId;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

