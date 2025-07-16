package org.jedi_bachelor.task.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:city.properties")
@ConfigurationProperties(prefix = "city")
public class CityProperties {
    private long workDayDuration;
    private long workerDuration;
    private long lunchDuration;
    private int banksCount;
    private int workersCount;
    private int spendersCount;
    private int initialBankMoney;
    private int initialCitizenMoney;
    private int workerSalary;
    private int workerMoneyLimit;

    public long getWorkDayDuration() {
        return workDayDuration;
    }

    public void setWorkDayDuration(long workDayDuration) {
        this.workDayDuration = workDayDuration;
    }

    public long getWorkerDuration() {
        return workerDuration;
    }

    public void setWorkerDuration(long workerDuration) {
        this.workerDuration = workerDuration;
    }

    public long getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(long lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public int getBanksCount() {
        return banksCount;
    }

    public void setBanksCount(int banksCount) {
        this.banksCount = banksCount;
    }

    public int getWorkersCount() {
        return workersCount;
    }

    public void setWorkersCount(int workersCount) {
        this.workersCount = workersCount;
    }

    public int getSpendersCount() {
        return spendersCount;
    }

    public void setSpendersCount(int spendersCount) {
        this.spendersCount = spendersCount;
    }

    public int getInitialBankMoney() {
        return initialBankMoney;
    }

    public void setInitialBankMoney(int initialBankMoney) {
        this.initialBankMoney = initialBankMoney;
    }

    public int getInitialCitizenMoney() {
        return initialCitizenMoney;
    }

    public void setInitialCitizenMoney(int initialCitizenMoney) {
        this.initialCitizenMoney = initialCitizenMoney;
    }

    public int getWorkerSalary() {
        return workerSalary;
    }

    public void setWorkerSalary(int workerSalary) {
        this.workerSalary = workerSalary;
    }

    public int getWorkerMoneyLimit() {
        return workerMoneyLimit;
    }

    public void setWorkerMoneyLimit(int workerMoneyLimit) {
        this.workerMoneyLimit = workerMoneyLimit;
    }
}