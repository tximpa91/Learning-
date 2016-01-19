#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int CountCharacters (int , char );
char ReadCharacter(int , int );
int Rchar(int ,char  );

int main  (int argc, char * argv[])
{	char caracter;
	int archivo=0;

	if ( argc != 4)
	{
		printf ("The number of arguments is invalid \n");
		printf ("The correct format is ./count R file character or ./count M file character \n");
	return -1;}


	if (strcmp("R",argv[1])==0)
	{	
		archivo=open(argv[2],O_RDONLY);
	 	
	 	if (archivo >=3)
	  {		caracter=*argv[3];
			printf("The character has " "%d" " ocurrences\n",Rchar(archivo,caracter));
	   }

		 
		else{
			printf("Error opening the file\n");
	       	return -1;				 }	 		
           }
	 else{
	     if (strcmp("M",argv[1])==0)
        {
			archivo=open(argv[2],O_RDONLY);
	     if (archivo>=3)
	     { 
			caracter=*argv[3];
	        printf("The character has " "%d" " ocurrences\n",CountCharacters(archivo,caracter));
			}
	     
             else{
					printf("Error opening the file\n");	} 
					return -1;

			}
		 else{	
				printf("Invalid Order \n");
				return -1;


				}
		}



	close(archivo);
	

	return 0;


}


int Rchar(int fd,char caracter)
{
	
	int i;
	int total=0;
	int length;
	
	length = lseek(fd,0,SEEK_END);
	lseek(fd,0,SEEK_SET);
	
	for (i=0; i< length ; i++)
    {
      		if (caracter==ReadCharacter(fd,i))
		   total=total+1;
         } 

	
	return total;

}






