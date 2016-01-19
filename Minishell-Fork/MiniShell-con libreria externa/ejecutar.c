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


void child(int signum) {
    int status;


     
     waitpid(-1, &status, WNOHANG); //recollecta todos los hijos zombies sin especificar cual

}

void varias_ordenes(char *orden){

char *token = strtok(orden , ";"); /*Separamos por espacios*/ 

        while(token !=NULL){
                ejecutar_linea_ordenes(token); //aca obtenemos la orden sin el punto y coma y ejecutamos 
                token = strtok(NULL,";");
        }
         
        }


int ejecutar_orden(const char *orden, int *pbackgr){

int indice_entrada;
int indice_salida;
char ** comando;
int pid;
comando = (char **) parser_orden(orden ,&indice_entrada ,&indice_salida ,pbackgr );
if (comando==NULL){


return -1;
}else{
 if((pid=fork())==0){

//hijo
if(execvp(comando[0],comando)==-1){
//ERROR 

printf("Introdujo una orden incorrecta\n");

exit(0);
}



}
else {
return pid;
}




}
free_argumentos(comando);
return 0;
}




void ejecutar_linea_ordenes( const char *orden)
{
int pid ; 
int backgr;
int status;

pid=ejecutar_orden(orden,&backgr);

if (pid==-1){

printf("Error en la minishell\n");

}else{

if (backgr==0){

waitpid(pid,&status,0);// este es el caso cuando no hay ampersan


}
else{

struct sigaction sa;
sa.sa_handler=child;
sa.sa_flags =0;
sigaction(SIGCHLD,&sa,NULL);//tratamiento de hijo zombie


}
}



//fin
}














