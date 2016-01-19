/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author luisparada
 */
public interface InterfaceCumbre extends Remote{
    String abrirCumbre()  throws RemoteException;
    String cerrarCumbre() throws RemoteException;
    
}
