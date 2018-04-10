package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.environment.EnumEnvironment;

public class SocketServer implements Runnable{

	private EnumEnvironment environment = null;
	private ServerSocket serverSocket;

	// 읽고 쓰기
	private BufferedReader bufferedReader = null;
	private InputStreamReader inputStreamReader = null;
	
	@Override
	public void run() {
		
		/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 				  [ 서버  ]
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
		String message = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		try {
			serverSocket = new ServerSocket(environment.getPort());

			// 클라이언트의 연결요청에 대기 ( 다중 클라이언트인 경우?? )
			System.out.println("-- Waiting for Client Request ...");
			Socket socket = serverSocket.accept();
			System.out.println("-- Connection!");
			
			while (true) {
				ois = new ObjectInputStream(socket.getInputStream());
				message = (String) ois.readObject();

				System.out.println("Client Message >> " + message);

				String serverMessage = bufferedReader.readLine();

				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(serverMessage);

				if (message.equalsIgnoreCase("exit"))
					break;
			}

			serverSocket.close();

			// close()
			ois.close();
			oos.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + " : IOE | ClassNotFoundE");
			System.exit(1);
		} // catch()

	}// run() : thread
	
	public void setEnvironment(EnumEnvironment environment){
		this.environment = environment;
	}
	
	public void setReader(){
		this.inputStreamReader = new InputStreamReader(System.in);
		this.bufferedReader = new BufferedReader(inputStreamReader);
	}
}
