package vader.lab.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countryList")
    public ResponseEntity<ResultModel> getCountryList(@RequestParam(required = false) String country) {
        ResultModel resultModel = new ResultModel();

        try {

            List<CountryDTO> countryList = countryService.getCountryList();
            Map<String, Object> rawMap = new HashMap<>();
            rawMap.put("list", countryList);

            resultModel.setData(rawMap);

            return ResponseEntity.ok().body(resultModel);
        } catch (Exception e) {
            String message = "";
            Map<String, Object> rawMap = new HashMap<>();

            message = e.getMessage();
            rawMap.put("error", message);

            resultModel.setData(rawMap);

            return ResponseEntity.status(400).body(resultModel);
        }
    }
}
