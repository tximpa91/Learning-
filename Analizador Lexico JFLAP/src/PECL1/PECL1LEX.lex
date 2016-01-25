
package PECL1;

import java.io.*;
import java.util.*;


%%

%{
	 static int palabrasreservadas=0;
	 static int numeros=0;
	 static int identificador=0;
	 static int errores=0;
	 static int booleanos=0;
	 static int funciones=0;
	 static int comentarios= 0;
	 static String mensaje="";
	 static ArrayList<String> FuncionesToken = new ArrayList<String>();
	
	
	
	
	public static void main (String argv[])throws java.io.IOException {
			for(int i=0; i<argv.length ; i++){
			File fis=new File(argv[i]); 
        	String nombre= fis.getName();
 			int tamaño = nombre.length();
 			
        	if (nombre.startsWith(".prog", tamaño-5))
        	{
        	FileInputStream realFile =new FileInputStream(fis.getAbsolutePath());   
        		
				System.out.println();
				System.out.println( "Comenzando el análisis de su archivo: "+ nombre);
				System.out.println();
				
				
				Analizador yy = new Analizador(realFile);   
				while (yy.yylex() != -1) ;  
				reporte();
				
				} 
			else{
			System.out.println();
			System.out.println( "Comenzando el análisis de su archivo: "+ nombre);
		
			System.out.println( "Su Archivo:  " +nombre+ " no es compatible");
			
				}		
			}
		}
		public  static void reporte(){
			System.out.println(""); 
			System.out.println("Estadísticas"); 
			System.out.println("------------"); 
			System.out.println(""); 
			System.out.println("Numero de palabras reservadas: " +palabrasreservadas); 
			System.out.println("Numeros:" + numeros); 
			System.out.println("Booleanos: " + booleanos);
		   
		    
			int i=0;
			int size = FuncionesToken.size();
			System.out.print("Funciones: "+size+ " (");
				while(i<FuncionesToken.size()){
				System.out.print(FuncionesToken.get(i)+ "," );
				i++;
			
			}
			System.out.print(")");
			System.out.println();
			System.out.println("Identificadores: " + identificador);
			palabrasreservadas=0;
	 		numeros=0;
	 		identificador=0;
	 		errores=0;
	 		booleanos=0;
	 		funciones=0;
	 		comentarios= 0;
	 		mensaje="";
	 		FuncionesToken.removeAll(FuncionesToken);
		}
		
		public static  void Agregar (String token){
			if (!FuncionesToken.contains(token)){
				FuncionesToken.add(token);
			}
		}
		
		
		
		public static void Implicito(String token)
		{
			String nuevo = token.toUpperCase();
			
			switch(nuevo)
			{
				case "PROGRAM": palabrasreservadas++;
					 			break;
					 			
				case "RETURN": palabrasreservadas++;
							   break;
							   
				case "TRUE":   palabrasreservadas++; 
							   booleanos++;
							   break;
							   
				case "FALSE":  palabrasreservadas++; 
							   booleanos++;
							   break;
							   
				case "INTEGER": palabrasreservadas++; 	
								break;
				
				case "BOOLEAN": palabrasreservadas++;
								break;
				
				case "FUNCTION": palabrasreservadas++;
								 break;
								 
				case "BEGIN": 	palabrasreservadas++;
								break;
								
				case "END":		palabrasreservadas++;
								break;
				
				case "VARDECL": palabrasreservadas++;
								break;
			
				default: identificador++;
						 break;
			
			
			}
		
			
		}
		
		
		
		
		public  void Error (int opcion )
		{
			
			Integer linea=1;
			Integer columna=1;
			columna = columna+yycolumn;
			linea = linea+yyline;
				switch (opcion)
			{
				case 0:  System.out.println("Error en la linea: " +linea+" "+ "columna: " +columna+ " Identificador Erroneo: "+ "'" +yytext()+"'");
						 break;
				
			
				case 1: System.out.println("Error en la linea: " +linea+" "+ "columna: " +columna+ " Funcion Erroneo: "+ "'" +yytext()+"'");
						break;
				case 2: System.out.println("Error en la linea: " +linea+" "+ "columna: " +columna+ " Operador o Puntuacion erronea: "+ "'" +yytext()+"'");
						break;
				}
		}
		
		
		

%}


%integer
%line
%column
%unicode
%ignorecase
%class Analizador
/* Seccion de definicion de los macros */
	
	Identificador 		=  [a-zA-Z]([a-zA-Z0-9_])* 
	Funciones 			= "$"[a-zA-Z][a-zA-Z0-9_]*  
	Numeros 			=  ("-"|"+")?[0-9]+
	Salto				= \r|\n|\r\n
	Comentarios			= "//" ~[\r\n] 
	Espacio			   	= {Salto} | [ \t\f]
	Operadores			= "+"|"-"|"="|\-\>	
	Puntuacion			= ","|";"|"("|")"		
			
%%

	
	{Identificador }    				{ Implicito(yytext()); }
	{Numeros}							{  numeros++; }
	{Funciones}   						{ funciones++; Agregar(yytext());}
	{Salto}								{/*	Salto de linea*/}
	{Espacio}							{/*	Espacio*/}
	{Comentarios}						{/* Comentarios */}
	{Operadores}						{/* Operadores */}
	{Puntuacion}						{/* Puntuacion */}
	
(\-\<)+																							{ Error(2); }
(\<\>)+																							{ Error(2); }
(\>\=)+																							{ Error(2); }
(\<\=)+																							{ Error(2); }
(\+\=)+																							{ Error(2); }
(\+\+)+																							{ Error(2); }
(\-\-)+																							{ Error(2); }
(\=\=)+																							{ Error(2); }
(\-\=)+																							{ Error(2); }
")""("																							{ Error(2); }

	

[\!\"\#\%\&\'\*\\\/\:\<\=\>\?\@\[\\\\\\\]\^\_\`\{\|\}\~0-9]+[a-zA-Z][a-zA-Z0-9_]*				{ Error(0); }
"$"[\+\-\,\;\\!\"\#\%\&\'\*\\\/\:\<\=\>\?\@\[\\\\\\\]\^\_\`\{\|\}\~0-9]+[a-zA-Z][a-zA-Z0-9_]* 	{ Error(1); }
. 		{System.out.println("Error en la linea: " +yyline+" "+ "columna: " +yycolumn+  "'" +yytext()+"'");}



