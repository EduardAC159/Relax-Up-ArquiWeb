package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Entities.Interaccion;

import java.util.List;
import java.util.Optional;

public interface IInteraccionService {
    public List<Interaccion> list();
    List<Object[]> CantidadInteraccionesUsuario();
    public Interaccion insert(Interaccion interaccion);
    public void update(Interaccion interaccion);
    public void delete(int id);
    public Optional<Interaccion> listId(int id);
}
