package br.com.pcon.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.api.event.ResourceCreatedEvent;
import br.com.pcon.api.model.Category;
import br.com.pcon.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	/*
	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Category> list = categoryRepository.findAll(); 
		return !list.isEmpty() ? ResponseEntity.ok(list) : ResponseEntity.noContent().build();
	}
	*/

	@GetMapping
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Category> category = categoryRepository.findById(id);
		Category retorno = new Category();
		if(!category.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		retorno = category.get();

		publisher.publishEvent(new ResourceCreatedEvent(this, response, id));
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody Category entity, HttpServletResponse response) {
		Category category = categoryRepository.save(entity);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, category.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
}
