package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Emergencia;
import pe.edu.upc.relaxup.Repositories.IEmergenciaRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IEmergenciaServicio;

import java.util.List;
import java.util.Optional;

@Service
public class EmergenciaServiceImplement implements IEmergenciaServicio {
    @Autowired
    private IEmergenciaRepository eR;
    @Override
    public List<Emergencia> list() {
        return eR.findAll();
    }

    @Override
    public Emergencia insert(Emergencia emergencia) {
        return eR.save(emergencia);
    }

    @Override
    public void update(Emergencia emergencia) {
        eR.save(emergencia);

    }

    @Override
    public void delete(int id) {
        eR.deleteById(id);

    }

    @Override
    public Optional<Emergencia> listId(int id) {
        return eR.findById(id);
    }
}
