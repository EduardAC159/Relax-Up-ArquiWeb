package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Emergencia;
import pe.edu.upc.relaxup.Repositories.IEmergenciaRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IEmergenciaServicio;

import java.util.List;

@Service
public class EmergenciaServiceImplement implements IEmergenciaServicio {
    @Autowired
    private IEmergenciaRepository eR;
    @Override
    public List<Emergencia> list() {
        return eR.findAll();
    }
}
