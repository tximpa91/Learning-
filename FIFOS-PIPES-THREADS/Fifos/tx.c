#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include "caps.h"

int main(int argc, char * argv[])
{
    int fiforead,fifowrite;

    if ((argc == 3) || (argc == 4)){
	 
        fiforead = open(argv[1], O_RDONLY);
        if (fiforead < 0)
        {
            printf("ERROR IN PIPE \n");
            return -1;
        }
	fifowrite = open(argv[2], O_WRONLY);
        if (fifowrite < 0)
        {
            printf("ERROR IN PIPE");
            return -1;
        }
	caps(fiforead,fifowrite,argv[3]);
	perror ("caps");
        return -1;
        close (fiforead);
        close (fifowrite);
    }
    else
    {
        printf("INCORRECT ORDER OR PARAMETERS");
    }
    return 0;
}
