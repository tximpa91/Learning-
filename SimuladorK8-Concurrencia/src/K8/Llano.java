/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import static java.lang.Thread.sleep;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class Llano   {
    private ListaThreads llano;
   

    public Llano( JTextField pantalla) {
        this.llano = new ListaThreads(pantalla);
       
        
       
    }
    
 public void addLlano(Escalador e){
     
    llano.meter(e);
 }
    public void outLlano(Escalador e){
        llano.sacar(e);
    }    
    
   
    public void preparacion (){
        try{
          sleep(1000);
         
         }catch(InterruptedException d){
         }
    
    
    
    }
         
    
    
    
}