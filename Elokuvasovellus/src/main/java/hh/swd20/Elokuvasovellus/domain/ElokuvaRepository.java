package hh.swd20.Elokuvasovellus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ElokuvaRepository extends CrudRepository<Elokuva, Long> {
	
	List<Elokuva> findByTitle(String title);

}
