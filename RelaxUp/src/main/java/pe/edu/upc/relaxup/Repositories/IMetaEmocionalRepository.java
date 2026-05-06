package pe.edu.upc.relaxup.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.relaxup.Entities.MetaEmocional;

import java.util.List;

@Repository
public interface IMetaEmocionalRepository extends JpaRepository<MetaEmocional, Integer> {
    @Query(value = "SELECT \n" +
            "    COUNT(DISTINCT u.id_usuario) AS cantidad_usuarios_completaron_meta\n" +
            "FROM \n" +
            "    public.usuario u\n" +
            "    INNER JOIN public.meta_emocional m ON u.id_usuario = m.id_usuario\n" +
            "WHERE \n" +
            "    m.estado = 'Completada';", nativeQuery = true)
    List<Object[]> CantidadMetaEmocionalUsuario();
}
