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

public class AbonosClientes extends javax.swing.JFrame {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public AbonosClientes() {
        initComponents();
        txtCodigoAbonos.requestFocusInWindow();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        txtIdAbono.setEnabled(false);
        txtFechaAbono.setEnabled(false);
        txtNombreUsuario.setEnabled(false);
        txtTotalApagar.setEnabled(false);

        mostrarPrincipal("", "");
        mostrarClientes("", "");
        mostrarVentas("", "");

        btnActualizar.setEnabled(false);
    }

    public void mostrarPrincipal(String nombre, String total) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID abono");
        modelo.addColumn("Nombre cliente");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Fecha");
        modelo.addColumn("ID_Venta");
        modelo.addColumn("Nombre del Usuario");
        modelo.addColumn("Total de venta");

        JTAbonos.setModel(modelo);

        String SQL = "";

        try {
            String Abonos[] = new String[7];
            PreparedStatement ps;

            if (nombre.equals("") || total.equals("")) {
                SQL = "SELECT abonos.id_abonos, clientes.nombre AS cliente, abonos.cantidad, abonos.fecha, venta.id_venta, usuarios.nombre,venta.total "
                        + "FROM abonos "
                        + "INNER JOIN venta ON venta.id_venta = abonos.ventaFK "
                        + "INNER JOIN usuarios ON usuarios.id_usuario = abonos.id_usuariosFK "
                        + "INNER JOIN clientes ON clientes.id_cliente = venta.id_venta";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT abonos.id_abonos, clientes.nombre AS cliente, abonos.cantidad, abonos.fecha, venta.id_venta, usuarios.nombre,venta.total,clientes.id_cliente "
                        + "FROM abonos "
                        + "INNER JOIN venta ON venta.id_venta = abonos.ventaFK "
                        + "INNER JOIN usuarios ON usuarios.id_usuario = abonos.id_usuariosFK "
                        + "INNER JOIN clientes ON clientes.id_cliente = venta.id_venta "
                        + "WHERE clientes.nombre LIKE ? OR abonos.cantidad LIKE ?");
                ps.setString(1, "%" + txtCodigoAbonos.getText() + "%");
                ps.setString(2, "%" + txtCodigoAbonos.getText() + "%");

            }

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

