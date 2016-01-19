all: microshell

microshell: example.o parser.o orders.o orders.h parser.h
	gcc -g -Wall -o microshell example.o parser.o orders.o

example.o: example.c parser.h
	gcc -g -Wall -c -o example.o example.c

orders.o: orders.c orders.h
	gcc -g -Wall -c -o orders.o orders.c

parser.o: parser.c parser.h
	gcc -g -Wall -c -o parser.o parser.c

clean:
	rm -f microshell *.o

