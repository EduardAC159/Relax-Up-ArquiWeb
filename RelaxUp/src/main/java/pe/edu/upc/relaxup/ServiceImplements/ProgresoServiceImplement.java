package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Progreso;
import pe.edu.upc.relaxup.Entities.Recordatorio;
import pe.edu.upc.relaxup.Repositories.IProgresoRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IProgresoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProgresoServiceImplement implements IProgresoService {
    @Autowired
    private IProgresoRepository pR;
    @Override
    public List<Progreso> list() {
        return pR.findAll();
    }

    @Override
    public Progreso insert(Progreso progreso) {
        return pR.save(progreso);
    }

    @Override
    public void update(Progreso progreso) {
        pR.delete(progreso);

    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);

    }

    @Override
    public Optional<Progreso> listId(int id) {
        return pR.findById(id);

    }
}
