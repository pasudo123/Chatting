# Chatting Program


### overview
2018 04 04 ~   
- 자바로 소켓 서버와 소켓 클라이언트 작성
- 서버와 클라이언트 프로그램에서 소켓에 데이터를 읽고 쓰기
- 개념정리 및 공부
- GUI 환경에서 채팅 프로그램 구현


### Concepts
* __Socket__
  * 소켓은 돌아가는 두 프로그램이 서로 통신을 수행할 수 있도록 양쪽에 생성되는 __엔드포인트(endpoint)__ 이다.
  * 소켓 서버는 특정 컴퓨터에서 실행되고, 특정 포트 번호에 바인딩 된 소켓을 가지고 있다.
  * 소켓 서버는 클라이언트 소켓의 요청이 올 때까지 기다리고 있다.
* __Client-Side__
  * 클라이언트는 서버가 실행되고 있는 __호스트의 이름__ __&__ __서버가 수신 중인 포트번호__ 를 알고 있어야한다.
  * 클라이언트는 서버와의 연결을 요청하기 위해 서버의 시스템 및 포트와의 접촉을 시도한다.
  * 클라이언트는 서버가 자신을 식별할 수 있도록 연결 중에 사용할 포트번호를 바인드(bind)하여야 한다.
* __Server-Side__
  * 클라이언트의 요청에 대해 호스트이름과 포트번호가 이상 없으면, 서버는 연결을 __승인(accept)__ 한다. 
  * 서버는 동일한 로컬 포트번호에 바인드된 새로운 소켓을 가지고 온다.
  * 클라이언트의 주소 및 포트로 설정된 Remote endpoint 가 클라이언트의 주소와 포트로 설정되어있다.
  * 클라이언트의 연결 요청에 대한 새로운 소켓이 필요하다.


### Learning By Doing
* IP Address
* Port Number
* Protocol
* MAC Address
* Connection-Oriented Protocol & Connection-less Protocol
  * TCP
  * UDP
* Socket

### Part of Code


### reference 
* [Java Socket Programming - JournalDev](https://www.journaldev.com/741/java-socket-programming-server-client)
* [Java Networking - java T point](https://www.javatpoint.com/java-networking)
* [Java Networking - tutorialspoint](https://www.tutorialspoint.com/java/java_networking.htm)
* [Java Socket - JAVA school](http://java-school.net/java/Socket)
