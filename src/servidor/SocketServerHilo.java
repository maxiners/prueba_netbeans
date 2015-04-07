package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SocketServerHilo implements Runnable {
    final ArrayList<Socket> clientes;
    String recibido;
    OutputStream osalida;
    DataOutputStream dsalida;

    InputStream ientrada;
    DataInputStream dentrada;
    
    private final Gui_Servidor gui_Servidor;
	
    Socket socket;

    public SocketServerHilo(Socket lsocket,Gui_Servidor gui_Servidor1,ArrayList<Socket> clientes){
        this.gui_Servidor=gui_Servidor1;
        this.clientes=clientes;
        gui_Servidor.Aescribir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e){
                if(e.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
                    try {
                        dsalida.writeUTF(gui_Servidor.Aescribir.getText());
                        gui_Servidor.Achat.append("\n"+gui_Servidor.Aescribir.getText());
                        gui_Servidor.Aescribir.setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent e){
                if(e.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
                    gui_Servidor.Aescribir.setText("");
                }
            }
        });
            try{
                socket = lsocket;
                gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Se conectó!");
            }
            catch (Exception excepcion) {
		System.out.println(excepcion);
            }
    }
    public void escribir(Socket cliente,String msm){
        Thread escritura = new Thread(
            new Runnable() {
            @Override
            public void run() {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream());
                    dataOutputStream.writeUTF(msm);
                    dataOutputStream.flush();

                }catch (IOException ex) {
                    Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            }
        );
        escritura.start();
        
    }
    public void run() {
        Thread lectura = new Thread(
                new Runnable() {

            @Override
            public void run() {
                try {
                    ientrada = socket.getInputStream();
                    dentrada = new DataInputStream(ientrada);
                    
                    while(socket.isConnected()){
                        recibido = dentrada.readUTF();
                        if(recibido.equals("exit()")){
                            Thread hilo = new Thread(
                                new Runnable() {

                                @Override
                                public void run() {
                                    for(int i=0; i<clientes.size();i++){
                                        if(socket.equals(clientes.get(i))){
                                            clientes.remove(i);
                                        }
                                    }
                                }
                            });
                            hilo.start();
                           
                        }
                        else{
                            for(int i=0; i<clientes.size();i++){
                                escribir(clientes.get(i), recibido);
                            }
                        }
                        
                        
                        //System.out.println("recibido desde el cliente: " + recibido);
                        gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Escribió");
                        gui_Servidor.Achat.append("\n"+recibido);
                        
                    }
                    gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Desconectado!");
                } catch (IOException ex) {
                    Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                    gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Desconectado!");
                }
            }
        }
        );
        
	//
        lectura.start();
        /*try{
        dsalida.close();
        dentrada.close();
        socket.close();
        }
        catch (IOException excepcion) {
        System.out.println(excepcion);
        }*/			
    }
}