#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/mman.h> 

int CountCharacters (int fd, char character)
{ 

	int total=0;
	int i;
	int length=lseek(fd,0,SEEK_END);
	char *addres;

	addres=(char *)mmap(NULL,length,PROT_READ,MAP_SHARED,fd,0);
	for (i=0; i<length; i++)
	{  if(addres[i]==character)
	    total=total+1;
					}
	
	if (munmap(addres, length)!=0){
		printf("Problem with free memory\n");}


	
	return total;


}

