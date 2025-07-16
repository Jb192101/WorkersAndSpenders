package org.jedi_bachelor.task.service;

import org.jedi_bachelor.task.config.CityProperties;
import org.jedi_bachelor.task.model.HelpDesk;
import org.jedi_bachelor.task.model.Spender;
import org.jedi_bachelor.task.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {
    @Autowired
    private CityService cityService;
    @Autowired
    private HelpDesk helpDesk;
    @Autowired
    private CityProperties cityProperties;

    public void startSimulation() throws InterruptedException {
        cityService.createCity();

        helpDesk.printInitialStatus();

        List<Thread> threads = new ArrayList<>();

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

        Thread.sleep(cityProperties.getWorkDayDuration());

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        helpDesk.printStatus();
        helpDesk.printFinalStatus();
    }
}