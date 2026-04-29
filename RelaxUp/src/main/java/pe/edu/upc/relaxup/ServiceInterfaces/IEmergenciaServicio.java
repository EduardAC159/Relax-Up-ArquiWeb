package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Emergencia;
import pe.edu.upc.relaxup.Entities.Interaccion;

import java.util.List;
import java.util.Optional;

public interface IEmergenciaServicio {
    public List<Emergencia> list();
    public Emergencia insert(Emergencia emergencia);
    public void update(Emergencia emergencia);
    public void delete(int id);
    public Optional<Emergencia> listId(int id);
}
