package org.jedi_bachelor.task.model;

import org.jedi_bachelor.task.config.CityProperties;

public class Spender extends Person {
    public Spender(String name, int initialMoney) {
        super(name, initialMoney);
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
                        if (worker.isAvailable()) {
                            worker.workFor(this);
                        }
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
        if (money >= CityProperties.getWorkerSalary()) {
            money -= CityProperties.getWorkerSalary();
            return CityProperties.getWorkerSalary();
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
