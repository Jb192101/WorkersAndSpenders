package org.jedi_bachelor.task.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:city.properties")
@ConfigurationProperties(prefix = "city")
public class CityProperties {
    private long dayDuration;
    private long workerDuration;
    private long lunchDuration;
    private int banksCount;
    private int workersCount;
    private int spendersCount;
    private int initialBankMoney;
    private int initialPersonMoney;
    private int salary;
    private int moneyLimit;

    public long getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(long dayDuration) {
        this.dayDuration = dayDuration;
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

    public int getInitialPersonMoney() {
        return initialPersonMoney;
    }

    public void setInitialPersonMoney(int initialPersonMoney) {
        this.initialPersonMoney = initialPersonMoney;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getMoneyLimit() {
        return moneyLimit;
    }

    public void setMoneyLimit(int moneyLimit) {
        this.moneyLimit = moneyLimit;
    }
}