package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.relaxup.Entities.Interaccion;

public interface IInteraccionRepository extends JpaRepository<Interaccion, Integer> {
}
