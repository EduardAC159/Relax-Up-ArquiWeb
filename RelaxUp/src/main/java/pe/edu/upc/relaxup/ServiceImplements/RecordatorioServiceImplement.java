package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Recordatorio;
import pe.edu.upc.relaxup.Repositories.IComunidadRepository;
import pe.edu.upc.relaxup.Repositories.IRecordatorioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecordatorioService;

import java.util.List;

@Service
public class RecordatorioServiceImplement implements IRecordatorioService {

    @Autowired
    private IRecordatorioRepository reR;

    @Override
    public List<Recordatorio> list() {
        return reR.findAll();
    }
}
