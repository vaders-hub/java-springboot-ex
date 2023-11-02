package vader.lab.demo.controller;

import vader.lab.demo.domain.ResponseMessage;
import vader.lab.demo.service.CountryService;
import vader.lab.demo.service.CountryStatsService;

import vader.lab.demo.domain.CountryDTO;
import vader.lab.demo.domain.CountryStats;
import vader.lab.demo.domain.ResultModel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private CountryStatsService countryStatsService;


    @GetMapping("/countryList")
    public ResponseEntity<ResultModel> getCountryList() {
        ResultModel resultModel = new ResultModel();

//        List<CountryDTO> countryList = countryService.getCountryList();
//        Map<String, Object> rawMap = new HashMap<>();
//        rawMap.put("list", countryList);

        List<CountryStats> countryStatList = countryStatsService.findAllCountryStats();
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("list", countryStatList);

        resultModel.setData(rawMap);

        return ResponseEntity.ok().body(resultModel);
    }

    @GetMapping("/country")
    public ResponseEntity<ResultModel> getCountry(@Valid @RequestParam(required = true) String country) {
        ResultModel resultModel = new ResultModel();

        CountryDTO searchedCountry = countryService.getCountry(country);

        resultModel.setResultCode("0000");
        resultModel.setData(searchedCountry);

        log.info("Getting country" + country);

        return ResponseEntity.ok().body(resultModel);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
