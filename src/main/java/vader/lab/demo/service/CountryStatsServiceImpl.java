package vader.lab.demo.service;


import vader.lab.demo.repository.CountryStatsRepository;
import vader.lab.demo.domain.CountryStats;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryStatsServiceImpl implements CountryStatsService {
    @Autowired
    CountryStatsRepository countryStatsRepository;

    @Override
    @Transactional
    public List<CountryStats> findAllCountryStats() {
        return countryStatsRepository.findAll();
    }
}
