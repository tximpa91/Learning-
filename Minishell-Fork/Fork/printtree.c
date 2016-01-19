
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>

#include "printtree.h"

// Maximum number of characters per line
#define LINE_LEN 80
// Maximum number of lines
#define LINE_NUM 50

// Read/write ends of the pipe
#define READ_END 0
#define WRITE_END 1

// These are the descriptors for the pipes to communicate the tasks with their
// parent and with their children respectively

static int to_parent[2];
static int to_children[2];

// This is the pointer to the string containing the name of the current task
static char * this_name;

/**
 * This function initializes the tree mechanism. It creates the pipe to 
 * communicate the main task (main) with their children.
 *
 * @param name The name of the main task that will be shown when the tree is
 *             generated.
 */
void tree_init(char * name)
{
    this_name = name;
    if (pipe(to_children) == -1)
    {
        perror("pipe");
        exit(-1);
    }
}

/**
 * This function ends the tree mechanism. It collects the information coming
 * from the children of the main task and prints the resulting tree.
 */
void tree_finish()
{
    // Buffer to store the information coming from the children
    char child_buffer[LINE_NUM][LINE_LEN];
    // Buffer to store the information that will be printed
    char print_buffer[LINE_NUM][LINE_LEN];

    int i = 0, j = 0, k = 0;
    int n = 0;
    int len = 0;

    // We have to initialize the buffers
    for (i = 0; i < LINE_NUM; i++)
    {
        print_buffer[i][0] = '\0';
        child_buffer[i][0] = '\0';
    }

    // We are the main function, so we are not going to write on this end of
    // the pipe
    close(to_children[WRITE_END]);

    snprintf(print_buffer[0], LINE_LEN, "%s", this_name);

    i = 0;

    // Main loop that will collect the information sent by the children and
    // store it in the print buffer
    while((n = read(to_children[READ_END], child_buffer, sizeof(child_buffer))) > 0)
    {
        // If it's empty -> nothing to be done
        if (child_buffer[0][0] == '\0')
        {
            continue;
        }
        if (i == 0)
        {
            len = strlen(print_buffer[0]);
            strncat(print_buffer[0], " --- ", LINE_LEN - 1 -
                   len); 
            len = strlen(print_buffer[0]);
            strncat(print_buffer[0], child_buffer[0], LINE_LEN - 1 -
                    len);
            i++;
        }
        else
        {
            if (strlen(this_name) + 2 < LINE_LEN - 1)
            {
                for (j = 0; j < i; j++)
                { 
                    if (print_buffer[j][strlen(this_name) + 2] == '-')
                    {
                        print_buffer[j][strlen(this_name) + 2] = '+';
                    }
                    else if (print_buffer[j][strlen(this_name) + 2] == ' ' ||
                             print_buffer[j][strlen(this_name) + 2] == '`')
                    {
                        print_buffer[j][strlen(this_name) + 2] = '|';
                    }
                }
            }
            for (k = 0; (k < strlen(this_name)) && (k < LINE_LEN - 1); k++)
            {
                print_buffer[i][k] = ' ';
            }
            print_buffer[i][k] = '\0';
            len = strlen(print_buffer[i]);
            strncat(print_buffer[i], "  `─ ", 
                    LINE_LEN - 1 - len);
            len = strlen(print_buffer[i]);
            strncat(print_buffer[i], child_buffer[0], 
                    LINE_LEN - 1 - len);
            i++;
        }
        for (j = 1; (i < LINE_NUM) && (child_buffer[j][0] != '\0'); i++, j++)
        {
            for (k = 0; (k < strlen(this_name) + 5) && (k < LINE_LEN - 1); k++)
            {
                print_buffer[i][k] = ' ';
            }
            print_buffer[i][k] = '\0';
            if (k == LINE_LEN - 1)
            {
                continue;
            }
            len = strlen(print_buffer[i]);
            strncat(print_buffer[i], child_buffer[j],
                    LINE_LEN - 1 - len);
        }
    }
    close(to_children[READ_END]);
    for (j = 0; j < i; j++)
    {
        if (print_buffer[j][0] != '\0')
        {
            printf("%s\n", print_buffer[j]);
        }
    }
}

/**
 * This function starts the tree mechanism on a task created with a fork(). It
 * creates the pipes communicate the task with their children and updates the
 * pipe used to communicate itself with its parent.
 *
 * @param name The name of the task that will be shown when the tree is
 *             generated.
 */
void tree_start(char * name)
{
    this_name = name;
    to_parent[READ_END] = to_children[READ_END];
    to_parent[WRITE_END] = to_children[WRITE_END];

    close(to_parent[READ_END]);

    if (pipe(to_children) == -1)
    {
        perror("pipe");
        exit(-1);
    }
}

/**
 * This function collects the information coming from the children of a task
 * created with fork(). After collecting all the information and composing it
 * on a buffer, it will send it to its parent task.
 */
