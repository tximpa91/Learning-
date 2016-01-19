package Distribuido;


import K8.Cumbre;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ServidorRMI extends Thread  {
    private  Paso p;
    private  Cumbre cumbre;

    public ServidorRMI(Paso p, Cumbre cumbre) {
        this.p = p;
        this.cumbre=cumbre;
        start();
    }
    
   

    public void run() {
        p.mirar();
       try {
           Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/ObjetoWork",cumbre);
            System.out.println("El Objeto Cumbre ha quedado registrado");
            
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
       p.mirar();
    }
}
