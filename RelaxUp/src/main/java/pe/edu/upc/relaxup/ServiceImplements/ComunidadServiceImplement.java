package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Repositories.IComunidadRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IComunidadService;

import java.util.List;
import java.util.Optional;

@Service
public class ComunidadServiceImplement implements IComunidadService {

    @Autowired
    private IComunidadRepository cR;
    @Override
    public List<Comunidad> list() {
        return cR.findAll();
    }

    @Override
    public Comunidad insert(Comunidad comunidad) {
        return cR.save(comunidad);
    }

    @Override
    public void update(Comunidad comunidad) {
        cR.save(comunidad);

    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);

    }

    @Override
    public Optional<Comunidad> listId(int id) {
        return cR.findById(id);
    }
}
