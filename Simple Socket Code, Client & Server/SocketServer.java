package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author DoubleR
 * @since 2018 04 04
 * 
 * This class implements java Socket Server
 * **/
public class SocketServer {
	
	private static ServerSocket server;		// Static Variable
	private static final int PORT = 9876;	// 서버 소켓에서 대기할 포트 번호
	
	public static void main(String[]args) throws IOException, ClassNotFoundException{
		
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
		
	}
}
