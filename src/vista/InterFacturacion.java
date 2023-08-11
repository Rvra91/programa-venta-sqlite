package vista;

import conexion.Conexion;
import controlador.Ctrl_Producto;
import controlador.Ctrl_RegistrarVenta;
import controlador.VentaPDF;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;
import modelo.Producto;

public class InterFacturacion extends javax.swing.JInternalFrame {
   
int seleecion=0;
String tipoPago;
int estado;

    //Modelo de los datos
    private DefaultTableModel modeloDatosProductos;
    //lista para el detalle de venta de los productos
    ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    private DetalleVenta producto;

   // private int idCliente = 0;//id del cliente sleccionado
    private int probb=0;
private int prob=0;
    private int idProducto = 0;
    private String nombre = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;
    private int porcentajeIva = 0;
int precioN;
    private int cantidad = 0;//cantidad de productos a comprar
    private double subtotal = 0.0;//cantidad por precio
    private double descuento = 0.0;
    private double iva = 0.0;
    private double totalPagar = 0.0;

    //variables para calculos globales
    private double subtotalGeneral = 0.0;
    private double descuentoGeneral = 0.0;
    private double ivaGeneral = 0.0;
    private double totalPagarGeneral = 0.0;
    //fin de variables de calculos globales

    private int auxIdDetalle = 1;//id del detalle de venta

