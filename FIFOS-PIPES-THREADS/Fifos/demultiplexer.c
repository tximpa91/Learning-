//
//  demultiplexer.c
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//

#include "demultiplexer.h"
#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <time.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <signal.h>
#define SIZE 100
#define SIZE_HALF (SIZE/2)+1





void concatenate (char all[SIZE], char half1[SIZE_HALF], char half2[SIZE_HALF])
{
   strcpy (all, half1);
   strcat (all, half2);
    
}



int demultiplexer(int readpipe, int readpipe1,int writepipe,char *process)
{

    int r1,r2;
    char half1[SIZE_HALF]; char half2[SIZE_HALF]; char all[SIZE];
    


    for (;;)
    {
        r1 = read (readpipe, half1, SIZE_HALF);
        r2 = read  (readpipe1,half2,SIZE_HALF);
        
        if ((r1 <0) || (r2 <0))
            break;
        else if ((r1==0) && (r2==0))
        {
            
            close (writepipe);
            return 0;
        }
        
    
        concatenate(all,half1,half2);

        if (write(writepipe, all, 1+strlen(all))<0)
            break;
        else printf(" %s : transmiting caps %lu+%lu bytes.\n", process,strlen(half1),strlen(half2));
        
    }
    
    perror("Demultiplexer");	// Error !
    return -1;		// Mostrar mensaje y salir









}
