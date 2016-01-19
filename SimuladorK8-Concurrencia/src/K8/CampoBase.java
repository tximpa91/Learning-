/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Paso;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */
public class CampoBase {
      private ListaThreads listaCampoBase;
      private Paso paso;

        public CampoBase(JTextField campoBase, Paso paso) {
        this.listaCampoBase = new ListaThreads(campoBase);
        this.paso=paso;
    }
    public void entraCampoBaseSubiendo(Escalador e){
        paso.mirar();
        listaCampoBase.meter(e);
        paso.mirar();
    }
    
    public void aclimatacion (){
        paso.mirar();
          try {
              sleep( (int) (Math.random() * (3000-300+1) + 300));
          } catch (InterruptedException ex) {
              Logger.getLogger(CampoBase.class.getName()).log(Level.SEVERE, null, ex);
          }
          paso.mirar();
    
    
    }
    public void saleCampoBaseSubiendo(Escalador e){
        paso.mirar();
        listaCampoBase.sacar(e);
        paso.mirar();
    }

    
}
