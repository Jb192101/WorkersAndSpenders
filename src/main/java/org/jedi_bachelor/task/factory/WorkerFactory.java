package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.model.Bank;
import org.jedi_bachelor.task.model.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerFactory implements AbstractFactory<Worker> {
    @Value("${city.initial-person-money}")
    private int initialMoney;
    @Value("${city.salary}")
    private int salary;
    @Value("${city.money-limit}")
    private int moneyLimit;
    private List<Bank> banks;

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setMoneyLimit(int moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public Worker create(String name) {
        return new Worker(name, initialMoney, salary, moneyLimit, banks);
    }
}
