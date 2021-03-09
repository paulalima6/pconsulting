package br.com.pcon.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pcon.api.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {//, LancamentoRepositoryQuery {
}
