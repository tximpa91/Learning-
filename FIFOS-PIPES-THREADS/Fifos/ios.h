//
//  ios.h
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//

#ifndef ____ios__
#define ____ios__


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


int io (int readend,int writeenedpipe, int writeenedpipe2, char * process);
#endif /* defined(____ios__) */
