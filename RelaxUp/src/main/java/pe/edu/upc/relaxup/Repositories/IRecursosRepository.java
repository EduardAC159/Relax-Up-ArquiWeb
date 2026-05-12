package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Recursos;

@Repository
public interface IRecursosRepository extends JpaRepository<Recursos, Integer> {
    @Query("SELECT COUNT(r) FROM Recursos r WHERE r.usuario.idUsuario = :idUsuario")
    int countRecursosByUsuario(@Param("idUsuario") int idUsuario);
}
