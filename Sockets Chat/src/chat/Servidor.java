/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author ingarukadev
 */
public class Servidor extends javax.swing.JFrame {
    
    private ServerSocket server;
    private final int PUERTOH = 8090;
   
    public Servidor() {
        initComponents();
        
        try {
            server = new ServerSocket(PUERTOH);
            mensajeria("*.: SERVIDOR CON CONEXION :.* \n");
            super.setVisible(true);
            
            while (true) {                
                Socket cliente = server.accept();
                mensajeria("Cliente conectado desde la dirección: " + cliente.getInetAddress().getHostAddress());
                
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                
                HiloServidor hilo = new HiloServidor(cliente, entrada.readUTF(), this);
                hilo.start();
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }              
        
    }

    public void mensajeria(String msn) {
        this.jTextArea1.append(" " + msn + "\n");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(".:: SERVIDOR ::.");
        setMinimumSize(new java.awt.Dimension(400, 550));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(300, 480));
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            // start application
            new Servidor();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
