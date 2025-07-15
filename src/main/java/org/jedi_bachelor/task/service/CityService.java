package org.jedi_bachelor.task.service;

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

    @Value("${city.banksCount}")
    private int banksCount;
    @Value("${city.workersCount}")
    private int workersCount;
    @Value("${city.spendersCount}")
    private int spendersCount;

    public void createCity() {
        HelpDesk helpDesk = HelpDesk.getInstance();

        for (int i = 1; i <= banksCount; i++) {
            helpDesk.addBank(bankFactory.create("Bank - " + i));
        }

        for (int i = 1; i <= workersCount; i++) {
            helpDesk.addWorker(workerFactory.create("Worker - " + i));
        }

        for (int i = 1; i <= spendersCount; i++) {
            helpDesk.addSpender(spenderFactory.create("Spender - " + i));
        }
    }
}
