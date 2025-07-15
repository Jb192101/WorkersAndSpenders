package org.jedi_bachelor.task.model;

public class Bank {
    private final String name;
    private int money;
    private volatile boolean isOpen = true;
    private volatile boolean isBusy = false;
    private final Object lock = new Object();

    public Bank(String name, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
    }

    public synchronized void processClient(Client client) {
        if (!isOpen) {
            System.out.printf("%s: Банк закрыт, клиент %s ушел%n", name, client.getName());
            return;
        }

        try {
            isBusy = true;

            System.out.printf("%s: Обслуживается клиент %s (баланс клиента: %d)%n",
                    name, client.getName(), client.getMoney());

            if (client instanceof Worker) {
                processWorkerDeposit((Worker) client);
            } else if (client instanceof Spender) {
                processSpenderLoan((Spender) client);
            }

            HelpDesk.getInstance().updateMoneyInfo(name, money);
            HelpDesk.getInstance().updateMoneyInfo(client.getName(), client.getMoney());

        } finally {
            isBusy = false;
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

    private void processWorkerDeposit(Worker worker) {
        int amountToDeposit = worker.getMoney();
        if (amountToDeposit <= 0) {
            System.out.printf("%s: Работяга %s не имеет денег для вклада%n", name, worker.getName());
            return;
        }

        worker.subtractMoney(amountToDeposit);
        this.money += amountToDeposit;

        System.out.printf("%s: Работяга %s внес депозит %d. Баланс банка: %d%n",
                name, worker.getName(), amountToDeposit, money);
    }

    private void processSpenderLoan(Spender spender) {
        int loanAmount = calculateLoanAmount(spender);
        if (loanAmount <= 0) {
            System.out.printf("%s: Банк не может выдать кредит транжире %s%n", name, spender.getName());
            return;
        }

        if (this.money < loanAmount) {
            System.out.printf("%s: Недостаточно средств для кредита транжире %s%n", name, spender.getName());
            return;
        }

        this.money -= loanAmount;
        spender.addMoney(loanAmount);

        System.out.printf("%s: Транжира %s получил кредит %d. Баланс банка: %d%n",
                name, spender.getName(), loanAmount, money);
    }

    private int calculateLoanAmount(Spender spender) {
        return Math.max(10, 100 - spender.getMoney());
    }

    public void stop() {
        isOpen = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void waitUntilAvailable() throws InterruptedException {
        synchronized (lock) {
            while (isBusy) {
                lock.wait();
            }
        }
    }

}

