package de.test;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener extends Thread {
	private BufferedReader bufferedReader;
	private ClientMain clientMain;
	private boolean run;
	
	public ClientListener(BufferedReader bufferedReader, ClientMain clientMain) {
		this.bufferedReader = bufferedReader;
		this.clientMain = clientMain;
		run = true;
	}
	
	@Override
	public void run() {
		clientMain.print("Listener running.");
		while(run) {
			try {
				if(bufferedReader.ready()) {
					String s = bufferedReader.readLine();
					clientMain.setMsgRec(s);
					clientMain.print("New message from Server: " + s);
				}
			} catch (IOException e) {
				clientMain.error(e,"Failed to read reader.");
			}
		}
	}
	
	public void exit() {
		run = false;
		this.interrupt();
	}
}
