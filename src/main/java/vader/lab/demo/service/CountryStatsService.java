package vader.lab.demo.service;

import vader.lab.demo.domain.CountryStats;

import java.util.List;

public interface CountryStatsService {
    List<CountryStats> findAllCountryStats();
}
