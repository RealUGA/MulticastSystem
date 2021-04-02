
public class User {

    private String id;
    private String ip;
    private int port;
    private boolean status;
    private int messageId;

    public User(String id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        status = true; // true for online status, false for offline status
        messageId = -1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean online) {
        status = online;
    }

    public int getLastMessage() {
        return messageId;
    }

    public void setLastMessage(int messageId) {
        this.messageId = messageId;
    }

    public String getIpAddress() {
        return ip;
    }

    public void setIpAddress(String ip) {
        this.ip = ip;
    }
}
