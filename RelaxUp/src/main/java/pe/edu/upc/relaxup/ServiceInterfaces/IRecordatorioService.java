package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Entities.Recordatorio;

import java.util.List;

public interface IRecordatorioService {
    public List<Recordatorio> list();
}
