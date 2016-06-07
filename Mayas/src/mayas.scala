
import scala.util.Random
import java.awt.Event._
import javax.swing._


object mayas extends App 
{
 
  
  println("Bienvenido a bloques Maya")
  
  println("Elija el nivel de dificultad")
  println("1- Facil\n2- Medio\n3- DifÌcil")
  
  val nivel = readInt
  val puntuacion = 0 
  
  val  dificultad = nivel match
  {
    case 1 => 3
    case 2 => 5
    case 3 => 7 
  }
    if(dificultad==3)
    {
      val col=11
      val fil =9 
      val bombas = 2
      println("Desea jugar automatico ?")
      println(" 1- Si \n 2- No ")
      val opcion = readInt
      

      new tablero(dificultad,fil,col,bombas,puntuacion,8,3,opcion)
      
    
    }
    if(dificultad==5)
    {
      val col=16
      val fil =12 
      val bombas = 3
      println("Desea jugar automatico ?")
      println(" 1- Si \n 2- No ")
      val opcion = readInt
      new tablero(dificultad,fil,col,bombas,puntuacion,10,5,opcion)
    
    }
    if(dificultad==7)
    {
      val col=15
      val fil =25 
      val bombas = 5
      println("Desea jugar automatico ?")
      println(" 1- Si \n 2- No ")
      val opcion = readInt
    
      new tablero(dificultad,fil,col,bombas,puntuacion,15,7,opcion)
    
    }
}
  
  
class tablero (dificultad:Int, Fil:Int,Col:Int,bombas:Int,puntuacion:Int,vidas:Int,colores:Int,opcion:Int)
{    
    val tablero =ponerBombas(crearLista(Nil, Fil*Col,colores), Fil*Col, bombas)
     if(opcion==1){          //Automático
       print("Puntos: " +puntuacion) 
     	 println(" Vidas: "+ vidas)
     	 println()
       imprimir(tablero,1,Col,1,0,Fil)
       optimo(tablero, Fil, Col, puntuacion, vidas, 0, 0, List(0,0,0), 0,Nil,0)
       }
     else jugar(tablero,Fil,Col,puntuacion,vidas,dificultad,bombas,colores)
       
    def imprimirX(tamaño:Int,cont:Int)
    {
       if(cont!=tamaño+1 && cont==1)
       {  print("     ")
          print(" "+cont+"  ")
          imprimirX(tamaño,cont+1)
       }
       else
       {
         if(cont!=tamaño+1)
         {
           if(cont>=10){
             print(" "+cont+" ")
             imprimirX(tamaño,cont+1)
             
           }else
           {
              print(" "+cont+"  ")
             imprimirX(tamaño,cont+1)
             
             
           }
           
         }
         else{
               println() 
               
             }
         
       }
       
       
    }
     
    def imprimir(l:List[Int],pos:Int,Col:Int,cont:Int,aux:Int,Fila:Int) 
     {
      if(l.isEmpty) Unit
        else
        {
      		if (pos%Col==0)    //Imprimir las columnas pares
        		{ 
        			l.head match
         			{
        			  case -2 => print(".  ")
        			  case -1 => print("|   |")
				  case 0 => print("| 0 ")
			    	  case 1 => print("| A |")
			    	  case 2 => print("| R |")
			    	  case 3 => print("| N |")
			    	  case 4 => print("| V |")
			    	  case 5 => print("| P |")
			    	  case 6 => print("| M |")
			    	  case 7 => print("| G |")
			    	  case 8 => print("| B |")
            	
         
         			}
        			println(" ")
        			if(cont<=Fila && cont<=9)
        			print(cont+"-  " )
        			else if(cont<=Fila && cont>9)print(cont+"- " )
           	 
         			imprimir(l.tail,1,Col,cont+1,1,Fila)
        		}
         		else    //Imprimir las columnas impares
         		{
         		  if(aux==0)
         		    print(1 +"-  ")
         		 
         			l.head match
         			{
         			  case -2 => print(".  ")
        			  case -1 => print("|   ")
        			  case 0 => print("| 0 ")
			    	  case 1 => print("| A ")
			    	  case 2 => print("| R ")
			    	  case 3 => print("| N ")
			    	  case 4 => print("| V ")
			    	  case 5 => print("| P ")
			    	  case 6 => print("| M ")
			    	  case 7 => print("| G ")
			    	  case 8 => print("| B ")
			    	
            	
            	   
         
         			}
          			
          	  imprimir(l.tail,pos+1,Col,cont,1,Fila)
            }
         
        }
      }
       
 
                           
                                                  
    	def crearLista(l:List[Int],Tam:Int,colores:Int):List[Int]={
    		if(Tam==0) l
    		else
    		{	val lis :List[Int]= l:::List((Random.nextInt(colores)+1))
    			crearLista(lis,Tam-1,colores)
    					
    			}
  	}

  		
  	 def poner  (col:Int, pos:Int,l:List[Int]):List[Int]={
 
 		if(l.isEmpty) l
 		else if(pos==0) col::l.tail
 		else l.head::poner(col,(pos-1),l.tail)
 		}
 			

