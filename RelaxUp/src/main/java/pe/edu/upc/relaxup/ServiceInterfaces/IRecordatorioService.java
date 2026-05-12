package pe.edu.upc.relaxup.ServiceInterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Entities.Recordatorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IRecordatorioService {
    public List<Recordatorio> list();
    public Recordatorio insert(Recordatorio recordatorio);
    public void update(Recordatorio recordatorio);
    public void delete(int id);
    public Optional<Recordatorio> listId(int id);
    List<Object[]> contarPorTipo();
    List<Recordatorio> buscarRecordatorios(String tipo, Integer usuarioId, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}

