/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K8;
import java.util.*;
import javax.swing.JTextField;

/**
 *
 * @author luisparada
 */

 
 
public class ListaThreads {
    ArrayList<Thread> lista;
    JTextField tf;
    public ListaThreads(JTextField tf){
        lista=new ArrayList<Thread>();
        this.tf=tf;
    }
    public synchronized void meter(Thread t){
        lista.add(t);
        imprimir();
    }
    public synchronized void sacar(Thread t){
        lista.remove(t);
        imprimir();
    }
    public void imprimir(){
        String contenido="";
        for(int i=0;i<lista.size();i++)
           contenido=contenido+((Escalador)lista.get(i)).getIdentificador()+"-";
        tf.setText(contenido);
    }
} 

