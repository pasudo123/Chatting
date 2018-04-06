package multichat.environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

public enum EnumEnvironment {
	
	ROOM(9090),			// 자취방 (와이파이)
	OFFICE(9090);		// 회사 (와이파이)

	private String ip;
	private int port;
	
	private EnumEnvironment(){}
	private EnumEnvironment(int port){
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
			this.port = port;
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public String getIp(){
		return ip;
	}
	
	public int getPort(){
		return port;
	}
}
