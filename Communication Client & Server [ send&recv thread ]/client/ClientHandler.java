package chat.client;

import java.net.Socket;

public class ClientHandler {
	private ClientReceive clientReceive = null;
	private ClientSend clientSend = null;
	private Socket socket = null;
	
	public void initClientHandler(){
		this.clientReceive = new ClientReceive();
		this.clientSend = new ClientSend();
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
}	
