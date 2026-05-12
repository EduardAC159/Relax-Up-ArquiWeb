package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Recordatorio;
import pe.edu.upc.relaxup.Repositories.IComunidadRepository;
import pe.edu.upc.relaxup.Repositories.IRecordatorioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecordatorioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecordatorioServiceImplement implements IRecordatorioService {

    @Autowired
    private IRecordatorioRepository reR;

    @Override
    public List<Recordatorio> list() {
        return reR.findAll();
    }
    @Override
    public Recordatorio insert(Recordatorio recordatorio) {
        return reR.save(recordatorio);
    }

    @Override
    public void update(Recordatorio recordatorio) {
        reR.save(recordatorio);

    }

    @Override
    public void delete(int id) {
        reR.deleteById(id);
    }

    @Override
    public Optional<Recordatorio> listId(int id) {
        return reR.findById(id);
    }

    @Override
    public List<Object[]> contarPorTipo() {
        return reR.contarPorTipo();
    }

    @Override
    public List<Recordatorio> buscarRecordatorios(String tipo, Integer usuarioId, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return reR.buscarRecordatorios(tipo, usuarioId, estado, fechaInicio, fechaFin);
    }
}
