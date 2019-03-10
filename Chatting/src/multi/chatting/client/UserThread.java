package multi.chatting.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public UserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	public void run() {
		
		/** 서버에게 [쓰기] **/
		String sendMessage = new String();
		ObjectOutputStream out = null;
		
		/** 서버로부터 [읽기] **/
		String recvMessage = new String();
		ObjectInputStream in = null;
		
		try {
			
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(sendMessage);
			out.flush();
			
		}
		
	}
	
}
