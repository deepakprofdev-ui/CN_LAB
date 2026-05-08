#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

int main() {
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in server;
    server.sin_family = AF_INET;
    server.sin_port = htons(5500);
    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    connect(sock, (struct sockaddr*)&server, sizeof(server));
    char *msg = "Hello from C Client!\n";
    send(sock, msg, strlen(msg), 0);
    char buf[1024] = {0};
    recv(sock, buf, 1024, 0);
    printf("Java Server says: %s\n", buf);
    close(sock);
    return 0;
}
