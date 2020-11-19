#include <pthread.h>
#include <mqueue.h>
#include "broadcastagent.h"
#include "util.h"
#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */
#include "network.h"
#include <string.h>
#include <stdlib.h>
#include "user.h"
#include <semaphore.h>


static mqd_t messageQueue;
static pthread_t threadId;
static char  mqName []= "/message_queue";
static int full;
static sem_t queueLock;

void *pauseQueue(void) {
    sem_wait(&queueLock);
    return NULL;
}

void *resumeQueue(void) {
    sem_post(&queueLock);
    return NULL;
}

static void *broadcastAgent(void *arg)
{
	//TODO: Implement thread function for the broadcast agent here!
	Message *tmpMessage = malloc(sizeof(Message));
	sem_init(&queueLock, 0, 1);
	for (;;){
		 
        	if (mq_receive(messageQueue, (char *) tmpMessage, sizeof(Message), 0) == -1) {
            		errnoPrint("error receiving message from message queue");
            		break;
		
        	}
	
		sem_wait(&queueLock);
		lockUser();

		sendServerMessage(tmpMessage); 
		unlockUser();
		sem_post(&queueLock);
	}


	return arg;
}

int broadcastAgentInit(void)
{	
	//broadcastAgentCleanup();
	struct mq_attr msgAttr;
	msgAttr.mq_flags = 0L;
	msgAttr.mq_maxmsg = 10L;
	msgAttr.mq_msgsize = sizeof(Message);
	msgAttr.mq_curmsgs = 0L;
	
	//TODO: create message queue
	if ((messageQueue = mq_open (mqName, O_CREAT | O_RDWR | O_EXCL, 0660, &msgAttr) )<0){
	errnoPrint("Failuer by opening messageQueue");
	return -1;
	}
	mq_unlink(mqName);
	if (pthread_create(&threadId, NULL, broadcastAgent, NULL) < 0) {
                	errnoPrint("pthread_create() failure...");
						
			return -1;
            	}
	
	
	
	//TODO: start thread
	return 1;
}

void broadcastAgentCleanup(void)
{
	//TODO: stop thread
	pthread_cancel(threadId);
	pthread_join(threadId, NULL);
	mq_unlink(mqName);
	mq_close (messageQueue);
	
	//TODO: destroy message queue
	
}

int putInQueue (Message *mqMessage){
struct timespec *abs_timeout = malloc(sizeof(struct timespec));
    abs_timeout->tv_nsec = 25;
    abs_timeout->tv_sec = 0;
    if (mq_timedsend(messageQueue, (char *) mqMessage, sizeof(Message), 0, abs_timeout) == -1) {
   	  full = -1;
    } else {
        full = 1;
    }
	
    return 1;

}

int isMqFull(void){
return full;
}

