package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;

@Repository
public interface    IContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia,Integer> {
}
