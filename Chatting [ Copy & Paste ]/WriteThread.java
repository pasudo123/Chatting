package re_chat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread{
	private PrintWriter writer;
	private Socket socket;
	private ChatClient client;
	private Scanner scn;
	
	public WriteThread(Socket psocket, ChatClient client){
		this.socket = psocket;
		this.client = client;
		
		try{
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
		}
		catch(IOException ex){
			System.out.println("Error getting output stream : " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void run(){
		System.out.print("Enter your name : ");
		
		scn = new Scanner(System.in);
		String userName = scn.next();
		
		client.setUserName(userName);
		writer.println(userName);
		
		String text;
		
		do{
			text = "[ " + userName + " ]";
			writer.println(text);
		}
		while(!text.equals("bye"));
		
		try{
			socket.close();
		}
		catch(IOException ex){
			System.out.println("Error writing to server : " + ex.getMessage());
		}
	}
}
