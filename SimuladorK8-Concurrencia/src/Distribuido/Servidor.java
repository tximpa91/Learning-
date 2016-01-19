/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuido;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class Servidor  extends Thread{
    private ServerSocket server;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output ;
    private JTextField cantidadDeEscaladores;
    private JTextField numeroDeConexiones;
    private boolean control;
    private Paso paso;

    public Servidor(JTextField cantidadDeEscaladores,Paso paso) {
        this.cantidadDeEscaladores = cantidadDeEscaladores;
        this.paso=paso;
        
        this.control=control;
        start();
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

   
   
    
    
    public void run (){
        
        int num=0;
        try {
            server= new ServerSocket(5000);
            System.out.println("Servidor Arrancando");
             ExecutorService pool = Executors.newFixedThreadPool(100);
        
        
        while (true){
            num++;
            paso.mirar();
            socket= server.accept();
            paso.mirar();
            pool.execute(new HandleRequest( cantidadDeEscaladores, socket,num ));
            
        }
        
        
        } catch (IOException K){
            System.out.println("Problema con el servidor");
        
        }
        
        
        
        
        
        
        
        
        
        
        
        
        }
        
                
    
    
    
    
    
    
    
    
    
    
    
    }
    
    
    
    
    
    
