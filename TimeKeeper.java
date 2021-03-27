import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class TimeKeeper implements Runnable{

    private long timeCheck;
    private LocalDateTime currentTime;
    
    public TimeKeeper(long timeCheck) {
        this.timeCheck = timeCheck;

    }

    @Override
    public void run() {
        while (true) {
            currentTime = LocalDateTime.now();
            if (Coordinator.mQueue.peek() != null && (currentTime - Coordinator.mQueue.getFirst().getMessageTime()) > timeCheck) {
                Coordinator.mQueue.removeFirst();
            }
        }

    }
}

