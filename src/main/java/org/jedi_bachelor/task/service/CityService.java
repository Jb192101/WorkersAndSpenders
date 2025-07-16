package org.jedi_bachelor.task.service;

import org.jedi_bachelor.task.config.CityProperties;
import org.jedi_bachelor.task.factory.BankFactory;
import org.jedi_bachelor.task.factory.SpenderFactory;
import org.jedi_bachelor.task.factory.WorkerFactory;
import org.jedi_bachelor.task.model.HelpDesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private BankFactory bankFactory;
    @Autowired
    private WorkerFactory workerFactory;
    @Autowired
    private SpenderFactory spenderFactory;
    @Autowired
    private CityProperties cityProperties;

    public void createCity() {
        HelpDesk helpDesk = HelpDesk.getInstance();

        for (int i = 1; i <= cityProperties.getBanksCount(); i++) {
            helpDesk.addBank(bankFactory.create("Bank - " + i));
        }

        for (int i = 1; i <= cityProperties.getWorkersCount(); i++) {
            helpDesk.addWorker(workerFactory.create("Worker - " + i));
        }

        for (int i = 1; i <= cityProperties.getSpendersCount(); i++) {
            helpDesk.addSpender(spenderFactory.create("Spender - " + i));
        }
    }
}
