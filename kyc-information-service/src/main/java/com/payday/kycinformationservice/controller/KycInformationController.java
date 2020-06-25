package com.payday.kycinformationservice.controller;

import com.payday.kycinformationservice.dal.KycInformationDAL;
import com.payday.kycinformationservice.dal.KycInformationRepository;
import com.payday.kycinformationservice.model.KycInformation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kycinformation")
public class KycInformationController {

    private final KycInformationDAL kycInformationDAL;
    private final KycInformationRepository kycInformationRepository;

    public KycInformationController(KycInformationDAL kycInformationDAL, KycInformationRepository kycInformationRepository) {
        this.kycInformationDAL = kycInformationDAL;
        this.kycInformationRepository = kycInformationRepository;
    }

    @Cacheable("kycinformation")
    @RequestMapping(value = "/{nationalId}", method = RequestMethod.GET)
    public KycInformation getKycInformationByNationalId(@PathVariable String nationalId)
    {
        KycInformation kycInformation = kycInformationDAL.getKycInformationByNationalId(nationalId);
        if (kycInformation != null)
        {
            return kycInformation;
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public KycInformation saveKycInformation(@RequestBody KycInformation kycInformation) {
        return kycInformationDAL.createKycInformation(kycInformation);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public KycInformation updateKycInformation(@RequestBody KycInformation kycInformation) {
        return kycInformationDAL.updateLoanRequestByLoanId(kycInformation);
    }
}
