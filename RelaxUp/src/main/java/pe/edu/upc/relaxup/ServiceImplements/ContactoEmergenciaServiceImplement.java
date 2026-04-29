package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;
import pe.edu.upc.relaxup.Repositories.IContactoEmergenciaRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IContactoEmergenciaService;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoEmergenciaServiceImplement implements IContactoEmergenciaService {

    @Autowired
    private IContactoEmergenciaRepository ceR;

    @Override
    public List<ContactoEmergencia> list() {
        return ceR.findAll();
    }

    @Override
    public ContactoEmergencia insert(ContactoEmergencia contactoEmergencia) {
        return ceR.save(contactoEmergencia);
    }

    @Override
    public void update(ContactoEmergencia contactoEmergencia) {
        ceR.save(contactoEmergencia);

    }

    @Override
    public void delete(int id) {
        ceR.deleteById(id);

    }

    @Override
    public Optional<ContactoEmergencia> listId(int id) {
        return ceR.findById(id);
    }
}
