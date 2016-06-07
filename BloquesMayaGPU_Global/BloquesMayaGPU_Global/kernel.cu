
#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include "cuda.h"
#include "cuda_runtime_api.h"
#include <stdlib.h>
#include <stdio.h>
#include <Windows.h>
#include <time.h>



__device__ void comprobar(int *matriz, int fil, int col, int columna) //metodo para recorrer toda la matriz en busca de posiciones vacias
{																	// para desplazarlas arriba		

	for (int k = 0; k < columna; k++)
	{
		if (col> 0)
		{
			if (matriz[col*columna + fil] == 0 && !matriz[(col - 1)*columna + fil] == 0) // Si hay una posicon vacia encima de otra no lo hace
			{
				int aux = matriz[col*columna + fil];
				matriz[col*columna + fil] = matriz[(col - 1)*columna + fil];
				matriz[(col - 1)*columna + fil] = 0;
			}
		}

		__syncthreads();
	}

}


__device__ void DesplazamientoDerecha(int *matriz, int  fila, int columna, int fil, int col2)
{

	int aux;
	for (int p = 0; p <= fila; p++){ // Para desplazar todas las posiciones de la columna
		if (matriz[(fila)*columna + col2] == 0) //Comprobar la ultima fila
		{
			for (int k = 0; k < columna; k++)
			{
				if (col2 > 0) //En orden
				{
					if (matriz[fil *columna + col2] == 0) //swap
					{
						int aux = matriz[fil *columna + (col2 - 1)];
						matriz[fil *columna + (col2 - 1)] = 0;
						matriz[fil *columna + col2] = aux;
					}

				}

				__syncthreads();
			}

		}
		__syncthreads(); //Despues de cada interacion, se sincronizan para esperar que los hilos hagan sus tareas.
	}
}




int *crearMatriz(int filas, int columna)
{
	int *matriz = (int *)malloc(filas * columna * sizeof(int));
	int contador = 0;
	for (int i = 0; i < filas; i++)
	{
		for (int j = 0; j < columna; j++)
		{
			int numero = rand() % 8 + 1;
			if (numero == 8 && contador < 2) //Creamos 2 bombas
			{
				contador++;
				matriz[i*columna + j] = numero;
			}
			else
			{
				if (numero != 8)
				{
					matriz[i*columna + j] = numero;
				}
				else
				{
					matriz[i*columna + j] = rand() % 7 + 1;
				}

			}
		}
	}

	return matriz;

}

void color(int opcion)
{
	HANDLE hConsole;
	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);


	switch (opcion)
	{
	case 0: {	SetConsoleTextAttribute(hConsole, 0); printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}
	case 1: {	SetConsoleTextAttribute(hConsole, 9); printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;

	}

	case 2: {	 SetConsoleTextAttribute(hConsole, 4); printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}

	case 3: { SetConsoleTextAttribute(hConsole, 14); printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break; }

	case 4: {	SetConsoleTextAttribute(hConsole, 2);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}

	case 5:{	SetConsoleTextAttribute(hConsole, 13);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}

	case 6: {	SetConsoleTextAttribute(hConsole, 6);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}

	case 7:	{	SetConsoleTextAttribute(hConsole, 1);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}

	case 8: {	SetConsoleTextAttribute(hConsole, 7);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break;
	}
	case 9: { SetConsoleTextAttribute(hConsole, 0);  printf("%d", opcion);
		SetConsoleTextAttribute(hConsole, 7); printf("|");
		break; }

	default: { printf("ERROR\n"); break; }

	}



}

void imprimir(int *matriz, int filas, int columna)
{

	HANDLE hConsole;
	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	//system("cls");
	for (int i = 0; i < filas; i++)
	{
		for (int j = 0; j <columna; j++)
			color(matriz[i*columna + j]);

		printf("\n");


	}

	SetConsoleTextAttribute(hConsole, 7);

}

boolean  posiValida(int fila, int columna, int i, int j) //Comprueba que la posicion metida por teclado sea correcta
{
	if ((i >= 0 && j >= 0) && (i <= fila - 1 && j <= columna - 1))
		return true;
	else
		return false;
}
__device__ void juegarriba(int *matriz, int tid1, int columna, int i, int j, int  bloque)
{

	switch (tid1) //Dos hilos que comprueben la posicion de arriba y la de encima de esta. 
	{
	case 1:	 if (bloque == matriz[(i - tid1)*columna + j])
	{
		matriz[i*columna + j] = 0;
		matriz[(i - tid1)*columna + j] = 0;
	}
			 break;
			 // Si la posicion de arriba es igual, se comprueba la de más arriba (para borrar 3)
	case 2:  if (bloque == matriz[(i - tid1)*columna + j] && bloque == matriz[(i - tid1 + 1)*columna + j])
	{
		matriz[i*columna + j] = 0;
		matriz[(i - tid1)*columna + j] = 0;
	}
			 break;
	}


}




