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

    public synchronized int getTotalMoney() {
        return totalMoney;
    }

    public synchronized List<Bank> getBanks() {
        return banks;
    }
}
