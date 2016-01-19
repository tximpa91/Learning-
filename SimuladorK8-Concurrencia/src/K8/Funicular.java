/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Paso;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class Funicular extends Thread {
    private ListaThreads dentro;
    private boolean subiendo;
    private  boolean direccion;
    private Llano llano;
    private CampoBase base;
    private int capacidad=0;
    private Socket socket;
    private DataOutputStream output;
    private JTextField status;
    private Paso paso;
    private AtomicInteger escaladores;

    public Funicular(JTextField dentro ,Llano llano, CampoBase base,JTextField status,Paso paso) {
        this.dentro = new ListaThreads(dentro);
        this.llano = llano;
        this.base=base;
        this.status=status;
        this.paso=paso;
        escaladores = new AtomicInteger(0);
        start();
    }
    
    public synchronized void subir (Escalador e) throws InterruptedException{
       
       paso.mirar();
        while((subiendo==true) &&(direccion==false)){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Funicular.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
           }
        while(dentro.lista.size()==4){
            wait();
            
        
        }
            llano.outLlano(e);
            
            dentro.meter(e);
            notifyAll();
    
            paso.mirar();
    
    
    
    
    }
    
    public synchronized void salirSubiendo (Escalador e ){
        paso.mirar();
        
        dentro.sacar(e);
        notifyAll();
        
        
    }
    public synchronized void salirBajando (Escalador e ){
        paso.mirar();
        
        dentro.sacar(e);
        notifyAll();
        
        
    }
     public synchronized void bajando (Escalador e ){
        paso.mirar();
     
        dentro.sacar(e);
        notifyAll();
        
        
    }
    
    public synchronized  void bajar (Escalador e) throws InterruptedException{
        paso.mirar();
        
          while(((subiendo==true) && (direccion==true))){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Funicular.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
           }
          while(dentro.lista.size()==4){
          wait();
          }
           
         
            
            dentro.meter(e);
            notifyAll();
    
    
        paso.mirar();
    
            
    
    
    }
    
    

    
    
    
    public  void viajando(){
        paso.mirar();
        
        direccion=true;
        subiendo=true;
        int numeroso=0;
        
        status.setText("Viajando");
            status.setBackground(Color.red);
            synchronized(this){
            try{
        socket = new Socket(InetAddress.getLocalHost(), 5000);
        output= new DataOutputStream(socket.getOutputStream());
        escaladores.addAndGet(1);
        numeroso=escaladores.get();
        output.writeInt(numeroso);
            }
            catch (IOException ie){}
            }
            
        
            
     try {
                sleep(2000);
            } catch (InterruptedException ex) {}
    }
    
    
   
   
    
    
    
    
    public void run (    ){
    
        while(true){
            paso.mirar();
            status.setText("Abajo");
            status.setBackground(Color.BLUE);
            direccion=true;
            subiendo=false;
           
           
                
                try {
                sleep(1000);
            } catch (InterruptedException ex) {}
                
                paso.mirar();
                status.setText("Arriba");
                status.setBackground(Color.BLUE);
                direccion=false;
                subiendo=false;
             try {
                sleep(1000);
            } catch (InterruptedException ex) {}
        }
        }
}
               
            
            
        
        
        
        
        
     
    
    
    
    
    
    
    
    
    
    

