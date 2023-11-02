package vader.lab.demo.domain;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@IdClass(CountryStats.class)
@Table(name = "country_stats")
public class CountryStats implements Serializable {
    @Id
    private Integer country_id;

    @Id
    private Integer year;
    private Integer population;
    private Double gdp;


}
