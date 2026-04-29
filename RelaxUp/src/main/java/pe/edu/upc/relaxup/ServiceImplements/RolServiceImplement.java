package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Rol;
import pe.edu.upc.relaxup.Repositories.IRolRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRolService;

import java.util.List;
@Service
public class RolServiceImplement implements IRolService {

    @Autowired
    private IRolRepository rR;

    @Override
    public List<Rol> list() {
        return rR.findAll();
    }
}
