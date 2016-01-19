
#include "ios.h"
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




void split (char all[SIZE], char half1[SIZE_HALF], char half2[SIZE_HALF])
{

    int a;
    a = strlen (all) / 2;
    strncpy (half1, all, a);
    half1[a] = '\0';
    strcpy (half2 , all+a);
}



int io (int readend,int writeenedpipe, int writeenedpipe2, char * process){
    
    
    int r;
    char all[SIZE]; char half1[SIZE_HALF]; char half2[SIZE_HALF];
    
    
    printf ("Type lines of text (Ctrl+D to finish):\n");
    
    for (;;)
    {
        if (fgets(all, SIZE ,stdin))
        {
            
            split(all,half1,half2);
            if ((write(writeenedpipe, half1, 1+strlen(half1))<0) || (write(writeenedpipe2,half2,1+strlen(half2)) <0 ))
                break;
            else printf("%s  : transmiting %lu bytes.\n", process,strlen(all));
                    }
        else
        {
            close (writeenedpipe);
            close(writeenedpipe2);
        }
        
        r = read (readend, all, SIZE);
        
        if (r<0)
            break;
        else if (r==0)
            return 0;
        
        fputs(all, stdout);
           }
    perror("io");
    return -1;    
    
    
}
