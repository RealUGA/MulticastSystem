import java.io.*;
import java.net.*;


public class CommandWorker implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter outforb;
    private PrintWriter outfora;
    private String Command;
    private String Addendum;
    private String OwnIP;
    private String PORT;
    private String IP;
    private String ID;


    public CommandWorker(Socket clientSocket) throws IOException {
        this.client = clientSocket;

    }

    public void register(String id, String IP, int port) {

        User member = new User(id, IP, port);
        Coordinator.userList.add(member);
        outfora.println("User Added");

    }

    public void deregister(String id) {
        int length = Coordinator.userList.size();
        for (int i = 0; i <= length; i++) {

            if (Coordinator.userList.get(i).getId().equals(id)) {
                Coordinator.userList.remove(i);
            }
        }
        outfora.println("User Removed");

    }

    public void disconnect(String id) {
        int length = Coordinator.userList.size();

        for (int i = 0; i <= length; i++) {

            if (Coordinator.userList.get(i).getId().equals(id)) {
                Coordinator.userList.get(i).setStatus(false);
                int last_message_id = Coordinator.mQueue.getLast().getId();

                Coordinator.userList.get(i).setLastMessage(last_message_id);
            }
        }
        outfora.println("User Disconnected");


    }

    public void reconnect(String ipofb, int portofb, String id) throws IOException {

        Socket threadb = new Socket(ipofb, portofb);
        PrintWriter outforb = new PrintWriter(threadb.getOutputStream(), true);

        int length = Coordinator.userList.size();

        for (int i = 0; i <= length; i++) {

            if (Coordinator.userList.get(i).getId().equals(id)) {
                Coordinator.userList.get(i).setStatus(true);
                Coordinator.userList.get(i).setPort(portofb);
                int mqlength = Coordinator.mQueue.size();
                boolean flag = false;

                for (int j = 0; j < mqlength; j++) {
                    if (flag == true) {
                        outforb.println(Coordinator.mQueue.get(j).getContents());
                    }
                    if (Coordinator.mQueue.get(j).getMessageID == Coordinator.userList.get(i).getLastMessage()) {
                        flag = true;
                    }
                    if (flag == false) {
                        for (int k = 0; k < mqlength; k++) {
                            outforb.println(Coordinator.mQueue.get(k).getContents());

                        }
                    }
                    threadb.close();


                }

            }
        }

    }

    public void msend(String messagetobesent) throws IOException {
        int length = Coordinator.userList.size();

        for (int i = 0; i <= length; i++) {

            if (Coordinator.userList.get(i).getStatus() == true) {
                int port = Coordinator.userList.get(i).getPort();
                String ip = Coordinator.userList.get(i).getIpAddress();

                Socket threadb = new Socket(ip, port);
                PrintWriter outforb = new PrintWriter(threadb.getOutputStream(), true);

                outforb.println(messagetobesent);

                Message newmsg = new Message(Coordinator.messageNumber + 1, messagetobesent);
                Coordinator.messageNumber++;
                Coordinator.mQueue.add(newmsg);
                threadb.close();

            }
        }

    }

    @Override
    public void run() {
        try{
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));// receives client input
        outfora = new PrintWriter(client.getOutputStream(), true); // used to output to client

        
        int index = 0;
        
        fullCommand = input.readLine();
        int length = fullCommand.length();
        
        index = fullCommand.indexOf(" ");
        ID = fullCommand.substring(0,index);

        fullCommand = fullCommand.substring(index + 1, length - 1);
        OwnIP = fullCommand.substring(0,index);

        fullCommand = fullCommand.substring(index + 1, length - 1);
        Command = fullCommand.substring(0,index);

        fullCommand = fullCommand.substring(index + 1, length - 1);
        Addendum = fullCommand.substring(0,index);
            

        

        if(Command.equals("register")) {
            register(ID,OwnIP,Integer.parseInt(Addendum));
        }
        if(Command.equals("deregister")) {
            deregister(ID);
        }
        if(Command.equals("disconnect")) {
            disconnect(ID);
        }
        if(Command.equals("reconnect")) {
            reconnect(ID,OwnIP,Integer.parseInt(Addendum));
        }
        if(Command.equals("msend")) {
            msend(Addendum);
        }


    }catch(IOException e){

        }
}

}

