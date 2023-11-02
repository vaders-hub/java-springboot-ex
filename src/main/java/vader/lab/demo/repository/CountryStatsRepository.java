package vader.lab.demo.repository;

import vader.lab.demo.domain.CountryStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryStatsRepository extends JpaRepository<CountryStats, Long> {
    List<CountryStats> findAll();
}
