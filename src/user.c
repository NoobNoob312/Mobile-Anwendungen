#include <pthread.h>
#include "user.h"
#include "clientthread.h"
#include <errno.h>
#include <sys/socket.h>		
#include <sys/types.h>
#include <stdlib.h>	
#include <stdio.h>	
#include "util.h"
#include "network.h"
#include <netinet/in.h>
#include <time.h>
#include <string.h>
#include <unistd.h>


static pthread_mutex_t userLock = PTHREAD_MUTEX_INITIALIZER;
static User *userFront = NULL;		// Head
static User *userBack = NULL;		// Tale

/*
pthread_mutex_unlock(&userLock or Heap -> initialise pointer currentUser
3) pointer currentUser saves created users address
*/

User *createUser()
{

	User *currentUser;
	currentUser = malloc(sizeof(User)); 
	return currentUser;

}

/*
The function insertUser insert a User in a list.
1) create a pointer, which the function createUser is assigned 
*/
int insertUser(User *newUser)
{
	newUser->next = NULL;

	if (userFront == NULL && userBack == NULL)
	{
		debugPrint("newUser added as first element");
		userFront = userBack = newUser;
		newUser->prev = NULL;
		
		return 1;	
	}
	else if(userBack != NULL)
	{
		userBack->next = newUser;
		newUser->prev = userBack;
		userBack = newUser;
		debugPrint("newUser added as last element");
		return 1;
	}
	else
	{
		errnoPrint("Fehler in Liste");
		
		return -1;
	}
	

	
}


/*
This function deletes a selected user.
There a different cases, if there is a deletion. 
1. Case = REMOVE_USER_WITHOUT_MESSAGE --> Only delete User in List but not send a RemoveUser Message
2. Case = REMOVE_USER_WITH_MESSAGE --> User have to be deleted and RemoveUser Message must be send
*/
User *deleteUser(User *remUser){

debugPrint("in deleteUser");
	
	
	User *currentUser = userFront;
	
		while (currentUser != NULL)
		{ 
			if (currentUser == remUser) 
			{	
				//delete Header
				if (currentUser == userFront)	
				{
					debugPrint("Delete User at Front");
					userFront = currentUser->next;
					if (currentUser->next != NULL) 
					{
           					currentUser->next->prev = NULL;
        				}
					else{
						userBack = NULL;
					}
				}		
				
				//delete at Tail
				else if (currentUser == userBack)
				{
					debugPrint("Delete User at Back");
					userBack= currentUser->prev;	
					if (currentUser->prev != NULL) 
					{
	           				currentUser->prev->next = NULL;
	        			}
								
				}
				//delete in middle
				else if (currentUser->prev != NULL && currentUser->next != NULL)
				{
					debugPrint("Delete User in the middle");
					currentUser->prev->next = currentUser->next;
					currentUser->next->prev = currentUser->prev;
					
				}	

				close(currentUser->sock);
				// Finally, free the memory occupied by currentUser 
				//free(currentUser);
				break;
			}
			currentUser= currentUser->next;
		}
	
	
	
	
					
	return userFront;

}


/*
Checks if Username is used. If it's not used, Username can be added to the User and its socket.
Return USERNAME_USED if used
Return USERNAME_FREE if not used at the moment
*/
int usernameUsed (char username[MAX_NAME])
{
 	debugPrint("in usernameUsed function");
	
	
	User *currentUser = userFront;

	while (currentUser != NULL)
	{ 
		if (strcmp(currentUser->username, username) == 0)
		{
			printf("Duplicated Username was found: %s \n", currentUser->username);


			return USERNAME_USED;
			 
		}
	
		currentUser = currentUser->next;

	}

	debugPrint("Username: '%s' was not found \n", username);

	 
	
		
	return USERNAME_FREE;
}



//send to all User a message
int sendServerMessage(Message *mess) 
{
	//lockUser();
	User *currentUser = userFront;
	if (currentUser == NULL){
		unlockUser();
		return -1;
	}
	
	while(currentUser != NULL){
		
		networkSend(currentUser->sock, mess);
		currentUser= currentUser->next;
	}
	//unlockUser();
	return 1;

}


//get User by User Name
User *userAccessViaName(char name[MAX_NAME]) {
    User *currentUser = malloc(sizeof(User));
    currentUser = userFront;
    while (currentUser != NULL) {
        if (strncmp(currentUser->username, name, sizeof(currentUser->username)) == 0) {
            break;
        }
        currentUser = currentUser->next;
    }
    return currentUser;
}




void lockUser (){

if ((pthread_mutex_lock(&userLock)) != 0) 
	{
		perror(" Mutex Lock failure \n ");
		exit(EXIT_FAILURE);
	}
}


void unlockUser(){
if ((pthread_mutex_unlock(&userLock)) != 0)
	{
		errnoPrint("Mutex unlock failed \n ");
		exit(EXIT_FAILURE);
	}
}
