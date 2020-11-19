#include <errno.h>
#include "network.h"
#include <sys/socket.h> //needed for recv()
#include <string.h>
#include <netinet/in.h>
#include <stdlib.h>
#include "util.h"
#include <stdio.h>
#include <limits.h>
#include "user.h"
#include <fcntl.h>
#include <signal.h>


ssize_t networkReceive(int fd, Message *buffer)
{	
	ssize_t bytes_Header;
	//Reset message
	memset (&buffer->messHeader,0,sizeof(buffer->messHeader)); //fill memory with 0
	//get message header ()
	bytes_Header = recv (fd, &buffer->messHeader, sizeof(buffer->messHeader),MSG_WAITALL); //waits for message and return length of heade in byte, MGS_WAITALL=block untill full request
	
	debugPrint ("Receiving message for socket %d", fd);
	debugPrint ("Byte Read: %ld", bytes_Header);
	
    /*if ((ssize_t) bytes_Header < (ssize_t) (sizeof(buffer->messHeader.length) + sizeof(buffer->messHeader.type))) {
        errnoPrint("read too few bytes from messageHeader");
        return -1;
    }*/
	

	

	return bytes_Header;



}

//sending of messages of all kinds
int networkSend(int fd, const Message *buffer)
{
	
	debugPrint("networkSend");
	ssize_t sendHeader, sendBody;
	//sending header
	if ((sendHeader = send (fd, &buffer->messHeader, sizeof(buffer->messHeader), MSG_NOSIGNAL))<0 ){	
		//errnoPrint("Failure sending header");	
		return -1;
	}
	
	//sending body
	if ((sendBody = send (fd, &buffer->messBody, ntohs(buffer->messHeader.length), MSG_NOSIGNAL))<0){
		errnoPrint("Failure sending body");
		return -1;
	}
	
	debugPrint("networkSend success");
	return 1;
}

//handling of loginRequest
int recvLoginRequest (int fd, Message *buffer){
	memset (&buffer->messBody,0,sizeof(buffer->messBody));
	//ssize_t bytes_reading;
	//Message nachricht = *buffer;
	buffer->messHeader.length = ntohs(buffer->messHeader.length);
	
	ssize_t bytes_reading = recv(fd, &buffer->messBody, buffer->messHeader.length, MSG_WAITALL);
	if (bytes_reading < 0){
		errnoPrint("Failure at reviving Login Request MessageBody ");
		return -1;
	}	

	debugPrint("Received type: %hhu  length %hu \n", buffer->messHeader.type, buffer->messHeader.length); //hu unasigned short (16 bit), hhu = unasigned char
	debugPrint("check name %s \n", buffer->messBody.logReq.name);
	debugPrint("Magic number of LoginRequest: %X. \n", ntohl(buffer->messBody.logReq.magic));



	
	
	debugPrint("Login Request\n");
	debugPrint("header length: %hu ",buffer->messHeader.length);
	//checking lenght size
	if ((buffer->messHeader.length < LENGTH_MIN) || (buffer->messHeader.length > LENGTH_MAX)) {
		errnoPrint("False length \n");
		return -1;   
	}
	//checking magic Number
	else if ((ntohl(buffer->messBody.logReq.magic)) != MAGIC_LOGIN_REQUEST){
			errnoPrint("false magic number\n");
			return -1;
	}


	



        //checking do for forbidden characters
        for (int  i = 0; i < ((int)strlen(buffer->messBody.logReq.name)); i++) {

		if ((int) (buffer->messBody.logReq.name[i]) == DOUBLE_QUOTE 
		|| (int)(buffer->messBody.logReq.name[i]) == QUOTE 	
		|| (int) (buffer->messBody.logReq.name[i]) == BACKTICK	
		||(int)(buffer->messBody.logReq.name[i]) < NO_CHAR_UNDER_ALLOWED  	
		//|| (int)(buffer->messBody.logReq.name[i]) > NO_CHAR_OVER_ALLOWED
		) 
		{
			errnoPrint("Character not allowed.\n");
                        //Send Response with Code 2
                       	sendLoginResponse(fd,LOGIN_RESPONSE_STATUS_NAME_INVALID);
			return -1;
                       
		}
	}
	
	
	if (buffer->messBody.logReq.version != VERSION) {
		//Send Response with Code 3
		errnoPrint("Version missmatch");
		sendLoginResponse(fd,LOGIN_RESPONSE_STATUS_PROTOCOL__MISMATCH);
		
		return -1;
	}

	return 1;
}


