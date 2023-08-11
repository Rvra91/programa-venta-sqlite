package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Edison Zambrano
 */
public class Reportes {

    /* ********************************************************************
    * metodo para crear reportes de los clientes registrados en el sistema
    *********************************************************************** */
    public void ReportesClientes() {
        Document documento = new Document();
        try {
              String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);
        
        // Verificar si la carpeta existe
        if(!directorio.exists()) {
            directorio.mkdir();
        }
        
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEsteetica Nore\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select idCliente, concat(nombre, ' ', apellido) as nombres, cedula, telefono, direccion from tb_cliente");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    /* ********************************************************************
    * metodo para crear reportes de los productos registrados en el sistema
    *********************************************************************** */
   
    
    public void ReportesProductos() throws DocumentException, FileNotFoundException {
    Document documento = new Document();
   
          
          try{
        String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteProductos");
        ruta = ruta + "/ReporteProductos";
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String fechaActualStr = formatoFecha.format(fechaActual);

        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte_Productos" + fechaActualStr + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/" + nombreArchivo));

        //formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \n © Ramon Varela\n\n");
        parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Productos \n\n");

        documento.open();
        //agregamos los datos
        documento.add(parrafo);

        float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6, 4};

        PdfPTable tabla = new PdfPTable(columnsWidths);
        tabla.addCell("Codigo");
        tabla.addCell("Nombre");
        tabla.addCell("Cant.");
        tabla.addCell("Precio");
        tabla.addCell("Descripcion");
        tabla.addCell("Por. Iva");
        tabla.addCell("Categoria");
        tabla.addCell("Total Vendido");

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "SELECT p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIva, c.descripcion as categoria, SUM(dv.cantidad) as totalVendido "
                    + "FROM tb_producto p INNER JOIN tb_categoria c ON p.idCategoria = c.idCategoria "
                    + "LEFT JOIN tb_detalle_venta dv ON p.idProducto = dv.idProducto "
                    + "GROUP BY p.idProducto "
                    + "ORDER BY totalVendido DESC;");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                do {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                    tabla.addCell(rs.getString(6));
                    tabla.addCell(rs.getString(7));
                    tabla.addCell(rs.getString(8));
                } while (rs.next());
                documento.add(tabla);
            } else {
                Paragraph vacio = new Paragraph();
                vacio.add("No se encontraron productos registrados.");
                documento.add(vacio);
            }

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }
        documento.close();

        JOptionPane.showMessageDialog(null, "Reporte creado");
    }  catch (DocumentException e) {
        System.out.println("Error 1 en: " +e);
    }
    }
        /* ********************************************************************
    * metodo para crear reportes de los categorias registrados en el sistema
    *********************************************************************** */
   public void ReportesCategorias() {
  Document documento = new Document();
    try {
        String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteDeCategorias");
         ruta= ruta+"/ReporteDeCategorias";
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

         Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String fechaActualStr = formatoFecha.format(fechaActual);
        
        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte_Categorias" + fechaActualStr + ".pdf";
        
        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/" + nombreArchivo));
        
        

        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado  \n © Ramon Varela\n\n");
        parrafo.setFont(FontFactory.getFont("Roboto", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Categorias \n\n");

        documento.open();
      
        documento.add(parrafo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID");
        tabla.addCell("Categoria");
        tabla.addCell("Cantidad de Productos");
        tabla.addCell("Cantidad de Productos Vendidos");

        tabla.addCell("Total Generado");

        try {
            Connection cn = Conexion.conectar();
          PreparedStatement pst = cn.prepareStatement(
"SELECT c.idCategoria, c.descripcion, COUNT(p.idProducto) AS cantidad_productos, " +
"SUM(dv.subtotal) AS total_generado, SUM(dv.cantidad) AS cantidad_vendida " +
"FROM tb_categoria c " +
"INNER JOIN tb_producto p ON c.idCategoria = p.idCategoria " +
"INNER JOIN tb_detalle_venta dv ON p.idProducto = dv.idProducto " +
"INNER JOIN tb_cabecera_venta cv ON dv.idCabeceraVenta = cv.idCabeceraVenta " +
"GROUP BY c.idCategoria " +
"ORDER BY total_generado DESC"
);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                do {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(5)); // Nueva celda para la cantidad de productos vendidos

                    tabla.addCell(rs.getString(4));
                } while (rs.next());
                documento.add(tabla);
            } else {
                Paragraph noData = new Paragraph("No se encontraron resultados.");
                documento.add(noData);
            }

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }

        documento.close();

        JOptionPane.showMessageDialog(null, "Reporte creado");

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }

    }
    
        /* ********************************************************************
    * metodo para crear reportes de las ventas registrados en el sistema
    *********************************************************************** */public void ReportesVentas() {
        
   Document documento = new Document();
    try {
      String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteVentas");
         ruta= ruta+"/ReporteVentas";
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

        
      Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String fechaActualStr = formatoFecha.format(fechaActual);
        
        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte_Ventas" + fechaActualStr + ".pdf";
        
        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/" + nombreArchivo));
       
       
        //formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \n© Ramon Varela\n\n");
        parrafo.setFont(FontFactory.getFont("Roboto", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Ventas \n\n");

        documento.open();
        //agregamos los datos
        
        documento.add(parrafo);
        
       float[] columnsWidths = {3, 5, 5, 4, 4, 3}; // Agregar un ancho para la columna del nombre del producto
PdfPTable tabla = new PdfPTable(columnsWidths);

tabla.addCell("Codigo");
tabla.addCell("Nombre del producto"); 

tabla.addCell("Fecha Venta");
tabla.addCell("Cantidad vendida");
tabla.addCell("Tipo de Pago");
tabla.addCell("Tot. Pagar");

        double sumaTotal = 0;

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
              "SELECT c.idCabeceraVenta as id, c.valorPagar as total, c.fechaVenta as fecha, SUM(d.cantidad) as cantidad, c.tipoPago, p.nombre " +
"FROM tb_cabecera_venta c " +
"LEFT JOIN tb_detalle_venta d ON c.idCabeceraVenta = d.idCabeceraVenta " +
"LEFT JOIN tb_producto p ON d.idProducto = p.idProducto " +
"GROUP BY c.idCabeceraVenta, p.nombre;");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(6));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(2)); 

                sumaTotal += Double.parseDouble(rs.getString(2));
            }
            
            PdfPCell cell = new PdfPCell(new Phrase("Total: " + sumaTotal));
            cell.setColspan(6);
            tabla.addCell(cell);

            documento.add(tabla);
            
            rs.close();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }
        
        documento.close();
        
        JOptionPane.showMessageDialog(null, "Reporte creado");

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }
}




