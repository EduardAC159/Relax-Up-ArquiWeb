package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Recursos;
import pe.edu.upc.relaxup.Repositories.IRecursosRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecursosService;

import java.util.List;
import java.util.Optional;

@Service
public class RecursosServiceImplement implements IRecursosService {

    @Autowired
    private IRecursosRepository recR;

    @Override
    public List<Recursos> list() {
        return recR.findAll();
    }

    @Override
    public Recursos insert(Recursos recursos) {
        return recR.save(recursos);
    }

    @Override
    public void update(Recursos recursos) {
        recR.save(recursos);

    }

    @Override
    public void delete(int id) {
        recR.deleteById(id);

    }

    @Override
    public Optional<Recursos> listId(int id) {
        return recR.findById(id);
    }
}
