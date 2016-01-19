#ifndef EJECUTAR_H
#define EJECUTAR_H

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>
#include <unistd.h>
#include <string.h>


#endif



int ejecutar_orden(const char *orden, int *pbackgr);
void ejecutar_linea_ordenes( const char *orden);
void varias_ordenes( char *orden);
void child(int signum);
