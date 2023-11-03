package vader.lab.demo.domain;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@IdClass(CountryStat.class)
@Table(name = "country_stats")
public class CountryStat implements Serializable {
    @Id
    @Column(name = "country_id", insertable = false, updatable = false)
    private Integer country_id;

    @Id
    private Integer year;
    private Integer population;
    private Double gdp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Country country;
}
