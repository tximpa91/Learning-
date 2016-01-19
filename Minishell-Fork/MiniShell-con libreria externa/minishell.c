#include <stdio.h>   
#include <sys/types.h>
#include <unistd.h>  
#include <stdlib.h>
#include <sys/wait.h> 
#include <string.h> 
#include "internas.h"
#include "parser.h"
#include "ejecutar.h"
#include "libmemoria.h"
#include <signal.h>
#include "entrada_minishell.h"






int main ( int argc, char *argv[]){


char buf [ BUFSIZ ];



while (1){

imprimir_prompt();
leer_linea_ordenes(buf);
if (strcmp(buf,"exit")==0){
printf("Hasta luego\n");
exit(0);
}
else {
if(ord_interna(buf)==0){

//externa
varias_ordenes(buf);
}

}

}

return 0;


}


