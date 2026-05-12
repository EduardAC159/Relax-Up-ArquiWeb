package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Progreso;

import java.util.List;
import java.util.Optional;

public interface IProgresoService {
    public List<Progreso> list();
    public Progreso insert(Progreso progreso);
    public void update(Progreso progreso);
    public void delete(int id);
    public Optional<Progreso> listId(int id);
    List<Object[]> CantidadNivelControlIra();
    List<Object[]>  PromedioControlIraMetaEmocional();
}
