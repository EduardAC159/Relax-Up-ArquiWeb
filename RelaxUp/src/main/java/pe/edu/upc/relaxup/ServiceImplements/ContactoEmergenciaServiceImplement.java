package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;
import pe.edu.upc.relaxup.Repositories.IContactoEmergenciaRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IContactoEmergenciaService;

import java.util.List;

@Service
public class ContactoEmergenciaServiceImplement implements IContactoEmergenciaService {

    @Autowired
    private IContactoEmergenciaRepository ceR;

    @Override
    public List<ContactoEmergencia> list() {
        return ceR.findAll();
    }
}
