
package  PECL2;

import java.io.*;
import java.util.*;
import java_cup.runtime.*;



%%
 
%{

private Symbol sym(int type)
	{
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
	{
		return new Symbol(type, yyline, yycolumn, value);
	}
	 static int palabrasreservadas=0;
	 static int numeros=0;
	 static int identificador=0;
	 static int errores=0;
	 static int booleanos=0;
	 static int funciones=0;
	 static int comentarios= 0;
	 static String mensaje="";
	 public static int lexico=0;
	 static ArrayList<String> FuncionesToken = new ArrayList<String>();
	 
	
	
	
	
	
	public static void main (String argv[])throws java.io.IOException {
			for(int i=0; i<argv.length ; i++){
			File fis=new File(argv[4]); 
        	String nombre= fis.getName();
 			int tamaño = nombre.length();
 			
        	if (nombre.startsWith(".prog", tamaño-5))
        	{
        	FileInputStream realFile =new FileInputStream(fis.getAbsolutePath());   
        		
				System.out.println();
				System.out.println( "Comenzando el análisis de su archivo: "+ nombre);
				System.out.println();
				
				
				Yylex yy = new Yylex(realFile);   
				while (yy.yylength() != -1) ;  
				
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
		
		
		
		public static int  Implicito(String token)
		{
			String nuevo = token.toUpperCase();
			
			switch(nuevo)
			{
				case "PROGRAM": palabrasreservadas++;
					 			return 1;
					 			
				case "RETURN": palabrasreservadas++;
							   return 2;
							   
				case "TRUE":   palabrasreservadas++; 
							   booleanos++;
							   return 3;
							   
				case "FALSE":  palabrasreservadas++; 
							   booleanos++;
							   return 4;
							   
				case "INTEGER": palabrasreservadas++; 	
								return 5;
				
				case "BOOLEAN": palabrasreservadas++;
								return 6;
				
				case "FUNCTION": palabrasreservadas++;
								 return 7;
								 
				case "BEGIN": 	palabrasreservadas++;
								return 8;
								
				case "END":		palabrasreservadas++;
								return 9;
				
				case "VARDECL": palabrasreservadas++;
								return 10;
			
				default:   return 11;
						 
			
			
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
				case 0:  System.out.println("Error lexico  en la linea: " +linea+" "+ "columna: " +columna+ " Identificador Erroneo: "+ "'" +yytext()+"'");
						 lexico=lexico + 1;
						 break;
				
			
				case 1: System.out.println("Error lexico  en la linea: " +linea+" "+ "columna: " +columna+ " Funcion Erronea: "+ "'" +yytext()+"'");
						lexico=lexico + 1;
						break;
			
				}
		}
		
		
		

%}
%cup
%integer
%line
%column
%unicode
%ignorecase
%full
%notunix

/* Seccion de definicion de los macros */
	
	Identificador 		=  [a-zA-Z]([a-zA-Z0-9_])* 
	Funciones 			= "$"[a-zA-Z][a-zA-Z0-9_]*  
	Numeros 			=  ("-"|"+")?[0-9]+
	Salto				= [\r\n]
	Comentarios			= "//" ~[\r\n] 
	Espacio			   	=  [ \t\f]
	
		
			
%%

	
	{Identificador }    				{ 	switch(Implicito(yytext()))
											{
												case 1:  return new Symbol(sym.TK_PROGRAM,yyline + 1,yycolumn + 1,yytext());
					 											
					 			
												case 2:  return new Symbol(sym.TK_RETURN,yyline + 1,yycolumn + 1,yytext());
							 						    
							   
												case 3:  return new Symbol(sym.TK_TRUE,yyline + 1,yycolumn + 1,yytext());
							  						 
							   
												case 4:  return new Symbol(sym.TK_FALSE,yyline + 1,yycolumn + 1,yytext());
													  	
							   
												case 5:  return new Symbol(sym.TK_INTEGER,yyline + 1,yycolumn + 1,yytext() );
				
												case 6:  
												
												
												return new Symbol(sym.TK_BOOL,yyline + 1,yycolumn + 1,yytext() );
				
												case 7:  return new Symbol(sym.TK_FUNCTION,yyline + 1,yycolumn + 1,yytext());
													 
								 
												case 8:  return new Symbol(sym.TK_BEGIN,yyline + 1,yycolumn + 1,yytext());
													
								
												case 9:	 return new Symbol(sym.TK_END,yyline + 1,yycolumn + 1,yytext());
													
					
												case 10:  return new Symbol(sym.TK_VARCDEL,yyline + 1,yycolumn + 1,yytext());
											

												default: 
														 
														 
														 return new Symbol (sym.TK_ID,yyline + 1,yycolumn + 1,yytext() );
						 						}
						 						
						 						 }
	{Numeros}							{   numeros++; 
											
											 return new Symbol (sym.TK_NUMERO, yyline,yycolumn, new Integer(yytext())); }
	{Funciones}   						{ funciones++; Agregar(yytext()); 
											return new Symbol(sym.TK_FUNCID,yyline + 1,yycolumn + 1,yytext()); }

	","									{  return new Symbol(sym.TK_COMA,yyline + 1,yycolumn + 1,yytext());}
	";"									{  
											return new Symbol(sym.TK_SEMI,yyline + 1,yycolumn + 1,yytext()); }
	"("									{  return new Symbol (sym.TK_OPENPARENT,yyline + 1,yycolumn + 1,yytext() ); }
	")"									{  return new Symbol (sym.TK_CLOSEPARENT,yyline + 1,yycolumn + 1,yytext() ); }
	"+"									{  return new Symbol(sym.TK_SUMA,yyline + 1,yycolumn + 1,yytext() ); }
	"-"									{  return new Symbol(sym.TK_RESTA,yyline + 1,yycolumn + 1,yytext() ); }
	"="									{  return new Symbol(sym.TK_IGUAL,yyline + 1,yycolumn + 1,yytext() ); }
	"\-\>"								{   return new Symbol(sym.TK_ASIGNACION,yyline + 1,yycolumn + 1,yytext() ); }
    "\-\<"								{ System.out.println("Error en la linea: " +yyline+" "+ "columna: " +yycolumn+  "'" +yytext()+"'");}
    {Salto}								{/*	Salto de linea*/
    																	}
	{Espacio}							{/*	Espacio*/
										
										
																}
	{Comentarios}						{/* Comentarios */
										 				}

	
[\!\"\#\%\&\'\*\\\/\:\<\=\>\?\@\[\\\\\\\]\^\_\`\{\|\}\~0-9]+[a-zA-Z][a-zA-Z0-9_]*				{ Error(0); }
"$"[\+\-\,\;\\!\"\#\%\&\'\*\\\/\:\<\=\>\?\@\[\\\\\\\]\^\_\`\{\|\}\~0-9]+[a-zA-Z][a-zA-Z0-9_]* 	{ Error(1); }
.																						{System.out.println("Error en la linea: " +(yyline + 1) +" "+ "columna: " +(yycolumn + 1)+ " Error lexico "+ "'" +yytext()+"'");
																																lexico= lexico + 1;  }



