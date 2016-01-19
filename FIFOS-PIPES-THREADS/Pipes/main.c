//
//  ios.h
//  
//
//  Created by Luis Angel Parada on 25/12/14.
//
//

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
#include "caps.h"
#include "ios.h"
#include "demultiplexer.h"
#define SIZE 100
#define SIZE_HALF (SIZE/2)+1





int main (){
    
    int ios[2], TA[2], TA_1[2], TB_1[2], TB_2[2], TB_3[2],d ;
    pid_t proces1,proces2,proces3,proces4;
    int status;
  


	if (pipe(ios) || pipe(TA) || pipe(TB_1)){
		perror("pipe '1'");
		return -1;
		}
	//Child1
	proces1=fork();
	switch (proces1){
		case -1:
			perror("fork '1'");
			return -2;


		case 0:
			//close the ends which not use for recive EOF
			close(ios[1]);
			close(TA[0]);
			close(TB_1[0]);
			getpid();
			return io(ios[0], TA[1], TB_1[1],"Multiplexor ");


		default:
			if (pipe(TA_1) || pipe(TB_2) || pipe(TB_3)){
				perror("pipe '2'");
				return -1;
			}
		//child 2
		proces2=fork();
		switch (proces2){
			case -1:
				perror("fork '2'");
				return -2;

			case 0:
				//grandchild
				proces3=fork();
				switch (proces3){

					case -1:
						perror("proces nested'2_1'");
						return -2;

					case 0:
						//close the ends which not use for recive EOF
						close(ios[1]);
						close(ios[0]);
						close(TA[1]);
						close(TA[0]);
						close(TA_1[1]);
						close(TA_1[0]);
						close(TB_1[1]);
						close(TB_2[0]);
						close(TB_3[1]);
						close(TB_3[0]);
						d = caps(TB_1[0], TB_2[1],"TB_1 -> TB_2");
						return d;

					default:
						//close the ends which not use for recive EOF
						close(ios[1]);
						close(ios[0]);
						close(TA[1]);
						close(TA[0]);
						close(TA_1[1]);
						close(TA_1[0]);
						close(TB_1[1]);
						close(TB_1[0]);
						close(TB_2[1]);
						close(TB_3[0]);
						d = caps(TB_2[0], TB_3[1],"TB_2 -> TB_3");
						waitpid(proces3,&status,0);
						return d;


			}

			default:
				//child 3
				proces4=fork();
				switch (proces4){
					case -1:
						perror("fork '2_2'");
						return -2;

					case 0:
						//close the ends which not use for recive EOF
						close(ios[1]);
						close(ios[0]);
						close(TA[1]);
						close(TA_1[0]);
						close(TB_1[1]);
						close(TB_1[0]);
						close(TB_2[1]);
						close(TB_2[0]);
						close(TB_3[1]);
						close(TB_3[0]);

						d= caps(TA[0], TA_1[1], "TA   -> TA_1");
						return d;
					default:
						close(ios[0]);
						close(TA[1]);
						close(TA[0]);
						close(TA_1[1]);
						close(TB_1[1]);
						close(TB_1[0]);
						close(TB_2[1]);
						close(TB_2[0]);
						close(TB_3[1]);
						d = demultiplexer(TA_1[0],TB_3[0],ios[1],"Demultiplexor");
						waitpid(proces1,&status,0);
						waitpid(proces2,&status,0);
						waitpid(proces4,&status,0);
						return d;

			} //child 3
		} // child 2
	}//child 1
} // end main