__device__ void juegabajo(int *matriz, int tid1, int columna, int i, int j, int  bloque)
{

	switch (tid1)
	{
	case 1:	 if (bloque == matriz[(i + tid1)*columna + j])
	{
		matriz[i*columna + j] = 0;
		matriz[(i + tid1)*columna + j] = 0;
	}
			 break;
			 // Comprueba si la posicion de abajo es igual y si es comprueba la de arriba de esta posicion
	case 2:  if (bloque == matriz[(i + tid1)*columna + j] && matriz[(i + tid1 - 1)*columna + j] == bloque)
	{
		matriz[i*columna + j] = 0;
		matriz[(i + tid1)*columna + j] = 0;
	}
			 break;
	}
}

__device__ void juegaderecha(int *matriz, int tid, int columna, int i, int j, int  bloque)
{
	switch (tid)
	{
	case 1:	 if (bloque == matriz[(i)*columna + (j + tid)])
	{
		matriz[i*columna + j] = 0;
		matriz[i*columna + (j + tid)] = 0;
	}
			 break;

			 //Comprueba la posicion de su derecha y si es 0 comprueba a su vez la de la derecha
	case 2:  if (bloque == matriz[i*columna + (j + tid)] && bloque == matriz[i*columna + (j + tid - 1)])
	{
		matriz[i*columna + j] = 0;
		matriz[i*columna + (j + tid)] = 0;
	}
			 break;
	}


}

__device__ void juegaizquierda(int *matriz, int tid, int columna, int i, int j, int  bloque)
{
	switch (tid)
	{
	case 1:	 if (bloque == matriz[(i)*columna + (j - tid)])
	{
		matriz[i*columna + j] = 0;
		matriz[i*columna + (j - tid)] = 0;
	}
			 break;
			 //Comprueba la posicion de su izquierda y si es 0 comprueba a su vez la de la izquierda
	case 2:  if (bloque == matriz[i*columna + (j - tid)] && bloque == matriz[i*columna + (j - tid + 1)])
	{
		matriz[i*columna + j] = 0;
		matriz[i*columna + (j - tid)] = 0;
	}
			 break;
	}


}


__device__ void bombasabajo(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i + 1)*columna + j] = 0; // Eliminar abajo
}

__device__ void bombasarriba(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i - 1)*columna + j] = 0; //Eliminar arriba

}

__device__ void bombasderecha(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[i*columna + j + 1] = 0; // Elimnar derecha


}

__device__ void bombasizquierda(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[i*columna + j - 1] = 0; //Elimanar izquierda


}

__device__ void bombasDderechaA(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i - 1)*columna + j + 1] = 0; //Diagonal superior Derecha


}

__device__ void bombasIzquierdaA(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i - 1)*columna + j - 1] = 0; //Diagonal  superior Izquierda


}

__device__ void bombasDerechaB(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i + 1)*columna + j + 1] = 0; //Diagonal  inferior Derecha

}

__device__ void bombasIzquierdaB(int *matriz, int columna, int i, int j)
{
	matriz[i*columna + j] = 0;// Su misma posicion
	matriz[(i - 1)*columna + j - 1] = 0;//Diagonal inferior Izquierda


}



