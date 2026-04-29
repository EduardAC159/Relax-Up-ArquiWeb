package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Rol;
@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
}
