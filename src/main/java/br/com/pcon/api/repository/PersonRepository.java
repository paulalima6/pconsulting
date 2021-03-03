package br.com.pcon.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
