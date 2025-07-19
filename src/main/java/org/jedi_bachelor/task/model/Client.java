package org.jedi_bachelor.task.model;

public interface Client {
    int getMoney();

    void setMoney(int amount);

    void addMoney(int amount);

    String getName();
}