package com.payday.riskanalysisservice.service;

import com.payday.riskanalysisservice.model.LoanRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<LoanRequest> getLoanRequestResult(String loanId) throws InterruptedException
    {
        final String uri = "http://172.22.0.2:16/loan/"+loanId;
        LoanRequest loanRequest = restTemplate.getForObject(uri, LoanRequest.class);
        Thread.sleep(1000L);    //Intentional delay
        return CompletableFuture.completedFuture(loanRequest);
    }
}
