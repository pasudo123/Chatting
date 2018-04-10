package re_chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	private String hostName;
	private int port;
	private String userName;
	
	public ChatClient(String hostName, int port){
		this.hostName = hostName;
		this.port = port;
	}
	
	public void execute(){
		try{
			Socket socket = new Socket(hostName, port);
			
			System.out.println("Connected to the chat server");
			
			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();
		}
		catch(UnknownHostException ex){
			System.out.println("Server not found : " + ex.getMessage());
		}
		catch(IOException ex){
			System.out.println("I/O Error : " + ex.getMessage());
		}
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}

	public static void main(String[]args){
		String hostName = "localhost";
		int port = 9090;
		
		ChatClient client = new ChatClient(hostName, port);
		client.execute();
	}
}
