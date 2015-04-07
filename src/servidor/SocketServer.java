package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class SocketServer {
    
    //SocketServer socketServer;
    final ArrayList<Socket> clientes;
    
    public SocketServer(Gui_Servidor gui_Servidor){
        clientes = new ArrayList<>();
            try {
                ServerSocket servidor = new ServerSocket(3000, 65000);
                int i=0;
                do{
                    System.out.println("Esperando cliente");							
                    clientes.add(servidor.accept());
                    
                    Runnable nuevoSocket = new SocketServerHilo(clientes.get(clientes.size()-1), gui_Servidor,clientes);
                    Thread hiloSocket = new Thread(nuevoSocket);
                    hiloSocket.start();
                    System.out.println(clientes.size()+" tamano");
                    /*Runnable socket_escritura = new SocketServerHilo(clientes, gui_Servidor);
                    Thread hiloescritura = new Thread(socket_escritura);
                    hiloescritura.start();*/
                    
                    
                    System.out.println("Cliente recibido");
                    System.out.println(i++);
                }while(true);
            }
            catch (IOException excepcion) {			
                System.out.println(excepcion);
            }
        
    }
}