    public InterFacturacion() {



        initComponents();
        jButton_RegistrarVenta.setEnabled(false);
this.CargarComboProductos11();
        nuevo_precio.setEnabled(true);
if (estado>=2){
    
    
    nuevo_precio.setEnabled(true);

}
        this.setSize(new Dimension(800, 600));
        this.setTitle("Facturacion");
int seleecion=0;
        //Cargar lo Clientes en la vista - cargar productos
       // this.CargarComboClientes();
       this.CargarComboProductos();

        this.inicializarTablaProducto();

        txt_efectivo.setEnabled(false);
        jButton_calcular_cambio.setEnabled(true);

        txt_subtotal.setText("0.0");
        txt_iva.setText("0.0");
        txt_descuento.setText("0.0");
        txt_total_pagar.setText("0.0");

        //insertar imagen en nuestro JLabel
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(800, 600, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();
    }

    //metodo para inicializar la tabla de los productos
    private void inicializarTablaProducto() {
        modeloDatosProductos = new DefaultTableModel();

        modeloDatosProductos.addColumn("N");
        modeloDatosProductos.addColumn("Nombre");
        modeloDatosProductos.addColumn("Cantidad");
        modeloDatosProductos.addColumn("P. Unitario");
        modeloDatosProductos.addColumn("SubTotal");
        modeloDatosProductos.addColumn("Descuento");
        modeloDatosProductos.addColumn("Iva");
        modeloDatosProductos.addColumn("Total Pagar");
        modeloDatosProductos.addColumn("Accion");
        //agregar los datos del modelo a la tabla
        this.jTable_productos.setModel(modeloDatosProductos);
    }

    //metodo para presentar la informacion de la tavla DetalleVenta
    private void listaTablaProductos() {
        this.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            this.modeloDatosProductos.setValueAt(i + 1, i, 0);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubTotal(), i, 4);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getIva(), i, 6);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
            this.modeloDatosProductos.setValueAt("Eliminar", i, 8);//aqui luego poner un boton de eliminar
        }
        //añadir al Jtable
        jTable_productos.setModel(modeloDatosProductos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel_wallpaper = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_producto11 = new javax.swing.JComboBox<>();
        nuevo_precio = new javax.swing.JTextField();
        jButton_añadir_producto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        txt_descuento = new javax.swing.JTextField();
        txt_iva = new javax.swing.JTextField();
        txt_total_pagar = new javax.swing.JTextField();
        txt_cambio = new javax.swing.JTextField();
        jButton_calcular_cambio = new javax.swing.JButton();
        Efectivo = new javax.swing.JButton();
        Tarjeta = new javax.swing.JButton();
        Nequi1 = new javax.swing.JButton();
        txt_efectivo = new javax.swing.JTextField();
        jButton_RegistrarVenta = new javax.swing.JButton();
        txt_cantidad = new javax.swing.JTextField();
        añadir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_producto = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Producto:");

        jLabel11.setText("jLabel11");

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/97862f60b9b95da6a50631e626e89989.jpg"))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setBackground(new java.awt.Color(51, 51, 51));
        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cambiar Precio");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("PRODUCTO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cantidad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 80, -1));

        jComboBox_producto11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_producto11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto:", "Item 2", "Item 3", "Item 4" }));
        jComboBox_producto11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_producto11ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_producto11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 170, -1));

        nuevo_precio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(nuevo_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 100, -1));

        jButton_añadir_producto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton_añadir_producto.setText("Añadir Productos");
        jButton_añadir_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_añadir_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_añadir_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 150, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 760, 210));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Subtotal:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descuento:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("iva");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total a pagar:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Efectivo:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cambio:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txt_subtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_subtotal.setEnabled(false);
        jPanel2.add(txt_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 120, -1));

        txt_descuento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_descuento.setEnabled(false);
        jPanel2.add(txt_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 120, -1));

        txt_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_iva.setEnabled(false);
        jPanel2.add(txt_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));

        txt_total_pagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_pagar.setEnabled(false);
        jPanel2.add(txt_total_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 120, -1));

        txt_cambio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cambio.setEnabled(false);
        txt_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cambioActionPerformed(evt);
            }
        });
        jPanel2.add(txt_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 120, -1));

        jButton_calcular_cambio.setBackground(new java.awt.Color(51, 255, 255));
        jButton_calcular_cambio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton_calcular_cambio.setText("Calcular Cambio");
        jButton_calcular_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcular_cambioActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_calcular_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 130, 50));

        Efectivo.setText("Efectivo");
        Efectivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Efectivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EfectivoMouseClicked(evt);
            }
        });
        Efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EfectivoActionPerformed(evt);
            }
        });
        jPanel2.add(Efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        Tarjeta.setText("Tarjeta");
        Tarjeta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TarjetaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TarjetaMousePressed(evt);
            }
        });
        Tarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TarjetaActionPerformed(evt);
            }
        });
        jPanel2.add(Tarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 110, -1));

        Nequi1.setText("Transferencia");
        Nequi1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Nequi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Nequi1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Nequi1MousePressed(evt);
            }
        });
        Nequi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nequi1ActionPerformed(evt);
            }
        });
        jPanel2.add(Nequi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        txt_efectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_efectivoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 380, 210));

        jButton_RegistrarVenta.setBackground(new java.awt.Color(51, 255, 255));
        jButton_RegistrarVenta.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton_RegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/impresora.png"))); // NOI18N
        jButton_RegistrarVenta.setText("Registrar Venta");
        jButton_RegistrarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_RegistrarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_RegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 170, 100));

        txt_cantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 60, -1));

        añadir.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        añadir.setText("Cambiar Precio");
        añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirActionPerformed(evt);
            }
        });
        getContentPane().add(añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, -1));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Precio:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Facturación");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jComboBox_producto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto:", "Item 2", "Item 3", "Item 4" }));
        jComboBox_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 170, -1));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -20, 1010, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_añadir_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_añadir_productoActionPerformed

        String combo = this.jComboBox_producto11.getSelectedItem().toString();
             String productoSeleccionado = jComboBox_producto.getSelectedItem().toString();
        
        //validar que seleccione un producto
        if (combo.equalsIgnoreCase("Seleccione producto:")) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        } else {
            //validar que ingrese una cantidad
            if (!txt_cantidad.getText().isEmpty()) {
                //validar que el usuario no ingrese caracteres no numericos
                boolean validacion = validar(txt_cantidad.getText());
                if (validacion == true) {
                    //validar que la cantidad sea mayor a cero
                    if (Integer.parseInt(txt_cantidad.getText()) > 0) {
                        cantidad = Integer.parseInt(txt_cantidad.getText());
                        //ejecutar metodo para obtener datos del producto
                        this.DatosDelProducto();
                        //validar que la cantidad de productos seleccionado no sea mayor al stock de la base de datos
                        if (cantidad <= cantidadProductoBBDD) {

                            subtotal = precioUnitario * cantidad;
                            totalPagar = subtotal + iva + descuento;

                            //redondear decimales
                            subtotal = (double) Math.round(subtotal * 100) / 100;
                            iva = (double) Math.round(iva * 100) / 100;
                            descuento = (double) Math.round(descuento * 100) / 100;
                            totalPagar = (double) Math.round(totalPagar * 100) / 100;

                            //se crea un nuevo producto
                            producto = new DetalleVenta(auxIdDetalle,//idDetalleVenta
                                    1, //idCabecera
                                    idProducto,
                                    nombre,
                                    Integer.parseInt(txt_cantidad.getText()),
                                    precioUnitario,
                                    subtotal,
                                    descuento,
                                    iva,
                                    totalPagar,
                                    1//estado
                            );
                            //añadir a la lista
                            listaProductos.add(producto);
                            JOptionPane.showMessageDialog(null, "Producto Agregado");
                            auxIdDetalle++;
                         

                            txt_cantidad.setText("");//limpiar el campo
                            //volver a cargar combo productos
                            this.CargarComboProductos();
                            this.CalcularTotalPagar();
                            txt_efectivo.setEnabled(true);
          

                        } else {
                            JOptionPane.showMessageDialog(null, "La cantidad supera el Stock");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero (0), ni negativa");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numericos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa la cantidad de productos");
            }
        }
        //llamar al metodo
        this.listaTablaProductos();
    }//GEN-LAST:event_jButton_añadir_productoActionPerformed

    private void jButton_calcular_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcular_cambioActionPerformed
       
        if (!txt_efectivo.getText().isEmpty()) {
            //validamos que el usuario no ingrese otros caracteres no numericos 
            boolean validacion = validarDouble(txt_efectivo.getText());
            if (validacion == true) {
                //validar que el efectivo sea mayor a cero
                double efc = Double.parseDouble(txt_efectivo.getText().trim());
                double top = Double.parseDouble(txt_total_pagar.getText().trim());

                if (efc < top) {
                    JOptionPane.showMessageDialog(null, "El Dinero en efectivo no es suficiente");
                } else {
                    double cambio = (efc - top);
                    double cambi = (double) Math.round(cambio * 100d) / 100;
                    String camb = String.valueOf(cambi);
                    txt_cambio.setText(camb);
                    probb=1;
        if (prob==1){
           
                   jButton_RegistrarVenta.setEnabled(true);

       }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No de admiten caracteres no numericos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");
        }
    }//GEN-LAST:event_jButton_calcular_cambioActionPerformed
    int idArrayList = 0;
    private void jTable_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_productosMouseClicked
        int fila_point = jTable_productos.rowAtPoint(evt.getPoint());
        int columna_point = 0;
        if (fila_point > -1) {
            idArrayList = (int) modeloDatosProductos.getValueAt(fila_point, columna_point);
        }
        int opcion = JOptionPane.showConfirmDialog(null, "¿Eliminar Producto?");
        //opciones de confir dialog - (si = 0; no = 1; cancel = 2; close = -1)
        switch (opcion) {
            case 0: //presione si
                listaProductos.remove(idArrayList - 1);
                this.CalcularTotalPagar();
                this.listaTablaProductos();
                break;
            case 1: //presione no
                break;
            default://sea que presione cancel (2) o close (-1)
                break;
        }
    }//GEN-LAST:event_jTable_productosMouseClicked

    private void jButton_RegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarVentaActionPerformed


     CabeceraVenta cabeceraVenta = new CabeceraVenta();
    DetalleVenta detalleVenta = new DetalleVenta();
    Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();

    String fechaActual = "";
    Date date = new Date();
    fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

    if (listaProductos.size() > 0) {

        // Obtener el tipo de pago según el botón presionado
      

        // Registrar cabecera de venta
        cabeceraVenta.setIdCabeceraventa(0);
        cabeceraVenta.setValorPagar(Double.parseDouble(txt_total_pagar.getText()));
        cabeceraVenta.setFechaVenta(fechaActual);
        cabeceraVenta.setTipoPago(tipoPago); // Agregar tipo de pago
        cabeceraVenta.setEstado(1);

        if (controlVenta.guardar(cabeceraVenta)) {
            JOptionPane.showMessageDialog(null, "¡Venta Registrada!");

            //Generar la factura de venta
           // VentaPDF pdf = new VentaPDF();
           // pdf.generarFacturaPDF();

            //guardar detalle
            for (DetalleVenta elemento : listaProductos) {
                detalleVenta.setIdDetalleVenta(0);
                detalleVenta.setIdCabeceraVenta(0);
                detalleVenta.setIdProducto(elemento.getIdProducto());
                detalleVenta.setCantidad(elemento.getCantidad());
                detalleVenta.setPrecioUnitario(elemento.getPrecioUnitario());
                detalleVenta.setSubTotal(elemento.getSubTotal());
                detalleVenta.setDescuento(elemento.getDescuento());
                detalleVenta.setIva(elemento.getIva());
                detalleVenta.setTotalPagar(elemento.getTotalPagar());
                detalleVenta.setEstado(1);

                if (controlVenta.guardarDetalle(detalleVenta)) {
                    txt_subtotal.setText("0.0");
                    txt_iva.setText("0.0");
                    txt_descuento.setText("0.0");
                    txt_total_pagar.setText("0.0");
                    txt_efectivo.setText("");
                    txt_cambio.setText("0.0");
                    auxIdDetalle = 1;

                    this.RestarStockProductos(elemento.getIdProducto(), elemento.getCantidad());

                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al guardar detalle de venta!");
                }
            }

            // Vaciar la lista de productos
            listaProductos.clear();
            listaTablaProductos();

        } else {
            JOptionPane.showMessageDialog(null, "¡Error al guardar cabecera de venta!");
        }

    } else {
        JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
    }



    }//GEN-LAST:event_jButton_RegistrarVentaActionPerformed

    private void jComboBox_producto11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_producto11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_producto11ActionPerformed

    private void EfectivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EfectivoMouseClicked
        prob=1;
      seleecion = 1; // guardar la selección del usuario en la variable
    Efectivo.setBackground(Color.GREEN); // cambiar el color de fondo del botón
    Tarjeta.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
   Nequi1.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
        tipoPago="Efectivo";
