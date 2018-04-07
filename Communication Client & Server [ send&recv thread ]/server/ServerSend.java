package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSend extends Thread{
	
	Socket socket = null;
	
	@Override
	public void run(){
		
		ObjectOutputStream oos = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while(true){
			try {
				// 메세지를 쓰기 위함 >> 그리고 상대방으로 보내기 위함.
				oos = new ObjectOutputStream(socket.getOutputStream());
				String sendMessage = br.readLine();
				
				oos.writeObject(sendMessage);
			} 
			catch (IOException e) {}
		}// while(true)
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
}
