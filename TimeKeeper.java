import java.io.*;
import java.net.*;
import java.time.Instant;

/**
 * This class consistantly checks to see if messages have exceeded the system allotted time for storage and deletes them.
 */
public class TimeKeeper implements Runnable{

    private long timeCheck;
    private Instant currentTime;

    /**
     * Assigns the system allotted for Message storage.
     */
    public TimeKeeper(long timeCheck) {
        this.timeCheck = timeCheck;

    }

    /**
     * Checks the time of the Message in front of the queue with the current time to see if it needs to be removed.
     */
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

