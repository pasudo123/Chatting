package simple.chatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[]args) {
		Client client = new Client();
		client.process();
	}

	/**
	 * IP 초기화 <p>
	 */
	private void initIP() {
		try {
			IP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private String IP = null;
	private Socket socket = null; 
	
	/** 서버에게 [쓰기] **/
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private String sendMessage = new String();
	private ObjectOutputStream out = null;
	
	/** 서버로부터 [읽기] **/
	private String recvMessage = new String();
	private ObjectInputStream in = null;
	
	private void process() {
		
		try {
			
			initIP();
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			socket = new Socket(IP, Configuration.PORT);
			
			System.out.println("[Client Info]");
			System.out.println("## client socket Port :: " + socket.getPort());
			System.out.println("## client socket Local Port :: " + socket.getLocalPort());
			System.out.println("## client socket Local SocketAddress :: " + socket.getLocalSocketAddress());
			System.out.println();
			
			while(true) {
				
				/** 클라이언트 >> 서버 : 메시지 [전송] **/
				sendMessage = br.readLine();
				
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(sendMessage);
				out.flush();
				
				if("exit".equalsIgnoreCase(sendMessage)) {
					break;
				}
				
				/** 서버 >> 클라이언트 :: 메시지 [받음] **/
				in = new ObjectInputStream(socket.getInputStream());
				recvMessage = (String)in.readObject();
				System.out.println("server > " + recvMessage);
			}

			if(in != null) {
				in.close();
			}
			
			if(out != null) {
				out.close();
			}

			socket.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
