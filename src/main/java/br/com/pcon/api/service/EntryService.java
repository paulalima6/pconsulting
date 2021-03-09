package br.com.pcon.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.pcon.api.model.Entry;
import br.com.pcon.api.model.Person;
import br.com.pcon.api.repository.EntryRepository;
import br.com.pcon.api.repository.PersonRepository;
import br.com.pcon.api.service.exception.InactiveOrNonexistentPersonException;

@Service
public class EntryService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	public Entry save(Entry entity) {
		Optional<Person> person = personRepository.findById(entity.getPerson().getId());
		Person returned = new Person();
		
		if(!person.isPresent() || !person.get().isActive()) {
			throw new InactiveOrNonexistentPersonException();
		}
		returned = person.get();
		
		entity.setPerson(returned);
		return entryRepository.save(entity);
	}

	public Entry update(Long id, Entry entity) {
		Entry returned = findExistenceEntry(id);
		
		if(entity.getPerson().equals(returned.getPerson())) {
			validatePerson(entity);
		}
		BeanUtils.copyProperties(entity, returned, "id");
		
		return entryRepository.save(returned);
	}
	
	private void validatePerson(Entry entity) {
		Optional<Person> person = personRepository.findById(entity.getPerson().getId());
		
		if(!person.isPresent() || !person.get().isActive()) {
			throw new InactiveOrNonexistentPersonException();
		}
	}

	private Entry findExistenceEntry(Long id) {
		Optional<Entry> entry = entryRepository.findById(id);
		if(!entry.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return entry.get();
	}

}
