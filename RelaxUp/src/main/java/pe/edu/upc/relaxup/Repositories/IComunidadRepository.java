package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Comunidad;

import java.util.List;

@Repository
public interface IComunidadRepository extends JpaRepository<Comunidad,Integer> {
    @Query(value = "select * from comunidad c order by c.nombre", nativeQuery = true)
    List<Object[]> buscarComunidadesOrdenadasPorNombre();

}
