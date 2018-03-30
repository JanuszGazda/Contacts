package com.janusz.Repository;

import com.janusz.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByPesel(Long pesel);
}
