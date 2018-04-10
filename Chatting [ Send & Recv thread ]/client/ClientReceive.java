package chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceive extends Thread{
	
	Socket socket = null;
	
	@Override
	public void run(){
		
		ObjectInputStream ois = null;
		
		while(true){
			try {
				// 메세지 읽기 위함 >> 
				ois = new ObjectInputStream(socket.getInputStream());
				String receiveMessage = (String)ois.readObject();
				
				System.out.println(">> " + receiveMessage);
			} 
			catch (IOException | ClassNotFoundException e) {}
		}
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
}
