#include <errno.h>
#include "connectionhandler.h"
#include "util.h"
#include "user.h"
#include <netinet/in.h>
#include <string.h>
#include "clientthread.h"
#include <unistd.h>
#include <stdlib.h>

static int createPassiveSocket(in_port_t port)
{
	int fd = -1;
	

	// create a socket 
	fd = socket(AF_INET,SOCK_STREAM,0);
	
	if(fd == -1)
	{	
		errnoPrint("Error opening Socket at %d",port);
		return -1;
	}
	int on = 1;
	setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on)); 	


	//socket structure
	struct sockaddr_in addr;
 	memset(&addr,0,sizeof(addr));
		addr.sin_family= AF_INET;
		addr.sin_addr.s_addr = htonl(INADDR_ANY); /*unassigned int to network byte order, host IP */ 
  		addr.sin_port = htons(port); /*unassigned short to network byte order */ 
		

	// binding of socket/  1.filedescriptor, 2.pointer to struct, 3.size of socket struct 
	if ((bind(fd, (struct sockaddr *) &addr, sizeof(addr)))<0) // man-page says 3rd para is form type socklen_t --> casting
	{
		errnoPrint ("Error to bind Socket at %d",port );
		return -1;
	};	
	/*bind() assigns the
       	address  specified  by  addr  to  the  socket  referred  to by the file
       	descriptor sockfd.*/
	

	//listening queue of 3 (3 ist standart for linux)
	if (listen(fd,3)<0)
	{
		errnoPrint ("Error listen" );
		return -1;
	}
return fd;
}


int connectionHandler(in_port_t port)
{
	//call to create the PassiveSocket	
	const int fd = createPassiveSocket(port);
	User *userThread;
	if(fd == -1)
	{
		errnoPrint("Unable to create server socket");
		return -1;
	}

	for(;;)
	{
		//accept() incoming connection
		int client_socket = accept (fd,NULL,NULL);
		if(client_socket < 0)
		{
			errnoPrint("Error Accepting Socket");
			//close(fd);
			return -1;
		}
		

		//Create User with UserThread and socket
		userThread = malloc(sizeof(User));
        	userThread->sock = client_socket;
           	if (pthread_create(&userThread->thread, NULL, clientthread, userThread) < 0) {
                	errnoPrint("pthread_create() failure...");
			close(client_socket);			
			return -1;
            	}
		
		
	}

	return 0;	//never reached
}
