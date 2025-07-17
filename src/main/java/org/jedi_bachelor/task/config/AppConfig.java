package org.jedi_bachelor.task.config;

import org.jedi_bachelor.task.factory.BankFactory;
import org.jedi_bachelor.task.factory.SpenderFactory;
import org.jedi_bachelor.task.factory.WorkerFactory;

import org.jedi_bachelor.task.model.Media;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableConfigurationProperties(CityProperties.class)
public class AppConfig {
    @Bean
    public BankFactory bankFactory() {
        return new BankFactory();
    }

    @Bean
    public WorkerFactory workerFactory() {
        return new WorkerFactory();
    }

    @Bean
    public SpenderFactory spenderFactory() {
        return new SpenderFactory();
    }

    @Bean
    public Media media() {
        return new Media();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("CitySimulation-");
        executor.initialize();
        return executor;
    }
}