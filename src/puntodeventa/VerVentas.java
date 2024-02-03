package puntodeventa;

import Conexion.conexionSQL;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerVentas extends javax.swing.JFrame {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public VerVentas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        txtIDVenta.setEnabled(false);
        txtFecha.setEnabled(false);
        txtIDCliente.setEnabled(false);
        txtIDUsuario.setEnabled(false);
        txtTotal.setEnabled(false);

        txtBuscarVentas.requestFocusInWindow();

        btnActualizar.setEnabled(false);

        ComboEstatus.addItem("Venta Completa");
        ComboEstatus.addItem("Venta Incompleta");

        mostrar("", "");
    }

    private void mostrar(String total, String id) {

        DefaultTableModel modelo = new DefaultTableModel();

        JTVentas.setModel(modelo);

        modelo.addColumn("ID_Venta");
        modelo.addColumn("Fecha de la venta");
        modelo.addColumn("ID Cliente");
        modelo.addColumn("ID Usuario");
        modelo.addColumn("Estatus de la Venta");
        modelo.addColumn("Total ventas");

        String SQL = "";
        try {
            String Clientes[] = new String[6];
            PreparedStatement ps;
            if (id.equals("") || total.equals("")) {
                SQL = "SELECT * FROM venta";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT * FROM venta WHERE total LIKE ?  OR id_venta = ?");
                ps.setString(1, "%" + txtBuscarVentas.getText() + "%");
                ps.setString(2, txtBuscarVentas.getText());

            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            int[] anchos = {55, 200, 85, 85, 170, 75};
            for (int i = 0; i < JTVentas.getColumnCount(); i++) {
                JTVentas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Clientes[0] = rs.getString(1);
                Clientes[1] = rs.getString(2);
                Clientes[2] = rs.getString(3);
                Clientes[3] = rs.getString(4);
                Clientes[4] = rs.getString(5);
                Clientes[5] = rs.getString(6);
                modelo.addRow(Clientes);
            }
            JTVentas.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    private void caracteristicasVentas() {

        int fila = JTVentas.getSelectedRow();

        if (fila >= 0) {
            txtIDVenta.setText(JTVentas.getValueAt(fila, 0).toString());
            txtFecha.setText(JTVentas.getValueAt(fila, 1).toString());
            txtIDCliente.setText(JTVentas.getValueAt(fila, 2).toString());
            txtIDUsuario.setText(JTVentas.getValueAt(fila, 3).toString());
            ComboEstatus.setSelectedItem(JTVentas.getValueAt(fila, 4).toString());
            txtTotal.setText(JTVentas.getValueAt(fila, 5).toString());

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    private void limpiar() {
        txtIDVenta.setText("");
        txtFecha.setText("");
        txtIDCliente.setText("");
        txtIDUsuario.setText("");
        ComboEstatus.setSelectedIndex(0);
        txtTotal.setText("");

    }

    public void actualizarDatos() {

        try {

            String SQL = "UPDATE venta SET status ='" + ComboEstatus.getSelectedItem().toString() + "' WHERE id_venta ='" + txtIDVenta.getText() + "' ";
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
        jPanel2 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIDVenta = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtIDCliente = new javax.swing.JTextField();
        txtIDUsuario = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ComboEstatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTVentas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscarVentas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        btnRegresar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 153)));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Ventas");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Caracteristicas de las ventas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 501, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(104, 104, 104))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(30, 30, 30))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 70));

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("ID_Venta");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 36, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("ID_Cliente");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 135, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("ID_Usuario");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Estatus de la venta");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 236, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Fecha de la venta");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 83, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Total");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 298, -1, -1));
        jPanel3.add(txtIDVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 35, 255, -1));
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 82, 255, -1));
        jPanel3.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 132, 255, -1));
        jPanel3.add(txtIDUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 179, 255, -1));
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 295, 137, -1));

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 44));

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpio.png"))); // NOI18N
        jButton4.setText("Limpiar");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 343, 104, 44));

        ComboEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        jPanel3.add(ComboEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 234, 134, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("Solo el Estatus");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 450, 410));

        JTVentas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)));
        JTVentas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        JTVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Venta", "Fecha de la venta", "ID_Cliente", "ID_Usuario", "Estatus de la venta", "Total de la venta"
            }
        ));
        JTVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTVentas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 660, 350));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText(" Buscar Venta");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtBuscarVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarVentasKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 260, -1));

        jLabel11.setText("por Total o Id");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVentasKeyReleased
        mostrar(txtBuscarVentas.getText(), txtBuscarVentas.getText());
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_txtBuscarVentasKeyReleased

    private void JTVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTVentasMouseClicked
        caracteristicasVentas();
        btnActualizar.setEnabled(true);


    }//GEN-LAST:event_JTVentasMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiar();
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        MenuAdmin m = new MenuAdmin();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarDatos();
        limpiar();
        btnActualizar.setEnabled(false);

    }//GEN-LAST:event_btnActualizarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboEstatus;
    private javax.swing.JTable JTVentas;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField txtBuscarVentas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIDUsuario;
    private javax.swing.JTextField txtIDVenta;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
