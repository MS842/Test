 

import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader extends Thread {
	private BufferedReader bufferedReader;
	private ServerMain serverMain;
	
	private boolean run;
	
	public ServerReader(BufferedReader bufferedReader, ServerMain serverMain) {
		this.bufferedReader = bufferedReader;
		this.serverMain = serverMain;
		run = true;
	}
	
	@Override
	public void run() {
		serverMain.print("Listener running.");
		while(run) {
			try {
				if(bufferedReader.ready()) {
					String s = bufferedReader.readLine();
					serverMain.setMsgRec(s);
					serverMain.print("New message from Client: " + s);
				}
			} catch (IOException e) {
				serverMain.error(e,"Failed to read reader.");
			}
		}
	}
	
	public void exit() {
		run = false;
		this.interrupt();
	}
}