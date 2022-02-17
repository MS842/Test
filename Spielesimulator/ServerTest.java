 

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ServerTest {
	public static void main(String[] args) {
		System.out.println("ServerTest started. Type the port:");
		
		Scanner s = new Scanner(System.in);
		String port = s.nextLine();
		
		ServerMain server = new ServerMain();
		server.start(Integer.parseInt(port));
		
		System.out.println("ServerTest setup done.");
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				server.sendMessage( "hallo Client: " + System.currentTimeMillis() );
			}
		};
		timer.schedule(task, 1000, 2000);
	}
}
