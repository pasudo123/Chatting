package multichat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.environment.EnumEnvironment;

public class ChattingUser {
	private String name;
	private Socket userSocket;
	
	// 유저 설정
	public ChattingUser(String name){
		initSocket(EnumEnvironment.ROOM);
		this.name = name;
	}
	
	// 소켓 초기화
	private void initSocket(EnumEnvironment environment){
		try {
			this.userSocket = new Socket(environment.getIp(), environment.getPort());
		} 
		catch (UnknownHostException e) {
			if(environment == EnumEnvironment.OFFICE){
				e.printStackTrace();
				System.exit(1);
			}
			initSocket(EnumEnvironment.OFFICE);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
