import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.time.Instant;
import java.util.Queue;
import java.util.LinkedList;

public class Coordinator {
    
    public static Queue<Message> mQueue = new LinkedList<Message>();; // stores Messages in a queue
    public static ArrayList<User> userList = new ArrayList<User>();
    public static int messageNumber = 0;
    
    public static void main(String[] args) throws IOException {

        File config = new File("coordinator-conf.txt");
		BufferedReader configReader = new BufferedReader(new FileReader(config));
        String timeString;
        long time;
        String portString;
		int port;
		portString = configReader.readLine();
		port = Integer.parseInt(portString); // gets Coordinator Port
        timeString = configReader.readLine();
        time = Long.parseLong(timeString);
		configReader.close(); // all config information read
        
        ServerSocket coordSocket = new ServerSocket(port);

        TimeKeeper tKeeper = new TimeKeeper(time);
        Thread timeCheck = new Thread(tKeeper);
        timeCheck.start();
        
        while(true) {
            System.out.println("Coordinator waiting for connection....");
            Socket partSocket = coordSocket.accept();
            //BufferedReader in = new BufferedReader(new InputStreamReader(partSocket.getInputStream()));
            //PrintWriter out = new PrintWriter(partSocket.getOutputStream(),true);
            
            System.out.println("Coordinator Connected");
            CommandWorker cWorker = new CommandWorker(partSocket);
            //CommandHandler clientThread1 = new CommandHandler(clientSocket1);
            Thread t = new Thread(cWorker);
            t.start();
        }

    }
}
