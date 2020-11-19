#ifndef CHAT_PROTOCOL_H
#define CHAT_PROTOCOL_H

#define LOGIN_RESPONSE_STATUS_SUCCESS 0
#define LOGIN_RESPONSE_STATUS_NAME_TAKEN 1
#define LOGIN_RESPONSE_STATUS_NAME_INVALID 2
#define LOGIN_RESPONSE_STATUS_PROTOCOL__MISMATCH 3
#define LOGIN_RESPONSE_STATUS_OTHER_SERVER_ERROR 255

#define NO_CHAR_UNDER_ALLOWED 32
//#define NO_CHAR_OVER_ALLOWED 127
#define QUOTE 39
#define DOUBLE_QUOTE 35
#define BACKTICK 96

#define CONNECTION_CLOSED_CLIENT 0
#define KICKED_FROM_SERVER 1
#define COMMUNICATION_ERROR 2
	
		
#define REMOVE_USER_WITHOUT_MESSAGE 0
#define REMOVE_USER_WITH_MESSAGE 1 
#define NO_REMOVE_CODE	2

#define MQ_NOT_PAUSED 0
#define MQ_PAUSED 1


#define USERNAME_USED 1
#define USERNAME_FREE 0

#define LENGTH_MAX 36
#define LENGTH_MIN 6

#define MAX_TEXT 512
#define MAX_NAME 31

#define VERSION 0

#define MAGIC_LOGIN_REQUEST 0x0badf00d
#define MAGIC_LOGIN_RESPONSE 0xc001c001
#define SERVER_NAME "GroupServer5"

#define COMMAND_KICK "/kick"
#define COMMAND_PAUSE "/pause"
#define COMMAND_RESUME "/resume"




#include <stdint.h>
#include <arpa/inet.h>


enum { MSG_MAX = 1024 };

enum {	logReq,
	logResp,
    	cli2Serv,
    	serv2Cli,
    	userAdd,
    	userRem};

/* old version
typedef struct __attribute__((packed))
{
	uint16_t len;		//real length of the text (big endian, len <= MSG_MAX)
	char text[MSG_MAX];	//text message
} Message;
*/


#pragma pack(1) //needed too get rid of padding within structure

typedef struct messageHeader {
    uint8_t type;	//indicates type of message
    uint16_t length;	//length of additional data
} messageHeader;

typedef struct loginRequest {
    uint32_t magic;
    uint8_t version;
    char name[MAX_NAME]; //max of 31 
} loginRequest;

typedef struct loginResponse {
    uint32_t magic;
    uint8_t code;
    char serverName[MAX_NAME];
} loginResponse;

typedef struct client2Server {
    char text[MAX_TEXT];
} client2Server;

typedef struct server2Client {
    uint64_t timestamp;
    char originalSender[32];//max of 31 + null termination
    char text[MAX_TEXT];
} server2Client;

typedef struct userAdded {
    uint64_t timestamp;
    char name[MAX_NAME];
} userAdded;

typedef struct userRemoved {
    uint64_t timestamp;
    uint8_t code;
    char name[MAX_NAME];
} userRemoved;

typedef union messageBody {
    loginRequest logReq;
    loginResponse logResp;
    client2Server cli2Serv;
    server2Client serv2Cli;
    userAdded userAdd;
    userRemoved userRem;
} messageBody;

typedef struct Message {
    messageHeader messHeader;
    messageBody messBody;
} Message;


#pragma pack(0) //reset


ssize_t networkReceive(int fd, Message *buffer);
int networkSend(int fd, const Message *buffer);
int recvLoginRequest(int fd, Message *buffer);
int sendLoginResponse(int fd,  uint8_t code);
int sendUserAdded (int fd, char *name, uint8_t type ,uint64_t timestamp);
int sendUserRemoved(int fd, char *name, uint8_t code, uint64_t timestamp);



int prepareServerMessage (char *username, Message *buffer, char *originalMessage, uint64_t timestamp );



int recvClientMessage(int fd, Message *buffer);

//int sendServerMessage(int fd, char *username, char *originalMessage, uint64_t timestamp);

#endif


