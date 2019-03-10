## Overview
2018 04 04 ~   
- 자바로 소켓환경에서 채팅 프로그램 구현.
- __클라이언트와 서버는 소켓에 쓰거나 소켓에서 읽음으로써 서로 통신할 수 있다.__

## 목차
1. [소켓 개념 정리](https://github.com/pasudo123/Chatting/blob/master/explain/%EC%86%8C%EC%BC%93%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC.md)

## Error Message
* _java.net.SocketException: Connection reset by peer: socket write error_  
__소켓에 쓰기를 수행하는 경우__, 반대편 소켓에 close 된 경우 발생
* _java.net.SocketException: Connection reset_  
__소켓을 읽기를 수행하는 경우__, 반대변 소켓에 close 된 경우 발생

## Reference 
* [Java Socket Programming - JournalDev](https://www.journaldev.com/741/java-socket-programming-server-client)
* [Java Socket - JAVA school](http://java-school.net/java/Socket)
* [How to create a chat console application in java using socket](http://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
