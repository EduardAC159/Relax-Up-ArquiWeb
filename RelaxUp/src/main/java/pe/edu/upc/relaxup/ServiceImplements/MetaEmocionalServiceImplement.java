package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.MetaEmocional;
import pe.edu.upc.relaxup.Repositories.IMetaEmocionalRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IMetaEmocionalService;

import java.util.List;
import java.util.Optional;

@Service
public class MetaEmocionalServiceImplement implements IMetaEmocionalService {

    @Autowired
    private IMetaEmocionalRepository meR;

    @Override
    public List<MetaEmocional> list() {
        return meR.findAll();
    }

    @Override
    public List<Object[]> CantidadMetaEmocionalUsuario() {
        return meR.CantidadMetaEmocionalUsuario();
    }

    @Override
    public MetaEmocional insert(MetaEmocional metaEmocional) {
        return meR.save(metaEmocional);
    }

    @Override
    public void update(MetaEmocional metaEmocional) {
        meR.save(metaEmocional);

    }

    @Override
    public void delete(int id) {
        meR.deleteById(id);

    }

    @Override
    public Optional<MetaEmocional> listId(int id) {
        return meR.findById(id);
    }
}
