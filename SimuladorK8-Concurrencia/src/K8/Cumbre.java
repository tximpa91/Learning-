/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Paso;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class Cumbre extends UnicastRemoteObject implements InterfaceCumbre{
    private ListaThreads listaCumbre;
    private Semaphore semaforo = new Semaphore(1, true);
    private Paso paso;
    private boolean abierto=true;
    private Campouno uno;
    private  CampoBase base;
    private JCheckBox clima;

    public Cumbre(JTextField Cumbre, Paso paso, Campouno uno,CampoBase base, JCheckBox clima)throws RemoteException{
       
        
        this.listaCumbre = new ListaThreads(Cumbre);
        this.paso= paso;
        this.uno=uno;
        this.base=base;
        this.clima=clima;
        
    }

    public boolean isAbierto() {
        return abierto;
    }
    
     @Override
    public String abrirCumbre() throws RemoteException {
        
        abierto= true;
        String n= "Reanudado";
        clima.setSelected(true);
        clima.doClick();
        clima.setBackground(Color.BLUE);
          
        return  n;
    }

    @Override
    public String cerrarCumbre() throws RemoteException {
        abierto= false;
        clima.setSelected(false);
        clima.doClick();
        
         String n= "Parados";
        return  n;
    }
    public void contemplar(){
        paso.mirar();
        try {
            Thread.sleep(2000+(int) (3000*Math.random()));
        } catch (InterruptedException e){}
    
    
    }
    
    
    public void entraCumbre(Escalador e){
        paso.mirar();
        
        try {
            semaforo.acquire(1);
            uno.saleCampoUno(e);
            listaCumbre.meter(e);
            
           
        } catch (InterruptedException s) {
        }
        paso.mirar();
        
    }
    public void saleCumbre(Escalador e){
        paso.mirar();
        
        listaCumbre.sacar(e);
        base.entraCampoBaseSubiendo(e);
        semaforo.release();
    }
    
    
}
