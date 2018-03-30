package com.janusz.Repository;

import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
