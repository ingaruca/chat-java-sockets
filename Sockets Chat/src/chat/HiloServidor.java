/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author ingarukadev
 */
public class HiloServidor extends Thread{
    
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Servidor server;
    private Socket Cliente;
    public static Vector<HiloServidor> usuarioActivo = new Vector();
    private String nombre;
    private ObjectOutputStream salidaObjeto;
    
    public HiloServidor(Socket socketcliente, String nombre, Servidor server) throws Exception {
        this.Cliente = socketcliente;
        this.server = server;
        this.nombre = nombre;
        usuarioActivo.add(this);
        
        for (int i = 0; i < usuarioActivo.size(); i++) {
            usuarioActivo.get(i).enviosMensaje(nombre + " se ha conectado.");
        }
    }
    
    public void run() {
        String mensaje = " ";
        
        while (true) {            
            try{
                entrada = new DataInputStream(Cliente.getInputStream());
                mensaje = entrada.readUTF();
                
                for (int i = 0; i < usuarioActivo.size(); i++) {
                    usuarioActivo.get(i).enviosMensaje(mensaje);
                    server.mensajeria("Mensaje enviado");
                }
            }catch(Exception e){
                break;
            }
        }
        
        usuarioActivo.removeElement(this);
        server.mensajeria("El usuario se ha desconectado.");
        
        try {
            Cliente.close();
        } catch (Exception e) {
        }
    }
    
    private void enviosMensaje(String msn) throws Exception {
        salida = new DataOutputStream(Cliente.getOutputStream());
        salida.writeUTF(msn);
        DefaultListModel modelo = new DefaultListModel();
        
        for (int i = 0; i < usuarioActivo.size(); i++) {
            modelo.addElement(usuarioActivo.get(i).nombre);
        }
        
        salidaObjeto = new ObjectOutputStream(Cliente.getOutputStream());
        salidaObjeto.writeObject(modelo);
    }
    
}
