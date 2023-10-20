package vader.lab.demo.service;

import vader.lab.demo.domain.enums.ERole;
import vader.lab.demo.domain.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
