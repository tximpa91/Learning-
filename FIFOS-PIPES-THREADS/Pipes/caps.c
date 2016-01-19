//
//  caps.c
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//

#include "caps.h"

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





int caps (int readend,int writeened, char * procces){
    int i, r;
    char buff[SIZE];
    
    srand ((unsigned) getpid() ^ (unsigned) time(NULL));
    
    for (;;)
    {
        r = read (readend, buff, SIZE);
        
        if (r<0)
            break;
        else if (r==0)
        {
            
            close (writeened);
            return 0;
        }
        
       
        for (i =0; buff[i]; i++)
            if (rand()>RAND_MAX/2)
                if (buff[i]>='a' && buff[i]<='z')
                    buff[i] -= ('a' - 'A');
        
        
        
        if (write(writeened, buff, 1+strlen(buff))<0)
            break;
        else printf(" %s : transmiting caps %lu bytes.\n",procces, strlen(buff));
        
    }
    
    perror("caps");	// Error !
    return -1;		// Mostrar mensaje y salir
    
}
