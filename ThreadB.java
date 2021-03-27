import java.io.BufferedReader;
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

	@Override
	public void run() {
		try {
			System.out.println(PORT);
			ServerSocket serverSocket1 = new ServerSocket(PORT);
			while (true) {
				System.out.println("Server waiting for connection....");
				Socket clientSocket1 = serverSocket1.accept();

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
				System.out.println("Server Connected");

				FileWriter myWriter = new FileWriter("log.txt");
//				System.out.println(in.readLine());
				myWriter.write(in.readLine());

				myWriter.close();
				System.out.println("Message appended to the log.txt");


			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}

