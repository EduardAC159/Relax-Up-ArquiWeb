package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.Progreso;

import java.util.List;

@Repository
public interface IProgresoRepository extends JpaRepository<Progreso,Integer> {
    @Query(value = "SELECT \n" +
            "    nivel_control_ira, \n" +
            "    COUNT(*) AS cantidad \n" +
            "FROM \n" +
            "    progreso \n" +
            "GROUP BY \n" +
            "    nivel_control_ira \n" +
            "ORDER BY \n" +
            "    nivel_control_ira;", nativeQuery = true)
    List<Object[]> CantidadNivelControlIra();
    @Query(value = "SELECT \n" +
            "    m.descripcion, \n" +
            "    AVG(p.nivel_control_ira) AS promedio \n" +
            "FROM \n" +
            "    progreso p \n" +
            "INNER JOIN \n" +
            "    meta_emocional m ON p.id_meta_emocional = m.id_meta \n" +
            "GROUP BY \n" +
            "    m.descripcion;", nativeQuery = true)
    List<Object[]>  PromedioControlIraMetaEmocional();
}