public void reporteVentasHoy() throws SQLException {
  // Obtener la fecha actual en formato yyyy/mm/dd
   // Obtener la fecha actual en formato yyyy/mm/dd
    try {
        Connection conn = Conexion.conectar();
        PreparedStatement pstDelete = conn.prepareStatement("DELETE FROM venta_hoy");
        pstDelete.executeUpdate();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al borrar datos de la tabla venta_hoy: " + e.getMessage());
    }
   
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    String fechaActualStr = dateFormat.format(date);

    try {
        Connection conn = Conexion.conectar();

        
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM venta_hoy WHERE fechaVenta = ?");
        pst.setString(1, fechaActualStr);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            // Actualizar registro existente
            int idCabeceraVenta = rs.getInt("idCabeceraVenta");
            // Aquí puedes poner el código para actualizar los valores de venta_hoy
            // con los valores de tb_cabecera_venta
        } else {
            // Insertar nuevo registro
            PreparedStatement pstInsert = conn.prepareStatement(
                    "INSERT INTO venta_hoy (idCabeceraVenta, valorPagar, fechaVenta, estado, tipoPago) "
                            + "SELECT idCabeceraVenta, valorPagar, fechaVenta, estado, tipoPago " + "FROM tb_cabecera_venta "
                            + "WHERE fechaVenta = ?");
            pstInsert.setString(1, fechaActualStr);
            pstInsert.executeUpdate();
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error en la consulta: " + e.getMessage());
    }

    Document documento = new Document();
    try {
        String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteVentas_Hoy");
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");

        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte_Ventas_Hoy_" + fechaActualStr.replaceAll("/", "") + "_"
                + formatoFecha.format(new Date()) + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/ReporteVentas_Hoy/" + nombreArchivo));
       
      
        //formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \n © Ramon Varela \n\n");
        parrafo.setFont(FontFactory.getFont("Roboto", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Ventas \n\n");

        documento.open();
        //agregamos los datos
      
        documento.add(parrafo);
        
       float[] columnsWidths = {3, 5, 5, 4, 4, 3}; // Agregar un ancho para la columna del nombre del producto
PdfPTable tabla = new PdfPTable(columnsWidths);

tabla.addCell("Codigo");
tabla.addCell("Nombre del producto"); 

tabla.addCell("Fecha Venta");
tabla.addCell("Cantidad vendida");
tabla.addCell("Tipo de Pago");
tabla.addCell("Tot. Pagar");

        double sumaTotal = 0;

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
              "SELECT c.idCabeceraVenta as id, c.valorPagar as total, c.fechaVenta as fecha, SUM(d.cantidad) as cantidad, c.tipoPago, p.nombre " +
"FROM venta_hoy c " +
"LEFT JOIN tb_detalle_venta d ON c.idCabeceraVenta = d.idCabeceraVenta " +
"LEFT JOIN tb_producto p ON d.idProducto = p.idProducto " +
"GROUP BY c.idCabeceraVenta, p.nombre;");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(6));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(2)); 

                sumaTotal += Double.parseDouble(rs.getString(2));
            }
            
            PdfPCell cell = new PdfPCell(new Phrase("Total: " + sumaTotal));
            cell.setColspan(6);
            tabla.addCell(cell);

            documento.add(tabla);
            
            rs.close();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }
        
        documento.close();
        
        JOptionPane.showMessageDialog(null, "Reporte creado");
 

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }
}public void reporteVentasMes() throws SQLException {
    // Obtener la fecha actual en formato yyyy/MM/dd
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String fechaActualStr = dateFormat.format(new Date());
    Calendar cal = Calendar.getInstance();
    String nombreMes = new SimpleDateFormat("MMMM").format(cal.getTime());
    
    // Obtener la fecha de inicio del mes
    Calendar calInicioMes = Calendar.getInstance();
    calInicioMes.set(Calendar.DAY_OF_MONTH, 1);
    String fechaInicioMes = dateFormat.format(calInicioMes.getTime());
    
    // Obtener la fecha de fin del mes
    Calendar calFinMes = Calendar.getInstance();

    calFinMes.set(Calendar.DAY_OF_MONTH, calFinMes.getActualMaximum(Calendar.DAY_OF_MONTH));
    String fechaFinMes = dateFormat.format(calFinMes.getTime());
                System.out.println(fechaFinMes);
    Document documento = new Document();
    try {
        String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteVentas_Mes");
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");

        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte De " + nombreMes + " " + fechaActualStr.replaceAll("/", "") + "_"
                + formatoFecha.format(new Date()) + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/ReporteVentas_Mes/" + nombreArchivo));

        // Formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \n © Ramon Varela \n\n");
        parrafo.setFont(FontFactory.getFont("Roboto", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Ventas De  \n\n" + nombreMes + " \n\n");

        documento.open();
        // Agregamos los datos
        documento.add(parrafo);

        float[] columnsWidths = {3, 5, 5, 4, 4, 3}; // Agregar un ancho para la columna del nombre del producto
        PdfPTable tabla = new PdfPTable(columnsWidths);

        tabla.addCell("Codigo");
        tabla.addCell("Nombre del producto");
        tabla.addCell("Fecha Venta");
        tabla.addCell("Cantidad vendida");
        tabla.addCell("Tipo de Pago");
        tabla.addCell("Tot. Pagar");

        double sumaTotal = 0;

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "SELECT c.idCabeceraVenta as id, c.valorPagar as total, c.fechaVenta as fecha, SUM(d.cantidad) as cantidad, c.tipoPago, p.nombre " +
                            "FROM venta_mes c " +
                            "LEFT JOIN tb_detalle_venta d ON c.idCabeceraVenta = d.idCabeceraVenta " +
                            "LEFT JOIN tb_producto p ON d.idProducto = p.idProducto " +
                            "WHERE c.fechaVenta >= ? AND c.fechaVenta <= ? " +
                            "GROUP BY c.idCabeceraVenta, p.nombre;");
            pst.setString(1, fechaInicioMes);
            pst.setString(2, fechaFinMes);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(6));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(2));

                sumaTotal += Double.parseDouble(rs.getString(2));
            }

            PdfPCell cell = new PdfPCell(new Phrase("Total: " + sumaTotal));
            cell.setColspan(6);
            tabla.addCell(cell);

            documento.add(tabla);

            rs.close();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }

        documento.close();

        JOptionPane.showMessageDialog(null, "Reporte creado");

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }
}


