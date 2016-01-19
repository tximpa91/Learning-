/*
    example.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "parser.h"
#include "orders.h"
#include <time.h>



    
        



void show_command (command * C);
int  execute( command * C);

int main ()
{
    command C;
    int r;
    char root_all[512];
    char  root[128];
    time_t timer;
    struct tm *tlocal;
   
    

    

    do              // Read commands and show them
    {
	timer=time(0);
	tlocal=localtime(&timer);
	strftime(root_all, sizeof(root_all), " %a %b %Y-%m-%d %H:%M", tlocal);
	getcwd(root,sizeof(root));
	strcat(root_all," ");
	strcat(root_all,root);
	init_command (&C);
	
        printf ("%s " ,root_all);

  
	r = read_command (&C, stdin);
	
	if (C.argc!=0){
	
		
	
        if (r<0)
            fprintf (stderr, "\nError %d: %s\n",
                             -r, err_messages[-2]);
        else
          
	
	    execute(&C);

        free_command (&C);
	
    	}}
    while (r==0);   // Repeat until error or EOF

    return 0;
}

void show_command (command * C)
{
    int i;

    printf ("\tRaw command: \"%s\"\n", C->raw_command);
    printf ("\tNumber of arguments: %d\n", C->argc);

    for (i=0; i<=C->argc; i++)
        if (C->argv[i] != NULL)
            printf ("\t\targv[%d]: \"%s\"\n", i, C->argv[i]);
        else
            printf ("\t\targv[%d]: NULL\n", i);

    if (C->input)
        printf ("\tInput: \"%s\"\n", C->input);

    if (C->output)
        printf ("\tOutput: \"%s\"\n", C->output);

    if (C->output_err)
        printf ("\tErr. output: \"%s\"\n", C->output_err);

    printf ("\tExecute in background: %s\n",
            C->background ? "Yes" : "No");
}

int execute (command * C){

	if (strcmp(C->argv[0],"exit")==0)
		exit(0);

	if (strcmp(C->argv[0],"mypwd")==0)
		return mypwd(C->argc);

	if (strcmp(C->argv[0],"myls")==0)
		return myls(C);

	if(strcmp(C->argv[0],"mymkdir")==0)
		return mymkdir(C);

	if(strcmp(C->argv[0],"myrmdir")==0)
		return myrmdir(C);

	if(strcmp(C->argv[0],"mycd")==0)
		return mycd(C);

	if(strcmp(C->argv[0],"mycat")==0)
		return mycat(C);

	if(strcmp(C->argv[0],"mycp")==0)
		return mycp(C);

	if(strcmp(C->argv[0],"myrm")==0)
		return myrm(C);
	
	if(strcmp(C->argv[0],"myclear")==0)
		return myclear(C);
	




	

	return 0;
}



