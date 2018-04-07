package chat;

import chat.environment.EnumEnvironment;
import chat.server.SocketServer;

public class ChattingLauncher {

	public static void main(String[]args){
		
		SocketServer socketServer = new SocketServer();
		socketServer.setEnvironment(EnumEnvironment.OFFICE);
		
		Thread thread = new Thread(socketServer);
		thread.start();
		
		System.out.println("-- " + ChattingLauncher.class.getName() + " Main Thread terminated");
	}
}
