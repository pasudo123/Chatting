package chat.client;

import java.io.IOException;
import java.net.Socket;

import chat.environment.EnumEnvironment;

public class SocketClient implements Runnable{
	
	private EnumEnvironment environment = null;
	private Socket clientSocket;

	@Override
	public void run() {
		
		/** 클라이언트 측에서 메세지를 센드 & 리시브를 따로 스레드를 구현한다. **/
		ClientReceive clientReceive = new ClientReceive();
		ClientSend clientSend = new ClientSend();
		
		try {
			// - 서버와 통신하기 위한 엔드포인트 소켓 생성 
			// - 해당 클라이언트 소켓에 대한 센드 및 리시브 스레드 설정
			clientSocket = new Socket(environment.getIp(), environment.getPort());
			clientReceive.setSocket(clientSocket);
			clientSend.setSocket(clientSocket);
			
			clientReceive.start();
			clientSend.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + " : IOE");
			System.exit(1);
		}
		
		/** 
		 * 해당 스레드는 종료되지만 센드 스레드와 리시브 스레드는 살아있다.
		 */
	}
	
	public void setEnvironment(EnumEnvironment environment){
		this.environment = environment;
	}
}
