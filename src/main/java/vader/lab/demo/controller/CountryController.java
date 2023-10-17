package vader.lab.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import vader.lab.demo.domain.ResponseMessage;
import vader.lab.demo.service.CountryService;
import vader.lab.demo.domain.CountryDTO;
import vader.lab.demo.domain.ResultModel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countryList")
    public ResponseEntity<ResultModel> getCountryList() {
        ResultModel resultModel = new ResultModel();

        List<CountryDTO> countryList = countryService.getCountryList();
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("list", countryList);

        resultModel.setData(rawMap);

        return ResponseEntity.ok().body(resultModel);
    }

    @GetMapping("/country")
    public ResponseEntity<ResultModel> getCountry(@Valid @RequestParam(required = true) String country) {
        ResultModel resultModel = new ResultModel();

        CountryDTO searchedCountry = countryService.getCountry(country);

        resultModel.setResultCode("0000");
        resultModel.setData(searchedCountry);

        return ResponseEntity.ok().body(resultModel);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResultModel> handleMissingParams(MissingServletRequestParameterException ex) {
        ResultModel resultModel = new ResultModel();
        String name = ex.getMessage();
        System.out.println(name + " parameter is missing");
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("error", name);
        resultModel.setData(rawMap);
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(resultModel);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
