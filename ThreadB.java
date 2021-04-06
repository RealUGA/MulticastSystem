import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadB implements Runnable {
	private static int PORT ;

	public ThreadB(int Port) throws IOException{
		this.PORT = Port;
	}

	private ServerSocket serverSocket1;
	
	@Override
	public void run() {
		try {
			System.out.println(PORT);
			serverSocket1 = new ServerSocket(PORT);
			while (true) {
				System.out.println("Server waiting for connection....");
				Socket clientSocket1 = serverSocket1.accept();
				System.out.println("connected");
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
				System.out.println("Server Connected");
				
				if (!in.ready()) {
					Thread.sleep(100);
				} else {
					while (in.ready()) {
						FileWriter myWriter = new FileWriter("log.txt", true);
						//System.out.println(in.readLine());
						//System.out.println("is this working?");
						myWriter.write(in.readLine());
						myWriter.write("\n");
						
						myWriter.close();
					System.out.println("Message appended to the log.txt");
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("ThreadB disconnected");
			try {
				serverSocket1.close();
			} catch (IOException error) {
				
			}
		}


	}
}
