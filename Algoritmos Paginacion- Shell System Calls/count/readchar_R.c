#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>



char ReadCharacter(int fd, int position)
{

	char letter=0;
	ssize_t test=0;

	lseek(fd,position,SEEK_SET);

	test=read(fd,&letter,1);
	
	
	if(test!=1)
		printf("Had occur an error reading de file\n");
	
	return letter;
}
