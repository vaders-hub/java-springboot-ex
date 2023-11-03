package vader.lab.demo.service;

import vader.lab.demo.domain.CountryStat;
import vader.lab.demo.repository.CountryStatRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryStatsServiceImpl implements CountryStatsService {
    @Autowired
    CountryStatRepository countryStatRepository;

    @Override
    @Transactional
    public List<CountryStat> findAllCountryStats() {

        return countryStatRepository.findAll();
    }
}
