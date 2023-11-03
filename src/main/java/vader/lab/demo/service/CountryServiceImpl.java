package vader.lab.demo.service;

import vader.lab.demo.domain.Country;
import vader.lab.demo.repository.CountryRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    @Transactional
    public List<Country> findAllCountries() {

        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public List<Country> findCountriesByArea(Integer area) {

        return countryRepository.findByAreaGreaterThan(area);
    }

    @Override
    @Transactional
    public Country saveCountry(Country country) {
        
        return countryRepository.save(country);
    }
}
