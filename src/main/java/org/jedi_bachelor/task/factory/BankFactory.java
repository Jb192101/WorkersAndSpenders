package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.config.CityProperties;
import org.jedi_bachelor.task.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankFactory implements AbstractFactory<Bank> {
    @Autowired
    private CityProperties cityProperties;

    @Override
    public Bank create(String name) {
        return new Bank(name, cityProperties.getInitialBankMoney(),
                cityProperties.getInitialCitizenMoney());
    }
}
