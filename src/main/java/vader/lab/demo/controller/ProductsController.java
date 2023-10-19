package vader.lab.demo.controller;

import vader.lab.demo.domain.ResultModel;
import vader.lab.demo.domain.Product;
import vader.lab.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;


import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.env.Environment;


@Slf4j
@Controller
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/locale-test")
    public ResponseEntity<ResultModel> locale() {
        ResultModel resultModel = new ResultModel();

        List<Product> products = productService.fetchProducts();

        resultModel.setResultCode("0000");
        resultModel.setData(products);


        return ResponseEntity.ok().body(resultModel);
    }


}
