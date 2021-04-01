import java.io.*;
import java.net.*;
import java.time.Instant;

public class TimeKeeper implements Runnable{

    private long timeCheck;
    private Instant currentTime;
    
    public TimeKeeper(long timeCheck) {
        this.timeCheck = timeCheck;

    }

    @Override
    public void run() {
        while (true) {
            currentTime = Instant.now();
            if (Coordinator.mQueue.peek() != null && (currentTime.getEpochSecond() - Coordinator.mQueue.getFirst().getMessageTime()) > timeCheck) {
                Coordinator.mQueue.removeFirst();
            }
        }

    }
}

