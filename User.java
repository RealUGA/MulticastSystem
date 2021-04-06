
/**
 * Creates a User object with specific attributes.
 */
public class User {

    private String id;
    private String ip;
    private int port;
    private boolean status;
    private int messageId;

    /**
     * Creates a User object with the specified id, ip, and port that will be marked as online and has a default messageId of -1.
     */
    public User(String id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        status = true; // true for online status, false for offline status
        messageId = -1;
    }

    /**
     * Retrieves the id of the User.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the User.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the port of ThreadB for the User.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port of ThreadB for the User.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Retrieves the User's online status.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the User's online status.
     */
    public void setStatus(boolean online) {
        status = online;
    }

    /**
     * Retrieves the id of the last message the User received while online.
     */
    public int getLastMessage() {
        return messageId;
    }

    /**
     * Sets the id of the last message the User received while online.
     */
    public void setLastMessage(int messageId) {
        this.messageId = messageId;
    }

    /**
     * Retrieves the ip address of the User's ThreadB.
     */
    public String getIpAddress() {
        return ip;
    }

    /**
     * Sets the ip address of the User's ThreadB.
     */
    public void setIpAddress(String ip) {
        this.ip = ip;
    }
}
