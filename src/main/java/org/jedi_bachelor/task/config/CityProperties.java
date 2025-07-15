package org.jedi_bachelor.task.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:city.properties")
@ConfigurationProperties(prefix = "city")
public class CityProperties {
    private static long workDayDuration;
    private static long workerDuration;
    private static long lunchDuration;
    private static int banksCount;
    private static int workersCount;
    private static int spendersCount;
    private static int initialBankMoney;
    private static int initialCitizenMoney;
    private static int workerSalary;
    private static int workerMoneyLimit;

    public static long getWorkDayDuration() {
        return workDayDuration;
    }

    public static long getWorkerDuration() {
        return workerDuration;
    }

    public static long getLunchDuration() {
        return lunchDuration;
    }

    public static int getBanksCount() {
        return banksCount;
    }

    public static int getWorkersCount() {
        return workersCount;
    }

    public static int getSpendersCount() {
        return spendersCount;
    }

    public static int getInitialBankMoney() {
        return initialBankMoney;
    }

    public static int getInitialCitizenMoney() {
        return initialCitizenMoney;
    }

    public static int getWorkerSalary() {
        return workerSalary;
    }

    public static int getWorkerMoneyLimit() {
        return workerMoneyLimit;
    }
}