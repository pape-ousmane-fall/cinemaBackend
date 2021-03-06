package fall.sn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fall.sn.model.Ville;
@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long> {

}
