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

    @Value("${city.banks-count}")
    private int banksCount;
    @Value("${city.workers-count}")
    private int workersCount;
    @Value("${city.spenders-count}")
    private int spendersCount;

    public void setBanksCount(int banksCount) {
        this.banksCount = banksCount;
    }

    public void setWorkersCount(int workersCount) {
        this.workersCount = workersCount;
    }

    public void setSpendersCount(int spendersCount) {
        this.spendersCount = spendersCount;
    }

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
