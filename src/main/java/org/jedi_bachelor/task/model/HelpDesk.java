package org.jedi_bachelor.task.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HelpDesk {
    private static volatile HelpDesk instance;
    private final ConcurrentHashMap<String, Integer> moneyStatistics = new ConcurrentHashMap<>();
    private final AtomicInteger totalMoney = new AtomicInteger(0);
    private final Object reportLock = new Object();

    private HelpDesk() {
    }

    public static HelpDesk getInstance() {
        if (instance == null) {
            synchronized (HelpDesk.class) {
                if (instance == null) {
                    instance = new HelpDesk();
                }
            }
        }
        return instance;
    }

    public void updateMoneyInfo(String name, int amount) {
        moneyStatistics.put(name, amount);
    }

    public void updateTotalMoney(int delta) {
        totalMoney.addAndGet(delta);
    }

    public void printCurrentReport() {
        synchronized (reportLock) {
            System.out.println("\n=== Current City Status ===");
            System.out.printf("Total money amount in city: %d$\n", totalMoney.get());

            moneyStatistics.forEach((name, amount) ->
                    System.out.printf("%s has money: %d$\n", name, amount));

            System.out.println("=========================\n");
        }
    }

    public void printFinalReport() {
        synchronized (reportLock) {
            System.out.println("\n=== FINAL REPORT ===");
            System.out.printf("Total money amount at the end: %d$\n", totalMoney.get());

            int bankMoney = moneyStatistics.entrySet().stream()
                    .filter(e -> e.getKey().startsWith("Bank-"))
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            int workerMoney = moneyStatistics.entrySet().stream()
                    .filter(e -> e.getKey().startsWith("Worker-"))
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            int spenderMoney = moneyStatistics.entrySet().stream()
                    .filter(e -> e.getKey().startsWith("Spender-"))
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            System.out.printf("Banks total: %d$\n", bankMoney);
            System.out.printf("Workers total: %d$\n", workerMoney);
            System.out.printf("Spenders total: %d$\n", spenderMoney);
            System.out.println("====================\n");
        }
    }

    public void logError(String message, Exception exception) {
        synchronized (reportLock) {
            System.err.println("ERROR: " + message);
            exception.printStackTrace();
        }
    }

    public void logInfo(String message) {
        synchronized (reportLock) {
            System.out.println("INFO: " + message);
        }
    }

    public void reset() {
        synchronized (reportLock) {
            moneyStatistics.clear();
            totalMoney.set(0);
        }
    }
}
