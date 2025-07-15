package org.jedi_bachelor.task.model;

public interface Client {
    int getMoney();

    void setMoney(int amount);

    void addMoney(int amount);

    void subtractMoney(int amount) throws IllegalArgumentException;

    String getName();

    boolean isActive();

    void stop();
}