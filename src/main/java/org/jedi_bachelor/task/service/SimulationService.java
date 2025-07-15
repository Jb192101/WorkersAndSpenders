package org.jedi_bachelor.task.service;

import org.jedi_bachelor.task.model.Bank;
import org.jedi_bachelor.task.model.HelpDesk;
import org.jedi_bachelor.task.model.Spender;
import org.jedi_bachelor.task.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {
    @Autowired
    private CityService cityService;
    @Autowired
    private HelpDesk helpDesk;

    @Value("${city.workDayDuration}")
    private long workDayDuration;

    public void startSimulation() throws InterruptedException {
        cityService.createCity();

        helpDesk.printInitialStatus();

        List<Thread> threads = new ArrayList<>();

        for (Bank bank : helpDesk.getBanks()) {
            Thread bankThread = new Thread(bank);
            threads.add(bankThread);
            bankThread.start();
        }

        for (Worker worker : helpDesk.getWorkers()) {
            Thread workerThread = new Thread(worker);
            threads.add(workerThread);
            workerThread.start();
        }

        for (Spender spender : helpDesk.getSpenders()) {
            Thread spenderThread = new Thread(spender);
            threads.add(spenderThread);
            spenderThread.start();
        }

        Thread.sleep(workDayDuration);

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        helpDesk.printFinalStatus();
    }
}