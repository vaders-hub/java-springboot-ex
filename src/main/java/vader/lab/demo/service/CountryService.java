package vader.lab.demo.service;

import vader.lab.demo.mapper.CountryMapper;
import vader.lab.demo.domain.CountryDTO;
import vader.lab.demo.exception.NoDataFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryMapper countryMapper;

    public List<CountryDTO> getCountryList() {

        return countryMapper.getCountryList();
    }

    public CountryDTO getCountry(String country) {
        CountryDTO contry = countryMapper.getCountry(country);

        if (contry == null) throw new NoDataFoundException(country);
        return countryMapper.getCountry(country);
    }
}
