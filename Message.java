import java.time.Instant;

public class Message() {

    private int id;
    private String contents;
    private Instant time;
    
    public Message(int id, String contents) {
        this.id = id;
        this.contents = contents;
        time = Instant.now();
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getMessageTime() {
        return time.getEpochSecond();
    }
    
    public void setMessageTime() {
        time = Instant.now();
    }


}
