package br.com.pcon.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcon.api.model.Category;
import br.com.pcon.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}
}
