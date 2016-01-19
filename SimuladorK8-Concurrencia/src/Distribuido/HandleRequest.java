/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuido;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class HandleRequest implements Runnable{
    private JTextField numeroDeConexiones;
    private JTextField numeroDeEscaladores;
    private Socket socket;
    private DataInputStream input;
    private DataOutput output;
    private int numerodeConexiones;
    private int s=0;


    public HandleRequest( JTextField numeroDeEscaladores, Socket socket,int numero) throws IOException {
        
        this.numeroDeEscaladores = numeroDeEscaladores;
        this.socket = socket;
        this.input = new DataInputStream(this.socket.getInputStream());
        this.output = new DataOutputStream(this.socket.getOutputStream());
        this.numerodeConexiones=numero;
        
    }
    

    @Override
    public void run() {
        
        
        try {
            int numeros = input.readInt();
            s=s+numeros;
            numeroDeEscaladores.setText(String.valueOf(numeros));

            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HandleRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
    
}
