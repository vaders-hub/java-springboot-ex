package vader.lab.demo.service;

import vader.lab.demo.domain.CountryStat;

import java.util.List;

public interface CountryStatsService {
    List<CountryStat> findAllCountryStats();
}
