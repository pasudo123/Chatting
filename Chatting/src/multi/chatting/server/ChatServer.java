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
	
	/** 유저 추가 **/
	public void addUserName(String userName) {
		userNames.add(userName);
	}
	
	
	
	/** 유저 존재 여부  **/
	public boolean hasUsers() {
		return (!this.userNames.isEmpty());
	}
}
