package multi.chatting.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import multi.chatting.server.ChatServer;

/**
 * 클라이언트에서 서버와 연결하기 위한 스레드 <p>
 * 
 * 추후에 멀티스레드로 여러 명이서 채팅이 가능하도록 할 수 있다. <p>
 * 
 * @author PASUDO
 *
 */
public class UserThread extends Thread{
	
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;
	
	public UserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	public void run() {
		
		try {
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
			
			printUsers();
			
			String userName = reader.readLine();
			server.addUserName(userName);
			
			String serverMessage = "New User Connected :: " + userName;
			server.broadcast(serverMessage, this);
			
			String clientMessage;
			
			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]" + clientMessage;
				server.broadcast(serverMessage,this);
			} while(!clientMessage.equals("bye"));
				
			server.removeUser(userName, this);
			socket.close();
			
			serverMessage = userName + " has quitted.";
			server.broadcast(serverMessage, this);
			
		} catch(IOException e) {
			System.out.println("Error in UserThread : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void printUsers() {
		if(server.hasUsers()) {
			writer.println("Connected Users : " + server.getUserNames());
		}else {
			writer.println("No Other users connected");
		}
	}
	
	public void sendMessage(String message) {
		writer.println(message);
	}
}
