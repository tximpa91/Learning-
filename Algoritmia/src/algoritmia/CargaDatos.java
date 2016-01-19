package algoritmia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dmagan7 and luisparada1
 */
public class CargaDatos {
    public static int filas;
    public static int columnas;


    public static int[] CargaDatosArray(String ruta) throws IOException{
        String aux = FileToString(ruta); // Crear un string del txt
        int[] result=ConvertStringToArrayInt(aux); //Pasar el string a array
        return result;
    }
    
    private static String FileToString(String ruta) throws FileNotFoundException, IOException{
        
        //En este string guardaremos nuestro txt
        String result = "";
        //Con esta funcion podemos leer el txt
        BufferedReader br = new BufferedReader(new FileReader(ruta));
    try {
        //Almacenamos los datos en la variable que hemos iniciado
        String line = br.readLine();
        result = line;
    } finally {
        br.close();
    }
    
        return result; 
    }
    
    public static  String FileToStringType2(String ruta) throws FileNotFoundException, IOException{
        
        //En este string guardaremos nuestro txt
        String result = "";
        String probando [];
        int max=-9999;
        
        //Con esta funcion podemos leer el txt
        int numerolineas=0;
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        try{
            while ((result = br.readLine())!=null) 
            {
                probando= result.split(";");
                numerolineas++;
                if(probando.length>max)
                {
                    max=probando.length;
                
                }
            }
           }
        finally
        {
            br.close();
        }
        result= String.valueOf(max)+","+String.valueOf(numerolineas);
        
        return result;
    }
    
    
    
    
    private static int[] ConvertStringToArrayInt(String str)
    {
        //Split: Trasnforma un string en un array de string utilizando un separador (en este caso el ;)
        String[] arrayStr = str.split(";");
        //Creamos un array de enteros del mismo tamanio del array de strings
        int[] intArray = new int[arrayStr.length];
        //Recorremos el array de string y transformamos cada elemento a un int        
        for(int i = 0; i < arrayStr.length; i++)
        {
            intArray[i] = Integer.parseInt(arrayStr[i]);
        }
        return intArray;
    }
    
    public static int[][] CargaDatosMatriz(String ruta, String ma) throws IOException
    { // ????
       String aux []= ma.split(",");
       String result="";
       ArrayList<String[]> auxiliar = new ArrayList<>();
       int i,k;
      
        columnas=Integer.parseInt(aux[0]);
        filas= Integer.parseInt(aux[1]);
       int Matrix[][] = new int[filas][columnas];
       BufferedReader br = new BufferedReader(new FileReader(ruta));
        
        try
        {
            while ((result = br.readLine())!=null) 
            {   
                auxiliar.add(result.split(";"));
                
            }
        }
        finally
        {
            br.close();
        }
        
       
            
        for (k=0; k<filas ;k++)
        {   
            for(int j=0;j<columnas;j++)
            {   aux=auxiliar.get(k);
                if(!(j >= aux.length))
                {
                    int numero= Integer.parseInt(aux[j]);
                    Matrix[k][j]=numero;
                }
                else
                {
                   Matrix[k][j]=99;
                }
            }
        }
        
        
        
    
    
         return Matrix;       
    }
}
    
    

