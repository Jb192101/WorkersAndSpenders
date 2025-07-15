package org.jedi_bachelor.task.model;

import java.util.List;

public class Worker extends Person {
    private final int salary;
    private final int moneyLimit;
    private final List<Bank> banks;

    public Worker(String name, int initialMoney, int salary, int moneyLimit, List<Bank> banks) {
        super(name, initialMoney);
        this.salary = salary;
        this.moneyLimit = moneyLimit;
        this.banks = banks;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                synchronized (this) {
                    addMoney(salary);
                }

                if (getMoney() >= moneyLimit) {
                    Bank bank = selectRandomBank();
                    bank.processClient(this);
                }

                if (getMoney() >= moneyLimit) {
                    Bank bank = selectRandomBank();
                    bank.waitUntilAvailable();
                    bank.processClient(this);
                }

                Thread.sleep(Integer.parseInt("${city.worker-duration}"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stop();
            }
        }
    }

    private Bank selectRandomBank() {
        // Реализация выбора случайного банка
        return null;
    }
}