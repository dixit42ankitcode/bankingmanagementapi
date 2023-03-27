package com.bankingmanagement.asyncconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("asyncbean")
    public Executor asyncconfig(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(80);
        threadPoolTaskExecutor.setQueueCapacity(200);
        threadPoolTaskExecutor.setThreadNamePrefix("bankingmanagementapi");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
