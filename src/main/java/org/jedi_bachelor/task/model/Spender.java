package org.jedi_bachelor.task.model;

import java.util.List;

public class Spender extends Person {
    private final List<Worker> workers;
    private final List<Bank> banks;

    public Spender(String name, int initialMoney, List<Worker> workers, List<Bank> banks) {
        super(name, initialMoney);
        this.workers = workers;
        this.banks = banks;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                if (this.money <= 0) {
                    Bank bank = selectRandomBank();
                    bank.waitUntilAvailable();
                    bank.processClient(this);
                }

                Worker worker = selectRandomWorker();
                synchronized (worker) {
                    if (getMoney() > 0) {
                        subtractMoney(1);
                        worker.addMoney(1);
                    } else {
                        // Если денег нет - идем в банк
                        Bank bank = selectRandomBank();
                        bank.processClient(this);
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stop();
            } catch (IllegalArgumentException e) {
                // Недостаточно денег - обрабатываем в основном цикле
            }
        }
    }

    private Worker selectRandomWorker() {
        // Реализация выбора случайного работяги
        return null;
    }

    private Bank selectRandomBank() {
        // Реализация выбора случайного банка
        return null;
    }
}
