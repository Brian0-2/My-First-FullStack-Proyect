package puntodeventa;

import Conexion.conexionSQL;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.SQLException;
//import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Productos extends javax.swing.JFrame {
//CONEXION

    int filas;
    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    DefaultTableModel modelo = new DefaultTableModel();

    public Productos() {
        initComponents();
        //this.setExtendedState(MAXIMIZED_BOTH);
        //GraphicsDevice grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //grafica.setFullScreenWindow(this);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        mostrar("", "");
        BtnEliminar.setEnabled(false);
        txtId.setEnabled(false);
        BtnGuardar.setEnabled(false);
        BtnActualizar.setEnabled(false);
        txtCodigoProducto.requestFocusInWindow();

        ComboTalla.addItem("Chica");
        ComboTalla.addItem("Mediana");
        ComboTalla.addItem("Grande");
        ComboTalla.addItem("ExGrande");
        ComboTalla.addItem("UniTalla");

        ComboMarca.addItem("Nike");
        ComboMarca.addItem("Adidas");
        ComboMarca.addItem("PUMA");
        ComboMarca.addItem("Carrera");
        ComboMarca.addItem("Indefinida");
    }
// TABLA

    public void mostrar(String nombre, String id) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Color");
        modelo.addColumn("Talla");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");

        JTablaProductos.setModel(modelo);

        String SQL = "";

        try {
            String Productos[] = new String[8];
            PreparedStatement ps;
            if (nombre.equals("") || id.equals("")) {
                SQL = "SELECT * FROM productos";
                ps = con.prepareStatement(SQL);

            } else {
                ps = con.prepareStatement("SELECT * FROM productos WHERE nombre LIKE ? OR id_producto = ?");
                ps.setString(1, "%" + txtCodigoProducto.getText() + "%");
                ps.setString(2, txtCodigoProducto.getText());
            }

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            //int columnas = rsmd.getColumnCount();

            int[] anchos = {50, 200, 200, 100, 150, 250, 200, 75};
            for (int i = 0; i < JTablaProductos.getColumnCount(); i++) {
                JTablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Productos[0] = rs.getString(1);
                Productos[1] = rs.getString(2);
                Productos[2] = rs.getString(3);
                Productos[3] = rs.getString(4);
                Productos[4] = rs.getString(5);
                Productos[5] = rs.getString(6);
                Productos[6] = rs.getString(7);
                Productos[7] = rs.getString(8);

                modelo.addRow(Productos);
            }
            JTablaProductos.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }

    }

    //CARACTERISTICAS DE PRODUCTOS
    public void caracteristicasProductos() {

        int fila = JTablaProductos.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(JTablaProductos.getValueAt(fila, 0).toString());
            txtNombre.setText(JTablaProductos.getValueAt(fila, 1).toString());
            txtCodigoBarras.setText(JTablaProductos.getValueAt(fila, 2).toString());
            txtCantidad.setText(JTablaProductos.getValueAt(fila, 3).toString());
            txtColor.setText(JTablaProductos.getValueAt(fila, 4).toString());
            ComboTalla.setSelectedItem(JTablaProductos.getValueAt(fila, 5).toString());
            ComboMarca.setSelectedItem(JTablaProductos.getValueAt(fila, 6).toString());
            txtPrecio.setText(JTablaProductos.getValueAt(fila, 7).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }

    }

    //GUARDAR
    public void guardarProductos() {

        try {

            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("INSERT INTO productos(nombre,codigo,cantidad,color,talla,marca,precio) VALUES(?,?,?,?,?,?,?)");

            if (ps != null) {

                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtCodigoBarras.getText());
                ps.setString(3, txtCantidad.getText());
                ps.setString(4, txtColor.getText());
                ps.setString(5, ComboTalla.getSelectedItem().toString());
                ps.setString(6, ComboMarca.getSelectedItem().toString());
                ps.setString(7, txtPrecio.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Ingreso Exitoso...");

                Object[] fila = new Object[7];

                fila[0] = txtNombre.getText();
                fila[1] = txtCodigoBarras.getText();
                fila[2] = txtCantidad.getText();
                fila[3] = txtColor.getText();
                fila[4] = ComboTalla.getSelectedItem().toString();
                fila[5] = ComboMarca.getSelectedItem().toString();
                fila[6] = txtPrecio.getText();

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

//ELIMINAR
    public void eliminarProducto() {

        int fila = JTablaProductos.getSelectedRow();

        String identidad = JTablaProductos.getValueAt(fila, 0).toString();

        int n = JOptionPane.showConfirmDialog(null, " ¿Desea eliminar registro? ", "Registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_NO_OPTION) {

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id_producto='" + identidad + "' ");

                ps.executeUpdate();
                mostrar("", "");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "error: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(this, "No se elimino producto... ");
        }

    }

    //ACTUALIZAR
    public void modificarDatos() {

        try {

            String SQL = "UPDATE productos SET id_producto ='" + txtId.getText() + "',nombre ='" + txtNombre.getText() + "',codigo ='" + txtCodigoBarras.getText()
                    + "',cantidad ='" + txtCantidad.getText() + "',color ='" + txtColor.getText() + "',talla ='" + ComboTalla.getSelectedItem().toString() + "',"
                    + "marca ='" + ComboMarca.getSelectedItem().toString() + "',precio ='" + txtPrecio.getText() + "' " + " WHERE id_producto ='" + txtId.getText() + "' ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeLargeUpdate();

            mostrar("", "");
            JOptionPane.showMessageDialog(this, "Dato aztualizado...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }

    }

//LIMPIAR
    public void limpiarCajas() {
        txtId.setText("");
        txtNombre.setText("");
        txtCodigoBarras.setText("");
        txtCantidad.setText("");
        txtColor.setText("");
        ComboTalla.setSelectedIndex(0);
        ComboMarca.setSelectedIndex(0);
        txtPrecio.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablaProductos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        JPFondoIngresarProductos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        BtnGuardar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        ComboTalla = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        ComboMarca = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        BntNuevoPorducto = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        BtnPDF = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTablaProductos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 0)));
        JTablaProductos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        JTablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Codigo", "Cantidad", "Color", "Talla", "Marca", "Precio"
            }
        ));
        JTablaProductos.setGridColor(new java.awt.Color(0, 0, 0));
        JTablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTablaProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 49, 681, 390));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Buscar producto ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 12, -1, -1));

        txtCodigoProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoProductoMouseClicked(evt);
            }
        });
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 11, 307, -1));

        jLabel14.setText("por Nombre o id");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 740, 450));

        JPFondoIngresarProductos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        JPFondoIngresarProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nombre del producto");
        JPFondoIngresarProductos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        JPFondoIngresarProductos.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 230, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Codigo de Barras");
        JPFondoIngresarProductos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Cantidad del producto ");
        JPFondoIngresarProductos.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));
        JPFondoIngresarProductos.add(txtCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 230, -1));
        JPFondoIngresarProductos.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 230, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Color del producto");
        JPFondoIngresarProductos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));
        JPFondoIngresarProductos.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 230, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Talla del producto");
        JPFondoIngresarProductos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Marca del producto");
        JPFondoIngresarProductos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Precio del producto");
        JPFondoIngresarProductos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));
        JPFondoIngresarProductos.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 230, -1));

        BtnGuardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardado.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 100, 50));

        BtnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        BtnActualizar.setText("Actualizar");
        BtnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(BtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 110, 50));

        BtnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(BtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 100, 50));

        ComboTalla.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ComboTalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        JPFondoIngresarProductos.add(ComboTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 130, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("ID del producto");
        JPFondoIngresarProductos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));
        JPFondoIngresarProductos.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 230, -1));

        ComboMarca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ComboMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        JPFondoIngresarProductos.add(ComboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 130, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("$");
        JPFondoIngresarProductos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 10, 20));

        Limpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpio.png"))); // NOI18N
        Limpiar.setText("Limpiar");
        Limpiar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 100, 50));

        BntNuevoPorducto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BntNuevoPorducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"))); // NOI18N
        BntNuevoPorducto.setText("Nuevo");
        BntNuevoPorducto.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        BntNuevoPorducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntNuevoPorductoActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(BntNuevoPorducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 20));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/derecha.png"))); // NOI18N
        JPFondoIngresarProductos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, 20));

        BtnPDF.setText("GenerarPDF");
        BtnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPDFActionPerformed(evt);
            }
        });
        JPFondoIngresarProductos.add(BtnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 90, -1));

        getContentPane().add(JPFondoIngresarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 500, 440));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Productos");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Caracteristicas de productos");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        BtnRegresar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izquierda.png"))); // NOI18N
        BtnRegresar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 153)));
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        MenuAdmin MA = new MenuAdmin();
        MA.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnRegresarActionPerformed

    private void JTablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablaProductosMouseClicked
        caracteristicasProductos();
        BtnActualizar.setEnabled(true);
        txtId.setEnabled(false);
        BtnEliminar.setEnabled(true);
        BtnGuardar.setEnabled(false);
    }//GEN-LAST:event_JTablaProductosMouseClicked

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        guardarProductos();
        mostrar("", "");
        BtnEliminar.setEnabled(false);
        BtnGuardar.setEnabled(false);
        limpiarCajas();
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        eliminarProducto();

    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        modificarDatos();
        limpiarCajas();
        BtnActualizar.setEnabled(false);
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarCajas();
        txtId.setEnabled(true);
        BtnActualizar.setEnabled(false);
        BtnEliminar.setEnabled(false);
    }//GEN-LAST:event_LimpiarActionPerformed

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
        mostrar(txtCodigoProducto.getText(), txtCodigoProducto.getText());
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    private void txtCodigoProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoProductoMouseClicked
        BtnEliminar.setEnabled(false);
    }//GEN-LAST:event_txtCodigoProductoMouseClicked

    private void BntNuevoPorductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntNuevoPorductoActionPerformed
        BtnGuardar.setEnabled(true);
        txtId.setEnabled(false);
        BtnActualizar.setEnabled(false);
        BtnEliminar.setEnabled(false);
        limpiarCajas();
    }//GEN-LAST:event_BntNuevoPorductoActionPerformed

    private void BtnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPDFActionPerformed
        
    }//GEN-LAST:event_BtnPDFActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BntNuevoPorducto;
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnPDF;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JComboBox<String> ComboMarca;
    private javax.swing.JComboBox<String> ComboTalla;
    private javax.swing.JPanel JPFondoIngresarProductos;
    private javax.swing.JTable JTablaProductos;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
