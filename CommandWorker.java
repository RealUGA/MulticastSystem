import java.io.*;
import java.net.*;


public class CommandWorker implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter outforb;
    private String Command;
    private String Addendum;
    private String OwnIP;
    private String PORT;
    private String IP;
    private String ID;


    public CommandWorker(Socket clientSocket) throws IOException {
        this.client = clientSocket;

    }

    public void register(String id,String IP, int port){

        User member = new User(id,IP,port);
        userList.add(member);
        out.println("user added");

    }
    public void deregister(addendum){
        length = userList.size();
        for(int i; i<=length, i++){

            if(arraylist[i].userid = addendum){
                arraylist.remove(int i);
            }
        }
        out.println("user removed");

    }
    public void disconnect(addendum){
        length = userList.size();

        for(int i; i<=length, i++){

            if(arrayList[i].userid = addendum){
                arrayList[i].setStatus(false);
                lastid = mQueue.getLast().getMessageID();

                arrayList[i].setLastMessage(lastid);
            }
        }
        out.println("User Disconnected");


    }
    public void reconnect(addendum){

        Socket threadb = new Socket(ipofb, portofb);
        PrintWriter outforb = new PrintWriter(threadb.getOutputStream(),true);

        length = userList.size();

        for(int i; i<length, i++){

            if(arrayList[i].userid = userid){
                arrayList[i].setStatus(true);
                arrayList[i].setPort(portofb);
                mqlength = mqueue.size();
                flag = false;

                for(int j = 0, j<mqlength, j++){
                    if(flag == true){
                        outforb.println(mqueue[j].getMessageBody);
                    }
                    if(mqueue[j].getMessageID == arrayList[i].getLastMessage){
                        flag = true;
                    }
                    if(flag == false){
                        for(int j = 0, j<mqlength, j++){
                            outforb.println(mqueue[j].getMessageBody);

                        }
                    }
                    threadb.close();




                }

            }
        }

    }
    public void msend(addendum){
        messagetobesent = addendum;
        length = userList.size();

        for(int i; i<length, i++){
            if(arrayList[i].status == true){
                port = arraylist[i].getport();
                ip = arraylist[i].getip();

                Socket threadb = new Socket(ip, port);
                PrintWriter outforb = new PrintWriter(threadb.getOutputStream(),true);

                outforb.println(messagetobesent);

                Message newmsg = new Message(messageNumber+1, messagetobesent);
                messageNumber++;
                Mqueue.add(newmsg);
                threadb.close();
            }
        }

    }

    @Override
    public void run() {
        input = new BufferedReader(new InputStreamReader(client.getInputStream())); // receives client input
        outforb = new PrintWriter(client.getOutputStream(),true); // used to output to client

        ID = input[0];
        OwnIP = input[1];
        Command = input[2];
        Addendum = input[3];

        if Command == "register"{
            register();
        }
        if Command == "deregister"{
            deregister();
        }
        if Command == "disconnect"{
            disconnect();
        }
        if Command == "reconnect"{
            reconnect();
        }
        if Command == "msend"{
            msend();
        }



    }
}

