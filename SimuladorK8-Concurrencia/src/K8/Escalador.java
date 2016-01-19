/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Paso;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisparada
 */
public class Escalador  extends Thread{
    private int identificador;
    private Funicular teleferico;
    private Llano llano;
    private Paso paso;
    private CampoBase base;
    private Campouno uno;
    private Cumbre cumbre;
    private boolean subiendo = true;

    public Escalador(int identificador, Funicular teleferico, Llano llano, Paso paso, CampoBase base, Campouno uno, Cumbre cumbre) {
        this.identificador = identificador;
        this.teleferico = teleferico;
        this.llano = llano;
        this.paso = paso;
        this.base = base;
        this.uno = uno;
        this.cumbre = cumbre;
        start();
    }
    



    

   

    public int getIdentificador() {
        return identificador;
    }

    public static int getMIN_PRIORITY() {
        return MIN_PRIORITY;
    }

    public static int getNORM_PRIORITY() {
        return NORM_PRIORITY;
    }

    public static int getMAX_PRIORITY() {
        return MAX_PRIORITY;
    }
    
    
    public void run(){
     
         paso.mirar();
         
         llano.addLlano(this);
         
         paso.mirar();
         
         llano.preparacion();
         
        try {
            teleferico.subir(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escalador.class.getName()).log(Level.SEVERE, null, ex);
        } 
        teleferico.viajando();
         teleferico.salirSubiendo(this);
         
         
         
         paso.mirar();
         
       
         
         
         base.entraCampoBaseSubiendo(this);
         
         paso.mirar();
         
         
         base.aclimatacion();
         
         paso.mirar();
         uno.entraCampoUno(this);
         uno.aclimatacion();
         
         paso.mirar();
         
         
         
         
         
         if(cumbre.isAbierto()){
             paso.mirar();
            cumbre.entraCumbre(this);
            
            cumbre.contemplar();
            
            paso.mirar();
            
            cumbre.saleCumbre(this);
            paso.mirar();
            base.aclimatacion();
             try {
                 teleferico.bajar(this);
             } catch (InterruptedException ex) {
                 Logger.getLogger(Escalador.class.getName()).log(Level.SEVERE, null, ex);
             }
            base.saleCampoBaseSubiendo(this);
            teleferico.viajando();
            teleferico.salirSubiendo(this);
     
      
            paso.mirar();
         
            llano.addLlano(this);
         
           
         }
         else{
             try {
                 uno.saleCampoUno(this);
                 paso.mirar();
                 base.entraCampoBaseSubiendo(this);
                 base.aclimatacion();
                 base.saleCampoBaseSubiendo(this);
                 paso.mirar();
                 teleferico.bajar(this);
                 paso.mirar();
                 teleferico.viajando();
                 paso.mirar();
                 teleferico.salirBajando(this);
                 
                 llano.addLlano(this);
                 paso.mirar();
             } catch (InterruptedException ex) {
                 Logger.getLogger(Escalador.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         
         
         }
         
         
         
         
         
         
      
         
         
     
    
    
    
    
    }
    
    
    
    
}
