package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> list();
    public Usuario insert(Usuario usuario);
    public void update(Usuario usuario);
    public void delete(int id);
    public Optional<Usuario> listId(int id);

    Usuario login(String username, String password);

    Usuario findByUsername(String username);

    Usuario findByEmail(String email);

    Usuario findByCelular(int celular);
}
