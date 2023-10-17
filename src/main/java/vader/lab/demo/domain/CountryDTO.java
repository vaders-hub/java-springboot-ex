package vader.lab.demo.domain;

import lombok.Data;

import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Data
@RedisHash(value = "country", timeToLive = 30)
public class CountryDTO {
    private Long country_id;
    private String name;
    private Integer area;
    private Date national_day;
    private String country_code2;
    private String country_code3;
    private Integer region_id;
}
