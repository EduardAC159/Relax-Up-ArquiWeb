package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.ContactoEmergencia;

import java.util.List;
import java.util.Optional;

public interface IContactoEmergenciaService {
    public List<ContactoEmergencia> list();
    public ContactoEmergencia insert(ContactoEmergencia contactoEmergencia);
    public void update(ContactoEmergencia contactoEmergencia);
    public void delete(int id);
    public Optional<ContactoEmergencia> listId(int id);
}
