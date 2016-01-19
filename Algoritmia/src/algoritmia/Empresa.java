/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmia;

import java.util.Arrays;

/**
 *
 * @author luisparada1
 */
public class Empresa {
    
    
    private static int Filas;
    private static int Columnas;
    public static int [] capacidadCamiones;
    public static int [] beneficios;
    public static int [][]pesos;

    public Empresa( int tipoGrano, int camiones, int[] capacidadCamiones, int[] beneficios, int[][] pesos) {
      
        this.Filas = tipoGrano;
        this.Columnas = camiones;
        this.capacidadCamiones = capacidadCamiones;
        this.beneficios = beneficios;
        this.pesos = pesos;
    }
    
    public  void ordenaPesos(){
        int o=0;
        int[] aux = new int[Columnas];
        for(int i=0;i<Filas;i++){
            for(int j=0;j<Columnas;j++)
                aux[j] = pesos[i][j];
            Arrays.sort(aux);
            o=0;
            for(int j=Columnas-1;j>=0;j--){
                pesos[i][j] = aux[j];
             
            }
        }
    }
    
    public void imprimir()
    {
        for(int i=0;i<Filas;i++){
            for(int j=0;j<Columnas;j++)
              System.out.print(pesos[i][j]);
            System.out.println("");
        }
    System.out.println("");
    }

    
    public void Beneficios ()
    {
        System.out.println("--------------------------------------------------------------------");
        int beneficiototal=0;
        int capacidadCamion=0;
        int beneficio=0;
        int auxiliar=0;
        int i=0;
        
        for (int m=0; m< capacidadCamiones.length;m++)
        {
            capacidadCamion=capacidadCamiones[m];
         beneficio=0;
        
       
            for ( i=pesos.length;i>0;i--)
            {   
            
                for (int j=4;j>0;j--)
                {   
                    if((capacidadCamion - pesos[i-1][j-1]) >= 0)
                    {
                        
                        beneficio = beneficio + (beneficios[i-1] * pesos[i-1][j-1]);
                        capacidadCamion = capacidadCamion - pesos[i-1][j-1];
                        auxiliar=pesos[i-1][j-1];
                        pesos[i-1][j-1]=99;
                        System.out.println("Camion numero: " +(m+1)+" Saco " + (j) + " del tipo " + (i) +" " +beneficios[i-1]+"*"+auxiliar+ " con beneficio " + beneficio + " y capacidad " + capacidadCamion);
                    }
           
           
           
                }
           
        
            }
            System.out.println("El beneficio total del camion: " +(m+1)+ " es "+beneficio);
            System.out.println("--------------------------------------------------------------------");
        
            beneficiototal=beneficiototal+beneficio;
        }
        
        System.out.println("El beneficio "+beneficiototal);
    
    
    
    
    }
    
    
}
