package br.com.pcon.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.pcon.api.model.Person;
import br.com.pcon.api.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person save(Long id, Person entity) {
		Person returned = findPersonById(id);
		BeanUtils.copyProperties(entity, returned, "id");
		return personRepository.save(returned);
	}
	
	public Person updateStatus(Long id, Boolean status) {
		Person returned = findPersonById(id);
		returned.setActive(status);
		return personRepository.save(returned);
	}

	private Person findPersonById(Long id) {
		Optional<Person> person = personRepository.findById(id);
		Person returned = new Person();
		
		if(!person.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		returned = person.get();
		return returned;
	}
}
