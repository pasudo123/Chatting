## Overview
2018 04 04 ~   
- 자바로 소켓 서버와 소켓 클라이언트 작성
- 서버와 클라이언트 프로그램에서 소켓에 데이터를 읽고 쓰기
- 개념정리 및 공부
- GUI 환경에서 채팅 프로그램 구현
- __클라이언트와 서버는 소켓에 쓰거나 소켓에서 읽음으로써 서로 통신할 수 있다.__


## Concepts
* __Socket__
  * 소켓은 돌아가는 두 프로그램이 서로 통신을 수행할 수 있도록 양쪽에 생성되는 __엔드포인트(endpoint)__ 이다.
  * 소켓 서버는 특정 컴퓨터에서 실행되고, __특정 포트 번호에 바인딩 된 소켓__ 을 가지고 있다.
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
* __End-Point__
  * 엔드포인트는 IP주소와 포트번호의 조합이다. 모든 TCP 연결은 두 개의 끝점으로 고유하게 식별될 수 있다. 두 개의 끝점이 고유하게 식별된다면 클라이언트와 서버간에 여러 개의 연결을 가질 수 있게 된다. (Multiple Connections)

## Learning By Doing
* IP Address
* Port Number
* Protocol
* MAC Address
* Connection-Oriented Protocol & Connection-less Protocol
  * TCP
  * UDP
* Socket

## Part of Code
#### Socket Server
~~~
server = new ServerSocket(PORT);

// 해당 포트번호로 클라이언트에게 요청이 올때까지 계속 대기
while(true){
	
	System.out.println("Waiting for Client Request");
	Socket socket = server.accept();
	
	// read from socket & convert to String
	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	String message = (String) ois.readObject();
	
	System.out.println("Message Received : " + message);
	
	// create Object & write Object to Socket
	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	oos.writeObject("Hi Client " + message);
	
	// close(
	ois.close();
	oos.close();
	
	// exit 요청은 서버 종료 (대소문자 구분 X)
	if(message.equalsIgnoreCase("exit"))
		break;

}// while(true)

System.out.println("Shutdown Socket Server");
server.close();
~~~

#### Socket Client
~~~
// localhost IP 획득
// 서버가 다른 IP에 존재하면 해당 IP로 선정 (서버측의 IP로 구성)
InetAddress host = InetAddress.getLocalHost();

Socket socket = null;
ObjectOutputStream oos = null;
ObjectInputStream ois = null;

int size = 5;

for(int i = 1; i <= size; i++){
	
	// Establish Socket connection to server
	socket = new Socket(host.getHostAddress(), 9876);
	
	// write to socket using ObjectOutputStream
	oos = new ObjectOutputStream(socket.getOutputStream());
	
	System.out.println("Sending Request to Socket Server");
	
	if(i == size)
		oos.writeObject("exit");
	else
		oos.writeObject(i+"");
	
	ois = new ObjectInputStream(socket.getInputStream());
	String message = (String) ois.readObject();
	
	System.out.println("Message : " + message);
	
	// close()
	ois.close();
	oos.close();
	
	Thread.sleep(1000);
}
~~~

## Reference 
* [Java Socket Programming - JournalDev](https://www.journaldev.com/741/java-socket-programming-server-client)
* [Java Networking - java T point](https://www.javatpoint.com/java-networking)
* [Java Networking - tutorialspoint](https://www.tutorialspoint.com/java/java_networking.htm)
* [Java Socket - JAVA school](http://java-school.net/java/Socket)
* [All About Socket](https://docs.oracle.com/javase/tutorial/networking/sockets/index.html)
