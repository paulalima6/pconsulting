package br.com.pcon.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping
	public List<Person> listAll() {
		return personRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Person> insert(@Valid Person entity, HttpServletResponse response) {
		Person person = personRepository.save(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(person.getId()).toUri();
		
		response.setHeader("Local", uri.toASCIIString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
}
