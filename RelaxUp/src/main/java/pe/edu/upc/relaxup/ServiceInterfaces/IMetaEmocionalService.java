package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.MetaEmocional;
import pe.edu.upc.relaxup.Entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IMetaEmocionalService {
    public List<MetaEmocional> list();
    List<Object[]> CantidadMetaEmocionalUsuario();
    public MetaEmocional insert(MetaEmocional metaEmocional);
    public void update(MetaEmocional metaEmocional);
    public void delete(int id);
    public Optional<MetaEmocional> listId(int id);
}
