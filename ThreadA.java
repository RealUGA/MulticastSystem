import java.io.*;
import java.net.*;


public class ThreadA implements Runnable{
	private int PORT;
	private String IP;
	private String Command;
	private String Addendum;
	private String ID;
	private String OwnIP;


	public ThreadA(int Port, String IP, String Command, String Addendum, String ID, String OwnIP) throws IOException {
		this.PORT = Port;
		this.IP = IP;
		this.ID = ID;
		this.Command = Command;
		this.Addendum = Addendum;
		this.OwnIP = OwnIP;


	}
	@Override
	public void run() {
		try{
//			System.out.println(PORT+IP+Command+Addendum+ID+OwnIP);
		Socket coordinator = new Socket(IP, PORT);
		PrintWriter coordinatorOutput = new PrintWriter(coordinator.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(coordinator.getInputStream()));

		coordinatorOutput.println(ID+" "+OwnIP+" "+Command+" "+Addendum);
		System.out.println(in.readLine());


		}catch (IOException e){
		}
	}
}
