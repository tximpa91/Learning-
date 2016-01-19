#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#define n_coches 8

pthread_t tabla_hilos[n_coches];
//variable global
int suma=0;


pthread_mutex_t mutex;



typedef struct {
int id;
char *cadena;
}hilos_param;

typedef struct{

int id;
char *cadena;
}orden;

orden posiciones [n_coches];
hilos_param parametros [n_coches];

void *funcion_coches(hilos_param *p){
int aleatorio;
printf(" Salida %s %d \n", p->cadena,p->id);
fflush (stdout);
aleatorio=rand();
//bloqueamos en esta parte ya que viene la seccion critica de codigo para que cada hilo pueda ejecutar esta instruccion sin problemas
pthread_mutex_lock(&mutex);
suma=suma+aleatorio;
pthread_mutex_unlock(&mutex);
//desbloqueamos aqui ya que en este momento ya superamos la seccion critica de codigo y por que  no queremos interferir con el orden de llegada de los coches
sleep(1 + (aleatorio%4));
printf("Llegada %s %d \n",p->cadena,p->id);


pthread_exit(&suma);


}




int main (){

int i, *res;

printf("Inicio proceso de creacion de los hilos ....\n");


for(i=0;i<n_coches;i++){
parametros[i].id=i;
parametros[i].cadena="coche";

if(pthread_create(&tabla_hilos[i],NULL,(void *)&funcion_coches,(void *)&parametros[i])==0){
printf("Hilo creado correctamente hilo %d \n",i);



}else{

printf("Error en crear el hilo \n");


}


}



printf("Proceso de creacion de hilos terminado\n");
printf("Salida de coches\n");
//inicio del semaforo 
pthread_mutex_init(&mutex,NULL);


for(i=0;i<n_coches;i++){
//aca espereamos que cada hilo termine su proceso
pthread_join(tabla_hilos[i],(void **)(&res));


}
//destruimos el semaforo ya que hemos terminado de necesitarlo
pthread_mutex_destroy(&mutex);


printf("Todos los coches han LLegado a la Meta, el valor aleatorio: %d \n",*res);



return 0;
}
