package com.payday.incomeinformationservice.controller;

import com.payday.incomeinformationservice.dal.IncomeInformationDAL;
import com.payday.incomeinformationservice.dal.IncomeInformationRepository;
import com.payday.incomeinformationservice.model.CreditComplex;
import com.payday.incomeinformationservice.model.IncomeInformation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/incomeinformation")
public class IncomeInformationController {

    private final IncomeInformationDAL incomeInformationDAL;
    private final IncomeInformationRepository incomeInformationRepository;

    public IncomeInformationController(IncomeInformationDAL incomeInformationDAL, IncomeInformationRepository incomeInformationRepository) {
        this.incomeInformationDAL = incomeInformationDAL;
        this.incomeInformationRepository = incomeInformationRepository;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public IncomeInformation saveIncomeInformation(@RequestBody IncomeInformation incomeInformation) {
        return incomeInformationDAL.createIncomeInformation(incomeInformation);
    }

    @RequestMapping(value = "/{nationalId}", method = RequestMethod.GET)
    public IncomeInformation getIncomeInformationByNationalId(@PathVariable String nationalId) {
        IncomeInformation incomeInformation = incomeInformationDAL.getIncomeInformationByNationalId(nationalId);
        if (incomeInformation != null) {
            return incomeInformation;
        } else {
            return null;
        }
    }
}
