 

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ClientTest {
	public static void main(String[] args) {
		System.out.println("ClientTest started. Type the port:");
		
		Scanner s = new Scanner(System.in);
		String port = s.nextLine();
		
		ClientMain client = new ClientMain();
		client.connect(Integer.parseInt(port));
		
		System.out.println("ClientTest setup done.");
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				client.sendMessage( "hallo Server: " + System.currentTimeMillis() );
			}
		};
		timer.schedule(task, 1300, 2200);
	}
}
