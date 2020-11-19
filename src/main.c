#include <stdlib.h>
#include "connectionhandler.h"
#include "util.h"
#include <limits.h> //imports limits of datatypes
#include <errno.h>
#include <pthread.h>
#include <broadcastagent.h>

int main(int argc, char **argv)
{	debugEnabled();
	styleEnabled();
	
	utilInit(argv[0]);
	infoPrint("Chat server, group 05 \n");	//TODO: Add your group number!
	long port;
	char *endptr = NULL;//stores first invalid character of input
	int result=0;

	

	
//TODO: evaluate command line arguments
	if (argc ==1){
		if (broadcastAgentInit() < 0) {
			broadcastAgentCleanup();
            		return EXIT_FAILURE;
       		}


		port = 8111;
		infoPrint("Running at Port %li \n", port);
		result = connectionHandler((in_port_t)port);
		
		
		
	}
	else if (argc <1 || argc >2){
	 	infoPrint("No valid parameters -> use following ./server [PORT]\n");
	}
	else{
		//long int strtol(const char *nptr, char **endptr, int base);

		port = strtol(argv[1], &endptr, 10); //convert string to long and using for checking if to large port number
		
		if (port>UINT16_MAX || 0>port){
			errnoPrint("Port number too big or negative\n");
            		exit (EXIT_FAILURE);
		}
 		if(!port) {
      			errnoPrint("Could not convert Port number. False input \n");
			result = -1;
      			exit(EXIT_FAILURE);
			
   		}

		if (!*endptr){ //if there are no invalid character
			if (broadcastAgentInit() < 0) {
				broadcastAgentCleanup();
            			return EXIT_FAILURE;
       			 }		
			infoPrint("Connect to Port %li  \n", port);
			result = connectionHandler((in_port_t)port);
			
			 
			
		}
		else {
			errnoPrint("Failure, Please Use only digits \n");

			exit(EXIT_FAILURE);
			
   		}	
	
	
	
	
		
		
		
	
	
	}
	

	if (result <0)
	{
		broadcastAgentCleanup();
	}
	return result != -1 ? EXIT_SUCCESS : EXIT_FAILURE;
}

