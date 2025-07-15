package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.model.Bank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankFactory implements AbstractFactory<Bank> {
    @Value("${city.initial-bank-money}")
    private int initialMoney;

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    @Override
    public Bank create(String name) {
        return new Bank(name, initialMoney);
    }
}
