package br.Cardatabase.repository;

import org.springframework.data.repository.CrudRepository;

import br.Cardatabase.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