public void reporteAño() throws SQLException {
  // Obtener la fecha actual en formato yyyy/mm/dd
   // Obtener la fecha actual en formato yyyy/mm/dd
  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
String fechaActualStr = dateFormat.format(new Date());
Calendar cal = Calendar.getInstance();
String nombreMes = new SimpleDateFormat("MMMM").format(cal.getTime());

try {
    Connection conn = Conexion.conectar();
    PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM venta_año " +
            "WHERE strftime('%Y', fechaVenta) = strftime('%Y', ?");
      
    pst.setString(1, fechaActualStr);
    pst.setString(2, fechaActualStr);
    ResultSet rs = pst.executeQuery();
    // procesar resultados...
    conn.close();
} catch (SQLException e) {
    System.out.println("Error en la consulta: " + e.getMessage());
}

try {
    Connection conn = Conexion.conectar();
    PreparedStatement pstDelete = conn.prepareStatement("DELETE FROM venta_año");
    pstDelete.executeUpdate();
    conn.close();
} catch (SQLException e) {
    System.out.println("Error al borrar datos de la tabla venta_año: " + e.getMessage());
}
   
try {
    Connection conn = Conexion.conectar();
    PreparedStatement pst = conn.prepareStatement("SELECT * FROM venta_año WHERE fechaVenta = ?");
    pst.setString(1, fechaActualStr);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        // Actualizar registro existente
        int idCabeceraVenta = rs.getInt("idCabeceraVenta");
        // Aquí puedes poner el código para actualizar los valores de venta_hoy
        // con los valores de tb_cabecera_venta
    } else {
        // Insertar nuevo registro
        PreparedStatement pstInsert = conn.prepareStatement(
                "INSERT INTO venta_año (idCabeceraVenta, valorPagar, fechaVenta, estado, tipoPago) "
                        + "SELECT idCabeceraVenta, valorPagar, fechaVenta, estado, tipoPago " + "FROM tb_cabecera_venta "
                        + "WHERE fechaVenta = ?");
        pstInsert.setString(1, fechaActualStr);
        pstInsert.executeUpdate();
    }
    conn.close();
} catch (SQLException e) {
    System.out.println("Error en la consulta: " + e.getMessage());
}

    Document documento = new Document();
    try {
        String ruta = System.getProperty("user.home") + "/Desktop/Reporte";
        File directorio = new File(ruta);

        // Verificar si la carpeta existe
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Verificar si la carpeta "ReporteVentas" existe dentro de la carpeta "Reporte"
        File carpetaReporteVentas = new File(ruta + "/ReporteVentas_Mes");
        if (!carpetaReporteVentas.exists()) {
            carpetaReporteVentas.mkdir();
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy_HHmmss");

        // Crear el nombre del archivo con la fecha actual
        String nombreArchivo = "Reporte De " + nombreMes+ "  "+fechaActualStr.replaceAll("/", "") + "_"
                + formatoFecha.format(new Date()) + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/ReporteVentas_Mes/" + nombreArchivo));
       
      
        //formato al texto
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \n © Ramon Varela \n\n");
        parrafo.setFont(FontFactory.getFont("Roboto", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Ventas De  \n\n" + nombreMes + " \n\n");

        documento.open();
        //agregamos los datos
      
        documento.add(parrafo);
        
       float[] columnsWidths = {3, 5, 5, 4, 4, 3}; // Agregar un ancho para la columna del nombre del producto
PdfPTable tabla = new PdfPTable(columnsWidths);

tabla.addCell("Codigo");
tabla.addCell("Nombre del producto"); 

tabla.addCell("Fecha Venta");
tabla.addCell("Cantidad vendida");
tabla.addCell("Tipo de Pago");
tabla.addCell("Tot. Pagar");

        double sumaTotal = 0;

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
              "SELECT c.idCabeceraVenta as id, c.valorPagar as total, c.fechaVenta as fecha, SUM(d.cantidad) as cantidad, c.tipoPago, p.nombre " +
"FROM venta_año c " +
"LEFT JOIN tb_detalle_venta d ON c.idCabeceraVenta = d.idCabeceraVenta " +
"LEFT JOIN tb_producto p ON d.idProducto = p.idProducto " +
"GROUP BY c.idCabeceraVenta, p.nombre;");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(6));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(2)); 

                sumaTotal += Double.parseDouble(rs.getString(2));
            }
            
            PdfPCell cell = new PdfPCell(new Phrase("Total: " + sumaTotal));
            cell.setColspan(6);
            tabla.addCell(cell);

            documento.add(tabla);
            
            rs.close();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error 4 en: " + e);
        }
        
        documento.close();
        
        JOptionPane.showMessageDialog(null, "Reporte creado");
 

    } catch (DocumentException e) {
        System.out.println("Error 1 en: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error 2 en: " + ex);
    } catch (IOException ex) {
        System.out.println("Error 3 en: " + ex);
    }

}
} 
 

