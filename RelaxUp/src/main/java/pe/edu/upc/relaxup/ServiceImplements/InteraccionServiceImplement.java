package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Repositories.IInteraccionRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IInteraccionService;

import java.util.List;

@Service
public class InteraccionServiceImplement implements IInteraccionService {

    @Autowired
    private IInteraccionRepository iR;

    @Override
    public List<Interaccion> list() {
        return iR.findAll();
    }
}
