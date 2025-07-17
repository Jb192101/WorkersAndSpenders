package org.jedi_bachelor.task.model;

public class Worker extends Person {
    private final int moneyLimit;
    private final long workDuration;
    private volatile boolean isWorking = false;
    private Media media;

    public Worker(String name, int initialMoney, int moneyLimit, long workDuration, Media media) {
        super(name, initialMoney);
        this.moneyLimit = moneyLimit;
        this.workDuration = workDuration;
        this.media = media;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (this.money >= moneyLimit) {
                isWorking = true;
                Bank bank = selectRandomBank();
                if (bank != null) {
                    try {
                        bank.processClient(this);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                isWorking = false;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        media.printStopThread(this.getName());
    }

    public synchronized void workFor(Spender spender) throws InterruptedException {
        if(isWorking) {
            wait();
        }

        isWorking = true;
        try {
            Thread.sleep(workDuration);
            this.money += spender.paySalary();
        } finally {
            isWorking = false;
            notifyAll();
        }
    }

    private Bank selectRandomBank() {
        return HelpDesk.getInstance().getRandomBank();
    }
}