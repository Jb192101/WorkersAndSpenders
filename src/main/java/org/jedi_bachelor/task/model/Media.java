package org.jedi_bachelor.task.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Media implements Runnable {
    @Value("${city.lunchDuration}")
    private long lunchDuration;

    private Thread thread;

    @PostConstruct
    public void init() {
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            HelpDesk.getInstance().printStatus();

            try {
                Thread.sleep(lunchDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
