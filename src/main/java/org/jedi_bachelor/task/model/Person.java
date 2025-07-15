package org.jedi_bachelor.task.model;

public abstract class Person implements Client, Runnable {
    protected final String name;
    protected int money;
    protected volatile boolean isActive = true;
    protected final Object moneyLock = new Object();

    public Person(String name, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
    }

    @Override
    public int getMoney() {
        synchronized (moneyLock) {
            return money;
        }
    }

    @Override
    public void setMoney(int amount) {
        synchronized (moneyLock) {
            this.money = amount;
        }
    }

    @Override
    public void addMoney(int amount) {
        synchronized (moneyLock) {
            this.money += amount;
        }
    }

    @Override
    public void subtractMoney(int amount) throws IllegalArgumentException {
        synchronized (moneyLock) {
            if (money < amount) {
                throw new IllegalArgumentException("Not enough money");
            }
            this.money -= amount;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void stop() {
        isActive = false;
    }

    @Override
    public abstract void run();
}

