package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author DoubleR
 * @since 2018 04 04
 * 
 * This class implements java Socket Server
 * **/
public class SocketClient {
	
	public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException{
		
		// localhost IP 획득
		// 서버가 다른 IP에 존재하면 해당 IP로 선정 (서버측의 IP로 구성)
		InetAddress host = InetAddress.getLocalHost();
		
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		int size = 5;
		
		for(int i = 1; i <= size; i++){
			
			// Establish Socket connection to server
			socket = new Socket(host.getHostAddress(), 9876);
			
			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("Sending Request to Socket Server");
			
			if(i == size)
				oos.writeObject("exit");
			else
				oos.writeObject(i+"");
			
			ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			
			System.out.println("Message : " + message);
			
			// close()
			ois.close();
			oos.close();
			
			Thread.sleep(1000);
		}
	}
}
