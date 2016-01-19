//
//  io_mux.c
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//
#include "ios.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int main(int argc, char * argv[])
{
    int fiforead,fifowrite,fifowrite_2;
    
    
    if ((argc == 4) || (argc == 5))
    {
	
        fiforead = open(argv[1], O_RDONLY);
        if (fiforead < 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	fifowrite = open(argv[2], O_WRONLY);
        if (fifowrite < 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	fifowrite_2 = open(argv[3], O_WRONLY);
        if (fifowrite_2< 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	io(fiforead,fifowrite,fifowrite_2,argv[4]);
	perror ("io");
        return -1;
        close (fiforead);
        close (fifowrite);
        close (fifowrite_2);
    }
    else	
    {
        printf("INCORRECT ORDER OR PARAMETERS\n");
        return -1;
    }

}

