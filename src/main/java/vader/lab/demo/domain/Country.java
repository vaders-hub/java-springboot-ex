package vader.lab.demo.domain;

import lombok.Data;

import javax.persistence.*;


import java.util.Date;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;
    private String name;
    private Integer area;
    private Date national_day;
    private String country_code2;
    private String country_code3;
    private Integer region_id;
}
