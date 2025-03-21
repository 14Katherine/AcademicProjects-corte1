/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.layersmvc.presentacion;

import co.edu.unicauca.layersmvc.access.IProjectRepository;
import co.edu.unicauca.layersmvc.access.IStudentRepository;
import co.edu.unicauca.layersmvc.access.ProjectRepository;
import co.edu.unicauca.layersmvc.access.StudentRepository;
import co.edu.unicauca.layersmvc.domain.Application;
import co.edu.unicauca.layersmvc.domain.Company;
import co.edu.unicauca.layersmvc.domain.Project;
import co.edu.unicauca.layersmvc.domain.Student;
import co.edu.unicauca.layersmvc.domain.service.ServiceCompany;
import co.edu.unicauca.layersmvc.domain.service.ServiceProject;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Katherine
 */
public class GUIACoordinatorView extends javax.swing.JFrame {

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEmpresDatos;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JTable jTableEstudiantes;
    private javax.swing.JScrollPane jScrollPane2;
    private String[] estado;
    private IStudentRepository studentRepository;
    private IProjectRepository projectRepository;

    private ServiceProject serviceProject;

    private List<Project> projects;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    GUIACoordinatorView(List<Project> projects, IStudentRepository studentRepository, IProjectRepository projectRepository,ServiceProject serviceProject) {
        this.projects = projects;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
        this.serviceProject = serviceProject;
        initComponents();
       configureEstadoColumnProject(); // Configurar el JComboBox en la columna "Estado"
        configureEstadoColumnEstudiante(); // Configurar el JComboBox en la columna "Estado"
        loadProjectsAplication();
        loadProjects();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpresDatos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEstudiantes = new javax.swing.JTable();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableEmpresDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nit", "Empresa", "Proyecto", "Objetivos", "Descripción", "Tiempo Max", "Presupuesto", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEmpresDatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empresas", jPanel2);

        jTableEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Proyecto", "Nombre Proyecto", "Nombre Empresa", "Id Estudiante", "Nombre Estudiante", "Apellidos Estudiante", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableEstudiantes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estudiantes", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSalir))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonSalir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        GUIUserView login = new GUIUserView();

        login.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed
    
    /**
     * ACTUALIZACION DEL ESTADO CON COMBOBOX/
     *
     */
    
    private void configureEstadoColumnEstudiante() {
        String[] estados = {"Aceptado", "Rechazado"};
        JComboBox<String> comboBox = new JComboBox<>(estados);

        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int row = jTableEstudiantes.getSelectedRow();
                if (row != -1) {
                    // Obtener valores de la fila seleccionada
                    int idProyecto = (int) jTableEstudiantes.getValueAt(row, 0);
                    int idEstudiante = (int) jTableEstudiantes.getValueAt(row, 3);
                    String nuevoEstado = (String) e.getItem();

                    // Actualizar en la base de datos
                    actualizarEstadoEstudiante(idProyecto, idEstudiante, nuevoEstado);
                }
            }
        });

        TableColumn estadoColumn = jTableEstudiantes.getColumnModel().getColumn(6);
        estadoColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    private void actualizarEstadoEstudiante(int idProyecto, int idEstudiante, String nuevoEstado) {
        serviceProject.updateStudentStatus(idProyecto, idEstudiante, nuevoEstado);
        loadProjectsAplication(); // Recargar la tabla después de actualizar
    }
    
    private void configureEstadoColumnProject() {
    String[] estados = {"Pendiente", "Aceptado", "Rechazado"};
    JComboBox<String> comboBox = new JComboBox<>(estados);

    comboBox.addItemListener(e -> {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            int row = jTableEmpresDatos.getSelectedRow();
            if (row != -1) {
                int idProyecto = (int) jTableEmpresDatos.getValueAt(row, 0);
                String nuevoEstado = (String) e.getItem();

                // Actualizar en la base de datos
                actualizarEstadoProyecto(idProyecto, nuevoEstado);
            }
        }
    });

    TableColumn estadoColumn = jTableEmpresDatos.getColumnModel().getColumn(7);
    estadoColumn.setCellEditor(new DefaultCellEditor(comboBox));
}
    private void actualizarEstadoProyecto(int idProyecto, String nuevoEstado) {
    serviceProject.updateProjectStatus(idProyecto, nuevoEstado);
    loadProjects(); // Recargar la tabla después de actualizar
}



    private void loadProjects() {
        DefaultTableModel model = (DefaultTableModel) jTableEmpresDatos.getModel();
        model.setRowCount(0);  // Limpiar la tabla antes de agregar los nuevos datos

        for (Project p : projects) {

            // Agregar una nueva fila con un botón en la columna "Estado"
            model.addRow(new Object[]{
                p.getCompanyNit(),
                p.getNombreEmpresa(),
                p.getNombreProyecto(),
                p.getObjetivos(),
                p.getDescripcion(),
                p.getTiempoMax(),
                p.getPresupuesto(),
                p.getEstado()

            });
        }
    }

    private void loadProjectsAplication() {
        DefaultTableModel model = (DefaultTableModel) jTableEstudiantes.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar los nuevos datos

        List<Project> projects = projectRepository.list(); // Obtener los proyectos del repositorio

        for (Project p : projects) {
            // Obtener la lista de estudiantes postulados al proyecto
            List<Student> postulantes = studentRepository.list();

            for (Student s : postulantes) {
                model.addRow(new Object[]{
                    p.getId(),
                    p.getNombreProyecto(),
                    p.getNombreEmpresa(),
                    s.getIdentificacion(),
                    s.getNombre(),
                    s.getApellidos(),
                    s.getEstado() // Mostrar el estado real del estudiante
                });
            }
        }

        model.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
    }


    /*private void showProjectDetails(Project project) {
    // Crear el mensaje con los detalles del proyecto (Objetivos y Estado)
    String message = "Objetivos: " + project.getObjetivos() + "\nEstado: " + project.getEstado();
    JOptionPane.showMessageDialog(this, message, "Detalles del Proyecto", JOptionPane.INFORMATION_MESSAGE);
}*/
    public static void main(String[] args) {
        // Crear los repositorios
        IProjectRepository projectRepository = new ProjectRepository();
        IStudentRepository studentRepository = new StudentRepository();

        // Crear el servicio pasando los repositorios
        ServiceProject serviceProject = new ServiceProject(projectRepository, studentRepository);

        // Obtener la lista de proyectos
        List<Project> projectList = serviceProject.listProjects();

        // Iniciar la vista correctamente
        java.awt.EventQueue.invokeLater(() -> {
            new GUIACoordinatorView(projectList, studentRepository, projectRepository, serviceProject).setVisible(true);
        });
    }

    /**

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEmpresDatos;
    private javax.swing.JTable jTableEstudiantes;
    // End of variables declaration//GEN-END:variables
*/
}
