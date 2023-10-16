package vader.lab.demo.mapper;

import vader.lab.demo.domain.CountryDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CountryMapper {
    List<CountryDTO> getCountryList();

    CountryDTO getCountry(@Param("country") String country);
}
