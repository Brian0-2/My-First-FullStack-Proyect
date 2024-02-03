package puntodeventa;

import Conexion.conexionSQL;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class Clientes extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public Clientes() {
        initComponents();
        txtCodigoClientes.requestFocusInWindow();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        mostrar("", "");
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        txtId.setEnabled(false);

        btnEliminar.setEnabled(false);
    }

    public void mostrar(String nombre, String id) {

        DefaultTableModel modelo = new DefaultTableModel();

        jTablaClientes.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        String SQL = "";
        try {
            String Clientes[] = new String[8];
            PreparedStatement ps;
            if (nombre.equals("") || id.equals("")) {
                SQL = "SELECT * FROM clientes";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ? OR id_cliente = ?");
                ps.setString(1, "%" + txtCodigoClientes.getText() + "%");
                ps.setString(2, txtCodigoClientes.getText());
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            int[] anchos = {50, 200, 130, 280};
            for (int i = 0; i < jTablaClientes.getColumnCount(); i++) {
                jTablaClientes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Clientes[0] = rs.getString(1);
                Clientes[1] = rs.getString(2);
                Clientes[2] = rs.getString(3);
                Clientes[3] = rs.getString(4);
                modelo.addRow(Clientes);
            }
            jTablaClientes.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    public void caracteristicasClientes() {

        int fila = jTablaClientes.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(jTablaClientes.getValueAt(fila, 0).toString());
            txtNombre.setText(jTablaClientes.getValueAt(fila, 1).toString());
            txtTelefono.setText(jTablaClientes.getValueAt(fila, 2).toString());
            txtDireccion.setText(jTablaClientes.getValueAt(fila, 3).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    public void guardarNuevosClientes() {
        try {

            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO clientes(nombre,telefono,direccion) VALUES(?,?,?)");

            if (ps != null) {

                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtTelefono.getText());
                ps.setString(3, txtDireccion.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Ingreso Exitoso...");

                Object[] fila = new Object[4];
                fila[0] = txtId.getText();
                fila[1] = txtNombre.getText();
                fila[2] = txtTelefono.getText();
                fila[3] = txtTelefono.getText();

                modelo.addRow(fila);

            } else {
                JOptionPane.showMessageDialog(this, "         Error de ingreso de datos... "
                        + "\nVerifica que se halllan llenado bien los campos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "            Error de ingreso de datos... "
                    + "\nVerifica que se hallan llenado bien los campos..."
                    + "\nTambien verifica la seleccion en la tabla..."
                    + "\nCompra que los identificadores no sean iguales...");
            System.out.println("Tipo de error:  " + e);
        }
    }

    public void eliminarCliente() {

        int fila = jTablaClientes.getSelectedRow();

        String identidad = jTablaClientes.getValueAt(fila, 0).toString();

        int n = JOptionPane.showConfirmDialog(null, " ¿Desea eliminar registro? ", "Registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_NO_OPTION) {

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE id_cliente='" + identidad + "' ");

                ps.executeUpdate();
                mostrar("", "");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "error: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(this, "No se elimino cliente... ", "Cliente", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void limpiar() {

        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }

    public void modificarDatos() {

        try {

            String SQL = "UPDATE clientes SET id_cliente ='" + txtId.getText() + "',nombre ='" + txtNombre.getText()
                    + "',telefono ='" + txtTelefono.getText() + "',direccion ='" + txtDireccion.getText() + "' " + " WHERE id_cliente ='" + txtId.getText() + "' ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeLargeUpdate();

            mostrar("", "");
            JOptionPane.showMessageDialog(this, "Dato aztualizado...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoClientes = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Cliente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtCodigoClientes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtCodigoClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoClientesKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigoClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 340, -1));

        jTablaClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)));
        jTablaClientes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Telefono", "Direccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTablaClientes.setGridColor(new java.awt.Color(204, 204, 204));
        jTablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaClientes);
        if (jTablaClientes.getColumnModel().getColumnCount() > 0) {
            jTablaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTablaClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTablaClientes.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTablaClientes.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 600, 360));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 310, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Telefono");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 310, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("ID cliente");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardado.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 110, 40));

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 120, 40));

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DarBaja.png"))); // NOI18N
        btnEliminar.setText("Dar de baja");
        btnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 120, 40));
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 310, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Direccion");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 310, -1));

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 110, 20));

        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpio.png"))); // NOI18N
        btnLimpiar.setText("limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/derecha.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 40));

        jButton1.setText("Pdf");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 430, 400));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText(" Clientes");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Caracteristicas  de los clientes");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, -1, -1));

        Regresar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        Regresar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 153)));
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        jPanel3.add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 80));

        jLabel9.setText("por Nombre o Id");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        MenuAdmin M = new MenuAdmin();
        M.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_RegresarActionPerformed

    private void jTablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaClientesMouseClicked
        caracteristicasClientes();
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        txtId.setEnabled(false);
    }//GEN-LAST:event_jTablaClientesMouseClicked

    private void txtCodigoClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClientesKeyReleased
        mostrar(txtCodigoClientes.getText(), txtCodigoClientes.getText());
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_txtCodigoClientesKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarNuevosClientes();
        mostrar("", "");
        txtId.setEnabled(false);
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarCliente();
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificarDatos();
        limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        txtId.requestFocusInWindow();

        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      
    }//GEN-LAST:event_jButton1MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaClientes;
    private javax.swing.JTextField txtCodigoClientes;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
