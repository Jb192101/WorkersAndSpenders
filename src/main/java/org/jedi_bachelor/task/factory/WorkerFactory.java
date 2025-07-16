package org.jedi_bachelor.task.factory;

import org.jedi_bachelor.task.config.CityProperties;
import org.jedi_bachelor.task.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerFactory implements AbstractFactory<Worker> {
    @Autowired
    private CityProperties cityProperties;

    @Override
    public Worker create(String name) {
        return new Worker(name,
                cityProperties.getInitialCitizenMoney(),
                cityProperties.getWorkerSalary(),
                cityProperties.getWorkerMoneyLimit(),
                cityProperties.getWorkerDuration());
    }
}
