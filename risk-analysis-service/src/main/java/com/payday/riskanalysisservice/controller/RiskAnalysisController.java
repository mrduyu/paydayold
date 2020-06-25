package com.payday.riskanalysisservice.controller;

import com.payday.riskanalysisservice.model.LoanRequest;
import com.payday.riskanalysisservice.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/analyze")
public class RiskAnalysisController {

    @Autowired
    private AsyncService service;

    @RequestMapping(value = "/{loanId}", method = RequestMethod.GET)
    public LoanRequest analyzeLoan(@PathVariable String loanId) throws InterruptedException, ExecutionException
    {
        CompletableFuture<LoanRequest> loanRequest = service.getLoanRequestResult(loanId);
        CompletableFuture.allOf(loanRequest).join(); // Wait until they are all done
        return loanRequest.get();
    }
}
