package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Repositories.IInteraccionRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IInteraccionService;

import java.util.List;
import java.util.Optional;

@Service
public class InteraccionServiceImplement implements IInteraccionService {

    @Autowired
    private IInteraccionRepository iR;

    @Override
    public List<Interaccion> list() {
        return iR.findAll();
    }

    @Override
    public List<Object[]> CantidadInteraccionesUsuario() {
        return iR.CantidadInteraccionesUsuario();
    }

    @Override
    public Interaccion insert(Interaccion interaccion) {
        return iR.save(interaccion);
    }

    @Override
    public void update(Interaccion interaccion) {
        iR.save(interaccion);

    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);

    }

    @Override
    public Optional<Interaccion> listId(int id) {
        return iR.findById(id);
    }
}
