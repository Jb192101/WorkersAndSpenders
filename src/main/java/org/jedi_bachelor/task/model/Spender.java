package org.jedi_bachelor.task.model;

public class Spender extends Person {
    private int salary;
    private Media media;

    public Spender(String name, int initialMoney, int salary, Media media) {
        super(name, initialMoney);
        this.media = media;
        this.salary = salary;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (this.money < this.salary) {
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
        media.printStopThread(this.getName());
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

    public int getSalary() {
        return this.salary;
    }
}
