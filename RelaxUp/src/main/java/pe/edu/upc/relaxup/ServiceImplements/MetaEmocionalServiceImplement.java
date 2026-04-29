package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.MetaEmocional;
import pe.edu.upc.relaxup.Repositories.IMetaEmocionalRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IMetaEmocionalService;

import java.util.List;

@Service
public class MetaEmocionalServiceImplement implements IMetaEmocionalService {

    @Autowired
    private IMetaEmocionalRepository meR;

    @Override
    public List<MetaEmocional> list() {
        return meR.findAll();
    }
}
