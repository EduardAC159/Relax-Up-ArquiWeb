package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Entities.Recordatorio;

import java.util.List;
import java.util.Optional;

public interface IRecordatorioService {
    public List<Recordatorio> list();
    public Recordatorio insert(Recordatorio recordatorio);
    public void update(Recordatorio recordatorio);
    public void delete(int id);
    public Optional<Recordatorio> listId(int id);
}
