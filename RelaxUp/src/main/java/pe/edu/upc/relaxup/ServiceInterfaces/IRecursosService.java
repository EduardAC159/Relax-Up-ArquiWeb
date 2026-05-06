package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Recursos;

import java.util.List;
import java.util.Optional;

public interface IRecursosService {
    public List<Recursos> list();
    public Recursos insert(Recursos recursos);
    public void update(Recursos recursos);
    public void delete(int id);
    public Optional<Recursos> listId(int id);
}
