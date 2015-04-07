package servidor;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author morte
 */
public class Gui_Servidor extends JFrame{
    
    //Container container;
    JTextArea jTextArea, Achat, Aescribir;
    JScrollPane jScrollPane, Schat, Sescribir;
    JButton Benviar;
    JLabel Llog, Lchat, Lescribir;
    
    JPanel jPanel;
    
    GridBagConstraints constraints;
    
    public Gui_Servidor() throws InterruptedException{
        super("Servidor sockets");
        //container = new Container();
        //container.setLayout(new GridLayout(1, 1));
        jPanel = new JPanel(new GridBagLayout());
        
        jTextArea = new JTextArea("Iniciando");
        jTextArea.setEditable(false);
        Llog = new JLabel("Sucesos");
        
        Achat = new JTextArea();
        Achat.setEditable(false);
        Schat = new JScrollPane(Achat);
        Lchat = new JLabel("Chat");
        
        Aescribir = new JTextArea();
        Aescribir.setEditable(true);
        Sescribir = new JScrollPane(Aescribir);
        Lescribir = new JLabel("Escribir mensaje");
        
        Benviar = new JButton("Enviar");
        
        jScrollPane = new JScrollPane(jTextArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        constraints = new GridBagConstraints();
    }
       
    public void construir(){
        
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 0; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.
        constraints.weighty = 0.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.CENTER;
        
        jPanel.add(Lchat,constraints);
        
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 1; // El área de texto empieza en la fila uno
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 6; // El área de texto ocupa 2 filas.
        constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        jPanel.add(Schat, constraints);
        
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 7; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.
        constraints.weighty = 0.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.CENTER;
        
        jPanel.add(Lescribir,constraints);
        
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 8; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas
        constraints.weighty = 0.1; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        jPanel.add(Sescribir, constraints);
        
        constraints.gridx = 3; // El área de texto empieza en la columna cero.
        constraints.gridy = 0; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.
        constraints.weighty = 0.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.CENTER;
        
        jPanel.add(Llog,constraints);
        
        constraints.gridx = 3; // El área de texto empieza en la columna cero.
        constraints.gridy = 1; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 10; // El área de texto ocupa 2 filas.
        constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        
        jPanel.add(jScrollPane, constraints);
        
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 10; // El área de texto empieza en la fila cero
        constraints.gridwidth = 3; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.
        constraints.weighty = 0.0; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.CENTER;
        
        jPanel.add(Benviar, constraints);
        
        getContentPane().add(jPanel);
        //setBounds(100, 100, 600, 400);
        setSize(800, 900);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) throws IOException {    
		
        Thread thread = new Thread(
                new Runnable() {

            @Override
            public void run() {
                try {
                    Gui_Servidor gui_Servidor = new Gui_Servidor();
                    gui_Servidor.construir();
                    Thread.sleep(1000);
                    SocketServer socketServer = new SocketServer(gui_Servidor);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gui_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        thread.start();
    }
    
}
