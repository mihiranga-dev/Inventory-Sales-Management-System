/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jframe;

/**
 *
 * @author mihiranga.dev
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class ProductReportGenerator {
    public void generateproductReport() {
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");

        // IMPORTANT: We use the .jrxml file (the raw code), NOT the .jasper file
        String reportPath = "src/Report/product_report.jrxml"; 
        
        HashMap<String, Object> parameters = new HashMap<>();

        // 1. Load and Compile the report code manually
        // This fixes the "Error loading object" (Version Mismatch)
        JasperReport jr = JasperCompileManager.compileReport(reportPath);

        // 2. Fill the report with data
        // If this fails, it means the "Aharoni" font is still in the .jrxml file
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parameters, con);

        // 3. Show the report
        JasperViewer.viewReport(jasperPrint, false);

        con.close();
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
}
