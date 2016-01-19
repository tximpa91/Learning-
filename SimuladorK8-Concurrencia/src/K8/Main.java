/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import Distribuido.Monitor;
import Distribuido.Vigilante;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisparada
 */
public class Main {
    public static void main (String[] s) throws RemoteException, SocketException{
       
            Monitor monitor= new Monitor();
            monitor.setVisible(true);
            
            ProgPrincipal inicio = new ProgPrincipal();
            
            inicio.setVisible(true);
            inicio.setLocationRelativeTo(null);
    
           
            Vigilante vigilente = new Vigilante();
            vigilente.setVisible(true);
            vigilente.setLocationRelativeTo(inicio);
            
            
            
       
        
    
        
    
    }
    
}
