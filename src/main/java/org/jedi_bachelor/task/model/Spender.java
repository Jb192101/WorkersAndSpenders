package org.jedi_bachelor.task.model;

import org.jedi_bachelor.task.config.CityProperties;

public class Spender extends Person {
    private int salary;


    public Spender(String name, int initialMoney, int salary) {
        super(name, initialMoney);
        this.salary = salary;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (this.money <= 0) {
                Bank bank = selectRandomBank();
                if (bank != null) {
                    try {
                        bank.processClient(this);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            } else {
                Worker worker = selectRandomWorker();
                if (worker != null) {
                    try {
                        worker.workFor(this);
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

    public int paySalary() {
        if (money >= salary) {
            money -= salary;
            return salary;
        }
        return 0;
    }

    private Worker selectRandomWorker() {
        return HelpDesk.getInstance().getRandomWorker();
    }

    private Bank selectRandomBank() {
        return HelpDesk.getInstance().getRandomBank();
    }
}
