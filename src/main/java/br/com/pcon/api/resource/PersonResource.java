package br.com.pcon.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.api.model.Person;
import br.com.pcon.api.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;

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

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(returned).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.ok(returned);
	}
	
	@PostMapping
	public ResponseEntity<Person> insert(@Validated @RequestBody Person entity, HttpServletResponse response) {
		Person person = personRepository.save(entity);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(person.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
}
