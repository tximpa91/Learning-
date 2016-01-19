/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Paso;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class Campouno {
    private ListaThreads campoUno;
    private Paso paso ;
    private CampoBase base;
    private Semaphore campo = new Semaphore(10, true);
    
    
    public Campouno(JTextField campo, Paso paso, CampoBase base) {
        this.campoUno = new ListaThreads(campo);
        this.paso=paso;
        this.base= base;
    }
    public void entraCampoUno(Escalador e){
        
        
        paso.mirar();
        try{
            campo.acquire(1);
            base.saleCampoBaseSubiendo(e);
            campoUno.meter(e);
      
            }catch(InterruptedException s ){}
        
                
        
    }
    public void saleCampoUno(Escalador e){
        paso.mirar();
        campoUno.sacar(e);
        campo.release();
    
}
    public void aclimatacion(){
        paso.mirar();
        try{
          sleep((int)2000);
         
         }catch(InterruptedException d){
         }
    
    
    }
}
