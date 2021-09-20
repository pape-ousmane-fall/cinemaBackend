package fall.sn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fall.sn.model.Film;
@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long> {

}