 	def ponerBombas(l:List[Int],tam:Int,bombas:Int):List[Int]={   
 		if(bombas==0)l
 		else{
 			val num = Random.nextInt(tam)+1      		 //Elegimos posición aleatoria
 			ponerBombas(poner(8,num,l),tam,bombas-1)    	// Método para meter las bombas en las posiciones elegidas
 		}
 		 			
 	}
 		
	  
  def getPosicion(pos:Int, tablero:List[Int],cont:Int):Int={     //Devuelve la posición que queramos obtener
       	if(tablero.isEmpty) 0 
       	else{
		if(pos==cont) tablero.head
	       	else getPosicion(pos,tablero.tail,cont+1)}
       
   }                     
  
  
  def compruebaDerecha(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]={
       val posicionSig=y*col+(x+1)
       val posicionActual=y*col+x
       val bloqueaux=getPosicion(posicionSig,tablero,0)  // Bloque donde vamos a comparar
       if (bloqueaux==bloque)
       {
       		val tableronew=poner(0, posicionSig, tablero) //Ponemos a 0 el bloque si es igual al pulsado y devolvemos la lista
       		val tableronew2 =posicionValida(tableronew, y, x+1, fila, col, bloque)  //Comprobamos que la posicion es valida
       		tableronew2
       		
       }
       else
       {
       	 tablero
       }
       
  }     
  
  def compruebaIzquierda(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]={
       val posicionSig=y*col+(x-1)
       val posicionActual=y*col+x
       val bloqueaux=getPosicion(posicionSig,tablero,0)
       if (bloqueaux==bloque)
       {
       		val tableronew=poner(0, posicionSig, tablero)
       		val tableronew2 =posicionValida(tableronew, y, x-1, fila, col, bloque)
       		tableronew2
       }
       else
       {
       	  tablero
       }
       
  }    
  
  def compruebaArriba(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]={
       val posicionSig=(y-1)*col+x
       val posicionActual=y*col+x
       
       
       val bloqueaux=getPosicion(posicionSig,tablero,0)
       if (bloqueaux==bloque)
       {  
       		val tableronew=poner(0, posicionSig, tablero)
       		val tableronew2 =posicionValida(tableronew, y-1, x, fila, col, bloque)
       		tableronew2
       }
       else
       {
       	  tablero
       }
       
  }
  def compruebaAbajo(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
       
       val posicionSig=(y+1)*col+x
       val posicionActual=y*col+x
       val bloqueaux= getPosicion(posicionSig,tablero,0)
       if (bloqueaux==bloque)
       { 
       		val tableronew=poner(0, posicionSig, tablero)
       		val tableronew2 =posicionValida(tableronew, y+1, x, fila, col, bloque)
       		tableronew2
       }
       else
       {
       	  tablero
       }
       
  }
  
  def compruebaPuntos(lista:List[Int], puntos:Int,posiciones:List[Int]):List[Int]=
  {
   if(lista.isEmpty)posiciones
   else
   {
     if(lista.head==0)
     compruebaPuntos(lista.tail,puntos+1,posiciones:::List(puntos))
     else
       compruebaPuntos(lista.tail,puntos+1,posiciones)
   }
  }

  
   def compruebaFinal(lista:List[Int], fin:Int):Int=
  {
   if(lista.isEmpty)fin
   else
   {
     if(lista.head == -1)
     compruebaFinal(lista.tail,fin+1)
     else
       compruebaFinal(lista.tail,fin)
   }
  }


