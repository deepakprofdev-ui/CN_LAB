#include<winsock2.h>
#include<stdio.h>
#pragma comment(lib,"ws2_32.lib")
int main(){
  WSADATA w; WSAStartup(MAKEWORD(2,2),&w);
  SOCKET s=socket(AF_INET,SOCK_STREAM,0);
  struct sockaddr_in sv;
  sv.sin_family=AF_INET; sv.sin_addr.s_addr=inet_addr("127.0.0.1");
  sv.sin_port=htons(8888);
  connect(s,(struct sockaddr*)&sv,sizeof(sv));
  send(s,"Hello World",11,0);
  printf("Sent: Hello World");
  closesocket(s); WSACleanup();
}
