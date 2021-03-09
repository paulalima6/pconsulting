package br.com.pcon.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.pcon.api.event.ResourceCreatedEvent;
import br.com.pcon.api.model.Entry;
import br.com.pcon.api.repository.EntryRepository;
import br.com.pcon.api.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryResource {

	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private EntryService entryService;

	@GetMapping
	public List<Entry> findAll() {
		return entryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> findById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Entry> entry = entryRepository.findById(id);
		Entry returned = new Entry();

		if (!entry.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		returned = entry.get();
		publisher.publishEvent(new ResourceCreatedEvent(this, response, returned.getId()));
		return ResponseEntity.ok(returned);
	}
	
	@DeleteMapping("/{id}")
	public List<Entry> delete(@PathVariable Long id) {
		entryRepository.deleteById(id);
		
		return findAll();
	}
	
	@PostMapping
	public ResponseEntity<Entry> insert(@Validated @RequestBody Entry entity, HttpServletResponse response) {
		Entry entry = entryRepository.save(entity);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, entry.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entry);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Entry> update(@PathVariable Long id, @Valid @RequestBody Entry entity, HttpServletResponse response) {
		Entry entry = entryService.update(id, entity);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, entry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(entry);
	}
	
}