            int[] anchos = {50, 200, 70, 100, 50, 120, 80};
            for (int i = 0; i < JTAbonos.getColumnCount(); i++) {
                JTAbonos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Abonos[0] = rs.getString(1);
                Abonos[1] = rs.getString(2);
                Abonos[2] = rs.getString(3);
                Abonos[3] = rs.getString(4);
                Abonos[4] = rs.getString(5);
                Abonos[5] = rs.getString(6);
                Abonos[6] = rs.getString(7);

                modelo.addRow(Abonos);
            }
            JTAbonos.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    public void caracteristicasAbonos() {

        int fila = JTAbonos.getSelectedRow();

        if (fila >= 0) {
            txtIdAbono.setText(JTAbonos.getValueAt(fila, 0).toString());
            txtCantidadAbono.setText(JTAbonos.getValueAt(fila, 2).toString());
            txtFechaAbono.setText(JTAbonos.getValueAt(fila, 3).toString());
            txtNombreUsuario.setText(JTAbonos.getValueAt(fila, 1).toString());
            txtTotalApagar.setText(JTAbonos.getValueAt(fila, 6).toString());

            PreparedStatement ps;

            //id del cliente...
            try {
                ps = con.prepareStatement("SELECT clientes.id_cliente "
                        + "FROM abonos "
                        + "INNER JOIN venta ON venta.id_venta = abonos.ventaFK "
                        + "INNER JOIN usuarios ON usuarios.id_usuario = abonos.id_usuariosFK "
                        + "INNER JOIN clientes ON clientes.id_cliente = venta.id_venta "
                        + "WHERE abonos.id_abonos = ?");
                ps.setString(1, JTAbonos.getValueAt(fila, 0).toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    txtIDCliente.setText(rs.getString("id_cliente"));
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbonosClientes.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    public void modificarDatos() {
        String valor = String.valueOf(txtCantidadAbono.getText());
        if (!valor.matches("[0-9.]*")) {
            JOptionPane.showMessageDialog(null, "Datos Invalidos...");
        } else {
            try {
                String sql1 = "UPDATE abonos SET   cantidad='" + txtCantidadAbono.getText() + "' "
                        + "WHERE id_abonos='" + txtIdAbono.getText() + "' ";

                PreparedStatement ps = con.prepareStatement(sql1);
                ps.executeLargeUpdate();

                mostrarPrincipal("", "");
                JOptionPane.showMessageDialog(this, "Dato aztualizado...");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void limpiarCajas() {

        txtCantidadAbono.setText("");
        txtFechaAbono.setText("");
        txtNombreUsuario.setText("");
        txtTotalApagar.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAbonos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCantidadAbono = new javax.swing.JTextField();
        txtFechaAbono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTotalApagar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdAbono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoAbonos = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegresarVentas = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        labelNombreUsuarioAbonos = new javax.swing.JLabel();
        txIDUsuarioAbonos = new javax.swing.JTextField();
        txtIDCliente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnAbonos = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTventas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        buscarVentas = new javax.swing.JTextField();
        idCliente = new javax.swing.JTextField();
        nombreCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        idVenta = new javax.swing.JTextField();
        fechaVenta = new javax.swing.JTextField();
        statusVenta = new javax.swing.JTextField();
        totalVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Abonos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTAbonos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        JTAbonos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        JTAbonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID abono", "Nombre cliente", "Cantidad", "Fecha del abono", "ID_Venta", "Nombre del Usuario", "Total a pagar"
            }
        ));
        JTAbonos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTAbonosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTAbonos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 710, 170));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Cantidad del abono");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Fecha del abono");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel2.add(txtCantidadAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 250, -1));
        jPanel2.add(txtFechaAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 250, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Nombre del usuario");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel2.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 250, -1));

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizarBD.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 110, 30));

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpio.png"))); // NOI18N
        jButton4.setText("Limpiar");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 90, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Total a pagar: $");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));
        jPanel2.add(txtTotalApagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 250, -1));

        jLabel11.setText("Solo (Cantidad) del  abono");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        jLabel12.setText("AA    MM    DD");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 100, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("ID del Abono");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        jPanel2.add(txtIdAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 450, 230));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Buscar Abonos de Clientes");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtCodigoAbonos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoAbonosKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigoAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 280, -1));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Abonos de clientes");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Caracteristicas de abonos");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        btnRegresarVentas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegresarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        btnRegresarVentas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 153)));
        btnRegresarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarVentasActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 30, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel15.setText("Usuario: ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel3.add(labelNombreUsuarioAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 60, 30));
        jPanel3.add(txIDUsuarioAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 0, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 60));
        jPanel1.add(txtIDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 0, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Buscar Cliente a abonar");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jScrollPane2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jtClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jtClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre Cliente", "Telefono"
            }
        ));
        jtClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtClientes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, 240));

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Cantidad Abonar");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        txtAbono.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jPanel4.add(txtAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel14.setText("$");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 20, 30));

        btnAbonos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnAbonos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/billetesA.png"))); // NOI18N
        btnAbonos.setText("Abonar");
        btnAbonos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonosActionPerformed(evt);
            }
        });
        jPanel4.add(btnAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 50));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 340, 210, 260));

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 270, -1));

        jScrollPane3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        JTventas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        JTventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Status", "Total de venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTventasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(JTventas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 480, 240));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Buscar Venta");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        buscarVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarVentasKeyReleased(evt);
            }
        });
        jPanel1.add(buscarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 260, -1));
        jPanel1.add(idCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 0, -1));
        jPanel1.add(nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 0, -1));
        jPanel1.add(telefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, 0, -1));
        jPanel1.add(idVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 0, -1));
        jPanel1.add(fechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 590, 0, -1));
        jPanel1.add(statusVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 0, -1));
        jPanel1.add(totalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 590, 0, -1));

        jLabel7.setText("Por Nombre o cantidad");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/abajo.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, 40, 40));

        jLabel19.setText("por Nombre o por id");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        jLabel20.setText("por Estatus o por id");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarVentasActionPerformed
        Ventas hacerVentas = new Ventas();
        hacerVentas.setVisible(true);

        Ventas.JLabelNombreUsuarioVentas.setText(labelNombreUsuarioAbonos.getText());
        Ventas.txtIdUsuarioVentas.setText(txIDUsuarioAbonos.getText());
        this.dispose();
    }//GEN-LAST:event_btnRegresarVentasActionPerformed

    private void JTAbonosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAbonosMouseClicked
        caracteristicasAbonos();
        btnActualizar.setEnabled(true);


    }//GEN-LAST:event_JTAbonosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificarDatos();
        limpiarCajas();
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarCajas();
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtCodigoAbonosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoAbonosKeyReleased
        mostrarPrincipal(txtCodigoAbonos.getText(), txtCodigoAbonos.getText());
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_txtCodigoAbonosKeyReleased

    private void btnAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonosActionPerformed

        String valor = String.valueOf(txtAbono.getText().trim());
        if (valor.matches("[0-9]*")) {

        } else {
            JOptionPane.showMessageDialog(null, "Dato Invalido");
            txtAbono.setText("");
            return;
        }
        if (txtAbono.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Ingrese un abono..");
            return;

        }
        if (idVenta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una venta");
            return;
        }
        if (idCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");

        } else {
            try {
                double cantidadAbono = Double.parseDouble(txtAbono.getText());
                double total = 0;
                PreparedStatement ps = con.prepareStatement("SELECT total,id_venta FROM venta WHERE id_venta='" + idVenta.getText() + "' ");

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    total = rs.getInt("total");

                    if (cantidadAbono >= total) {
                        JOptionPane.showMessageDialog(null, "Se cubre mas del total a abonar");

                    } else {
                        JOptionPane.showMessageDialog(null, "Abono Exitoso...");
                        hacerAbono();
                        txtAbono.setText("");
                        mostrarPrincipal("", "");
                    }

                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        }
    }//GEN-LAST:event_btnAbonosActionPerformed

    private void hacerAbono() {

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO abonos (cantidad,fecha,ventaFK,id_usuariosFK) VALUES(?,NOW(),?,?)");

            if (ps != null) {

                ps.setString(1, txtAbono.getText());
                ps.setString(2, idVenta.getText());
                ps.setString(3, txIDUsuarioAbonos.getText());

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }

    }

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        mostrarClientes(txtBuscarCliente.getText(), txtBuscarCliente.getText());
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void jtClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtClientesMouseClicked
        caracteristicasClientes();
    }//GEN-LAST:event_jtClientesMouseClicked

    private void buscarVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarVentasKeyReleased
        mostrarVentas(buscarVentas.getText(), buscarVentas.getText());
    }//GEN-LAST:event_buscarVentasKeyReleased

    private void JTventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTventasMouseClicked
        caracteristicasVentas();
    }//GEN-LAST:event_JTventasMouseClicked

    public void mostrarClientes(String nombre, String id) {

        DefaultTableModel modelo = new DefaultTableModel();

        jtClientes.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");

        String SQL = "";
        try {
            String Clientes[] = new String[8];
            PreparedStatement ps;
            if (nombre.equals("") || id.equals("")) {
                SQL = "SELECT id_cliente,nombre,telefono FROM clientes";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT id_cliente,nombre,telefono FROM clientes WHERE nombre LIKE ? OR id_cliente = ?");
                ps.setString(1, "%" + txtBuscarCliente.getText() + "%");
                ps.setString(2, txtBuscarCliente.getText());
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            int[] anchos = {50, 200, 130};
            for (int i = 0; i < jtClientes.getColumnCount(); i++) {
                jtClientes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Clientes[0] = rs.getString(1);
                Clientes[1] = rs.getString(2);
                Clientes[2] = rs.getString(3);

                modelo.addRow(Clientes);
            }
            jtClientes.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    public void caracteristicasClientes() {

        int fila = jtClientes.getSelectedRow();

        if (fila >= 0) {
            idCliente.setText(jtClientes.getValueAt(fila, 0).toString());
            nombreCliente.setText(jtClientes.getValueAt(fila, 1).toString());
            telefonoCliente.setText(jtClientes.getValueAt(fila, 2).toString());

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    private void mostrarVentas(String total, String id) {

        DefaultTableModel modelo = new DefaultTableModel();

        JTventas.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Fecha de la venta");
        modelo.addColumn("Estatus de la Venta");
        modelo.addColumn("Total venta");

        String SQL = "";
        try {
            String Clientes[] = new String[6];
            PreparedStatement ps;
            if (id.equals("") || total.equals("")) {
                SQL = "SELECT id_venta,fecha,status,total FROM venta";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT id_venta,fecha,status,total FROM venta WHERE status LIKE ?  OR id_venta = ?");
                ps.setString(1, "%" + buscarVentas.getText() + "%");
                ps.setString(2, buscarVentas.getText());

            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            int[] anchos = {55, 200, 150, 75};
            for (int i = 0; i < JTventas.getColumnCount(); i++) {
                JTventas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Clientes[0] = rs.getString(1);
                Clientes[1] = rs.getString(2);
                Clientes[2] = rs.getString(3);
                Clientes[3] = rs.getString(4);

                modelo.addRow(Clientes);
            }
            JTventas.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    private void caracteristicasVentas() {

        int fila = JTventas.getSelectedRow();

        if (fila >= 0) {
            idVenta.setText(JTventas.getValueAt(fila, 0).toString());
            fechaVenta.setText(JTventas.getValueAt(fila, 1).toString());
            statusVenta.setText(JTventas.getValueAt(fila, 2).toString());
            totalVenta.setText(JTventas.getValueAt(fila, 3).toString());

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AbonosClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTAbonos;
    private javax.swing.JTable JTventas;
    private javax.swing.JButton btnAbonos;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRegresarVentas;
    private javax.swing.JTextField buscarVentas;
    private javax.swing.JTextField fechaVenta;
    private javax.swing.JTextField idCliente;
    private javax.swing.JTextField idVenta;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtClientes;
    public static javax.swing.JLabel labelNombreUsuarioAbonos;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JTextField statusVenta;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JTextField totalVenta;
    public static javax.swing.JTextField txIDUsuarioAbonos;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtCantidadAbono;
    private javax.swing.JTextField txtCodigoAbonos;
    private javax.swing.JTextField txtFechaAbono;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtIdAbono;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtTotalApagar;
    // End of variables declaration//GEN-END:variables
}