__global__ void KERNEL(int *matriz, int fila, int columna, int i, int j)
{

	int fil = threadIdx.y;
	int col = threadIdx.x;
	int bloque = matriz[i*columna + j]; //posicion que hemos seleccionado por pantalla
	printf("");
	if (bloque == 8) //Se comprueba si se ha pulsado una bomba
	{
		if (i == 0 && j == 0) // esquina superior izquierda
		{
			bombasabajo(matriz, columna, i, j);
			bombasderecha(matriz, columna, i, j);
			bombasDerechaB(matriz, columna, i, j);

		}
		else
		{
			if (i == fila - 1 && j == columna - 1) // esquina inferior derecha
			{
				bombasarriba(matriz, columna, i, j);
				bombasizquierda(matriz, columna, i, j);
				bombasIzquierdaA(matriz, columna, i, j);
			}
			else{

				if (i == fila - 1 && j == 0) // esquina inferior izquierda
				{
					bombasarriba(matriz, columna, i, j);
					bombasderecha(matriz, columna, i, j);
					bombasDderechaA(matriz, columna, i, j);

				}

				else
				{

					if (i == 0 && j == columna - 1) // esquina superior derecha
					{
						bombasabajo(matriz, columna, i, j);
						bombasizquierda(matriz, columna, i, j);
						bombasIzquierdaB(matriz, columna, i, j);

					}
					else
					{
						if (i == 0 && j < columna - 1) // primera fila
						{
							bombasabajo(matriz, columna, i, j);
							bombasizquierda(matriz, columna, i, j);
							bombasderecha(matriz, columna, i, j);
							bombasDerechaB(matriz, columna, i, j);
							bombasIzquierdaB(matriz, columna, i, j);

						}
						else
						{

							if (i < fila - 1 && j == 0) // primera columna
							{
								bombasarriba(matriz, columna, i, j);
								bombasderecha(matriz, columna, i, j);
								bombasabajo(matriz, columna, i, j);
								bombasDderechaA(matriz, columna, i, j);
								bombasDerechaB(matriz, columna, i, j);



							}

							else
							{

								if (i < fila - 1 && j == columna - 1) //Ultima columna
								{
									bombasarriba(matriz, columna, i, j);
									bombasabajo(matriz, columna, i, j);
									bombasizquierda(matriz, columna, i, j);
									bombasIzquierdaA(matriz, columna, i, j);
									bombasIzquierdaB(matriz, columna, i, j);


								}
								else
								{
									if (i == fila - 1 && j != 0 && j != columna - 1) // ultima fila
									{
										bombasarriba(matriz, columna, i, j);
										bombasderecha(matriz, columna, i, j);
										bombasizquierda(matriz, columna, i, j);
										bombasDderechaA(matriz, columna, i, j);
										bombasIzquierdaA(matriz, columna, i, j);


									}
									else
									{

										if (i < fila - 1 && j < columna - 1)
										{
											bombasabajo(matriz, columna, i, j);
											bombasarriba(matriz, columna, i, j);
											bombasderecha(matriz, columna, i, j);
											bombasizquierda(matriz, columna, i, j);
											bombasDderechaA(matriz, columna, i, j);
											bombasDerechaB(matriz, columna, i, j);
											bombasIzquierdaA(matriz, columna, i, j);
											bombasIzquierdaB(matriz, columna, i, j);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	else
	{
		if (i == 0 && j == 0) // esquina superior izquierda
		{
			juegabajo(matriz, fil, columna, i, j, bloque);
			juegaderecha(matriz, col, columna, i, j, bloque);
		}
		else
		{
			if (i == fila - 1 && j == columna - 1) // esquina inferior derecha
			{
				juegarriba(matriz, fil, columna, i, j, bloque);
				juegaizquierda(matriz, col, columna, i, j, bloque);
			}
			else{

				if (i == fila - 1 && j == 0) // esquina inferior izquierda
				{
					juegarriba(matriz, fil, columna, i, j, bloque);
					juegaderecha(matriz, col, columna, i, j, bloque);

				}

				else
				{

					if (i == 0 && j == columna - 1) // esquina superior derecha
					{
						juegaizquierda(matriz, fil, columna, i, j, bloque);
						juegabajo(matriz, fil, columna, i, j, bloque);
					}
					else
					{
						if (i == 0 && j < columna - 1) // primera fila
						{
							juegaizquierda(matriz, fil, columna, i, j, bloque);
							juegaderecha(matriz, fil, columna, i, j, bloque);
							juegabajo(matriz, fil, columna, i, j, bloque);
						}
						else
						{

							if (i < fila - 1 && j == 0) // primera columna
							{
								juegarriba(matriz, fil, columna, i, j, bloque);
								juegabajo(matriz, fil, columna, i, j, bloque);
								juegaderecha(matriz, col, columna, i, j, bloque);

							}

							else
							{

								if (i < fila - 1 && j == columna - 1) //Ultima columna
								{
									juegarriba(matriz, fil, columna, i, j, bloque);
									juegabajo(matriz, fil, columna, i, j, bloque);
									juegaizquierda(matriz, col, columna, i, j, bloque);

								}
								else
								{
									if (i == fila - 1 && j != 0 && j != columna - 1) // ultima fila
									{
										juegarriba(matriz, fil, columna, i, j, bloque);
										juegaderecha(matriz, col, columna, i, j, bloque);
										juegaizquierda(matriz, fil, columna, i, j, bloque);

									}
									else
									{

										if (i < fila - 1 && j < columna - 1)
										{
											juegarriba(matriz, fil, columna, i, j, bloque);
											juegabajo(matriz, fil, columna, i, j, bloque);
											juegaderecha(matriz, col, columna, i, j, bloque);
											juegaizquierda(matriz, fil, columna, i, j, bloque);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	__syncthreads();
	comprobar(matriz, fil, col, columna); //Se recorre la matriz en busca de 0 para desplazarlos para arriba


	__syncthreads();
	DesplazamientoDerecha(matriz, fila - 1, columna, fil, col);



}









void test(){
	cudaDeviceProp prop;
	int count;
	(cudaGetDeviceCount(&count));
	for (int i = 0; i < count; i++) {
		cudaGetDeviceProperties(&prop, i);
		printf(" --- General Information for device %d ---\n", i);
		printf("Name: %s\n", prop.name);
		printf("Compute capability: %d.%d\n", prop.major, prop.minor);
		printf("Clock rate: %d\n", prop.clockRate);
		printf("Device copy overlap: ");
		if (prop.deviceOverlap)
			printf("Enabled\n");
		else
			printf("Disabled\n");
		printf("Kernel execition timeout : ");
		if (prop.kernelExecTimeoutEnabled)
			printf("Enabled\n");
		else
			printf("Disabled\n");
		printf(" --- Memory Information for device %d ---\n", i);
		printf("Total global mem: %ld\n", prop.totalGlobalMem);
		printf("Total constant Mem: %ld\n", prop.totalConstMem);
		printf("Max mem pitch: %ld\n", prop.memPitch);
		printf("Texture Alignment: %ld\n", prop.textureAlignment);
		printf(" --- MP Information for device %d ---\n", i);
		printf("Multiprocessor count: %d\n",
			prop.multiProcessorCount);
		printf("Shared mem per mp: %ld\n", prop.sharedMemPerBlock);
		printf("Registers per mp: %d\n", prop.regsPerBlock);
		printf("Threads in warp: %d\n", prop.warpSize);
		printf("Max threads per block: %d\n",
			prop.maxThreadsPerBlock);
		printf("Max thread dimensions: (%d, %d, %d)\n",
			prop.maxThreadsDim[0], prop.maxThreadsDim[1],
			prop.maxThreadsDim[2]);
		printf("Max grid dimensions: (%d, %d, %d)\n",
			prop.maxGridSize[0], prop.maxGridSize[1],
			prop.maxGridSize[2]);
		printf("\n");
	}
}


int main()
{
	int opcion = -1, i = 0, j = 0;
	int *matriz, *matriz_CUDA, *matrizC;
	int filas = -1;
	int columna = -1;
	char modo = ' ';
	time_t t;
	srand((unsigned)time(&t));
	boolean valido = false;



	printf("Bienvenidos a Antique Blocks \n");

	printf(" -------------- Elija una Modalidad de Juego ----------------\n ");

	while (modo != 'a' && modo != 'm')
	{

		printf(" < a > Juego Automatico \n");
		printf(" < m > Juego Manual \n");
		modo = getchar();

	}

	while (filas < 0 && columna < 0)
	{
		system("cls");
		printf(" Inutroduzca la cantidad de filas: \n");
		scanf("%d", &filas);
		printf(" Introduzca la cantidad de columnas: \n");
		scanf("%d", &columna);
		matriz = crearMatriz(filas, columna);
		system("cls");


	}

	if (modo == 'm') {
		int size = filas * columna * sizeof(int);

		dim3 DimGrid(1, 1);
		dim3 DimBlock(filas, columna, 1);
		cudaMalloc((void**)&matriz_CUDA, size);
		cudaMemcpy(matriz_CUDA, matriz, size, cudaMemcpyHostToDevice);//Mandamos la matriz a GPU

		while (true)
		{

			valido = false;
			while (!valido)
			{
				imprimir(matriz, filas, columna);
				printf("Introduzca la casilla a jugar \n");
				printf("Fila: ");
				scanf("%d", &i);
				printf("\n");
				printf("Columna ");
				scanf("%d", &j);
				printf("\n");
				i--;
				j--;
				valido = posiValida(filas, columna, i, j);
				if (valido == false)
				{
					printf("Posicion invalida \n");
					system("pause");
					system("cls");

				}

			}





			KERNEL << <DimGrid, DimBlock >> >(matriz_CUDA, filas, columna, i, j);


			cudaMemcpy(matriz, matriz_CUDA, size, cudaMemcpyDeviceToHost); //metemos la matriz en CPU


			system("cls");
		}
		cudaFree(matriz_CUDA);
		system("pause");
	}
	else
	{

		int size = filas * columna * sizeof(int);

		dim3 DimGrid(1, 1);
		dim3 DimBlock(filas, columna, 1);
		cudaMalloc((void**)&matriz_CUDA, size);
		cudaMemcpy(matriz_CUDA, matriz, size, cudaMemcpyHostToDevice);//Mandamos la matriz a GPU
		int aux, aux2;
		for (int i = 0; i < filas; i++)
		{
			for (int j = 0; j < columna; j++)
			{
				aux = i + 1;
				aux2 = j + 1;
				printf("Posicion a Jugar  fila: %d  columna: %d \n", aux, aux2);
				KERNEL << <DimGrid, DimBlock >> >(matriz_CUDA, filas, columna, i, j);


				cudaMemcpy(matriz, matriz_CUDA, size, cudaMemcpyDeviceToHost); //metemos la matriz en CPU

				imprimir(matriz, filas, columna);
				system("pause");
				system("cls");


			}


		}

	}


	return 0;



}