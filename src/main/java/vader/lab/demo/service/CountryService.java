package vader.lab.demo.service;

import vader.lab.demo.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAllCountries();

    List<Country> findCountriesByArea(Integer area);
}
