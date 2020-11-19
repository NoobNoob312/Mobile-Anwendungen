#ifndef BROADCASTAGENT_H
#define BROADCASTAGENT_H
#include "network.h"


int broadcastAgentInit(void);
void broadcastAgentCleanup(void);
int putInQueue (Message *mqMessage);

void *pauseQueue(void);

void *resumeQueue(void);

int isMqFull(void);


#endif
