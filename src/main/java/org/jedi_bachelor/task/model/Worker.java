package org.jedi_bachelor.task.model;

public class Worker extends Person {
    private final int salary;
    private final int moneyLimit;
    private final int workDuration;
    private volatile boolean isWorking = false;

    public Worker(String name, int initialMoney, int salary, int moneyLimit, int workDuration) {
        super(name, initialMoney);
        this.salary = salary;
        this.moneyLimit = moneyLimit;
        this.workDuration = workDuration;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (this.money >= moneyLimit) {
                Bank bank = selectRandomBank();
                if (bank != null) {
                    try {
                        bank.processClient(this);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public synchronized boolean isAvailable() {
        return !isWorking;
    }

    public synchronized void workFor(Spender spender) throws InterruptedException {
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