package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.config.CityProperties;
import org.jedi_bachelor.task.model.Spender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpenderFactory implements AbstractFactory<Spender> {
    @Autowired
    private CityProperties cityProperties;

    @Override
    public Spender create(String name) {
        return new Spender(name, cityProperties.getInitialCitizenMoney(), cityProperties.getWorkerSalary());
    }
}
