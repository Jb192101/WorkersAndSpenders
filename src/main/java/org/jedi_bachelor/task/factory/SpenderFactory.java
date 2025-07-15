package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.model.Bank;
import org.jedi_bachelor.task.model.Spender;
import org.jedi_bachelor.task.model.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpenderFactory implements AbstractFactory<Spender> {
    @Value("${city.initial-person-money}")
    private int initialMoney;
    private List<Worker> workers;
    private List<Bank> banks;

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public Spender create(String name) {
        return new Spender(name, initialMoney, workers, banks);
    }
}
