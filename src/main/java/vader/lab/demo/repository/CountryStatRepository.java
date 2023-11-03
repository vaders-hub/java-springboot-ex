package vader.lab.demo.repository;

import vader.lab.demo.domain.CountryStat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryStatRepository extends JpaRepository<CountryStat, Long> {
    List<CountryStat> findAll();
}
