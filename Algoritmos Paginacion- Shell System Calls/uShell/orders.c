#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "parser.h"
#include <dirent.h>
#include <string.h>
#include <time.h>
#include <sys/mman.h> 



int myrm(command *C){
	 
	if (C->argc==2){
		if( (unlink(C->argv[1]))!=0){
			printf("Error\n");
			return -1;}

		           }
		else{
			
			printf("Error to many arguments\n");
			return -1;
			}
	
	
	return 0;
	
	}
	
int mycp(command *C){
	int fd,fd2,length;
	char buffer[5024];

	if(C->argc==3){
		
		fd=open(C->argv[1],O_RDONLY);
		fd2=open(C->argv[2],O_WRONLY|O_CREAT,0644);
		
		if(fd<3){
			printf("Error opening the file\n");
			return -1;
			}
			
		if (fd2<3){
		
			
			printf("Error creating the file\n");
			return-1;
			}
			
			
			
			length=lseek(fd,0,SEEK_END);
				   lseek(fd,0,SEEK_SET);
			
			read(fd,buffer,length);
			write(fd2,buffer,length);
			
			close(fd);
			close(fd2);
			
	}else{
			
			printf("Error in arguments\n");
			
			return -1;
			}
	
	
		return 0;
			
	
	}
int mypwd(int argc){
	char buffer[512];
	char *addres;
	


	if(argc!=1)
	return -1;
	addres=getcwd(buffer,sizeof(buffer));

	if (addres!=NULL)
		printf("%s \n",addres);
	else
		return -1;

	return 0;
}

int myls (command *C){
	
	char date [120];
	char buffer[512];
	char directorioactual[512];
	struct dirent *direntp;
	DIR *dirp;
	struct stat  statbuf;
	struct tm *fecha;
	time_t info;




	if (C->argc ==1){
		getcwd(buffer,sizeof(buffer));
		dirp= opendir(buffer);
			
		if (dirp==NULL){
		printf("Error open the directory\n");
		return -1;
			}
		direntp = readdir(dirp);
	
		while(direntp!=NULL){
			
			if( (strcmp(direntp->d_name,"..")!=0) && (strcmp(direntp->d_name,".")!=0))
				printf( "%s\n", direntp->d_name );
				direntp = readdir( dirp );
		}
	
		if (closedir(dirp)==-1){
			printf("Error closing the directory \n");
		return -1;}
	return 0;
	} //argc1
	else{ // argc 2
		
		if (C->argc ==2 && (strcmp(C->argv[1],"-l")!=0)){
		
		
			dirp=opendir(C->argv[1]);
		if (dirp==NULL){
			printf("Error open the directory\n");
		return -1;}
		
		direntp = readdir( dirp );
	
		while(direntp!=NULL){
	
		if( (strcmp(direntp->d_name,"..")!=0) && (strcmp(direntp->d_name,".")!=0))
	
			printf( "%s\n", direntp->d_name );
	
			direntp = readdir( dirp );
					}
		
			printf("\n");	
	 	 if (closedir(dirp)==-1){
			printf("Error closing the directory \n");
			return -1;
			}
		
	
		return 0;
		}
		else{
			
		if ((C->argc ==2) && (strcmp(C->argv[1],"-l")==0)){
			getcwd(buffer,sizeof(buffer));
			dirp= opendir(buffer);	
 		if (dirp==NULL){
			printf("Error open the directory\n");
		return -1;}
		
		direntp = readdir( dirp );
	
		while(direntp!=NULL){
	
		if( (strcmp(direntp->d_name,"..")!=0) && (strcmp(direntp->d_name,".")!=0)){
			if((stat(direntp->d_name,&statbuf)==0)){
				
				info=statbuf.st_mtime;
				fecha=localtime(&info);
 				strftime(date, sizeof(date), " %b %Y-%m-%d %H:%M", fecha);
				printf( "%d\t" "%d\t" "%d\t" "%d\t" "%s\t"  "%s\t\n",statbuf.st_mode,statbuf.st_uid,statbuf.st_gid,(int)statbuf.st_size,date,direntp->d_name );
								}	
			}
	
		direntp = readdir( dirp );
		}
	
		if (closedir(dirp)==-1){
			printf("Error closing the directory \n");
		return -1;
		}
		return 0;}//fin arg2
		
		}
		
		if ((C->argc ==3) && (strcmp(C->argv[1],"-l")==0)){
			
			dirp= opendir(C->argv[2]);
			if (dirp==NULL){
				printf("Error open the directory\n");
				return -1;
				}
			direntp = readdir(dirp);
			
			
			
			
			
			while(direntp!=NULL){

				strcpy(directorioactual,C->argv[2]);
				strcat(directorioactual,"/");
				if( (strcmp(direntp->d_name,"..")!=0) && (strcmp(direntp->d_name,".")!=0)){
					strcat(directorioactual,direntp->d_name);
					
					if((stat(directorioactual,&statbuf)==0)){
						
						info=statbuf.st_mtime;
						fecha=localtime(&info);
						strftime(date, sizeof(date), " %b %Y-%m-%d %H:%M", fecha);
						printf( "%d\t" "%d\t" "%d\t" "%d\t" "%s\t"  "%s\t\n",statbuf.st_mode,statbuf.st_uid,statbuf.st_gid,(int)statbuf.st_size,date,direntp->d_name );
								}	
		
	
					
						}
			
	
			direntp = readdir(dirp);
			}
			
			if (closedir(dirp)==-1){
				printf("Error closing the directory \n");
			return -1;}
		
		
		return 0;}
		else{ 
			printf("Error do not admit these parameter\n");
			return -1;
			
			}
		
		}//else arg11
		
		
	return 0;
}

int mymkdir(command *C){
	

	if (C->argc==2){
		

	
		if( (mkdir(C->argv[1],755))!=0){
			printf("Error\n");
			return -1;}

		
		
		}
		else{
			
			printf("Error to many arguments\n");
			
			return -1;
			}
	return 0;
}

int myrmdir(command *C){
	
	
	
	if (C->argc==2){
		
	
	
		if( (rmdir(C->argv[1]))!=0){
			printf("Error\n");
			return -1;}

		
		
		}
		else{
			
			printf("Error to many arguments\n");
			
			return -1;
			}
	
	
	return 0;
	
	}

int mycd(command *C){
	
	
	char buffer[512];
	
	if(C->argc==1){
		getcwd(buffer,sizeof(buffer));
		
		if ((chdir(buffer)==-1)){
			
			printf("Error\n");
			return -1;}
			
			}
		
	else{
		if(C->argc==2){
			if ((chdir(C->argv[1])==-1)){
				printf("Error\n");
				return -1;}
			
						}
					
					
					
					
					
		else{   printf("ERROR to many arguments\n");
			return -1;
						
						}
			
			
			
			}
	return 0;
	}

int mycat(command *C){
			

	char *addres;
	int length;
	int fd;
	
	if (C->argc==2){
		fd=open(C->argv[1],O_RDONLY);
	if( fd <3){
		printf("Error opening the file\n");
		return -1;
		}
		length=lseek(fd,0,SEEK_END);


		addres=(char *)mmap(NULL,length,PROT_READ,MAP_SHARED,fd,0);
		printf("%s",addres);
	if (munmap(addres, length)!=0){
		printf("Problem with free memory\n");}

	}else{	printf("Error \n");
		return -1;
			
			
			}
	return 0;
	
	}

int myclear(command *C){


	system("clear");
	return 0;

	}
