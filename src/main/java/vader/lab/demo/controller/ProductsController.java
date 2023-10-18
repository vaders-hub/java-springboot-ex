package vader.lab.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import vader.lab.demo.domain.ResultModel;
import vader.lab.demo.domain.Product;

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
import vader.lab.demo.domain.Employee;


@Slf4j
@Controller
public class ProductsController {

    @GetMapping("/locale-test")
    public ResponseEntity<ResultModel> locale() {
        ResultModel resultModel = new ResultModel();

        List<Product> products = fetchProducts();

        resultModel.setResultCode("0000");
        resultModel.setData(products);

        log.info("Getting country" + products);

        return ResponseEntity.ok().body(resultModel);
    }

    /**
     * @param locale
     * @return
     */
    private List<Product> fetchProducts() {
        Locale locale = LocaleContextHolder.getLocale();

        List<Product> products = new ArrayList<Product>();

        Product product = new Product();
        product.setName("television");
        product.setPrice(localizePrice(locale, 15678.43));
        product.setLastUpdated(localizeDate(locale, LocalDate.of(2021, Month.SEPTEMBER, 22)));
        products.add(product);

        product = new Product();
        product.setName("washingmachine");


        product.setPrice(localizePrice(locale, 152637.76));
        product.setLastUpdated(localizeDate(locale, LocalDate.of(2021, Month.SEPTEMBER, 20)));
        products.add(product);
        return products;
    }

    private String localizeDate(final Locale locale, final LocalDate date) {
        String localizedDate = DateTimeFormatter.ISO_LOCAL_DATE.format(date);
        return localizedDate;
    }


    private String localizePrice(final Locale locale, final Double price) {
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        String localizedPrice = numberFormat.format(price);
        return localizedPrice;
    }
}
