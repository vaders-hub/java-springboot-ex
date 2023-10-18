package vader.lab.demo.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Product {
    private String name;
    private String lastUpdated;
    private String price;
    private String summary;
}
