package vader.lab.demo.controller;

import vader.lab.demo.service.CountryDTOService;
import vader.lab.demo.service.CountryService;
import vader.lab.demo.service.CountryStatsService;

import vader.lab.demo.domain.CountryDTO;
import vader.lab.demo.domain.Country;
import vader.lab.demo.domain.CountryStat;
import vader.lab.demo.domain.ResultModel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

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
    private CountryDTOService countryDTOService;

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryStatsService countryStatsService;


    @GetMapping("/countryList")
    public ResponseEntity<ResultModel> getCountryList() {
        ResultModel resultModel = new ResultModel();

        // List<CountryDTO> countryList = countryDTOService.getCountryList();
        List<Country> countryList = countryService.findAllCountries();
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("countrylist", countryList);

        resultModel.setData(rawMap);

        return ResponseEntity.ok().body(resultModel);
    }

    @GetMapping("/countryListByArea")
    public ResponseEntity<ResultModel> getCountryListByArea(@RequestParam(required = true) Integer area) {
        ResultModel resultModel = new ResultModel();

        List<Country> countryList = countryService.findCountriesByArea(area);
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("countrylist", countryList);

        resultModel.setData(rawMap);

        return ResponseEntity.ok().body(resultModel);
    }

    @GetMapping("/countryStatList")
    public ResponseEntity<ResultModel> getCountryStatList() {
        ResultModel resultModel = new ResultModel();

        List<CountryStat> countryStatList = countryStatsService.findAllCountryStats();
        Map<String, Object> rawMap = new HashMap<>();
        rawMap.put("countryStatlist", countryStatList);

        resultModel.setData(rawMap);

        return ResponseEntity.ok().body(resultModel);
    }

    @GetMapping("/country")
    public ResponseEntity<ResultModel> getCountry(@Valid @RequestParam(required = true) String country) {
        ResultModel resultModel = new ResultModel();

        CountryDTO searchedCountry = countryDTOService.getCountry(country);
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
