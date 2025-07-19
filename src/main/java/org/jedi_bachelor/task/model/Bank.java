package org.jedi_bachelor.task.model;

public class Bank implements Runnable {
    private final String name;
    private int money;
    private volatile boolean isOpen = true;
    private volatile boolean isOnLunch = false;
    private final Object lock = new Object();

    private final int initialCitizenMoney;
    private final long workerDuration;
    private final long lunchDuration;

    private Media media;

    public Bank(String name, int initialMoney, int initCitMoney, long lunchDuration, long workerDuration, Media media) {
        this.name = name;
        this.money = initialMoney;
        this.initialCitizenMoney = initCitMoney;
        this.lunchDuration = lunchDuration;
        this.workerDuration = workerDuration;
        this.media = media;
    }

    public synchronized void processClient(Client client) throws InterruptedException {
        while (!isOpen || isOnLunch) {
            wait();
        }

        if (client instanceof Worker) {
            Worker worker = (Worker) client;
            int amount = worker.getMoney();
            worker.setMoney(0);
            money += amount;
        } else if (client instanceof Spender) {
            Spender spender = (Spender) client;
            int loanAmount = Math.abs(spender.getSalary() - initialCitizenMoney);
            if (money >= loanAmount) {
                money -= loanAmount;
                spender.addMoney(loanAmount);
            }
        }

        notifyAll();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                isOpen = true;
                lock.notifyAll();
            }

            long curTime = System.currentTimeMillis();

            while (!Thread.currentThread().isInterrupted()) {
                if (System.currentTimeMillis() - curTime >= workerDuration && !isOnLunch) {
                    goToLunch();
                    Thread.sleep(lunchDuration);
                    returnFromLunch();
                    curTime = System.currentTimeMillis() + 100;
                }

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            synchronized (lock) {
                isOpen = false;
                lock.notifyAll();
            }
            media.printStopThread(this.getName());
        }
    }

    private void goToLunch() {
        isOnLunch = true;
    }

    private void returnFromLunch() {
        isOnLunch = false;
    }
}

