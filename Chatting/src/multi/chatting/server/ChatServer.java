package multi.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import multi.chatting.client.UserThread;

public class ChatServer {
	
	private int port;
	private Set<String> userNames = new HashSet<String>();
	private Set<UserThread> userThreads = new HashSet<UserThread>();
	
	public ChatServer(int port) {
		this.port = port;
	}
	
	public static void main(String[]args) {
		if(args.length < 1) {
			System.out.println("Syntax :: Java ChatServer <port-number>");
			System.exit(1);
		}
		
		/** 포트번호를 인자로 받음  **/
		int port = Integer.parseInt(args[0]);
		
		ChatServer server = new ChatServer(port);
		server.execute();
	}
	
	public void execute() {
		
		try(ServerSocket serverSocket = new ServerSocket(port)){
			
			System.out.println("-- Waiting for Client Request ...");
			
			while(true) {
				
				Socket socket = serverSocket.accept();
				System.out.println("New user Connected ! ");
				
				UserThread newUser = new UserThread(socket, this);
				userThreads.add(newUser);
				newUser.start();
				
			}// while(true)
			
		} catch(IOException e) {
			System.out.println("Error in the Server :: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * excludeUser 를 제외한 나머지 유저들에게 메시지 전송 <p>
	 * @param message
	 * @param excludeUser
	 */
	public void broadcast(String message, UserThread excludeUser) {
		for(UserThread user : userThreads) {
			if(user == excludeUser) {
				continue;
			}
			
			user.sendMessage(message);
		}
	}
	
	/**
	 * userName 을 가지고 user 추가 <p>
	 * @param userName
	 */
	public void addUserName(String userName) {
		userNames.add(userName);
	}
	
	/**
	 * client 측에서 연결을 끊을 시, UserThread 에서 해당 User 를 삭제한다 . <p>
	 * @param userName
	 * @param paramUser
	 */
	public void removeUser(String userName, UserThread paramUser) {
		boolean removed = userNames.remove(userName);
		
		if(!removed) {
			return;
		}
		
		userThreads.remove(paramUser);
		System.out.println("The user " + userName + " quitted");
	}
	
	public Set<String> getUserNames(){
		return this.userNames;
	}
	
	/**
	 * userThreads 에 유저가 존재하는지 여부 리턴 <p>
	 * @return
	 */
	public boolean hasUsers() {
		return (!this.userNames.isEmpty());
	}
}
