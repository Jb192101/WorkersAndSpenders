package org.jedi_bachelor.task.model;

import org.jedi_bachelor.task.config.CityProperties;

public class Bank {
    private final String name;
    private int money;
    private volatile boolean isOpen = true;
    private volatile boolean isOnLunch = false;
    private final Object lock = new Object();
    private final int initialCitizenMoney;

    public Bank(String name, int initialMoney, int initCitMoney) {
        this.name = name;
        this.money = initialMoney;
        this.initialCitizenMoney = initCitMoney;
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
            int loanAmount = initialCitizenMoney - spender.getMoney();
            if (money >= loanAmount) {
                money -= loanAmount;
                spender.setMoney(initialCitizenMoney);
            }
        }

        notifyAll();
    }

    private int calculateLoanAmount(Spender spender) {
        return Math.max(10, 100 - spender.getMoney());
    }

    public void stop() {
        isOpen = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void waitUntilAvailable() throws InterruptedException {
        synchronized (lock) {
            while (!isOnLunch) {
                lock.wait();
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}

