package vader.lab.demo.service;

import vader.lab.demo.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;


import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

    public List<Product> fetchProducts() {
        Locale locale = LocaleContextHolder.getLocale();

        List<Product> products = new ArrayList<Product>();

        Product product = new Product();
        product.setName(messageSource.getMessage("television", null, locale));
        product.setPrice(localizePrice(locale, 15678.43));
        product.setLastUpdated(localizeDate(locale, LocalDate.of(2021, Month.SEPTEMBER, 22)));
        products.add(product);

        product = new Product();
        product.setName(messageSource.getMessage("washingmachine", null, locale));
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
