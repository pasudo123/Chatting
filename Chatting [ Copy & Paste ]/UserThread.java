package re_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 해당 스레드는 연결된 각 클라이언트에 대한 연결을 처리하는 서버
 * **/
public class UserThread extends Thread{
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;
	
	public UserThread(Socket socket, ChatServer server){
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run(){
		try{
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
			
			printUsers();
			
			String userName = reader.readLine();
			server.addUserName(userName);
			
			String serverMessage = "New user connected : " + userName;
			server.broadcast(serverMessage, this);
			
			String clientMessage;
			
			do{
				clientMessage = reader.readLine();
				serverMessage = "[ " + userName + " ] : " + clientMessage;
				server.broadcast(serverMessage, this);
			}
			while(!clientMessage.equals("bye"));
			
			server.removeUser(userName, this);
			socket.close();
			
			serverMessage = userName = " has quitted";
			server.broadcast(serverMessage, this);
			
		}// try
		catch(IOException ex){
			System.out.println("Error in UserThread : " + ex.getMessage());
			ex.printStackTrace();
		}// catch
	}
	
	public void printUsers(){
		if(server.hasUsers())
			writer.println("Connected users : " + server.getUserNames());
		else
			writer.println("No other users connected");
	}
	
	public void sendMessage(String message){
		writer.println(message);
	}
}
