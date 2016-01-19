// La clase Paso define una Región Crítica Condicinal para la variable boolena cerrado
// que es comprobada por un proceso.
// Si vale false(abierto) el proceso puede continuar. Si es true(cerrado) el proceso se detiene
package Distribuido;
public class Paso {
    private boolean cerrado=false;

    public synchronized void mirar(){
        while(cerrado){
            try{wait();} catch(InterruptedException ie){}
        }
    }
    public synchronized void abrir(){
        cerrado=false;
        notifyAll();
    }
    public synchronized void cerrar(){
        cerrado=true;
    }
}
