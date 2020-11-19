#include "clientthread.h"
#include "user.h"
#include "util.h"
#include "network.h"
#include <unistd.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#include <pthread.h>
#include "broadcastagent.h"

int notifyUserAdded(User *newUser);
int notifyUserRemoved(User *firstUser, uint8_t code, char *username);
int notifyServerMessage(User *senderUser, Message *buffer) ;
int handleCommands (User *admin, Message *recived, Message *sending);
int isPaused = MQ_NOT_PAUSED;

void *clientthread(void *arg) //handover of User Pointer
{
	User *self = (User *)arg;
	infoPrint("Client thread [%zi] started.", (ssize_t) pthread_self());
	ssize_t bytes_reading;
	Message *nachricht = malloc(sizeof(Message)); //message for reciving
	Message *newNachricht = malloc(sizeof(Message)); //message for sending vom server to client
	
	for(;;){
		infoPrint("Clientthread: Calling networkReceive function");
		bytes_reading = networkReceive(self->sock, nachricht); 
		infoPrint ("Clientthread: Receiving message for socket %d", self->sock);
		infoPrint ("Clientthread: Byte Read: %ld", bytes_reading);
		memset (&newNachricht->messHeader,0,sizeof(newNachricht->messHeader));
		memset (&newNachricht->messBody,0,sizeof(newNachricht->messBody));


		if (bytes_reading==0)
		{
			
			infoPrint("Start delete User with message");
			lockUser();
			if (strcmp(self->username, "") == 0){
				unlockUser();
				return NULL;
			}
			else if ((notifyUserRemoved(deleteUser (self),CONNECTION_CLOSED_CLIENT,self->username)) <0)
			{		
				free(self);
				
				unlockUser();	
				free(nachricht);
				free(newNachricht);
				pthread_exit(NULL);
				return NULL;
			}
			else 
			{
				free(self);
				unlockUser();
				free(nachricht);
				free(newNachricht);
				pthread_exit(NULL);
			}
			
		}

		else if (bytes_reading <0){
			//errnoPrint("Error Reciving at socket %i : Bytes: %li", self->sock, bytes_reading);
			lockUser();
			if ((notifyUserRemoved(deleteUser (self),CONNECTION_CLOSED_CLIENT,self->username)) <0)
			{		
				free(self);
				
				unlockUser();	
				free(nachricht);
				free(newNachricht);
				pthread_exit(NULL);
				return NULL;
			}
			else 
			{
				free(self);
				unlockUser();
				free(nachricht);
				free(newNachricht);
				pthread_exit(NULL);
			}
			return NULL;
		}


		switch (nachricht->messHeader.type) {
		case logReq: 
			if(recvLoginRequest (self->sock, nachricht)<0)
			{ 
				return NULL;
			} 
			else { 
			
				infoPrint("check following username taken: '%s' \n", nachricht->messBody.logReq.name);

				//code for testing
				if(strcmp(nachricht->messBody.logReq.name, "") == 0){
				return NULL;
				}


				//checking allready used names?
				lockUser();
				int checkAlreadyUsed = usernameUsed(nachricht->messBody.logReq.name);
					
      				if (checkAlreadyUsed == USERNAME_USED) {
					//Send Response with Code 1
					errnoPrint("Username already used failure");
					sendLoginResponse(self->sock,LOGIN_RESPONSE_STATUS_NAME_TAKEN);
					unlockUser();
              				return NULL;
                   
				} 
				else {
					infoPrint("adding to List");
					//adding of Username to User
					strncpy(self->username, nachricht->messBody.logReq.name, sizeof (self->username));
					if (insertUser(self)<0)
					{	unlockUser();
						return NULL;
					}
					infoPrint("Before response");
        				if (sendLoginResponse(self->sock,LOGIN_RESPONSE_STATUS_SUCCESS)<0){
						unlockUser();
						return NULL;
					}  
					if (notifyUserAdded(self)<0){
						unlockUser();
						return NULL;
					}
			
				}

				unlockUser();
				   
			} break;
		case logResp: errnoPrint("login Response message dropped"); 
			return NULL; 
		case cli2Serv: 
			infoPrint("calling client2server function");		
			if (recvClientMessage(self->sock, nachricht)<0)
			{
				return NULL;
			}
			else {
				if(nachricht->messBody.cli2Serv.text[0] == '/'){
					//lockUser();
                    			handleCommands(self, nachricht, newNachricht );
					//unlockUser();
               			}
				else {
					time_t current_time;
				
					prepareServerMessage(self->username,newNachricht, nachricht->messBody.cli2Serv.text, 
					(uint64_t)time(&current_time));
					putInQueue (newNachricht);
					if (isMqFull() <0){
						prepareServerMessage("", newNachricht,  "Discard Message! Chat paused and MQ full",time(&current_time));
						networkSend(self->sock, newNachricht);
					}
				}
							
			



				
			}
	 		break;
		case serv2Cli: errnoPrint("server to client message dropped"); 
			return NULL; 
		case userAdd: errnoPrint("user Added Message dropped"); 
			return NULL; 
		case userRem: errnoPrint("user Remove Message dropped"); 
			return NULL; 
		default: errnoPrint("Failure detecting Type");

			/*free(nachricht);
			free(newNachricht);
			free(self);*/
			return NULL;
			
		}



	}
		

infoPrint("Client thread[%zi] stopping.", (ssize_t) pthread_self());
infoPrint("User %s disconnected!", self->username);
free(self);
free(newNachricht);
free(nachricht);
return NULL;
	
	

}


