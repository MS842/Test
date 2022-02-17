package de.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	private Socket socket;
	private InputStreamReader inputStreamReader;
	private OutputStreamWriter outputStreamWriter;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	private Thread clientListener;
	
	private String msgReceived;
	private boolean print = true;
	
	public ClientMain(int port) {
		socket = null;
		inputStreamReader = null;
		outputStreamWriter = null;
		bufferedReader = null;
		bufferedWriter = null;
		connect(port);
	}
	
	public ClientMain() {
		socket = null;
		inputStreamReader = null;
		outputStreamWriter = null;
		bufferedReader = null;
		bufferedWriter = null;
	}
	
	public boolean connect(int port) {
		try {
			socket = new Socket("localhost",port);
		} catch (IOException e) { 
			return error(e, "Failed to connect to server on port " + port + ".");
		}
		print("Socket created on port: " + port + ".");
		
		try {
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		} catch (IOException e) { 
			return error(e, "Failed to setup readers and writers.");
		}
		print("Reader and writers setup.");
		
		clientListener = new ClientListener(bufferedReader,this);
		clientListener.start();
		
		return true;
	}
	
	public boolean sendMessage(String s) {
		try {
			bufferedWriter.write(s);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			print("Send message to Server: " + s);
			return true;
		} catch (IOException e) {
			return error(e, "Failed to send message.");
		}
	}
	
	public void setMsgRec(String s) {
		msgReceived = s;
	}
	
	public boolean newMsg() {
		if(msgReceived != null) return true;
		return false;
	}
	
	public String getMsg() {
		String s = msgReceived;
		msgReceived = null;
		return s;
	}
	
	public boolean exit() {
		try {
			clientListener.interrupt();
			inputStreamReader.close();
			outputStreamWriter.close();
			bufferedReader.close();
			bufferedWriter.close();
			socket.close();
			return true;
		} catch (IOException e) {
			return error(e, "Failed to close socket, reader or writer.");
		}
	}

	public boolean error(Exception e, String s) {
		print(s);
		print("----------StackTrace----------");
		e.printStackTrace();
		return false;
	}
	
	public void print(String s) {
		if(print) {
			System.out.println(s);
		}
	}
}
