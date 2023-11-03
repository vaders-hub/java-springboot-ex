package vader.lab.demo.repository;

import vader.lab.demo.domain.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAll();

    List<Country> findByAreaGreaterThan(Integer area);
}
