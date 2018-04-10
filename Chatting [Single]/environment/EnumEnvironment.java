package chat.environment;

public enum EnumEnvironment {
	
	ROOM("192.168.0.2", 9090),			// 자취방 (와이파이)
	OFFICE("192.168.2.104", 9090);		// 회사 (와이파이)

	private String ip;
	private int port;
	
	private EnumEnvironment(){}
	private EnumEnvironment(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public String getIp(){
		return ip;
	}
	
	public int getPort(){
		return port;
	}
}
