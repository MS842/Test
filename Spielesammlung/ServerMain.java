 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
	private Socket socket;
	public ServerSocket serverSocket;
	private InputStreamReader inputStreamReader;
	private OutputStreamWriter outputStreamWriter;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	private Thread serverListener;
	
	private String msgReceived;
	private boolean print = true;
	
	public ServerMain(int port) {
		socket = null;
		serverSocket = null;
		inputStreamReader = null;
		outputStreamWriter = null;
		bufferedReader = null;
		bufferedWriter = null;
		start(port);
	}
	
	public ServerMain() {
		socket = null;
		serverSocket = null;
		inputStreamReader = null;
		outputStreamWriter = null;
		bufferedReader = null;
		bufferedWriter = null;
	}
	
	public boolean start(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) { 
			return error(e, "Failed to start serverSocket on port " + port + ".");
		}
		print("ServerSocket created on port: " + port + ".");
		
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			return error(e, "Failed to connect to socket to serverSocket.");
		}
		print("Socket connected to serverSocket.");
		
		try {
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		} catch (IOException e) { 
			return error(e, "Failed to setup readers and writers.");
		}
		print("Reader and writers setup.");
		
		serverListener = new ServerReader(bufferedReader,this);
		serverListener.start();
		
		return true;
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
	
	public boolean sendMessage(String s) {
		try {
			bufferedWriter.write(s);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			print("Send message to Client: " + s);
			return true;
		} catch (IOException e) {
			return error(e, "Failed to send message.");
		}
	}
	
	public boolean exit() {
		try {
			serverListener.interrupt();
			inputStreamReader.close();
			outputStreamWriter.close();
			bufferedReader.close();
			bufferedWriter.close();
			socket.close();
			serverSocket.close();
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
