package br.com.pcon.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcon.api.event.ResourceCreatedEvent;
import br.com.pcon.api.model.Person;
import br.com.pcon.api.repository.PersonRepository;
import br.com.pcon.api.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	/*
	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Person> list = personRepository.findAll(); 
		return !list.isEmpty() ? ResponseEntity.ok(list) : ResponseEntity.noContent().build();
	}
	*/

	@GetMapping
	public List<Person> listAll() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Person> person = personRepository.findById(id);
		Person returned = new Person();
		if(!person.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			returned = person.get();
		}
		publisher.publishEvent(new ResourceCreatedEvent(this, response, id));
		return ResponseEntity.ok(returned);
	}
	
	@PostMapping
	public ResponseEntity<Person> insert(@Validated @RequestBody Person entity, HttpServletResponse response) {
		Person person = personRepository.save(entity);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, person.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@DeleteMapping("/{id}")
	public List<Person> delete(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		Person returned = new Person();
		
		if(person.isPresent()) {
			returned = person.get();
		}
		personRepository.delete(returned);
		return listAll();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Validated @RequestBody Person entity, HttpServletResponse response) {
		Person person = personService.save(id, entity);
		return ResponseEntity.ok().body(person);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Person> updateStatus(@PathVariable Long id, @RequestBody Boolean status, HttpServletResponse response) {
		Person person = personService.updateStatus(id, status);
		return ResponseEntity.ok().body(person);
	}
}
