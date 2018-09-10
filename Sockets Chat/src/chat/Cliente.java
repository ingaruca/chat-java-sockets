/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author ingarukadev
 */
public class Cliente extends javax.swing.JFrame {

    private Socket cliente;
    private final int PUERTOC = 8090;
    private String host = "localhost";
    private DataOutputStream salida;
    private String nombre;
    
    public Cliente() {
        initComponents();
        
        try {
            nombre = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
            super.setTitle(super.getTitle() + nombre);
            super.setVisible(true);
            
            cliente = new Socket(host, PUERTOC);
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(nombre);
            
            HiloCliente hilo = new HiloCliente(cliente, this);
            hilo.start();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    
    public void mensajeria(String msn) {
        this.txtChat.append(" " + msn + "\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUsuarios = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(550, 650));
        setMinimumSize(new java.awt.Dimension(550, 650));
        setPreferredSize(new java.awt.Dimension(600, 500));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de usuarios conectados"));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 23));

        jScrollPane1.setViewportView(listUsuarios);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 520));

        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane2.setViewportView(txtChat);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Escribir mensaje a enviar"));
        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 80));

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar))
                .addGap(11, 11, 11))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jSplitPane1.setRightComponent(jPanel1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try {
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(nombre + " : " + this.txtMensaje.getText());
            this.txtMensaje.setText(" ");
            this.txtMensaje.requestFocus();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    public void actualizarLista(DefaultListModel modelo) {
        this.listUsuarios.setModel(modelo);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
              
       try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            // start application
            new Cliente();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<String> listUsuarios;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
