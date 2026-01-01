/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import Jframe.DBConnection;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mihiranga.dev
 */
public class SalesPage extends javax.swing.JFrame {

    String product_name;
    int product_id, sales_id;
    DefaultTableModel quantity;

    public SalesPage() {
        initComponents();
        loadProducts();
    }

    private void loadProducts() {
        DefaultTableModel productModel = (DefaultTableModel) tbl_products.getModel();
        productModel.setRowCount(0);
        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM sales")) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("sales_id"),
                    rs.getString("customer_name"),
                    rs.getString("product_id"),
                    rs.getString("quantity"),
                    rs.getDouble("price")
                };
                productModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }

    public boolean addSale() {
        int sales_id = Integer.parseInt(txt_salesId.getText());
        String customer_name = txt_customerName.getText();
        int product_id = Integer.parseInt(txt_productId.getText());
        int quantity = Integer.parseInt(txt_quantity.getText());
        double price = Double.parseDouble(txt_price.getText());
        boolean isAdded = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO sales (sales_id, customer_name, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, sales_id);
            pst.setString(2, customer_name);
            pst.setInt(3, product_id);
            pst.setInt(4, quantity);
            pst.setDouble(5, price);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                String updateQuery = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
                PreparedStatement pst2 = con.prepareStatement(updateQuery);
            
                pst2.setInt(1, quantity);   
                pst2.setInt(2, product_id);
                
                int updateCount = pst2.executeUpdate();
                
                if (updateCount > 0) {
                isAdded = true;
                JOptionPane.showMessageDialog(this, "Sale Added and Stock Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Sale Added, but Error Updating Stock (Product ID not found)");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sale Addition Failed");
        }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }

        return isAdded;
    }

    public boolean updateSale() {
        int sales_id = Integer.parseInt(txt_salesId.getText());
        String customer_name = txt_customerName.getText();
        int product_id = Integer.parseInt(txt_productId.getText());
        int quantity = Integer.parseInt(txt_quantity.getText());
        double price = Double.parseDouble(txt_price.getText());
        boolean isUpdated = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE sales SET customer_name = ?, product_id = ?, quantity = ?, price = ? WHERE sales_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, customer_name);
            pst.setInt(2, product_id);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);
            pst.setInt(5, sales_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(this, "Sale Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Sale Update Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }

        return isUpdated;

    }

    public boolean deleteSale() {
        int sales_id = Integer.parseInt(txt_salesId.getText());
        boolean isDeleted = false;

        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM sales WHERE sales_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, sales_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(this, "Sale Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Sale Deletion Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }

        return isDeleted;

    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_products.getModel();
        model.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_salesId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_customerName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_productId = new javax.swing.JTextField();
        txt_quantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_products = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sales Management");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sale ID :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 90, 30));
        jPanel6.add(txt_salesId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 290, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Customer Name :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, 30));
        jPanel6.add(txt_customerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 290, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 100, 40));

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 100, 40));

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 110, 40));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Back.png"))); // NOI18N
        jLabel1.setText("Back");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 120, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Price :");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 110, 30));
        jPanel6.add(txt_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 290, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Id :");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));
        jPanel6.add(txt_productId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 290, 30));
        jPanel6.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 290, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity :");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 90, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 700));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));

        tbl_products.setBackground(new java.awt.Color(51, 51, 51));
        tbl_products.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_products.setForeground(new java.awt.Color(255, 255, 255));
        tbl_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sale ID", "Name", "Product ID", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_products);
        if (tbl_products.getColumnModel().getColumnCount() > 0) {
            tbl_products.getColumnModel().getColumn(0).setResizable(false);
            tbl_products.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbl_products.getColumnModel().getColumn(2).setResizable(false);
            tbl_products.getColumnModel().getColumn(3).setResizable(false);
            tbl_products.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 720, 670));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (deleteSale() == true) {
            JOptionPane.showMessageDialog(this, "Sale Deleted Successfully");
            clearTable();
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Sale Deletion Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addSale() == true) {
            JOptionPane.showMessageDialog(this, "Sale Added Successfully");
            clearTable();
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Sale Addition Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateSale() == true) {
            JOptionPane.showMessageDialog(this, "Sale Updated Successfully");
            clearTable();
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Sale Updatation Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tbl_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productsMouseClicked
        int rowNo = tbl_products.getSelectedRow();
        TableModel model = tbl_products.getModel();

        txt_salesId.setText(model.getValueAt(rowNo, 0).toString());
        txt_customerName.setText(model.getValueAt(rowNo, 1).toString());
        txt_productId.setText(model.getValueAt(rowNo, 2).toString());
        txt_quantity.setText(model.getValueAt(rowNo, 3).toString());
        txt_price.setText(model.getValueAt(rowNo, 4).toString());
    }//GEN-LAST:event_tbl_productsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_salesId.setText("");
        txt_customerName.setText("");
        txt_productId.setText("");
        txt_quantity.setText("");
        txt_price.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_products;
    private javax.swing.JTextField txt_customerName;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_productId;
    private javax.swing.JTextField txt_quantity;
    private javax.swing.JTextField txt_salesId;
    // End of variables declaration//GEN-END:variables
}