   def bombasIzquierda(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=y*col+x-1
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)   //Si la posicion de la izquierda es una bomba tambien
    {			//Llamamos al metodo bombas con la posicion de la izquierda (eliminada) para que efectue la 'explosión' 			//con la nueva bomba
      bombas(tableronew, y, x-1, fila, col, bloque)
    }
    else tableronew
  }


  def bombasArriba(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y-1)*col+x
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y-1, x, fila, col, bloque)
    }
    else tableronew
  }
  
  def bombasAbajo(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y+1)*col+x
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y+1, x, fila, col, bloque)
    }
    else tableronew
  }


  def bombasDerecha(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=y*col+x+1
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y, x+1, fila, col, bloque)
    }
    else tableronew
  }

//___DIAGOLANES___

    def bombasArDerecha(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y-1)*col+x+1
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y-1, x+1, fila, col, bloque)
    }
    else tableronew
  }


    def bombasArIzquierda(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y-1)*col+x-1 
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y-1, x-1, fila, col, bloque)
    }
    else tableronew
  }


  def bombasAbIzquierda(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y+1)*col+x-1
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y+1, x-1, fila, col, bloque)
    }
    else tableronew
  }
   def bombasAbDerecha(tablero:List[Int], eliminados:List[Int], x:Int,y:Int,fila:Int,col:Int,bloque:Int):List[Int]=
  {
    val posicionSig=(y+1)*col+x+1
    
    val bomba = getPosicion(posicionSig, tablero, 0)
    val tableronew=poner(0, posicionSig, tablero)
    if(bomba==bloque)
    {
      bombas(tableronew, y+1, x+1, fila, col, bloque)
    }
    else tableronew
  }  


 def bombas(tablero:List[Int],y:Int, x:Int, fila:Int,col:Int,bloque:Int):List[Int]=

