package br.com.pcon.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
