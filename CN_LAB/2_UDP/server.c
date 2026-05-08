#include<winsock2.h>
#include<stdio.h>
#pragma comment(lib,"ws2_32.lib")
int main(){
  WSADATA w; WSAStartup(MAKEWORD(2,2),&w);
  SOCKET s=socket(AF_INET,SOCK_STREAM,0),ns;
  struct sockaddr_in sv;
  char msg[100];
  sv.sin_family=AF_INET; sv.sin_addr.s_addr=INADDR_ANY;
  sv.sin_port=htons(8888);
  bind(s,(struct sockaddr*)&sv,sizeof(sv));
  listen(s,3);
  ns=accept(s,NULL,NULL);
  recv(ns,msg,100,0);
  printf("Client: %s",msg);
  closesocket(s); WSACleanup();
}
