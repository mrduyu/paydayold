package com.payday.incomeinformationservice.dal;

import com.payday.incomeinformationservice.model.IncomeInformation;

public interface IncomeInformationDAL {
    IncomeInformation getIncomeInformationByNationalId(String nationalId);
    IncomeInformation createIncomeInformation(IncomeInformation incomeInformation);
}
