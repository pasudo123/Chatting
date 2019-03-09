package simple.chatting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[]args) {
		Server server = new Server();
		server.process();
	}
	
	/** 소켓 관련  **/
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	/** 클라이언트로부터 [읽어들이는] 데이터 스트림 관련  **/
	private ObjectInputStream in = null;
	private String recvMessage = new String();
	
	/** 클라이언트로부터 [전송하는] 데이터 스트림 관련  **/
	private String sendMessage = null;
	private ObjectOutputStream out = null;
	
	private void process(){
		
		try {
			
			/**
			 * serverSocket = new ServerSocket(Configuration.PORT) 와 동일한 표현
			 * 위의 내용은 socket create 및  bind 가 포함되어 있는 상태이다.
			 */
			
			serverSocket = new ServerSocket();
			InetSocketAddress address = new InetSocketAddress(Configuration.PORT);
			serverSocket.bind(address);
			
			System.out.println("[Server Info]");
			System.out.println("## server socket Local Port :: " + serverSocket.getLocalPort());
			System.out.println("## server socket Local SocketAddress :: " + serverSocket.getLocalSocketAddress());
			System.out.println();
			
			System.out.println("-- Waiting for Client Request ...");
			socket = serverSocket.accept();
			System.out.println("-- Connection !");	
			
			while(true) {
				
				/** 클라이언트 >> 서버 : 메시지 [받음] **/
				in = new ObjectInputStream(socket.getInputStream());
				recvMessage = (String)in.readObject();
				
				/** client close **/
				if("exit".equalsIgnoreCase(recvMessage)) {
					System.out.println("-- Client Close");
					break;
				}
				
				/** 서버 >> 클라이언트 : 메시지 [전송] **/
				out = new ObjectOutputStream(socket.getOutputStream());
				sendMessage = recvMessage.toUpperCase();
				out.writeObject(sendMessage);
				out.flush();
				
			}// while(true)
			
			if(in != null) {
				in.close();
			}
			
			if(out != null) {
				out.close();
			}
			
			socket.close();
			serverSocket.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				}
				catch(IOException e) {
					System.exit(1);
				}
			}
		}
	}
}
