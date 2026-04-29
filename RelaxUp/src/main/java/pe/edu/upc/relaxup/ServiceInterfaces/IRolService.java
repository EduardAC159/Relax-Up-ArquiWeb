package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Rol;
import pe.edu.upc.relaxup.Entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public List<Rol> list();
    public Rol insert(Rol rol);
    public void update(Rol rol);
    public void delete(int id);
    public Optional<Rol> listId(int id);
}
