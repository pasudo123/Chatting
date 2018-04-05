package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.environment.EnumEnvironment;

public class SocketClient implements Runnable{
	
	private EnumEnvironment environment = null;
	private Socket clientSocket;
	
	// 읽고 쓰기
	private BufferedReader bufferedReader = null;
	private InputStreamReader inputStreamReader = null;
//	private ObjectOutputStream oos = null;
//	private ObjectInputStream ois = null;
	
	@Override
	public void run() {
		
		/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 			    [ 클라이언트  ]
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
		String message = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			// 서버와 통신하기 위한 엔드포인트 소켓 생성
			clientSocket = new Socket(environment.getIp(), environment.getPort());
			
			while(true){
				oos = new ObjectOutputStream(clientSocket.getOutputStream());

				message = bufferedReader.readLine();
				oos.writeObject(message);
				
				if(message.equalsIgnoreCase("exit"))
					break;
				
				ois = new ObjectInputStream(clientSocket.getInputStream());
				message = (String) ois.readObject();
				
				System.out.println("Server Message : " + message);
			}
			
			System.out.println("-- Client Close");
			// close()
			ois.close();
			oos.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + " : Error");
		}
		
	}
	
	public void setEnvironment(EnumEnvironment environment){
		this.environment = environment;
	}
	
	public void setReader(){
		this.inputStreamReader = new InputStreamReader(System.in);
		this.bufferedReader = new BufferedReader(inputStreamReader);
	}
}
