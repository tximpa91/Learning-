/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author dmagan7
 */
public class Algoritmia {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException{
    int i =110;
    int aux=0;
    int a =100;
    while (i>0){
        aux=aux+i %10*a;
        i=i/10;
        a=a/10;
    }
    System.out.println(aux);
        
    }
    
}