//Hay que hacer las comprobaciones en las esquinas, laterales y en la primera y ultima fila

  {if( x==0 && y==0)//esquina superior izquierda
   {  
       val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
       val l2 =bombasDerecha(l, Nil, x, y, fila,col, bloque)
       val tableronew=bombasAbDerecha(l2, Nil, x, y, fila,col, bloque)
       tableronew
     
   }
   else
   {  if(y==0 && x < col -1)//primera fila
      {  val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
         val l2 =bombasDerecha(l, Nil, x, y, fila,col, bloque)
         val l3=bombasAbDerecha(l2, Nil, x, y, fila,col, bloque)
         val l4 = bombasIzquierda(l3, Nil, x, y, fila,col, bloque)
         val tableronew = bombasAbIzquierda(l4, Nil, x, y, fila,col, bloque)
         tableronew
      }
     else
     { if( y==0 && x==col-1) //Esquina superior derecha
       {  
          val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
          val l2 = bombasIzquierda(l, Nil, x, y, fila,col, bloque)
          val tableronew = bombasArIzquierda(l2, Nil, x, y, fila,col, bloque)
          tableronew
       
       }
       else
       {
         if(y < fila -1 && x==0 ) //Primera columna  
         {
            val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
            val l2 = bombasDerecha(l, Nil, x, y,fila, col, bloque)
            val l3 =bombasArriba(l2, Nil, x, y,fila, col, bloque)
            val l4 = bombasAbDerecha(l3, Nil, x, y,fila, col, bloque)
            val tableronew = bombasArDerecha(l4, Nil, x, y,fila, col, bloque)
            tableronew
       
         }
         else
         {
           if( y==fila-1 && x==0) //Esquina inferior izquierda 
           {  
             val l= bombasArriba(tablero, Nil, x, y, fila,col, bloque)
             val l2 = bombasDerecha(l, Nil, x, y,fila, col, bloque)
             val tableronew = bombasArDerecha(l2, Nil, x, y,fila, col, bloque)
             tableronew
           }
           else
           { if(y==fila-1 && x < col-1)  //Ultima fila 
              {
                val l= bombasArriba(tablero, Nil, x, y, fila,col, bloque)
                val l2 = bombasIzquierda(l, Nil, x, y,fila, col, bloque) 
                val l3 = bombasDerecha(l2, Nil, x, y,fila, col, bloque) 
                val l4 = bombasArDerecha(l3, Nil, x, y,fila, col, bloque) 
                val tableronew = bombasArIzquierda(l4, Nil, x, y,fila, col, bloque) 
                tableronew
              }
             else
             {  if(y==fila-1 && x==col-1) //esquina inferior derecha  
               {
               
                 val l= bombasArriba(tablero, Nil, x, y, fila,col, bloque)
                 val l2 = bombasIzquierda(l, Nil, x, y,fila, col, bloque)
                 val tableronew= bombasArIzquierda(l2, Nil, x, y,fila, col, bloque)
                 tableronew
               }
               else
              {  
                 if(y < fila -1 && x==col-1)  //Ultima columna  
                 {
                   val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
                   val l1=bombasArriba(l, Nil, x, y, fila,col, bloque)
                   val l2=bombasIzquierda(l1, Nil, x, y, fila,col, bloque)
                   val l3 = bombasArIzquierda(l2, Nil, x, y, fila,col, bloque)
                   val tableronew = bombasAbIzquierda(l3, Nil, x, y, fila,col, bloque)
                   tableronew
                   
                 }
                 else
                 {
                    if(y < fila - 1 && x < col - 1) //Demás tablero
                    {
                      val l= bombasAbajo(tablero, Nil, x, y, fila,col, bloque)
                      val l1=bombasArriba(l, Nil, x, y, fila,col, bloque)
                      val l2=bombasDerecha(l1, Nil, x, y, fila,col, bloque)
                      val l3 = bombasIzquierda(l2, Nil, x, y,fila, col, bloque)
                      val l4 = bombasArDerecha(l3, Nil, x, y,fila, col, bloque)
                      val l5 = bombasAbDerecha(l4, Nil, x, y,fila, col, bloque)
                      val l6 = bombasAbIzquierda(l5, Nil, x, y,fila, col, bloque)
                      val tableronew = bombasArIzquierda(l6, Nil, x, y,fila, col, bloque)
                      tableronew
                     
                 }else
                 { 
                   Nil
                 }
                   
                 }
                 
               }
             
             }
           }
         }
      }
     }
    }
    
  }
 
  def posicionValida(tablero:List[Int],y:Int, x:Int, fila:Int,col:Int,bloque:Int):List[Int]=
  {

//Metodo para comprobar al borrar si se puede eliminar esa posicion

    if( x==0 && y==0)//esquina superior izquierda
   {  
       val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
       val tableronew=compruebaDerecha(l, Nil, x, y, fila,col, bloque)
       tableronew
     
   }
   else
   {  if(y==0 && x < col -1)//primera fila
      {  val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
         val l2=compruebaDerecha(l, Nil, x, y, fila,col, bloque)
         val tableronew = compruebaIzquierda(l2, Nil, x, y,fila, col, bloque)
         tableronew
      }
     else
     { if( y==0 && x==col-1)  //Esquina superior derecha
       {  
          val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
          val tableronew = compruebaIzquierda(l, Nil, x, y,fila, col, bloque)
          tableronew
       
       }
       else
       {
         if(y < fila -1 && x==0 ) //Primera columna 
         {
            val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
            val l2 = compruebaDerecha(l, Nil, x, y,fila, col, bloque)
            val tableronew=compruebaArriba(l2, Nil, x, y,fila, col, bloque)
            tableronew
       
         }
         else
         {
           if( y==fila-1 && x==0) //Esquina inferior izquierda
           {  
             val l= compruebaArriba(tablero, Nil, x, y, fila,col, bloque)
             val tableronew = compruebaDerecha(l, Nil, x, y,fila, col, bloque)
             tableronew
           }
           else
           { if(y==fila-1 && x < col-1) //Ultima fila 
              {
                val l= compruebaArriba(tablero, Nil, x, y, fila,col, bloque)
                val l2 = compruebaIzquierda(l, Nil, x, y,fila, col, bloque) 
                val tableronew = compruebaDerecha(l2, Nil, x, y,fila, col, bloque) 
                tableronew
              }
             else
             {  if(y==fila-1 && x==col-1) //esquina inferior derecha 
               {
               
                 val l= compruebaArriba(tablero, Nil, x, y, fila,col, bloque)
                 val tableronew = compruebaIzquierda(l, Nil, x, y,fila, col, bloque) 
                 tableronew
               }
               else
              {  
                 if(y < fila -1 && x==col-1) //Ultima columna  
                 {
                   val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
                   val l1=compruebaArriba(l, Nil, x, y, fila,col, bloque)
                   val tableronew=compruebaIzquierda(l1, Nil, x, y, fila,col, bloque)
                  tableronew
                   
                 }
                 else
                 {
                    if(y < fila - 1 && x < col - 1) //Demás tablero
                    {
                      val l= compruebaAbajo(tablero, Nil, x, y, fila,col, bloque)
                      val l1=compruebaArriba(l, Nil, x, y, fila,col, bloque)
                      val l2=compruebaDerecha(l1, Nil, x, y, fila,col, bloque)
                      val tableronew = compruebaIzquierda(l2, Nil, x, y,fila, col, bloque)
                      tableronew
                     
                 }else
                 { 
                   Nil
                 }
                }
               }
             }
           }
         }
       }
     }
   }
}


     def prints(l:List[Int]){
       if(l==Nil)Unit
       else{
       print(l.head)
       prints(l.tail)}
       
     }


     def eliminar(elim:List[Int],tablero:List[Int]):List[Int]={
     		if(elim.isEmpty)tablero
     		else
     		{
     			val tableronew=poner(-1,elim.head,tablero)
     			eliminar(elim.tail,tableronew)
     		
     		}
     
     } 
       
     
     def tam (l:List[Int],t:Int):Int={    //Saca el tamaño de la lista que le hemos insertado
    	if(l.isEmpty)t
    	else tam(l.tail,t+1)
    	}  

    
     def Puntos(eliminados:List[Int],puntos:Int):Int={
        val puntos2 =  tam(eliminados,0)
    
      if ( puntos2  >= 3)
      {
        val puntosahora =puntos+(puntos2*10)
        return puntosahora
       }
      else
        return puntos
      
      }
     

     def desplazar (tablero:List[Int],fila:Int,col:Int,k:Int,j:Int,i:Int):List [Int]={
     
     if (k<5) {
       if(j<col){
         
         if(i>0){
           if(getPosicion(i*col+j, tablero, 0)== -1) //Si la posicion de abajo está vacía 
           {
            //Swap
            val listaux= poner(getPosicion(((i-1)*col+j), tablero, 0), (i*col+j), tablero)
            val tableroaux= poner (-1, ((i-1)*col+j), listaux)
            desplazar(tableroaux, fila, col, k,j,i-1)
           }else{desplazar(tablero, fila, col, k,j,i-1)}
           
     
         }else desplazar(tablero, fila, col, k,j+1,fila-1)
         
         
       }else{desplazar(tablero, fila, col, k+1,0,fila-1)}
       
       
       
     } else tablero
     
     
     
   }
     
     
     def desplazarDerecha (tablero:List[Int],fila:Int,col:Int,k:Int,j:Int,i:Int):List [Int]={
     
//'for' recursivo

     if (k<col) {
       if(j<fila){
         
         if(i<col-1){
           if(getPosicion(j*col+i, tablero, 0)== -1) //Si la posicion de la derecha esta vacia
           {
             
            val listaux1= poner(getPosicion(j*col+i+1, tablero, 0), j*col+i, tablero)
            val tableroaux1= poner (-1, j*col+i+1, listaux1) //hacemos swap
            desplazarDerecha(tableroaux1, fila, col, k,j,i+1) //Le pasamos de nuevo el tablero para que siga comprobando
           }else{desplazarDerecha(tablero, fila, col, k,j,i+1)}
           
     
         }else desplazarDerecha(tablero, fila, col, k,j+1,0) //Cambiamos de fila
         
         
       }else{
	desplazarDerecha(tablero, fila, col, k+1,0,0)  //Cambiamos de columna

	}
       
     } else tablero
     
   }

     
     def optimo_puntos(lista:List[Int],y:Int,x:Int,puntos:Int):List[Int]=   // Metodo para comparar las jugadas y guardar la mejor
     {
       
       if(lista.head==puntos)lista
       else
       { if(lista.head>puntos)lista
         else List(puntos,y,x)
         
         
         
       }
      
 }
     
     
     
     def optimo(tablero:List[Int],fila:Int,col:Int,puntos:Int,vidas:Int,y:Int,x:Int,eliminados_aux:List[Int],puntosaux:Int,Lista:List[Int],totalBloques:Int)
     {

      if(y<fila){
         
         if(x<col){
           
           
           
         if((x >= 0 && y >= 0) && (y <= fila - 1 && x <= col - 1)) //Posicion dentro del tablero
     	    {
     	      val bloque = getPosicion(y*col+x, tablero, 0) //Empezaría con la primera posicion del tablero
     	      if(bloque ==8 && bloque != -1  )              //Si el bloque es una bomba
     	      {						   //Se eliminan las posiciones con el metodo bombas
     	        val tableron = bombas(poner(0,y*col+x,tablero), y, x, fila, col, bloque)
     	        val eliminados=compruebaPuntos(tableron, 0,Nil)  //Lista con las posiciones eliminadas
     	        val puntosactuales=tam(eliminados,0)		//Sabiendo cuantas posiciones se han eliminado sacamos los puntos
     	        val listas=optimo_puntos(eliminados_aux, y,x, puntosactuales)  //Si la jugada es mejor que la anterior actualiza 
									       //con los puntos actuales y la posicion
     	        optimo(tablero, fila, col, puntos, vidas, y, x+1, listas, listas.head,Nil,totalBloques)
     	      
     	     
     	      
     	    }
     	    else   //Si no es una bomba
     	    {
     	      if(bloque != -1)  //Si el bloque es un bloque NO eliminado
     	      {
     	        val tableron = posicionValida(tablero, y, x, fila, col, bloque) //Comprueba las posiciones de sus alrededores
     	        val eliminados=compruebaPuntos(tableron, 0,Nil)                //Lista de eliminados
     	        val puntosactuales=tam(eliminados,0)                           //Sacamos el tamaño de la lista de eliminados
     	        
     	        if(puntosactuales>=3){   //Si se ha eliminado más de 3 se estudiará la nueva jugada con la anterior
     	          val listas= optimo_puntos(eliminados_aux, y,x, puntosactuales)
     	          optimo(tablero, fila, col, puntos, vidas, y, x+1, listas, listas.head,Nil,totalBloques)
     	        }
     	        else    //Si no se eliminan más de 3 no se hace nada en esa jugada
     	        {
     	           val listas= optimo_puntos(eliminados_aux, y,x, puntosactuales)
     	           optimo(tablero, fila, col, puntos, vidas, y, x+1, listas, listas.head,Nil,totalBloques)
     	        }
		//Cambiamos de columna
     	      }else{optimo(tablero, fila, col, puntos, vidas, y, x+1, eliminados_aux, eliminados_aux.head,Nil,totalBloques)}
     	     }
     	   }
     	  else
        {
          println("Posicion  Invalida")
          jugar(tablero, fila, col, puntos, vidas,dificultad,bombas,colores)
        }
           
         //cambiamos de fila 
      }else optimo(tablero, fila, col, puntos, vidas, y+1, 0, eliminados_aux, eliminados_aux.head,Nil,totalBloques)
         
       
       }
       else 
       { 
         println()
         println()
         
         val x1 = getPosicion(2, eliminados_aux, 0)  //Sacamos la coordenada x
         val y1 = getPosicion(1, eliminados_aux, 0)  //Sacamos la coordenada y 
         val bloqueaux =getPosicion(y1*col+x1, tablero, 0)   //Traemos el bloque con esa posicion
        
         if( bloqueaux==8 && bloqueaux!= -1)  //Si es una bomba
           {  
            val tableron = bombas(poner(0,y1*col+x1,tablero), y1, x1, fila, col,bloqueaux)
     	      val eliminados=compruebaPuntos(tableron, 0,Nil)
     	      val puntosactuales=tam(eliminados,0)
     	      val tableronew = eliminar(eliminados, tableron)
     	      val tableroprueba = desplazar(tableronew, fila, col, 0, 0, fila-1)
     	      val tablerofinal = desplazarDerecha(tableroprueba, fila, col, 0, 0, col-1)
     	      print("Puntos: " +( puntos + ((getPosicion(0, eliminados_aux, 0)*10)))) 
     	      
            println(" Vidas: "+ vidas)
     	      imprimir(tablerofinal,1,col,1,0,fila)
           }
           else
           {
             if(bloqueaux != -1)  //Posicion no eliminada ni bomba
             {

              val tableron = posicionValida(tablero, y1, x1, fila, col,bloqueaux)  //Vemos que podemos eliminar
     	        val eliminados=compruebaPuntos(tableron, 0,Nil)  //Lista de eliminados
     	        val puntosactuales=tam(eliminados,0)		
     	       
     	        val tableronew = eliminar(eliminados, tableron)  //Ponemos en -1 las posiciones
     	        val tableroprueba = desplazar(tableronew, fila, col, 0, 0, fila-1)
     	        val tablerofinal = desplazarDerecha(tableroprueba, fila, col, 0, 0, col-1)
     	        print("Puntos: " + ( puntos + ((getPosicion(0, eliminados_aux, 0)*10)))) 
              println(" Vidas: "+ vidas)
     	        imprimir(tablerofinal,1,col,2,0,fila)
              
            }
          }
         
          
       
       }

     }
     
   
    
     
     def jugar(tablero:List[Int],fila:Int,col:Int,puntos:Int,vidas:Int,dificultad:Int,bomba:Int,colores:Int){
      
      if(vidas>0)
      { print("Puntos: " +puntos) 
        println(" Vidas: "+ vidas)
        imprimirX(col,1)
        imprimir(tablero,1,col,2,0,fila)
        
     	  println("Fila")
    	  val y1 =readInt
     	  println("Columna")
     	  val x1 = readInt
     	  val x = x1-1
     	  val y = y1-1
     	  
     	  if((x >= 0 && y >= 0) && (y <= fila - 1 && x <= col - 1)) //Posiciones dentro del tablero
     	  {
     	    val bloque = getPosicion(y*col+x, tablero, 0)
     	    if(bloque ==8 && bloque != -1)          //Bomba
     	    {
     	      val tableron = bombas(poner(0,y*col+x,tablero), y, x, fila, col, bloque) //Tablero con posiciones eliminadas
     	      val eliminados=compruebaPuntos(tableron, 0,Nil) //Lista con las posiciones eliminadas
     	      val puntosactuales=tam(eliminados,0) //Sacamos el tamaño de la lista de eliminados para sacar los puntos
     	  
     	        val tableronew = eliminar(eliminados, tableron)  //Ponemos a -1 para que salga en blanco
     	        val tableroprueba = desplazar(tableronew, fila, col, 0, 0, fila-1) 
     	        val tablerofinal = desplazarDerecha(tableroprueba, fila, col, 0, 0, col-1)

		//Si el tablero está vacío creamos un nuevo tablero para empezar una partida     	        
     	        if( compruebaFinal(tablerofinal, 0)==fila*col)    new tablero(dificultad,fila,col,bomba,(puntosactuales*10)+puntos,vidas,colores,2)             

		//Si no está vacío seguimos jugando
     	        else jugar(tablerofinal,fila,col,(puntosactuales*10)+puntos,vidas,dificultad,bomba,colores)
     	      
     	     
     	      
     	    }
     	    else
     	    {
     	      if(bloque != -1)  //Bloques no eliminados
     	      {
     	      
     	        val tableron = posicionValida(tablero, y, x, fila, col, bloque)
     	        val eliminados=compruebaPuntos(tableron, 0,Nil)
     	        val puntosactuales=tam(eliminados,0)
     	  
     	        if(puntosactuales>= 3)  //Si se eliminan 3 o más
     	        {  
     	          val tableronew = eliminar(eliminados, tableron)
     	          
     	          val tableroprueba = desplazar(tableronew, fila, col, 0, 0, fila-1-1)
     	          val tablerofinal = desplazarDerecha(tableroprueba, fila, col, 0, 0,0)
     	          
     	          if( compruebaFinal(tablerofinal, 0)==fila*col) new tablero(dificultad,fila,col,bomba,(puntosactuales*10)+puntos,vidas,colores,2)     

     	          else jugar(tablerofinal,fila,col,(puntosactuales*10)+puntos,vidas,dificultad,bomba,colores)
     	        
     	          
     	        }
     	        else  //Si se eliminan menos de 3 bloque se restan vidas
     	        {  
     	        
     	          val l= desplazar(poner(-1,y*col+x,tablero), fila, col, 0, 0, fila-1)  //Solo eliminamos la posicion pulsada
     	          val tablerofinal=desplazarDerecha(l, fila, col, 0, 0, 0)		//Desplazamos
     	          
     	          if( compruebaFinal(tablerofinal, 0)==fila*col) new tablero(dificultad,fila,col,bomba,puntos,vidas-1,colores,2)                    
     	          else jugar(tablerofinal,fila,col,puntos,vidas-1,dificultad,bomba,colores)
     	        }
     	      }else jugar(tablero,fila,col,puntos,vidas-1,dificultad,bomba,colores) // si es -1
     	    }
     	  }
     	  else
        {
          println("Posicion  Invalida")
          jugar(tablero, fila, col, puntos, vidas,dificultad,bomba,colores)
        }
      }
      else
      {
        println("Se le han agotado las vidas, ha perdido del juego")
        
      }
      
      
     }
     
     
}

    

    

  
  
  
    

    
   
    
   

  
    

  
  
  
  
  
  
  
