package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Interaccion;

import java.util.List;

@Repository
public interface IInteraccionRepository extends JpaRepository<Interaccion, Integer> {
    @Query(value = "SELECT \n" +
            "    u.id_usuario,\n" +
            "    u.nombre,\n" +
            "    COUNT(i.id_interaccion) AS cantidad_interacciones\n" +
            "FROM \n" +
            "    public.usuario u\n" +
            "    LEFT JOIN public.interaccion i ON u.id_usuario = i.id_usuario\n" +
            "GROUP BY \n" +
            "    u.id_usuario, u.nombre\n" +
            "ORDER BY \n" +
            "    cantidad_interacciones DESC, u.nombre;", nativeQuery = true)
    List<Object[]> CantidadInteraccionesUsuario();
}