if (probb==1){
    
            jButton_RegistrarVenta.setEnabled(true);

}

        
    }//GEN-LAST:event_EfectivoMouseClicked

    private void TarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TarjetaActionPerformed

    private void EfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EfectivoActionPerformed

    private void TarjetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TarjetaMouseClicked
        prob=1;
     
    seleecion = 2; // guardar la selección del usuario en la variable
    Tarjeta.setBackground(Color.GREEN); // cambiar el color de fondo del botón
    Efectivo.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
     Nequi1.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
tipoPago="Tarjeta";
if (probb==1){
    
            jButton_RegistrarVenta.setEnabled(true);

}

    }//GEN-LAST:event_TarjetaMouseClicked

    private void TarjetaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TarjetaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TarjetaMousePressed

    private void Nequi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Nequi1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Nequi1MouseClicked

    private void Nequi1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Nequi1MousePressed
      prob=1;
     
    seleecion = 2; // guardar la selección del usuario en la variable
    Nequi1.setBackground(Color.GREEN); // cambiar el color de fondo del botón
    Efectivo.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
     Tarjeta.setBackground(UIManager.getColor("control")); // volver al color original del otro botón
tipoPago="Transferencia";
if (probb==1){
    
            jButton_RegistrarVenta.setEnabled(true);

}    }//GEN-LAST:event_Nequi1MousePressed

    private void Nequi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nequi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nequi1ActionPerformed

    private void txt_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_efectivoActionPerformed

    private void añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirActionPerformed

    
       String nombreProducto = jComboBox_producto.getSelectedItem().toString();
    String precioNuevoString = nuevo_precio.getText().trim();
    int precioNuevo = 0;
    try {
        precioNuevo = Integer.parseInt(precioNuevoString);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El precio debe ser un número entero.");
        return;
    }

    Ctrl_Producto ctrlProducto = new Ctrl_Producto();
    Producto producto = new Producto();

    if (ctrlProducto.existeProducto(nombreProducto)) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("select * from tb_producto where nombre = ?");
            consulta.setString(1, nombreProducto);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPorcentajeIva(rs.getInt("porcentajeIva"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setEstado(rs.getInt("estado"));

                producto.setPrecio(precioNuevo);
  cn.close();
                if (ctrlProducto.actualizar(producto, producto.getIdProducto())) {
                    JOptionPane.showMessageDialog(null, "El precio del producto ha sido actualizado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el precio del producto.");
                }
            }

            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener el producto: " + e);
            JOptionPane.showMessageDialog(null, "Error al obtener el producto.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "El producto seleccionado no existe en la base de datos.");
    }



        
    }//GEN-LAST:event_añadirActionPerformed

    private void txt_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cambioActionPerformed

    private void jComboBox_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_productoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Efectivo;
    private javax.swing.JButton Nequi1;
    private javax.swing.JButton Tarjeta;
    private javax.swing.JButton añadir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton_RegistrarVenta;
    private javax.swing.JButton jButton_añadir_producto;
    private javax.swing.JButton jButton_calcular_cambio;
    private javax.swing.JComboBox<String> jComboBox_producto;
    private javax.swing.JComboBox<String> jComboBox_producto11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable_productos;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nuevo_precio;
    private javax.swing.JTextField txt_cambio;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descuento;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_subtotal;
    public static javax.swing.JTextField txt_total_pagar;
    // End of variables declaration//GEN-END:variables


    /*
    Metodo para cargar los clientes en el jComboBox
     */
   /* private void CargarComboClientes() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_cliente";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_cliente.removeAllItems();
            jComboBox_cliente.addItem("Seleccione cliente:");
            while (rs.next()) {
                jComboBox_cliente.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar clientes, !" + e);
        }
    }*/

    /*
    Metodo para cargar los productos en el jComboBox
     */
       private void CargarComboProductos11() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_producto.removeAllItems();
            jComboBox_producto.addItem("Seleccione producto:");
            while (rs.next()) {
                jComboBox_producto.addItem(rs.getString("nombre"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar productos, !" + e);
        }
    }

    private void CargarComboProductos() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_producto11.removeAllItems();
            jComboBox_producto11.addItem("Seleccione producto:");
            while (rs.next()) {
                jComboBox_producto11.addItem(rs.getString("nombre"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar productos, !" + e);
        }
    }

    /*
        Metodo para validar que el usuario no ingrese caracteres no numericos
     */
    private boolean validar(String valor) {
        try {
            int num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
        Metodo para validar que el usuario no ingrese caracteres no numericos
     */
    private boolean validarDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
        Metodo para mostrar los datos del producto seleccionado
     */
    private void DatosDelProducto() {
        try {
            String sql = "select * from tb_producto where nombre = '" + this.jComboBox_producto11.getSelectedItem() + "'";
            Connection cn = Conexion.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                cantidadProductoBBDD = rs.getInt("cantidad");
                precioUnitario = rs.getDouble("precio");
                porcentajeIva = rs.getInt("porcentajeIva");
                this.CalcularIva(precioUnitario, porcentajeIva);//calcula y retorna el iva
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del producto, " + e);
        }
    }

    /*
        Metodo para calcular iva
     */
    private double CalcularIva(double precio, int porcentajeIva) {
        int p_iva = porcentajeIva;

        switch (p_iva) {
            case 0:
                iva = 0.0;
                break;
            case 12:
                iva = (precio * cantidad) * 0.12;
                break;
            case 14:
                iva = (precio * cantidad) * 0.14;
                break;
            default:
                break;
        }

        return iva;
    }

    /*
    Metodo para calcular el total a pagar de todos los productos agregados
     */
    private void CalcularTotalPagar() {
        subtotalGeneral = 0;
        descuentoGeneral = 0;
        ivaGeneral = 0;
        totalPagarGeneral = 0;

        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubTotal();
            descuentoGeneral += elemento.getDescuento();
            ivaGeneral += elemento.getIva();
            totalPagarGeneral += elemento.getTotalPagar();
        }
        //redondear decimales
        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        ivaGeneral = (double) Math.round(ivaGeneral * 100) / 100;
        descuentoGeneral = (double) Math.round(descuentoGeneral * 100) / 100;
        totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

        //enviar datos a la vista
        txt_subtotal.setText(String.valueOf(subtotalGeneral));
        txt_iva.setText(String.valueOf(ivaGeneral));
        txt_descuento.setText(String.valueOf(descuentoGeneral));
        txt_total_pagar.setText(String.valueOf(totalPagarGeneral));
    }

    /*
    Metodo para obtener id del cliente
     */
 /*   private void ObtenerIdCliente() {
        try {
            String sql = "select * from tb_cliente where concat(nombre,' ',apellido) = '" + this.jComboBox_cliente.getSelectedItem() + "'";
            Connection cn = Conexion.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idCliente = rs.getInt("idCliente");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener id del cliente, " + e);
        }
    }*/

    //metodo para restar la cantidad (stock) de los productos vendidos
    private void RestarStockProductos(int idProducto, int cantidad) {
        int cantidadProductosBaseDeDatos = 0;
        try {
            Connection cn = Conexion.conectar();
            String sql = "select idProducto, cantidad from tb_producto where idProducto = '" + idProducto + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cantidadProductosBaseDeDatos = rs.getInt("cantidad");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 1, " + e);
        }

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto = '" + idProducto + "'");
            int cantidadNueva = cantidadProductosBaseDeDatos - cantidad;
            consulta.setInt(1, cantidadNueva);
            if(consulta.executeUpdate() > 0){
                //System.out.println("Todo bien");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 2, " + e);
        }
    }
    
      public void habilitar_boton() {
        jButton_RegistrarVenta.setEnabled(true);
        jButton_RegistrarVenta.setOpaque(false);
        jButton_RegistrarVenta.setBackground(null);
        jButton_RegistrarVenta.setEnabled(false);
        jButton_RegistrarVenta.setOpaque(true);
        jButton_RegistrarVenta.setBackground(Color.LIGHT_GRAY);
        
        
        
    }
      
      public void precio_variable(){
          Producto Producto = new Producto();
          
          estado = Producto.getEstado();
      }

}
