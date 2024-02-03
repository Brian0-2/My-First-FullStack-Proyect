package puntodeventa;

import Conexion.conexionSQL;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MenuAdmin extends javax.swing.JFrame {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public MenuAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Administrador.setText("Brian Valdivia");
        //GraphicsDevice grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //grafica.setFullScreenWindow(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPFondo = new javax.swing.JPanel();
        Ventas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Administrador = new javax.swing.JLabel();
        BtnProductos = new javax.swing.JButton();
        btnVerVentas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventas.png"))); // NOI18N
        Ventas.setText("Hacer ventas");
        Ventas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 0, 0)));
        Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentasActionPerformed(evt);
            }
        });
        JPFondo.add(Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 260, 150));

        btnClientes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clientes.png"))); // NOI18N
        btnClientes.setText("Ver Clientes");
        btnClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnClientes.setMaximumSize(new java.awt.Dimension(259, 159));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        JPFondo.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 260, 150));

        Salir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)));
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        JPFondo.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, -1));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Administrador: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel1.add(Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 20));

        JPFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 90));

        BtnProductos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ropa.png"))); // NOI18N
        BtnProductos.setText("Ver Productos");
        BtnProductos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 0, 0)));
        BtnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProductosActionPerformed(evt);
            }
        });
        JPFondo.add(BtnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 260, 160));

        btnVerVentas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVerVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verVentas.png"))); // NOI18N
        btnVerVentas.setText("Ver Ventas");
        btnVerVentas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 0, 0)));
        btnVerVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentasActionPerformed(evt);
            }
        });
        JPFondo.add(btnVerVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 260, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/configuraciones.png"))); // NOI18N
        JPFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 630, 430));

        getContentPane().add(JPFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentasActionPerformed

        String contraseña = JOptionPane.showInputDialog(this, "Contraseña de usuario...");
        String nombre, id;

        String SQL = "SELECT * FROM usuarios WHERE password='" + contraseña + "'   ";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            if (rs.next()) {

                if (contraseña != null) {

                    nombre = rs.getString("nombre");
                    id = rs.getString("id_usuario");
                    contraseña = rs.getString("tipoUsuario");

                    switch (contraseña) {

                        case "Usuario":
                            Ventas V = new Ventas();
                            V.setVisible(true);
                            V.JLabelNombreUsuarioVentas.setText(nombre);
                            V.txtIdUsuarioVentas.setText(id);
                            this.dispose();
                            break;

                    }

                }

            } else {
                JOptionPane.showMessageDialog(this, "Error de usuario o en la contraseña", "Error de Login", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error  de login: -->" + e.getMessage());
        }


    }//GEN-LAST:event_VentasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        Clientes C = new Clientes();
        C.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void BtnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProductosActionPerformed
        Productos PS = new Productos();
        PS.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnProductosActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        int n = JOptionPane.showConfirmDialog(null, " ¿Desea Salir del sistema? ", "Ventas dice...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_NO_OPTION) {

            System.exit(0);

        } else {
            JOptionPane.showMessageDialog(this, "No... ");
        }
    }//GEN-LAST:event_SalirActionPerformed

    private void btnVerVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentasActionPerformed
        VerVentas Vv = new VerVentas();
        Vv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVerVentasActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Administrador;
    private javax.swing.JButton BtnProductos;
    private javax.swing.JPanel JPFondo;
    private javax.swing.JButton Salir;
    private javax.swing.JButton Ventas;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnVerVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
