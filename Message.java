import java.time.Instant;

/**
 * This class creates a Message object that stores information about sent messages.
 */
public class Message {

    private int id;
    private String contents;
    private Instant time;

    /**
     * Creates a Message with the specified id, contents, and the current time.
     */
    public Message(int id, String contents) {
        this.id = id;
        this.contents = contents;
        time = Instant.now();
    }

    /**
     * Gets the id of the Message.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the Message.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the contents of the Message.
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets the contents of the Message.
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * Retrieves the time the Message was sent.
     */
    public long getMessageTime() {
        return time.getEpochSecond();
    }

    /**
     * Sets the time the Message was sent.
     */
    public void setMessageTime() {
        time = Instant.now();
    }


}
