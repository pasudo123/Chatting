package chat;

import chat.client.SocketClient;
import chat.environment.EnumEnvironment;

public class ChattingUser {
	public static void main(String[]args){
		SocketClient socketClient = new SocketClient();
		socketClient.setEnvironment(EnumEnvironment.OFFICE);
		socketClient.setReader();
		
		Thread thread = new Thread(socketClient);
		thread.start();
		
		System.out.println("-- " + ChattingUser.class.getName() + " Main Thread terminated");
	}
}
