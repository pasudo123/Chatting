package multichat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import multichat.client.ChattingUser;
import multichat.environment.EnumEnvironment;

public class ChattingServer implements Runnable{
	private List<ChattingUser> chattingUserList;
	private ServerSocket serverSocket = null;
	
	public ChattingServer(){
		initServerSocket(EnumEnvironment.ROOM);
		this.chattingUserList = new ArrayList<ChattingUser>();
	}
	
	// 서버 소켓 초기화
	private void initServerSocket(EnumEnvironment environment){
		try {
			this.serverSocket = new ServerSocket(environment.getPort());
		}
		catch (IOException e) {
			if(environment == EnumEnvironment.OFFICE){
				e.printStackTrace();
				System.exit(1);
			}
			initServerSocket(EnumEnvironment.OFFICE);
		}
	}
	
	@Override
	public void run() {
		try {
			/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 * 
			 * 		      [ 클라이언트의 요청을 대기 / 수동 대기 모드 ]
			 * 
			 * (1) 클라이언트는 IP와 포트번호를 가지고 해당 서버의 IP와 포트 번호의 소켓과 연결 시도
			 * 
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
			Socket socket = serverSocket.accept();
			
			while(true){
				
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addChattingUser(ChattingUser user){
		chattingUserList.add(user);
	}
}