int notifyUserAdded(User *newUser)
{

	infoPrint("in notifyUserAdded");
	User *currentUser = newUser;
	time_t current_time;	
	

	
		while (currentUser != NULL && strcmp(currentUser->username, "") != 0)
		{
			
			if (currentUser != newUser)
			{	
			infoPrint("Message to User '%s' that User '%s' is already in room.",  newUser->username, currentUser->username);
			sendUserAdded(newUser->sock, currentUser->username, userAdd, 0);
			}
		
		currentUser = currentUser->prev;	

		}
		
		currentUser = newUser;
		while (currentUser != NULL && strcmp(currentUser->username, "") != 0)
		{
			infoPrint("Message to User '%s' that User '%s' was added.", currentUser->username, newUser->username);
			sendUserAdded(currentUser->sock, newUser->username, userAdd, time(&current_time));
			currentUser = currentUser->prev;	

		}

		
	
	infoPrint("Return from notifyUserAdded");
	return 1;
}




int notifyUserRemoved(User *firstUser, uint8_t code, char *username)
{
	infoPrint("notifUserRemoved function:");
	if (firstUser == NULL){
	}
	User *currentUser = firstUser;
	time_t current_time;	
	
	
	

	while (currentUser != NULL)
	{				
		
			infoPrint("Message to User '%s' that User '%s' was removed.", currentUser->username, username);
			sendUserRemoved(currentUser->sock, username, code, (uint64_t)time(&current_time));
		
		currentUser = currentUser->next;

	}
	infoPrint("Return from notifyUserRemoved");
	return 1;
}


int handleCommands (User *admin, Message *recived, Message *sending){
	infoPrint("handleCommands of User '%s'" , admin->username);
	time_t current_time;
	int isAdmin = strncmp(admin->username, "Admin", sizeof(admin->username));
	int userExists;
	char *buffer;
	
	
//Kick Command Handling
	if (strncmp(recived->messBody.cli2Serv.text, COMMAND_KICK, strlen(COMMAND_KICK)) == 0) {
		infoPrint("detected command %s", COMMAND_KICK );
		buffer = strtok(recived->messBody.cli2Serv.text, " ");
		infoPrint("Wort: %s\n",buffer);
		char *userToBeKicked = strtok(NULL, " ");
	
		//Kein Eingabe nach /kick
		infoPrint("Wort: %s\n",userToBeKicked);
		if (userToBeKicked == NULL) {
			prepareServerMessage("", sending,  "Invalid Command",time(&current_time));
			networkSend(admin->sock, sending);
        		return 1;	
			
		}
		
		//Abfangen mehr als ein Name
		buffer = strtok(NULL, " ");
		if (buffer != NULL){
			prepareServerMessage("", sending,  "Invalid Command",time(&current_time));
			networkSend(admin->sock, sending);
        		return 1;
		}
		//Abfangen ob kein Admin
		if ( isAdmin != 0) {
			char response [] = "You must be an administrator to use the /kick command";
		
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);
        		return 1;
		}
		//Abfangen ob Admin selber kicken kann
		if ((strcmp(userToBeKicked, "Admin")) == 0){
			char response [] = "Please Admin, logout normally :)";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);
			return 1;
		}


		//Abfangen wenn name nicht gibt
		lockUser();
		userExists = usernameUsed (userToBeKicked);
		unlockUser();
		if (userExists == USERNAME_FREE)
		{	
			infoPrint("couldnt find username %s", userToBeKicked);
			char response [] = "User doesnt exist";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);

                	return -1;
		}
		lockUser ();
		User *thisUser = userAccessViaName(userToBeKicked);
		unlockUser();
		pthread_cancel(thisUser->thread);
            	pthread_join(thisUser->thread, NULL);
		lockUser();
		notifyUserRemoved(deleteUser (thisUser),KICKED_FROM_SERVER,userToBeKicked);
		unlockUser();
	}	
	
//Pause Command Handling
	else if (strncmp(recived->messBody.cli2Serv.text, COMMAND_PAUSE, strlen(COMMAND_PAUSE)) == 0){
		if ( isAdmin != 0) {
			char response [] = "You must be an administrator to use the /pause command";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);
        		return 1;
		}
		if (isPaused == MQ_PAUSED){
			char response [] = "Server is already paused";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);
			return 1;
		}

		else{
			isPaused = MQ_PAUSED;
			
			char response [] = "Chat paused";
			infoPrint("Response: %s", response);
			prepareServerMessage("", sending,  response ,time(&current_time));
			sendServerMessage(sending);
			pauseQueue();

		}
		
	
		
	}
//ResumeCommand Handling
	else if (strncmp(recived->messBody.cli2Serv.text, COMMAND_RESUME, strlen(COMMAND_RESUME)) == 0){
		if ( isAdmin != 0) {
			char response [] = "You must be an administrator to use the /resume command";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			networkSend(admin->sock, sending);
		
        		return 1;
		}

		if (isPaused == MQ_NOT_PAUSED){
			char response [] = "Server isnt paused";
			infoPrint("Response: %s", response);
        		prepareServerMessage("", sending,  response ,time(&current_time));
			
			networkSend(admin->sock, sending);
			return 1;
		}
		else{
			isPaused = MQ_NOT_PAUSED;
			
			char response [] = "Chat no longer paused";
			infoPrint("Response: %s", response);
			prepareServerMessage("", sending,  response ,time(&current_time));
			lockUser();
			sendServerMessage(sending);
			unlockUser();
			resumeQueue();

			return 1;


		}
	}
	

	else {
	prepareServerMessage("", sending,  "Invalid Command",time(&current_time));
		networkSend(admin->sock, sending);
        	return 1;	


	}
	
	


	
return 1;

}

