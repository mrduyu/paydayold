package com.payday.kycinformationservice.dal;


import com.payday.kycinformationservice.model.KycInformation;

import java.util.List;

public interface KycInformationDAL {
    KycInformation getKycInformationByNationalId(String nationalId);
    KycInformation createKycInformation(KycInformation kycInformation);
    KycInformation updateLoanRequestByLoanId(KycInformation updatedKycInformation);
}
