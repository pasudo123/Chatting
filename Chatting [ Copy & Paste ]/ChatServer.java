package re_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * - 서버는 ChatServer & UserThread 두 클래스로 구현.
 * - ChatServer 클래스는 특정 포트에서 수신 대기하면서 서버를 시작
 * - 새 클라이언트가 연결되면 해당 클라이언트를 제공하기 위해 UserThread의 인스턴스가 생성
 * - 각 연결은 **별도** 의 스레드에서 처리, 서버는 동시에 여러 클라이언트를 처리 가능
 * **/

public class ChatServer {
	/**
	 * - 특정 포트 번호
	 * - 유저 이름, 유저는 셋 컬렉션으로 관리 왜? 순서는 중요하지 않다. 중복 체킹
	 * **/
	private int port;
	private Set<String> userNames = new HashSet<String>();
	private Set<UserThread> userThreads = new HashSet<UserThread>();
	
	public ChatServer(int port){
		this.port = port;
	}
	
	public void execute(){
		try(ServerSocket serverSocket = new ServerSocket(port)){
			
			System.out.println("Chat Server is listening on port : " + port);
			
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("New user connected");
				
				UserThread newUser = new UserThread(socket, this);
				userThreads.add(newUser);
				newUser.start();
			}
		} 
		catch (IOException e) {
			System.out.println("Error in the server : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Delivers a message from one user to others (broadcasting)
	public void broadcast(String message, UserThread excludeUser){
		for(UserThread anotherUser : userThreads){
			if(anotherUser != excludeUser)
				anotherUser.sendMessage(message);
		}
	}
	
	public void addUserName(String userName){
		userNames.add(userName);
	}
	
	// When a client is disconnect, removes the associated userName & userThread
	public void removeUser(String userName, UserThread anotherUser){
		boolean removed = userNames.remove(userName);
		
		if(removed){
			userThreads.remove(anotherUser);
			System.out.println("The user " + userName + " quitted");
		}
	}
	
	public Set<String> getUserNames(){
		return userNames;
	}
	
	public boolean hasUsers(){
		boolean isEmpty = userNames.isEmpty();
		return isEmpty;
	}
	
	public static void main(String[]args){
		int port = 9090;
		
		ChatServer chatServer = new ChatServer(port);
		chatServer.execute();
	}
}
