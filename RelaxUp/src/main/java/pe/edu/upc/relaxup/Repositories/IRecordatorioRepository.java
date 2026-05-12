package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Recordatorio;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IRecordatorioRepository extends JpaRepository<Recordatorio,Integer> {
    @Query("SELECT r.tipo, COUNT(r) FROM Recordatorio r GROUP BY r.tipo")
    List<Object[]> contarPorTipo();

    @Query("SELECT r FROM Recordatorio r " +
            "WHERE (:tipo IS NULL OR r.tipo = :tipo) " +
            "AND (:usuarioId IS NULL OR r.usuario.idUsuario = :usuarioId) " +
            "AND (:estado IS NULL OR r.estado = :estado) " +  // asegúrate que 'estado' existe
            "AND (:fechaInicio IS NULL OR r.fechaHora >= :fechaInicio) " +
            "AND (:fechaFin IS NULL OR r.fechaHora <= :fechaFin) " +
            "ORDER BY r.fechaHora ASC")
    List<Recordatorio> buscarRecordatorios(
            @Param("tipo") String tipo,
            @Param("usuarioId") Integer usuarioId,
            @Param("estado") String estado,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
}
