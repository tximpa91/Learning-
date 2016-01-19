//
//  demux.c
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//
#include "demultiplexer.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>


int main(int argc, char * argv[])
{
    int fiforead,fiforead_2,fifowrite;
    
    if ((argc == 4) || (argc == 5))
    {	
	fifowrite = open(argv[3], O_WRONLY);
        if (fifowrite < 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	fiforead = open(argv[1], O_RDONLY);
        if (fiforead < 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	fiforead_2 = open(argv[2], O_RDONLY);
        if (fiforead_2 < 0)
        {
            printf("ERROR IN PIPE\n");
            return -1;
        }
	demultiplexer (fiforead,fiforead_2,fifowrite,argv[4]);

        perror ("demux");
        return -1;
	

    }
    else	
    {
        printf("INCORRECT ORDER OR FORMAT\n");
    }
    return 0;
}
