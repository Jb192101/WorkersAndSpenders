package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.model.Spender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpenderFactory implements AbstractFactory<Spender> {
    @Value("${city.initial-person-money}")
    private int initialMoney;

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    @Override
    public Spender create(String name) {
        return new Spender(name, initialMoney);
    }
}
