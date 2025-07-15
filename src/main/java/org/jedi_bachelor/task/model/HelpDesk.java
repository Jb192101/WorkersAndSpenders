package org.jedi_bachelor.task.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HelpDesk {
    private static HelpDesk instance;

    private final List<Bank> banks = new ArrayList<>();
    private final List<Worker> workers = new ArrayList<>();
    private final List<Spender> spenders = new ArrayList<>();

    private volatile int totalMoney;

    private HelpDesk() {}

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static HelpDesk getInstance() {
        return instance;
    }

    public synchronized void addBank(Bank bank) {
        banks.add(bank);
        totalMoney += bank.getMoney();
    }

    public synchronized void addWorker(Worker worker) {
        workers.add(worker);
        totalMoney += worker.getMoney();
    }

    public synchronized void addSpender(Spender spender) {
        spenders.add(spender);
        totalMoney += spender.getMoney();
    }

    public synchronized List<Bank> getBanks() {
        return new ArrayList<>(banks);
    }

    public synchronized List<Worker> getWorkers() {
        return new ArrayList<>(workers);
    }

    public synchronized List<Spender> getSpenders() {
        return new ArrayList<>(spenders);
    }

    public synchronized <T> T getRandom(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }

    public synchronized Bank getRandomBank() {
        return getRandom(banks);
    }

    public synchronized Worker getRandomWorker() {
        return getRandom(workers);
    }

    public synchronized Spender getRandomSpender() {
        return getRandom(spenders);
    }

    public synchronized void printInitialStatus() {
        System.out.printf("Total money amount in city on day start: %d$\n", totalMoney);
        printStatus();
    }

    public synchronized void printStatus() {
        System.out.println("Good news for everyone! Total amount money in city is: " + totalMoney + "$");

        for (Bank bank : banks) {
            System.out.printf("This %s has money: %d$\n", bank.getName(), bank.getMoney());
        }

        for (Worker worker : workers) {
            System.out.printf("This %s has money: %d$\n", worker.getName(), worker.getMoney());
        }

        for (Spender spender : spenders) {
            System.out.printf("This %s has money: %d$\n", spender.getName(), spender.getMoney());
        }
    }

    public synchronized void printFinalStatus() {
        int calculatedTotal = 0;

        for (Bank bank : banks) {
            calculatedTotal += bank.getMoney();
        }

        for (Worker worker : workers) {
            calculatedTotal += worker.getMoney();
        }

        for (Spender spender : spenders) {
            calculatedTotal += spender.getMoney();
        }

        System.out.printf("Total money amount in city on day end: %d$\n", calculatedTotal);

        if (calculatedTotal != totalMoney) {
            System.err.println("WARNING: Money balance violation detected!");
        }
    }

    public synchronized void updateMoneyBalance(int oldValue, int newValue) {
        totalMoney += (newValue - oldValue);
    }

    public synchronized int getTotalMoney() {
        return totalMoney;
    }
}
