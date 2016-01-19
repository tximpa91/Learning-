all: pipes

pipes: main.o  demultiplexer.o demultiplexer.h ios.o caps.o ios.h caps.h
	gcc -Wall main.o caps.o  ios.o demultiplexer.o -o pipes

main.o:
	gcc -g -Wall -c main.c

caps.o: caps.c caps.h
	gcc -g -Wall -c caps.c ios.h



ios.o: ios.c ios.h
	gcc -g -Wall -c ios.c ios.h

demultiplexer.o: demultiplexer.c demultiplexer.h
	gcc -g -Wall -c demultiplexer.c demultiplexer.h
clean:
	rm -f *.o
	rm -f pipes
	rm -f *.h.gch
