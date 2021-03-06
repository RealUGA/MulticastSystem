import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.InetSocketAddress;
/**
 * This class creates a participant that connects or disconnects to a server and receives messages from the server.
 */
public class Participant {

    /**
     * Handles Participant commands and spawns off the appropriate threads.
     */
	public static void main(String[] args) {
		try {
		InetAddress localAddress = InetAddress.getLocalHost();
		String localIP = localAddress.toString();
		/*String hostName = InetAddress.getLocalHost().getHostName();
		InetAddress addrs[] = InetAddress.getAllByName(hostName);
		for (int i = 0; i < addrs.length; i++) {
			System.out.println(addrs[i]);
		}
		localAddress = addrs[1];
		localIP = localAddress.toString();*/
		File config = new File("participant-conf.txt");
		BufferedReader configReader = new BufferedReader(new FileReader(config));
		String id;
		String logFile;
		String portString;
		int port;
		String ipAddress;
		String coordinatorInfo;
		int spaceIndex = 0;
		id = configReader.readLine(); // gets Participant id
		logFile = configReader.readLine();
		coordinatorInfo = configReader.readLine();
		spaceIndex = coordinatorInfo.indexOf(" ");
		ipAddress = coordinatorInfo.substring(0,spaceIndex); // gets Coordinator ip Address
		portString = coordinatorInfo.substring(spaceIndex + 1);
		port = Integer.parseInt(portString); // gets Coordinator Port
		configReader.close(); // all config information read
		
		
		int slashIndex = 0;
		int ipLength = 0;
		ipLength = localIP.length();
		slashIndex = localIP.indexOf("/");
		localIP = localIP.substring(slashIndex + 1, ipLength);
		InetSocketAddress test = new InetSocketAddress (localIP,port);
		//System.out.println(test.getPort());
		
		Scanner inputScanner = new Scanner(System.in);
		String fullCommand;
		String command;
		String secondHalf;
		boolean connCheck = false;
		boolean regCheck = false;
		Thread messageThread = new Thread();
		while (true) {
			System.out.print("input: ");
			fullCommand = inputScanner.nextLine();
			spaceIndex = fullCommand.indexOf(" ");
			if (spaceIndex < 0) {
				command = fullCommand;
				secondHalf = fullCommand;
			} else {
				command = fullCommand.substring(0,spaceIndex);
				secondHalf = fullCommand.substring(spaceIndex + 1);
			}
		
			if (command.equals("register") && !regCheck) {
				ThreadB mRun = new ThreadB(Integer.parseInt(secondHalf)); // send the port to be created on
				messageThread = new Thread(mRun);
				messageThread.start();
				ThreadA cRun = new ThreadA(port, ipAddress, command, secondHalf, id, localIP);
				Thread commandThread = new Thread(cRun);
				commandThread.start();
				regCheck = true;
				connCheck = true;
			} else if (command.equals("reconnect") && !connCheck && regCheck) {
				ThreadB mRun = new ThreadB(Integer.parseInt(secondHalf)); // send the port to be created on
				messageThread = new Thread(mRun);
				messageThread.start();
				ThreadA cRun = new ThreadA(port, ipAddress, command, secondHalf, id, localIP);
				Thread commandThread = new Thread(cRun);
				commandThread.start();
				connCheck = true;
			} else if (command.equals("deregister") && regCheck && connCheck) {
				ThreadA cRun = new ThreadA(port, ipAddress, command, secondHalf, id, localIP);
				Thread commandThread = new Thread(cRun);
				commandThread.start();
				regCheck = false;
                connCheck = false;
				messageThread.interrupt();
			} else if (command.equals("disconnect") && connCheck && regCheck) {
				ThreadA cRun = new ThreadA(port, ipAddress, command, secondHalf, id, localIP);
				Thread commandThread = new Thread(cRun);
				commandThread.start();
				connCheck = false;
				messageThread.interrupt();
			} else if (command.equals("msend") && connCheck) {
				ThreadA cRun = new ThreadA(port, ipAddress, command, secondHalf, id, localIP);
				Thread commandThread = new Thread(cRun);
				commandThread.start();
			} else if (command.equals("register")) {
				System.out.println("Already registered on system.");
			} else if (command.equals("reconnect")) {
				System.out.println("Either already connected to system or not registerd.");
			} else if (command.equals("deregister")) {
				System.out.println("Must be registered and connected to system to deregister.");
			} else if (command.equals("disconnect")) {
				System.out.println("Must be registered and connected ot system to disconnect.");
			} else if (command.equals("msend")) {
				System.out.println("Must be online to send messages.");
			} else {
				System.out.println("Invalid command");
			}
		
		}
		} catch (IOException error) {
			
		}

	}			

}