void tree_collect()
{
    // Buffer used to obtain the information from its children
    char child_buffer[LINE_NUM][LINE_LEN];
    // Buffer used to forward the information to its parent
    char parent_buffer[LINE_NUM][LINE_LEN];

    int i = 0, j = 0, k = 0;
    int len = 0;

    // We have to initialize the buffer
    for (i = 0; i < LINE_NUM; i++)
    {
        parent_buffer[i][0] = '\0';
        child_buffer[i][0] = '\0';
    }

    // We are not going to send anything to our children, so we have to close
    // this end of the pipe
    close(to_children[WRITE_END]);

    // We have to print the name of the task at the beginning of the first line
    snprintf(parent_buffer[0], LINE_LEN, "%s", this_name);

    i = 0;

    // Main loop that will collect the information from the children and store
    // it properly on the buffer that will be used to send it to the parent
    // task
    while(read(to_children[READ_END], child_buffer, sizeof(child_buffer)) > 0)
    {
        if (child_buffer[0][0] == '\0')
        {
            continue;
        }
        if (i == 0)
        {
            len = strlen(parent_buffer[0]);
            strncat(parent_buffer[0], " --- ",
                    LINE_LEN - 1 - strlen(parent_buffer[0]));
            strncat(parent_buffer[0], child_buffer[0], 
                    LINE_LEN - 1 - strlen(parent_buffer[0]));
            i++;
        }
        else
        {
            if (strlen(this_name) + 2 < LINE_LEN - 1)
            {
                for (j = 0; j < i; j++)
                {
                    if (parent_buffer[j][strlen(this_name) + 2] == '-')
                        parent_buffer[j][strlen(this_name) + 2] = '+';
                    else if (parent_buffer[j][strlen(this_name) + 2] == ' ' ||
                             parent_buffer[j][strlen(this_name) + 2] == '`')
                    {
                        parent_buffer[j][strlen(this_name) + 2] = '|';
                    }
                }
            }
            for (j = 0; (j < strlen(this_name)) && (j < LINE_LEN - 1); j++)
            {
                parent_buffer[i][j] = ' ';
            }
            parent_buffer[i][j] = '\0';
            len = strlen(parent_buffer[i]);
            strncat(parent_buffer[i], "  `─ ",
                    LINE_LEN - 1 - len);
            len = strlen(parent_buffer[i]);
            strncat(parent_buffer[i], child_buffer[0],
                    LINE_LEN - 1 - len);
            i++;
        }
        for (j = 1; (i < LINE_NUM) && (child_buffer[j][0] != '\0'); i++, j++)
        {
            for (k = 0; (k < strlen(this_name) + 5) && (k < LINE_LEN - 1); k++)
            {
                parent_buffer[i][k] = ' ';
            }
            parent_buffer[i][k] = '\0';
            if (k == LINE_LEN - 1)
            {
                continue;
            }
            len = strlen(parent_buffer[i]);
            strncat(parent_buffer[i], child_buffer[j],
                    LINE_LEN - 1 - len);
        }
    }
    // After we have collected it, we have to send it, close the pipe and leave
    write(to_parent[WRITE_END], parent_buffer, sizeof(parent_buffer));
    close(to_parent[WRITE_END]);
    close(to_children[READ_END]);
}

int main ()
{
    int pid = 0;
    tree_init ("main_task "); // agregar padres 
    
    if ((pid=fork())==0){
    
    tree_start ("task_4");
    tree_collect();
    
    }
   
    
     if ((pid = fork()) == 0) { // de un padre sacar un padre y un hijo punto y raya
        tree_start ("task_1");
        if ((pid = fork()) == 0) {
            tree_start ("task_1_1 ");
            tree_collect(); }
        else { if((pid=fork())==0){
            tree_start("task_1_2");
           }
         }

    tree_collect(); }
     else{
    
    
        
     if ((pid = fork()) == 0) { // de un padre sacar un padre y un hijo punto y raya
        tree_start ("task_3");
        if ((pid = fork()) == 0) {
            tree_start ("task_3_2 ");
            tree_collect(); }
        else { if((pid=fork())==0){
            tree_start("task_3_1");
            
            if ((pid = fork()) == 0) {
            tree_start ("task_1_1_2 ");
            }
        else { if((pid=fork())==0){
            tree_start("task_1_1");}}}}tree_collect();}
    
     
        if ((pid = fork()) ==0) { // de un padre sacar un padre y un hijo punto y raya
        tree_start ("task_2");
        if ((pid = fork()) ==0) {
            tree_start ("task_2_1 ");
            tree_collect(); }
        else { if((pid=fork())==0){
            tree_start("task_2_2");
           }
        else{
        if((pid=fork())==0){
            tree_start("task_2_3");
        } else{
        if((pid=fork())==0){
            tree_start("task_2_5");
        }else{
        if((pid=fork())==0){
            tree_start("task_2_6");
        }else{if((pid=fork())==0){
            tree_start("task_2_7");
        }else{if((pid=fork())==0){
            tree_start("task_2_8");
        }else{if((pid=fork())==0){
            tree_start("task_2_9");
        }else{if((pid=fork())==0){
            tree_start("task_2_10");
        }else{if((pid=fork())==0){
            tree_start("task_2_4");
        }else{
        if((pid=fork())==0){
            tree_start("task_2_1");
          if((pid=fork())==0){
            tree_start("task_2_1");
            
            tree_collect(); }
        
           }}}}}}}}}
        
        
        }
         }

    tree_collect(); }
   
    }
    
    
        
 
 
     
   
       tree_finish ();
    return 0; }
