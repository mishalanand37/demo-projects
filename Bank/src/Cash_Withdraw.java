
import javax.swing.JOptionPane;
import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author misha
 */
public class Cash_Withdraw extends javax.swing.JFrame {

    /**
     * Creates new form Cash_Withdraw
     */
    public Cash_Withdraw() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        account_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        withdraw_field = new javax.swing.JTextField();
        withdraw_button = new javax.swing.JButton();
        cancel_field = new javax.swing.JButton();
        logout_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/sbi.jpg"))); // NOI18N

        jLabel2.setText("Account Number :");

        jLabel3.setText("Withdraw :");

        withdraw_button.setText("WITHDRAW");
        withdraw_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdraw_buttonActionPerformed(evt);
            }
        });

        cancel_field.setText("CANCEL");
        cancel_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_fieldActionPerformed(evt);
            }
        });

        logout_button.setText("LOGOUT");
        logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(account_field, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(withdraw_field)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(withdraw_button)
                            .addGap(33, 33, 33)
                            .addComponent(cancel_field)
                            .addGap(29, 29, 29)
                            .addComponent(logout_button))
                        .addComponent(jLabel1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(account_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(withdraw_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(withdraw_button)
                    .addComponent(cancel_field)
                    .addComponent(logout_button))
                .addGap(0, 139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_fieldActionPerformed
        // TODO add your handling code here:
        Create_Account ca=new Create_Account();
        ca.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cancel_fieldActionPerformed

    private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_buttonActionPerformed
        // TODO add your handling code here:
        Login l=new Login();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logout_buttonActionPerformed

    private void withdraw_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdraw_buttonActionPerformed
        // TODO add your handling code here:
       long ac,withdraw;
       ac=Long.parseLong(account_field.getText());
       withdraw=Long.parseLong(withdraw_field.getText());
       String sql="select * from account where Account_Number="+ac+";";
       
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","mishalggg");
           Statement st=con.createStatement();
         st.executeQuery(sql);
          ResultSet rs=st.executeQuery(sql);
          
          if(account_field.getText().isEmpty() || withdraw_field.getText().isEmpty()) {
              JOptionPane.showMessageDialog(this, "Error");
          }
          else if(rs.next()) {
               
          long c=Long.parseLong(rs.getString("Balance"));
          long de=c-withdraw;
           if(withdraw>500) {
           String sqlst="update account set Balance="+de+" where Account_Number="+ac+";";
           int rowsEffected=st.executeUpdate(sqlst);
           if(rowsEffected==0) {
               JOptionPane.showMessageDialog(this, "There s no valid Account");
               
           }
           else{
           JOptionPane.showMessageDialog(null, "Balance Updated Successfully");
           }
           }
           st.close();
           con.close();
            
                   }
           
       }
       
       catch(Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }//GEN-LAST:event_withdraw_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(Cash_Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cash_Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cash_Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cash_Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cash_Withdraw().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField account_field;
    private javax.swing.JButton cancel_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton logout_button;
    private javax.swing.JButton withdraw_button;
    private javax.swing.JTextField withdraw_field;
    // End of variables declaration//GEN-END:variables
}
