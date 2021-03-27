import java.time.LocalDateTime;

public class Message() {

    private int id;
    private String contents;
    private LocalDateTime time;
    
    public Message(int id, String contents) {
        this.id = id;
        this.contents = contents;
        time = LocalDateTime.now();
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

    public LocalDateTime getMessageTime() {
        return time;
    }
    
    public void setMessageTime() {
        time = LocalDateTime.now();
    }


}
