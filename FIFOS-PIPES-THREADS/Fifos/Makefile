all: tx io_mux demux
	mknod T0 p
	mknod T1 p
	mknod T2 p
	mknod T3 p
	mknod T4 p
	mknod T5 p
	mknod T6 p
	mknod T7 p
	mknod T8 p
	mknod T9 p

	
tx: 
	gcc -Wall tx.c caps.c -o tx

io_mux: 
	gcc -Wall io_mux.c ios.c -o io_mux

demux:
	gcc -Wall demux.c demultiplexer.c -o demux

clean: 
	rm -f tx
	rm -f demux
	rm -f io_mux
	rm -f T0 T1 T2 T3 T4 T5 T6 T7 T8 T9

