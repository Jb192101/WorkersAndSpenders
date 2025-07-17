package org.jedi_bachelor.task.model;

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
}

