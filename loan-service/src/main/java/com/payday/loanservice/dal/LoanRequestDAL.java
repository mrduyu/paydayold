package com.payday.loanservice.dal;

import com.payday.loanservice.model.IncomeInformation;
import com.payday.loanservice.model.LoanRequest;

import java.util.Date;
import java.util.List;

public interface LoanRequestDAL {
    List<LoanRequest> getAllLoanRequests();
    LoanRequest createLoanRequest(LoanRequest loanRequest);
    List<LoanRequest> getLoanRequestsByNationalId(String nationalId);
    LoanRequest getLoanRequestByLoanId(String loanId);
    LoanRequest updateLoanRequestByLoanId(String loanId, Boolean isGranted, String updatedUser);
    Double getIncome(String nationalId);
    Date getLastEmployerStartDate(String nationalId);
    String sendLoanRequestResultEmail (String message);
}
