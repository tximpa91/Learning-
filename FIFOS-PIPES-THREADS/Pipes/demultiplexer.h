//
//  demultiplexer.h
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//

#ifndef ____demultiplexer__
#define ____demultiplexer__

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
int demultiplexer(int readpipe, int readpipe1,int writepipe,char *process);


#endif /* defined(____demultiplexer__) */
