package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Progreso;
import pe.edu.upc.relaxup.Repositories.IProgresoRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IProgresoService;

import java.util.List;
@Service
public class ProgresoServiceImplement implements IProgresoService {
    @Autowired
    private IProgresoRepository pR;
    @Override
    public List<Progreso> list() {
        return pR.findAll();
    }
}
