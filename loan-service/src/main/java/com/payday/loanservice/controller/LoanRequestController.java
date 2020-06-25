package com.payday.loanservice.controller;

import com.payday.loanservice.dal.LoanRequestDAL;
import com.payday.loanservice.dal.LoanRequestRepository;
import com.payday.loanservice.model.AnalyzeRequest;
import com.payday.loanservice.model.IncomeInformation;
import com.payday.loanservice.model.LoanRequest;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/loan")
public class LoanRequestController {

    private final LoanRequestDAL loanRequestDAL;
    private final LoanRequestRepository loanRequestRepository;

    public LoanRequestController(LoanRequestDAL loanRequestDAL, LoanRequestRepository loanRequestRepository) {
        this.loanRequestDAL = loanRequestDAL;
        this.loanRequestRepository = loanRequestRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LoanRequest> getAllLoanRequests() {
        return loanRequestRepository.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LoanRequest saveLoanRequest(@RequestBody LoanRequest loan) {
        return loanRequestDAL.createLoanRequest(loan);
    }

    @RequestMapping(value = "/nationalId/{nationalId}", method = RequestMethod.GET)
    public List<LoanRequest> getLoanRequestsByNationalId(@PathVariable String nationalId) {
        List<LoanRequest> loanList = loanRequestDAL.getLoanRequestsByNationalId(nationalId);
        if (loanList != null) {
            return loanList;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{loanId}", method = RequestMethod.GET)
    public LoanRequest getLoanRequestByLoanId(@PathVariable String loanId) throws InterruptedException {
        LoanRequest loanRequest = loanRequestDAL.getLoanRequestByLoanId(loanId);
        if (loanRequest != null) {
            return loanRequest;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public Boolean analyzeLoanRequest(@RequestBody AnalyzeRequest analyzeRequest) throws InterruptedException, ParseException {
        Random rand = new Random();
        int waitingTime = rand.nextInt(300)*1000; // max 5 min.
        double interesRate = 0.2; // Default interest rate

        Thread.sleep(waitingTime); // It is set for simulate 5 min engine response.

        Date startDate = loanRequestDAL.getLastEmployerStartDate(analyzeRequest.getNationalId());
        Double monthlyIncome = loanRequestDAL.getIncome(analyzeRequest.getNationalId());

        LoanRequest loanRequest = loanRequestDAL.getLoanRequestByLoanId(analyzeRequest.getLoanId()); // Get requested loan amount
        List<LoanRequest> loanRequestList = loanRequestDAL.getLoanRequestsByNationalId(analyzeRequest.getNationalId()); // Get all loans to analyze
        Double previouslyGrantedLoanAmount = 0.0;

        if(!loanRequestList.isEmpty())
        {
            for (int i = 0; i <loanRequestList.size(); i++) {
                if(loanRequestList.get(i).getGranted()){
                    previouslyGrantedLoanAmount += loanRequestList.get(i).getAmount();
                }
            }
        }
        Double sumOfLoans = previouslyGrantedLoanAmount + loanRequest.getAmount();
        boolean isLoanGranted = false;
        if(monthlyIncome-sumOfLoans >=0 && getDaysBetween(startDate) > 90){
            isLoanGranted = true;
        }
        if(isLoanGranted)
        {
            loanRequestDAL.sendLoanRequestResultEmail("Your loan request approved. Check your balance.");
        }
        else
        {
            loanRequestDAL.sendLoanRequestResultEmail("Your loan request rejected!");
        }
        return  isLoanGranted;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public LoanRequest updateLoanRequest(@RequestBody LoanRequest loan) {
        LoanRequest updatedLoan = loanRequestDAL.updateLoanRequestByLoanId(loan.getId(), loan.getGranted(), loan.getUpdatedUser());
        if (updatedLoan != null) {
            return updatedLoan;
        } else {
            return null;
        }
    }

    private long getDaysBetween(Date startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String now = format.format(new Date());
        String startDateAsString =  format.format(startDate);
        Date dateNow = format.parse(now);
        startDate = format.parse(startDateAsString);
        LocalDate nowDate = LocalDate.parse(now);
        LocalDate startDateAsLocalDate = LocalDate.parse(startDateAsString);
        long noOfDaysBetween = ChronoUnit.DAYS.between(startDateAsLocalDate,nowDate); //calculating number of days in between

        return noOfDaysBetween;
    }

}
