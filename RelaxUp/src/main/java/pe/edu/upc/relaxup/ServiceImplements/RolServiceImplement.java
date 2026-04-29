package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Rol;
import pe.edu.upc.relaxup.Repositories.IRolRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImplement implements IRolService {

    @Autowired
    private IRolRepository rR;

    @Override
    public List<Rol> list() {
        return rR.findAll();
    }

    @Override
    public Rol insert(Rol rol) {
        return rR.save(rol);
    }

    @Override
    public void update(Rol rol) {
        rR.save(rol);

    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);

    }

    @Override
    public Optional<Rol> listId(int id) {
        return rR.findById(id);
    }
}