int sendLoginResponse(int fd,  uint8_t code)
{
Message responseMessage;
    //prepare message
    debugPrint("response Message building...");
    responseMessage.messHeader.type = logResp;
    responseMessage.messBody.logResp.magic = htonl(MAGIC_LOGIN_RESPONSE);
    responseMessage.messBody.logResp.code = code;
    strncpy(responseMessage.messBody.logResp.serverName , SERVER_NAME, sizeof(responseMessage.messBody.logResp.serverName)); 
    
    responseMessage.messHeader.length = htons((sizeof(responseMessage.messBody.logResp.magic)+sizeof(responseMessage.messBody.logResp.code)+strlen(responseMessage.messBody.logResp.serverName)));
    debugPrint(" Type: %hu Length Header: %hu Magic Number: %X \n Servername: %s  Servername Length:  %zi \n " ,responseMessage.messHeader.type,
												ntohs(responseMessage.messHeader.length), 
												ntohl(responseMessage.messBody.logResp.magic), 
												responseMessage.messBody.logResp.serverName,
												strlen(responseMessage.messBody.logResp.serverName));
    //send message 
    if (networkSend(fd,&responseMessage)<0)
   	{
		return -1;
	}
return 1;
}

int sendUserAdded(int fd, char *name, uint8_t type, uint64_t timestamp){
	//Message *tmp = malloc(sizeof(message));
	Message *sendUserAdd = malloc (sizeof(Message));
	debugPrint("in sendUserAdded: building of message...");
	//preperate message
	 
	sendUserAdd->messHeader.type = type;
	sendUserAdd->messBody.userAdd.timestamp = ntoh64u(timestamp);
	strncpy(sendUserAdd->messBody.userAdd.name, name, sizeof(sendUserAdd->messBody.userAdd.name));
	sendUserAdd->messHeader.length = htons((sizeof(sendUserAdd->messBody.userAdd.timestamp)+strlen(sendUserAdd->messBody.userAdd.name)));
	


	
	//send message
	if ((errno = networkSend(fd,sendUserAdd))<0){
		errnoPrint("failure sendig UserAdd");
		free(sendUserAdd);
		return -1;
	}
	debugPrint("End of sendUserAdded");
	free(sendUserAdd);
	return 1;
}


int sendUserRemoved(int fd, char *name, uint8_t code, uint64_t timestamp){
	Message *sendUserRemove = malloc(sizeof(Message));
	
	//preperate message
	sendUserRemove->messBody.userRem.timestamp = ntoh64u(timestamp); 
	debugPrint("Code in sendUserRemove : %u", code);
	sendUserRemove->messBody.userRem.code = code;
	sendUserRemove->messHeader.type = userRem;
	
	strncpy(sendUserRemove->messBody.userRem.name, name, sizeof(sendUserRemove->messBody.userRem.name));
	sendUserRemove->messHeader.length = htons((sizeof(sendUserRemove->messBody.userRem.timestamp)+sizeof(sendUserRemove->messBody.userRem.code)+strlen(sendUserRemove->messBody.userRem.name)));
	
	//send message
	if ((errno = networkSend(fd,sendUserRemove))<0){
		free(sendUserRemove);
		//errnoPrint("failure sendig UserRemove");
		return -1;
	}
	free(sendUserRemove);
	
	
	debugPrint("End of sendUserRemoved");
	return 1;
}

int recvClientMessage(int fd, Message *buffer)
{	memset (&buffer->messBody,0,sizeof(buffer->messBody));
	buffer->messHeader.length = ntohs(buffer->messHeader.length);
	
	ssize_t bytes_reading = recv(fd, &buffer->messBody, buffer->messHeader.length, MSG_WAITALL);
	if(bytes_reading <0)
	{
		errnoPrint("Failure at reciving Client Message");
		return -1;	
	}
	//Message temp = *buffer;
	debugPrint("in fuction recvClient Message");
	
	return 1;	
}


int prepareServerMessage (char *username, Message *messageToClient, char *originalMessage, uint64_t timestamp)
{
	messageToClient->messHeader.type = serv2Cli;
	strncpy(messageToClient->messBody.serv2Cli.originalSender, username, sizeof (messageToClient->messBody.serv2Cli.originalSender));
	strncpy(messageToClient->messBody.serv2Cli.text, originalMessage, sizeof (messageToClient->messBody.serv2Cli.text));
	messageToClient->messBody.serv2Cli.timestamp = ntoh64u(timestamp);
	messageToClient->messHeader.length = htons(sizeof (messageToClient->messBody.serv2Cli.timestamp)
		+strlen(messageToClient->messBody.serv2Cli.text) + sizeof (messageToClient->messBody.serv2Cli.originalSender));
	return 1;
}
