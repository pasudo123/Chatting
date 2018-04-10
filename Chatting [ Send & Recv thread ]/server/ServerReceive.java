package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceive extends Thread{
	
	Socket socket = null;
	
	@Override
	public void run(){
		
		ObjectInputStream ois = null;
		
		// 서버는 계속 돌아간다.
		while(true){
			try {
				// 메세지 읽기 위함 >> 
				ois = new ObjectInputStream(socket.getInputStream());
				String receiveMessage = (String)ois.readObject();
				
				if(receiveMessage.equalsIgnoreCase("exit"))
					receiveMessage = "Clinet Exit 하였습니다.";
	
				System.out.println(">> " + receiveMessage);
			} 
			catch (IOException | ClassNotFoundException e) {}
		}
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
}
