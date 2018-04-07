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

	@Override
	public void run() {
		
		/** 서버 측에서 메시지를 센드 & 리시브 관련 스레도 따로 구현 **/
		ServerReceive serverReceive = new ServerReceive();
		ServerSend serverSend = new ServerSend();
		
		try {
			serverSocket = new ServerSocket(environment.getPort());
			
			System.out.println("-- Waiting for Client Request ...");
			Socket socket = null;
			
			// 특정 IP 및 특정 포트에 대한 클라이언트 연결 요청을 계속 기다린다.
			while(true){
				while ((socket = serverSocket.accept()) != null) {
					serverReceive.setSocket(socket);
					serverSend.setSocket(socket);
					System.out.println("-- Connection!");

					serverReceive.start();
					serverSend.start();
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + " : IOE");
			System.exit(1);
		} // catch()
	}// run() : thread
	
	public void setEnvironment(EnumEnvironment environment){
		this.environment = environment;
	}
}
