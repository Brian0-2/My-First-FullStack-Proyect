package puntodeventa;

import Conexion.conexionSQL;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ventas extends javax.swing.JFrame {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    int fila = 0;
    double total = 0.00;
    boolean status = false;

    public Ventas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //this.setExtendedState(MAXIMIZED_BOTH);
        txtCodigo.requestFocus();
        btnEliminar.setEnabled(false);
        txtTotal.setEnabled(false);
        txtIDcliente.setEnabled(false);
        txtNombre.setEnabled(false);

        ComboEstauts.addItem("Venta a credito");
        mostrarClientes("", "");

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                   TABLA VENTAS
    public void validarAdministrador() {

        String contraseña = JOptionPane.showInputDialog(this, "Contraseña");

        String SQL = "SELECT * FROM usuarios WHERE password='" + contraseña + "'   ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            if (rs.next()) {

                if (contraseña != null) {

                    contraseña = rs.getString("tipoUsuario");

                    switch (contraseña) {
                        case "Administrador":

                            MenuAdmin Am = new MenuAdmin();
                            Am.setVisible(true);
                            MenuAdmin.Administrador.setText("Brian");
                            this.dispose();
                            break;
                        case "usuario":
                            break;

                    }

                }
            } else {

                JOptionPane.showMessageDialog(this, "Error de usuario", "Error de Login", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTproductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Administrador = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        JLabelNombreUsuarioVentas = new javax.swing.JLabel();
        txtIdUsuarioVentas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ComboEstauts = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtBuscarClientes = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtIDcliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Codigo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 80, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Producto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        txtNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 180, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Cantidad");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, -1));

        txtCantidad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 90, -1));
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 0, -1));

        btnPagar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pago.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 480, 120, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("Total: $");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, 100, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Carrito.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 210, 220));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 0, -1));

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, 80, -1));

        txtTotal.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        txtTotal.setForeground(java.awt.Color.green);
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 180, 40));

        jTproductos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jTproductos.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jTproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTproductos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 570, 290));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Administrador.setFont(new java.awt.Font("Arial", 1, 8)); // NOI18N
        Administrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/admin.png"))); // NOI18N
        Administrador.setText("Admin");
        Administrador.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        Administrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministradorActionPerformed(evt);
            }
        });
        jPanel2.add(Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel6.setText("Usuario: ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));
        jPanel2.add(JLabelNombreUsuarioVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 80, 20));
        jPanel2.add(txtIdUsuarioVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 50, 0, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Ventas");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 80, 30));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 30, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 80));

        ComboEstauts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Venta Completa" }));
        jPanel1.add(ComboEstauts, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 150, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setText("Estatus de venta");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/derecha.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 30, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/abajo.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 550));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/billetes.png"))); // NOI18N
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 140, 120));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Clientes");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 80));

        JTClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        JTClientes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        JTClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID cliente", "Nombre", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTClientes);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 390, 160));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Buscar: ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtBuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyReleased(evt);
            }
        });
        jPanel3.add(txtBuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 240, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Nombre Cliente");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));
        jPanel3.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 180, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Id cliente");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));
        jPanel3.add(txtIDcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 100, -1));

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 60, 20));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/efectivos.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 140, 60));

        BtnSalir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 80, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("EasyAbonos");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 90, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/abajo.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 40, 40));

        jLabel19.setText("por Nombre o Id");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 400, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministradorActionPerformed
        validarAdministrador();

    }//GEN-LAST:event_AdministradorActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        int n = JOptionPane.showConfirmDialog(null, " ¿Desea Salir del sistema? ", "Ventas dice...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_NO_OPTION) {

            System.exit(0);

        } else {
            JOptionPane.showMessageDialog(this, "No... ");
        }

    }//GEN-LAST:event_BtnSalirActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtCodigo.getText())) {

                try {

                    PreparedStatement ps = con.prepareStatement("SELECT id_producto,nombre,precio FROM productos WHERE id_producto=? ");
                    ps.setString(1, txtCodigo.getText());

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        txtId.setText(rs.getString("id_producto"));
                        txtNombre.setText(rs.getString("nombre"));
                        txtPrecio.setText(rs.getString("precio"));
                        txtCantidad.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro producto");
                        txtCodigo.setText("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
                }

            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        String valor = String.valueOf(txtCantidad.getText());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtCantidad.getText())) {
                if (valor.matches("[0-9]*")) {

                    try {
                        double precio = Double.parseDouble(txtPrecio.getText());
                        int cantidad = Integer.parseInt(txtCantidad.getText());
                        int existencias = 0;

                        PreparedStatement ps = con.prepareStatement("SELECT cantidad FROM productos WHERE id_producto=? ");
                        ps.setString(1, txtCodigo.getText());

                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            existencias = rs.getInt("cantidad");

                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro producto");
                            return;
                        }
                        if (existencias >= cantidad) {

                            DefaultTableModel tabla = (DefaultTableModel) jTproductos.getModel();
                            double importe = precio * cantidad;

                            tabla.addRow(new Object[]{txtCodigo.getText(), txtNombre.getText(), txtCantidad.getText(), importe});

                            fila++;

                            limpiarcajas();
                            actualizarPago();

                            txtCodigo.requestFocusInWindow();

                        } else {
                            JOptionPane.showMessageDialog(null, "No hay existencias suficientes...");

                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Dato Invalido");
                    txtCantidad.setText("");

                }

            }

        }

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void limpiarcajas() {
        txtCodigo.setText("");
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtTotal.setText("");

    }

    private void eliminar() {
        int n = JOptionPane.showConfirmDialog(null, " ¿Desea eliminar producto? ", "Venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_NO_OPTION) {
            DefaultTableModel modelo2 = (DefaultTableModel) jTproductos.getModel();
            modelo2.removeRow(jTproductos.getSelectedRow());
            fila--;
            actualizarPago();
            txtCodigo.requestFocusInWindow();
            btnEliminar.setEnabled(false);
        }
    }

    private void actualizarPago() {

        total = 0.00;

        int numeroFilas = jTproductos.getRowCount();

        for (int i = 0; i < numeroFilas; i++) {

            total = total + Double.parseDouble(String.valueOf(jTproductos.getModel().getValueAt(i, 3)));
        }
        txtTotal.setText(String.format("%.2f", total));
    }

    private void agregarProductos(int idVenta) {

        double precio;
        int idProducto, cantidad;
        String codigo;
        int filasTabla = jTproductos.getRowCount();

        for (int i = 0; i < filasTabla; i++) {
            codigo = (String) jTproductos.getValueAt(i, 0);
            try {
                PreparedStatement ps = con.prepareStatement("SELECT id_producto,precio FROM productos WHERE id_producto=? ");
                ps.setString(1, codigo);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    idProducto = Integer.parseInt(rs.getString("id_producto"));
                    precio = rs.getDouble("precio");

                    cantidad = Integer.parseInt(jTproductos.getValueAt(i, 2).toString());

                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO productos_ventas(id_ventaFK,id_productoFK,precio,cantidad) VALUES(?,?,?,?)");
                    ps2.setInt(1, idVenta);
                    ps2.setInt(2, idProducto);
                    ps2.setDouble(3, precio);
                    ps2.setInt(4, cantidad);

                    ps2.executeUpdate();

                    PreparedStatement ps3 = con.prepareStatement("UPDATE productos SET cantidad = (cantidad-?) WHERE id_producto=?");
                    ps3.setInt(1, cantidad);
                    ps3.setInt(2, idProducto);

                    ps3.executeUpdate();

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }

        }
    }

    private void pagar() {
        if (txtIDcliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecciona el tipo de cliente...");
            return;
        }
        if (fila == 0) {
            JOptionPane.showMessageDialog(null, "No hay articulos a la venta");

        } else {

            try {
                int idVenta = 0;

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO venta (fecha,clienteFK,usuariosFK,status,total) VALUES(NOW(),?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, txtIDcliente.getText());
                ps.setString(2, txtIdUsuarioVentas.getText());
                ps.setString(3, ComboEstauts.getSelectedItem().toString());
                ps.setDouble(4, total);

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                idVenta = rs.getInt(1);

                if (idVenta == 0) {
                    JOptionPane.showMessageDialog(null, "Error al guardar venta...");
                    return;
                }
                agregarProductos(idVenta);

                limpiarcajas();
                limpiarTabla();
                total = 0.00;
                txtTotal.setText("0.00");

                JOptionPane.showMessageDialog(null, "Venta Exitosa...");

                txtCodigo.requestFocusInWindow();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        }

    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTproductos.getModel();
        int filas = jTproductos.getRowCount();

        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    //            TABLA CLIENTES
    private void mostrarClientes(String nombre, String id) {

        DefaultTableModel modelo = new DefaultTableModel();

        JTClientes.setModel(modelo);

        modelo.addColumn("ID cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        
        txtNombreCliente.setText("ClienteG");
        txtIDcliente.setText("1");

        String SQL = "";
        try {
            String Clientes[] = new String[3];
            PreparedStatement ps;
            if (nombre.equals("") || id.equals("")) {
                SQL = "SELECT id_cliente,nombre,telefono FROM clientes";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT id_cliente,nombre,telefono FROM clientes WHERE nombre LIKE ? OR id_cliente = ?");
                ps.setString(1, "%" + txtBuscarClientes.getText() + "%");
                ps.setString(2, txtBuscarClientes.getText());
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            //int columnas = rsmd.getColumnCount();

            int[] anchos = {50, 200, 130};
            for (int i = 0; i < JTClientes.getColumnCount(); i++) {
                JTClientes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Clientes[0] = rs.getString(1);
                Clientes[1] = rs.getString(2);
                Clientes[2] = rs.getString(3);

                modelo.addRow(Clientes);
            }
            JTClientes.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    private void caracteristicasClientes() {

        int filaclientes = JTClientes.getSelectedRow();

        if (filaclientes >= 0) {
            txtIDcliente.setText(JTClientes.getValueAt(filaclientes, 0).toString());
            txtNombreCliente.setText(JTClientes.getValueAt(filaclientes, 1).toString());

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    private void limpiar() {
        txtNombreCliente.setText("");
        txtIDcliente.setText("");

    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        pagar();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void jTproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTproductosMouseClicked
        btnEliminar.setEnabled(true);
    }//GEN-LAST:event_jTproductosMouseClicked

    private void JTClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTClientesMouseClicked
        caracteristicasClientes();
    }//GEN-LAST:event_JTClientesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AbonosClientes abonos = new AbonosClientes();
        abonos.setVisible(true);
        AbonosClientes.labelNombreUsuarioAbonos.setText(JLabelNombreUsuarioVentas.getText());
        AbonosClientes.txIDUsuarioAbonos.setText(txtIdUsuarioVentas.getText());

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyReleased
         mostrarClientes(txtBuscarClientes.getText(), txtBuscarClientes.getText());
    }//GEN-LAST:event_txtBuscarClientesKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Administrador;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JComboBox<String> ComboEstauts;
    public static javax.swing.JLabel JLabelNombreUsuarioVentas;
    private javax.swing.JTable JTClientes;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTable jTproductos;
    private javax.swing.JTextField txtBuscarClientes;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtIDcliente;
    private javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtIdUsuarioVentas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
