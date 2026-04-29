package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;

import java.util.List;
import java.util.Optional;

public interface IComunidadService {
    public List<Comunidad> list();
    public Comunidad insert(Comunidad comunidad);
    public void update(Comunidad comunidad);
    public void delete(int id);
    public Optional<Comunidad> listId(int id);
}
