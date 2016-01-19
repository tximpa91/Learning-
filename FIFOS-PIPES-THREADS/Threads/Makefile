all: threads_eco_caps

threads_eco_caps: threads_echo_caps.o
	gcc threads_echo_caps.c -o threads_eco_caps -lpthread -Wall



clean:
	rm -f *.o
	rm -f  threads_eco_caps
