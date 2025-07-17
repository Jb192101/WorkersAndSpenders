package org.jedi_bachelor.task.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Media implements Runnable {
    @Value("${city.lunchDuration}")
    private long lunchDuration;

    @Autowired
    private HelpDesk helpDesk;

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
            printStatus();

            try {
                Thread.sleep(lunchDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void printInitialStatus() {
        System.out.printf("Total money amount in city on day start: %d$\n", helpDesk.getTotalMoney());
        printStatus();
    }

    public synchronized void printStatus() {
        System.out.println("Good news for everyone! Total amount money in city is: " + helpDesk.getTotalMoney() + "$");

        for (Bank bank : helpDesk.getBanks()) {
            System.out.printf("This %s has money: %d$\n", bank.getName(), bank.getMoney());
        }

        for (Worker worker : helpDesk.getWorkers()) {
            System.out.printf("This %s has money: %d$\n", worker.getName(), worker.getMoney());
        }

        for (Spender spender : helpDesk.getSpenders()) {
            System.out.printf("This %s has money: %d$\n", spender.getName(), spender.getMoney());
        }
    }

    public synchronized void printFinalStatus() {
        int calculatedTotal = 0;

        for (Bank bank : helpDesk.getBanks()) {
            calculatedTotal += bank.getMoney();
        }

        for (Worker worker : helpDesk.getWorkers()) {
            calculatedTotal += worker.getMoney();
        }

        for (Spender spender : helpDesk.getSpenders()) {
            calculatedTotal += spender.getMoney();
        }

        System.out.printf("Total money amount in city on day end: %d$\n", calculatedTotal);

        if (calculatedTotal != helpDesk.getTotalMoney()) {
            System.err.println("WARNING: Money balance violation detected!");
        }
    }

    public synchronized void printStopThread(String name) {
        System.out.println("Thread " + name + " has been stopped");
    }
}
