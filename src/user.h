#ifndef USER_H
#define USER_H

#include <pthread.h>
#include "network.h"

typedef struct User
{
	struct User *prev;
	struct User *next;
	pthread_t thread;	//thread ID of the client thread
	int sock;		//socket for client
	char username[MAX_NAME];
} User;


User *createUser(void);

int insertUser(User *newUser);

User *deleteUser(User *remUser);

int usernameUsed (char username[MAX_NAME]);

int sendServerMessage(Message *mess);

void unlockUser();

void lockUser();

User *userAccessViaName(char name[MAX_NAME]);








#endif
